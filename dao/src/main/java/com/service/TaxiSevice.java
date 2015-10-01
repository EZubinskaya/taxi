package com.service;

import com.json.InfoAboutTaxi;
import com.models.CarClass;
import com.models.Status;
import com.models.Taxi;

import java.util.List;
import java.util.Map;

/**
 * Created by kzub on 9/27/2015.
 */
public interface TaxiSevice {

    List<Taxi> findAll();

    void changeStatus(Status status, Taxi taxi);

    List<Taxi> findAllFreTaxi(CarClass classC);

    Taxi findCarRegistrationNumber(String carRegistrationSign);
}
