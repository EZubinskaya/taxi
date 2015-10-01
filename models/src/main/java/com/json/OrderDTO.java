package com.json;

import com.models.Status;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by kzub on 9/28/2015.
 */
public class OrderDTO implements Serializable {

    private long order_id;
    private double price;
    private String carRegistrationSign;
    private Time estimationTime;
    private Status status;

    public OrderDTO() {
    }

    public OrderDTO(long order_id, double price, String carRegistrationSign, Time estimationTime, Status status) {
        this.price = price;
        this.carRegistrationSign = carRegistrationSign;
        this.estimationTime = estimationTime;
        this.status = status;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
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

    public Time getEstimationTime() {
        return estimationTime;
    }

    public void setEstimationTime(Time estimationTime) {
        this.estimationTime = estimationTime;
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

        OrderDTO orderDTO = (OrderDTO) o;

        if (order_id != orderDTO.order_id) return false;
        if (Double.compare(orderDTO.price, price) != 0) return false;
        if (carRegistrationSign != null ? !carRegistrationSign.equals(orderDTO.carRegistrationSign) : orderDTO.carRegistrationSign != null)
            return false;
        if (estimationTime != null ? !estimationTime.equals(orderDTO.estimationTime) : orderDTO.estimationTime != null)
            return false;
        return status == orderDTO.status;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (order_id ^ (order_id >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (carRegistrationSign != null ? carRegistrationSign.hashCode() : 0);
        result = 31 * result + (estimationTime != null ? estimationTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}