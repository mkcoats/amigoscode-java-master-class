package com.mkcoats.car;

import java.util.List;

public class CarService {
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car getCarByReg(String carRegNumber) {
        for (Car car : carDAO.getCars()) {
            if (car != null && car.getRegNumber().equals(carRegNumber)) {
                return car;
            }
        }
        return null;
    }

    public List<Car> getAllCars() {
        return carDAO.getCars();
    }
}
