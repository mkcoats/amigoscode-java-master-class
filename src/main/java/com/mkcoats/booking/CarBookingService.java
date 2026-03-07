package com.mkcoats.booking;

import com.mkcoats.car.Car;
import com.mkcoats.car.CarService;
import com.mkcoats.user.User;
import com.mkcoats.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;

    private final CarService carService;
    private final UserService userService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService, UserService userService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
        this.userService = userService;
    }

    public CarBooking bookCar(UUID userId, String carRegNumber, LocalDate startDate, LocalDate endDate) {
        // Check if Valid User
        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Invalid UserId");
            return null;
        }

        // Check if Valid Car
        Car car = carService.getCarByReg(carRegNumber);
        if (car == null) {
            System.out.println("Invalid Car Reg Number");
            return null;
        }

        long timeframe = ChronoUnit.DAYS.between(startDate, endDate);
        BigDecimal price = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(timeframe));
        CarBooking newBooking = new CarBooking(UUID.randomUUID(), user, car, startDate, endDate, price, BookingStatus.ACTIVE);

        for (CarBooking booking : carBookingDAO.getBookings()) {
            if (booking != null) {
                if (car.getId().equals(booking.getCar().getId())) {
                    System.out.println("Car is Unavailable");
                    return null;
                }
            }
        }

        carBookingDAO.saveBooking(newBooking);
        return newBooking;
    }

    public List<User> getRegisteredUsers() {
        return userService.getAllUsers();
    }

    public void deleteBooking(UUID uuid) {
        CarBooking booking = carBookingDAO.findBookingById(uuid);
        if (booking == null) {
            System.out.println("Booking cannot be found.");
        } else {
            carBookingDAO.deleteBooking(uuid);
            System.out.println("Booking has been deleted.");
        }
    }

    public List<Car> getCarsBookedForUser(UUID userId) {
        List<CarBooking> bookings = carBookingDAO.getBookings();
        List<Car> userBookedCars = new ArrayList<>();
        for (CarBooking booking : bookings) {
            if (booking != null && userId.equals(booking.getUser().getId())) {
                userBookedCars.add(booking.getCar());
            }
        }
        return userBookedCars;
    }

    public List<CarBooking> getAllBookings() {
        return carBookingDAO.getBookings();
    }

    public List<Car> getAvailableCars() {
        List<CarBooking> carBookings = carBookingDAO.getBookings();
        if (carBookings.isEmpty()) {
            return carService.getAllCars();
        }

        List<Car> availableCars = new ArrayList<>();
        for (Car car : carService.getAllCars()) {
            boolean booked = false;
            for (CarBooking booking : carBookings) {
                if (booking == null || !car.equals(booking.getCar())) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }
}
