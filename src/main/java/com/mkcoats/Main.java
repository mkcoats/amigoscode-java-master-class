package com.mkcoats;
// TODO 3. implement https://amigoscode.com/learn/java-cli-build/lectures/3a83ecf3-e837-4ae5-85a8-f8ae3f60f7f5

import java.util.Scanner;

public class Main {

    private final static String MENU = "Car App Menu\n\n" +
            "1 - Book Car\n" +
            "2 - Delete Booking\n" +
            "3 - View All User Booked Cars\n" +
            "4 - View All Bookings\n" +
            "5 - View Available Cars\n" +
            "6 - View Available Electric Cars\n" +
            "7 - View All Users\n" +
            "8 - Exit\n";

    public static void main(String[] args) {
        System.out.println("Java Master Class");
        Scanner scanner = new Scanner(System.in);
        Integer selection = 0;

        while (!selection.equals(8)) {
            System.out.println(MENU);
            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    bookACar();
                    break;
                case 2:
                    deleteBooking();
                    break;
                case 3:
                    viewUserBookings();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    viewAvailableCars();
                    break;
                case 6:
                    viewElectricCars();
                    break;
                case 7:
                    viewAllUsers();
                    break;
                case 8:
                    System.out.println("Thank you for using <SOFTWARE>.\nHave a nice day.");
                    break;
                default:
                    System.out.println("Invalid Selection - Please Select an Option from the Menu");
            }
        }
    }

    private static void bookACar() {
        // System prompts for user ID, car selection, start date and end date.
        // Price is calculated from the car's rental price per day.
        // A car that is already booked cannot be booked again
        System.out.println("Book a Car");
    }

    private static void deleteBooking() {
        // Cancel an existing booking by booking ID, making the car available again
        System.out.println("Delete Booking");
    }

    private static void viewUserBookings() {
        // Display all cars booked by a specific user
        System.out.println("View User Bookings");
    }

    private static void viewAllBookings() {
        // Display every booking in the system
        System.out.println("View All Bookings");
    }

    private static void viewAvailableCars() {
        // List all cars not currently booked
        System.out.println("View Available Cars");
    }

    private static void viewElectricCars() {
        // Filter and display only available electric cars
        System.out.println("View Electric Cars");
    }

    private static void viewAllUsers() {
        // List all registered users
        System.out.println("View All Users");
    }
}
