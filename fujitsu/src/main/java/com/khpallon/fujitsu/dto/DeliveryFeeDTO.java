package com.khpallon.fujitsu.dto;

import com.khpallon.fujitsu.enums.*;

public class DeliveryFeeDTO {
    private City city;
    private Vehicle vehicle;
    private double fee;

    public DeliveryFeeDTO(City city, Vehicle vehicle, double fee) {
        this.city = city;
        this.vehicle = vehicle;
        this.fee = fee;
    }

    public City getCity() {
        return city;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getFee() {
        return fee;
    }
    
    public void setCity(City city) {
        this.city = city;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
