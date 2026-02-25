package com.mkcoats.car;

import java.util.UUID;

public class CarService {
    private CarDAO carDAO;

    public CarService() {
        carDAO = new CarDAO();
    }

    public Car getCarById(UUID carId) {
        Car[] cars = carDAO.getCars();
        for (int i = 0; i < cars.length; i++) {
            Car tempCar = cars[i];
            if (tempCar != null && tempCar.getId().equals(carId)) {
                return tempCar;
            }
        }
        return null;
    }

    public Car getCarByReg(String carRegNumber) {
        Car[] cars = carDAO.getCars();
        for (int i = 0; i < cars.length; i++) {
            Car tempCar = cars[i];
            if (tempCar != null && tempCar.getRegNumber().equals(carRegNumber)) {
                return tempCar;
            }
        }
        return null;
    }

    public Car[] getAllCars() {
        return carDAO.getCars();
    }
}
