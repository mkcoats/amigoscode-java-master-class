package com.mkcoats.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarBookingDAO {
    private static List<CarBooking> bookings;

    static {
        bookings = new ArrayList<>();
    }

    public List<CarBooking> getBookings() {
        return bookings;
    }

    public CarBooking saveBooking(CarBooking booking) {
        bookings.add(booking);
        return booking;
    }

    public CarBooking findBookingById(UUID id) {
        for (CarBooking booking : bookings) {
            if (booking != null && id.equals(booking.getId())) {
                return booking;
            }
        }
        return null;
    }

    public void deleteBooking(UUID id) {
        for (CarBooking booking : bookings) {
            if (id.equals(booking.getId())) {
                bookings.remove(booking);
            }
        }
    }
}
