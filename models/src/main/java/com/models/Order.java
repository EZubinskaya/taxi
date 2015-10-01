package com.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by kzub on 9/17/2015.
 */

@Entity(name="order_m")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private long orderId;

    @Column(name="price")
    private double price;

    @Column(name="car_registration_sign")
    private String carRegistrationSign;

    @Column(name="estimation_time")
    private String estimationTime;

    @Column(name="userLocation")
    private String userLocation;

    @Column(name="phone")
    private String phone;

    public Order() {
    }

    public Order(double price, String carRegistrationSign, String estimationTime,  String userLocation, String phone) {
        this.price = price;
        this.carRegistrationSign = carRegistrationSign;
        this.estimationTime = estimationTime;
        this.userLocation = userLocation;
        this.phone = phone;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCarRegistrationSign() {
        return carRegistrationSign;
    }

    public void setCarRegistrationSign(String carRegistrationSign) {
        this.carRegistrationSign = carRegistrationSign;
    }

    public String getEstimationTime() {
        return estimationTime;
    }

    public void setEstimationTime(String estimationTime) {
        this.estimationTime = estimationTime;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (carRegistrationSign != null ? !carRegistrationSign.equals(order.carRegistrationSign) : order.carRegistrationSign != null)
            return false;
        if (estimationTime != null ? !estimationTime.equals(order.estimationTime) : order.estimationTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (carRegistrationSign != null ? carRegistrationSign.hashCode() : 0);
        result = 31 * result + (estimationTime != null ? estimationTime.hashCode() : 0);
        return result;
    }
}
