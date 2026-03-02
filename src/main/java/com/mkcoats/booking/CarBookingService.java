package com.mkcoats.booking;

import com.mkcoats.car.Car;
import com.mkcoats.car.CarService;
import com.mkcoats.user.User;
import com.mkcoats.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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

    public User[] getRegisteredUsers() {
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

    public Car[] getCarsBookedForUser(UUID userId) {
        int userBookedCarCount = 0;
        CarBooking[] bookings = carBookingDAO.getBookings();
        Car[] userBookedCars = new Car[bookings.length];
        for (CarBooking booking : bookings) {
            if (booking != null && userId.equals(booking.getUser().getId())) {
                userBookedCars[userBookedCarCount] = booking.getCar();
                userBookedCarCount++;
            }
        }
        return userBookedCarCount > 0 ? Arrays.copyOf(userBookedCars, userBookedCarCount) : null;
    }

    public CarBooking[] getAllBookings() {
        int activeBookingCount = 0;
        CarBooking[] bookings = carBookingDAO.getBookings();
        CarBooking[] activebookings = new CarBooking[bookings.length];
        for (CarBooking booking : bookings) {
            if (booking != null) {
                activebookings[activeBookingCount] = booking;
                activeBookingCount++;
            }
        }
        return activeBookingCount > 0 ? Arrays.copyOf(activebookings, activeBookingCount) : null;
    }

    public Car[] getAvailableCars() {
        Car[] availableCars = carService.getAllCars();
        for (CarBooking booking : carBookingDAO.getBookings()) {
            if (booking != null) {
                Car bookedCar = booking.getCar();
                int index = 0;
                while (bookedCar != null && index < availableCars.length) {
                    if (availableCars[index] != null && availableCars[index].getRegNumber().equals(bookedCar.getRegNumber())) {
                        availableCars[index] = null;
                        bookedCar = null;
                    } else {
                        index++;
                    }
                }
            }
        }
        return availableCars;
    }
}
