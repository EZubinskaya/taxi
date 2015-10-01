package com.json;

import com.models.CarClass;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by kzub on 9/27/2015.
 */
public class InfoAboutTaxi implements Serializable{

    private String carClass;

    private String carRegistrationSign;

    private String duration;

    public InfoAboutTaxi() {
    }

    public InfoAboutTaxi(String carClass, String carRegistrationSign, String duration) {
        this.carClass = carClass;
        this.carRegistrationSign = carRegistrationSign;
        this.duration = duration;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarRegistrationSign() {
        return carRegistrationSign;
    }

    public void setCarRegistrationSign(String carRegistrationSign) {
        this.carRegistrationSign = carRegistrationSign;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoAboutTaxi that = (InfoAboutTaxi) o;

        if (carClass != that.carClass) return false;
        if (carRegistrationSign != null ? !carRegistrationSign.equals(that.carRegistrationSign) : that.carRegistrationSign != null)
            return false;
        return !(duration != null ? !duration.equals(that.duration) : that.duration != null);

    }

    @Override
    public int hashCode() {
        int result = carClass != null ? carClass.hashCode() : 0;
        result = 31 * result + (carRegistrationSign != null ? carRegistrationSign.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
