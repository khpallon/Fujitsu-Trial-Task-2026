package com.khpallon.fujitsu.dto;

public class DeliveryFeeDTO {
    private String city;
    private String vehicle;
    private double fee;

    public DeliveryFeeDTO(String city, String vehicle, double fee) {
        this.city = city;
        this.vehicle = vehicle;
        this.fee = fee;
    }

    public String getCity() {
        return city;
    }

    public String getVehicle() {
        return vehicle;
    }

    public double getFee() {
        return fee;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
