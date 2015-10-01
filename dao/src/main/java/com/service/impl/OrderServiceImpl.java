package com.service.impl;

import com.models.*;
import com.repository.OrderRepository;
import com.service.OrderService;
import com.service.TaxiSevice;
import com.service.UserService;
import com.util.GoogleFindDistanceAndDuration;
import com.util.Path;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kzub on 9/25/2015.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TaxiSevice taxiService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
       // taxiService.changeStatus(Status.BUSY, taxiService.findCarRegistrationNumber(order.getCarRegistrationSign()));
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public double priceCalculate(long distance, int amount, double delta) {
        long price = amount*distance;
        return  price + delta*price;
    }

    @Override
    public Order nearestTaxi(String phone, String userLocation, String carClass) {
        if(carClass == null) {
            carClass = CarClass.ECONOMY.name(); //TODO: default setting
        }
        List<Taxi> taxis = taxiService.findAllFreTaxi(CarClass.valueOf(carClass)); //TODO: free )))
        Taxi nearestTaxi = taxis.get(0);
        int minDuration = 0;
        String distanse = "";
        for (Taxi taxi: taxis) {
            String taxiLocation = taxi.getLocation();
            try {
                Path infoDurationDistanse = GoogleFindDistanceAndDuration.infoDurationDistanse(taxiLocation, userLocation);
                int currentDuration = infoDurationDistanse.getDuration();
                if(currentDuration < minDuration || minDuration == 0) {
                    nearestTaxi = taxi;
                    minDuration = currentDuration;
                    distanse = infoDurationDistanse.getDistance();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Order order = new Order();
        order.setEstimationTime(formatInterval(minDuration * 1000));
        order.setPrice(priceCalculate(parseDistance(distanse), 22, 0.1));
        order.setCarRegistrationSign(nearestTaxi.getCarRegistrationSign());
        save(order);
        taxiService.changeStatus(Status.BUSY, nearestTaxi);
        User user = userService.findByPhone(phone);
        if(user!=null) {
            userService.incrementCount(user);
        } else {
            userService.save(new User());
        }
        return order;
    }

    private static String formatInterval(final long l)
    {
        final long hr = TimeUnit.MILLISECONDS.toHours(l);
        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }

    public static int parseDistance(String d) {
        Matcher matcher = Pattern.compile("\\d+").matcher(d);
        matcher.find();
        return Integer.valueOf(matcher.group());
    }

}
