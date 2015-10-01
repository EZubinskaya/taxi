package com.models;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kzub on 9/17/2015.
 */
@Entity(name = "taxi")
public class Taxi  implements Serializable {

    @Id
    @Column(name = "taxi_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="carClass")
    @Enumerated(EnumType.STRING)
    private CarClass carClass;
//    private String carClass;

    @Column(name="carRegistrationSign")
    private String carRegistrationSign;

    @Column(name="location")
    private String location;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Taxi() {
    }

//    public Taxi(String carClass, String carRegistrationSign, String location, Status status) {
//        this.carClass = carClass;
//        this.carRegistrationSign = carRegistrationSign;
//        this.location = location;
//        this.status = status;
//    }


    public Taxi(CarClass carClass, String carRegistrationSign, String location, Status status) {
        this.carClass = carClass;
        this.carRegistrationSign = carRegistrationSign;
        this.location = location;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    //    public String getCarClass() {
//        return carClass;
//    }
//
//    public void setCarClass(String carClass) {
//        this.carClass = carClass;
//    }

    public String getCarRegistrationSign() {
        return carRegistrationSign;
    }

    public void setCarRegistrationSign(String carRegistrationSign) {
        this.carRegistrationSign = carRegistrationSign;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taxi taxi = (Taxi) o;

        if (id != taxi.id) return false;
        if (carClass != taxi.carClass) return false;
        if (carRegistrationSign != null ? !carRegistrationSign.equals(taxi.carRegistrationSign) : taxi.carRegistrationSign != null)
            return false;
        if (location != null ? !location.equals(taxi.location) : taxi.location != null) return false;
        return status == taxi.status;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (carRegistrationSign != null ? carRegistrationSign.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
