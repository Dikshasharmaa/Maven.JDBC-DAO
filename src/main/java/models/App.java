package models;

import daos.Dto;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args){
        DAO cDao = new DAO();
        List<CarDto> cars;

        System.out.println("----- Creating new Records-------");
        cDao.create(new CarDto("Honda","Pilot",2010,"White","Cgher2324"));
        cDao.create(new CarDto("Tesla","MY",2023,"White","ghtr5342jd"));
        cDao.create(new CarDto("BMW","X7",2023,"Black","ghrt3423edf"));
        System.out.println("Created new Records");

        System.out.println("--------Find All---------");
        cars = new ArrayList<CarDto>(cDao.findAll());
        for(CarDto c : cars){
            System.out.println(c.getId()+" "+c.getMake() +" "+ c.getModel()+" "+ c.getYear()+ " "+ c.getColor()+ " "+ c.getVin());
        }

        System.out.println("---------Find By Id------------");
        CarDto c2 = cDao.findById(cars.get(2).getId());
        System.out.println(c2.getId()+" "+c2.getMake() +" "+ c2.getModel()+" "+ c2.getYear()+ " "+ c2.getColor()+ " "+ c2.getVin());

        System.out.println("-----------Update Records-----------");
        c2.setMake("Mercedes");
        c2.setModel("Gls");
        c2.setYear(2024);
        c2.setColor("Black");
        c2.setVin("Re3454sbhfg");
        cDao.update(c2);
        System.out.println("Records updated");

        System.out.println("--------------Delete Record--------------");
        for(int i =0; i<cars.size();i++){
            if(cars.get(i).getId() == 3){
                cDao.delete(i);
            }
            System.out.println("Item Deleted");
        }



    }
}
