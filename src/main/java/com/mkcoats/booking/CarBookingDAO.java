package com.mkcoats.booking;

import com.mkcoats.car.Car;
import com.mkcoats.car.CarDAO;

import java.util.UUID;

public class CarBookingDAO {
    private static CarBooking[] bookings;

    static {
        CarDAO carDAO = new CarDAO();
        Car[] cars = carDAO.getCars();
        bookings = new CarBooking[cars.length];
    }

    public CarBooking[] getBookings() {
        return bookings;
    }

    public CarBooking saveBooking(CarBooking booking) {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] == null) {
                bookings[i] = booking;
            }
            return booking;
        }
        return null;
    }

    public CarBooking findBookingById(UUID id) {
        for (CarBooking booking : bookings) {
            if (booking != null && id.equals(booking.getId())) {
                return booking;
            }
        }
        return null;
    }

    private int bookingCount() {
        int count = 0;
        for (CarBooking booking : bookings) {
            if (booking != null) {
                count++;
            }
        }
        return count;
    }

    public void deleteBooking(UUID id) {
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && id.equals(bookings[i].getId())) {
                bookings[i] = null;
            }
        }
    }
}
