package com.mkcoats;

import com.mkcoats.booking.CarBooking;
import com.mkcoats.booking.CarBookingDAO;
import com.mkcoats.booking.CarBookingService;
import com.mkcoats.car.Car;
import com.mkcoats.car.CarDAO;
import com.mkcoats.car.CarService;
import com.mkcoats.user.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private final static String MENU = """
            Car App Menu
            
            1 - Book Car
            2 - Delete Booking
            3 - View All User Booked Cars
            4 - View All Bookings
            5 - View Available Cars
            6 - View Available Electric Cars
            7 - View All Users
            8 - Exit
            """;

    public static void main(String[] args) {
        CarDAO carDAO = new CarDAO();
        CarBookingDAO carBookingDAO = new CarBookingDAO();
        UserDAO userDAO = new UserFakeDataAccessService();

        CarService carService = new CarService(carDAO);
        UserService userService = new UserService(userDAO);

        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService, userService);

        System.out.println("Java Master Class");
        Scanner scanner = new Scanner(System.in);
        Integer selection = 0;

        while (!selection.equals(8)) {
            System.out.println(MENU);
            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    bookACar(carBookingService, scanner);
                    break;
                case 2:
                    deleteBooking(carBookingService, scanner);
                    break;
                case 3:
                    viewUserBookings(carBookingService, scanner);
                    break;
                case 4:
                    viewAllBookings(carBookingService);
                    break;
                case 5:
                    viewAvailableCars(carBookingService);
                    break;
                case 6:
                    viewElectricCars(carBookingService);
                    break;
                case 7:
                    viewAllUsers(carBookingService);
                    break;
                case 8:
                    System.out.println("Thank you for using <SOFTWARE>.\nHave a nice day.");
                    break;
                default:
                    System.out.println("Invalid Selection - Please Select an Option from the Menu");
            }
        }
    }

    private static void bookACar(CarBookingService carBookingService, Scanner scanner) {
        // System prompts for user ID, car selection, start date and end date.
        // Price is calculated from the car's rental price per day.
        // A car that is already booked cannot be booked again
        System.out.println("Selection: Book a Car");
        System.out.println("Please Enter User ID to book car:");
        System.out.println("--Registerd Users");
        List<User> registeredUsers = carBookingService.getRegisteredUsers();
        if (registeredUsers.isEmpty()) {
            System.out.println("No Registered Users.");
        } else {
            for (User user : registeredUsers) {
                System.out.println(user);
            }
        }
        String userId = scanner.next();
        System.out.println("Please Enter Car Reg number for booking:");
        System.out.println("--Available Cars");
        List<Car> availableCars = carBookingService.getAvailableCars();
        if (availableCars == null) {
            System.out.println("No cars Available");
        } else {
            for (Car car : availableCars) {
                if (car != null) {
                    System.out.println(car);
                }
            }
        }
        String carReg = scanner.next();
        System.out.println("Please Enter Start Date for booking in format YYYY-MM-DD:");
        String startDateString = scanner.next();
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Please Enter End Date for booking in format YYYY-MM-DD:");
        String endDateString = scanner.next();
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        CarBooking newBooking = carBookingService.bookCar(UUID.fromString(userId), carReg, startDate, endDate);
        if (newBooking != null) {
            System.out.println("Booking successful:\n" + newBooking);
        }
    }

    private static void deleteBooking(CarBookingService carBookingService, Scanner scanner) {
        // Cancel an existing booking by booking ID, making the car available again
        System.out.println("Selection: Delete Booking");
        System.out.println("Please enter Booking Id to delete:");
        String bookingId = scanner.next();
        carBookingService.deleteBooking(UUID.fromString(bookingId));
    }

    private static void viewUserBookings(CarBookingService carBookingService, Scanner scanner) {
        // Display all cars booked by a specific user
        System.out.println("Selection: View User Bookings");
        System.out.println("Please enter User Id:");
        System.out.println(carBookingService.getRegisteredUsers().toString());
        String userId = scanner.next();
        List<Car> userBookedCars = carBookingService.getCarsBookedForUser(UUID.fromString(userId));
        if (userBookedCars == null) {
            System.out.println("No bookings for user: " + userId);
        } else {
            System.out.println("User: " + userId + "\nBookings:");
            for (Car car : userBookedCars) {
                System.out.println(car);
            }
        }
    }

    private static void viewAllBookings(CarBookingService carBookingService) {
        // Display every booking in the system
        System.out.println("Selection: View All Bookings");
        List<CarBooking> bookings = carBookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No current bookings");
        } else {
            for (CarBooking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private static void viewAvailableCars(CarBookingService carBookingService) {
        // List all cars not currently booked
        System.out.println("Selection: View Available Cars");
        List<Car> availableCars = carBookingService.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("No cars Available");
        } else {
            for (Car car : availableCars) {
                if (car != null) {
                    System.out.println(car);
                }
            }
        }
    }

    private static void viewElectricCars(CarBookingService carBookingService) {
        // Filter and display only available electric cars
        System.out.println("Selection: View Electric Cars");
        List<Car> availableElectricCars = carBookingService.getAvailableElectricCars();
        if (availableElectricCars.isEmpty()) {
            System.out.println("No electric cars Available");
        } else {
            for (Car car : availableElectricCars) {
                System.out.println(car);
            }
        }
    }

    private static void viewAllUsers(CarBookingService carBookingService) {
        // List all registered users
        System.out.println("Selection: View All Users");
        List<User> registeredUsers = carBookingService.getRegisteredUsers();
        if (registeredUsers.isEmpty()) {
            System.out.println("No Registered Users.");
        } else {
            for (User user : registeredUsers) {
                System.out.println(user);
            }
        }
    }
}
