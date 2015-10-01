package com.json;


import java.io.Serializable;

/**
 * Created by kzub on 9/23/2015.
 */

public class UserOrder implements Serializable{
    private String name;
    private String phone;
    private String location;
    private String carclass;

    public UserOrder() {
    }

    public UserOrder(String name, String phone, String location, String carclass) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.carclass = carclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarclass() {
        return carclass;
    }

    public void setCarclass(String carclass) {
        this.carclass = carclass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (name != null ? !name.equals(userOrder.name) : userOrder.name != null) return false;
        if (phone != null ? !phone.equals(userOrder.phone) : userOrder.phone != null) return false;
        if (location != null ? !location.equals(userOrder.location) : userOrder.location != null) return false;
        return carclass == userOrder.carclass;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (carclass != null ? carclass.hashCode() : 0);
        return result;
    }
}
