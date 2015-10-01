package com.service.impl;

import com.json.InfoAboutTaxi;
import com.models.CarClass;
import com.models.Order;
import com.models.Status;
import com.util.*;
import com.models.Taxi;
import com.repository.TaxiReposotory;
import com.service.TaxiSevice;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kzub on 9/27/2015.
 */
@Service
public class TaxiSeviceImpl implements TaxiSevice{
    @Autowired
    private TaxiReposotory taxiReposotory;

    @Override
    public List<Taxi> findAll() {
        return (List<Taxi>) taxiReposotory.findAll();
    }



    @Override
    public void changeStatus(Status status, Taxi taxi) {
        taxiReposotory.findOne(taxi.getId()).setStatus(status);
    }

    @Override
    public List<Taxi> findAllFreTaxi(CarClass classC) {
        return taxiReposotory.findAllFreeTaxi(classC);
    }

    @Override
    public Taxi findCarRegistrationNumber(String carRegistrationSign) {
        return taxiReposotory.findCarRegistrationNumber(carRegistrationSign);
    }

}
