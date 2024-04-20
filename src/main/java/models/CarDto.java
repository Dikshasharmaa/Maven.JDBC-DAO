package models;

import daos.Dto;

public class CarDto implements Dto {
    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private String vin;
    public CarDto(){
    }

    public CarDto(int id, String make, String model, int year, String color, String vin) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }


    @Override
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String getMake() {
        return make;
    }
    public void setMake(String make){
        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    @Override
    public int getYear() {
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }

    @Override
    public String getColor() {
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    @Override
    public String getVin() {
        return vin;
    }
    public void setVin(String vin){
        this.vin = vin;
    }
}
