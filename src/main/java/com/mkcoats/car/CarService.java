package com.mkcoats.car;

import java.util.UUID;

public class CarService {
    private CarDAO carDAO;

    public CarService() {
        carDAO = new CarDAO();
    }

    public Car getCarByReg(String carRegNumber) {
        for (Car car : carDAO.getCars()) {
            if (car != null && car.getRegNumber().equals(carRegNumber)) {
                return car;
            }
        }
        return null;
    }

    public Car[] getAllCars() {
        return carDAO.getCars();
    }
}
