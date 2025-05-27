package iucpark.manager; // Defines package
import iucpark.service.*; // Imports service classes
import java.util.Scanner; // Reads user input

public class Iuc_Park_Main implements RecordManager { // Manages parking system
    private final CheckInService checkInService; // Handles check-in
    private final CheckOutService checkOutService; // Handles check-out
    private final ExportService exportService; // Handles export
    private final AuthService authService; // Handles authentication
    private final Scanner scanner = new Scanner(System.in); // Reads user input

    public Iuc_Park_Main() { // Constructor
        FileService fileService = new FileService(); // Creates file service
        checkInService = new CheckInService(fileService); // Initializes check-in service
        checkOutService = new CheckOutService(fileService); // Initializes check-out service
        exportService = new ExportService(fileService); // Initializes export service
        authService = new AuthService(); // Initializes auth service
    }

    @Override
    public void checkIn(int couponCode, int plateNumber) { // Records check-in
        checkInService.checkIn(couponCode, plateNumber); // Delegates to service
    }

    @Override
    public void checkOut(int couponCode) { // Records check-out
        checkOutService.checkOut(couponCode, scanner); // Delegates to service
    }

    @Override
    public void exportRecords(String date, Integer plateNumber) { // Exports records
        exportService.exportRecords(date, plateNumber); // Delegates to service
    }

    public void run() { // Runs main loop
        if (!authService.authenticate(scanner)) { // Checks authentication
            System.out.println("Wrong credentials."); // Prints error
            return; // Exits
        }

        while (true) { // Starts menu loop
            System.out.println("\nSmart Motor Coupon System"); // Prints title
            System.out.println("1. Check In"); // Prints option 1
            System.out.println("2. Check Out"); // Prints option 2
            System.out.println("3. Export Records"); // Prints option 3
            System.out.print("Choose: "); // Prompts choice
            int choice; // Stores choice
            try {
                choice = scanner.nextInt(); // Reads choice
                scanner.nextLine(); // Clears newline
            } catch (Exception e) { // Handles invalid input
                scanner.nextLine(); // Clears input
                System.out.println("Enter a number."); // Prints error
                continue; // Loops again
            }
            if (choice == 1) { // Handles check-in
                System.out.print("Coupon (1-300): "); // Prompts coupon
                int couponCode; // Stores coupon
                try {
                    couponCode = Integer.parseInt(scanner.nextLine()); // Reads coupon
                } catch (NumberFormatException e) { // Handles invalid input
                    System.out.println("Invalid coupon format."); // Prints error
                    continue; // Loops again
                }
                System.out.print("Plate: "); // Prompts plate
                int plateNumber; // Stores plate
                try {
                    plateNumber = Integer.parseInt(scanner.nextLine()); // Reads plate
                } catch (NumberFormatException e) { // Handles invalid input
                    System.out.println("Invalid plate format."); // Prints error
                    continue; // Loops again
                }
                checkIn(couponCode, plateNumber); // Calls check-in
            } else if (choice == 2) { // Handles check-out
                System.out.print("Coupon (1-300): "); // Prompts coupon
                int couponCode; // Stores coupon
                try {
                    couponCode = Integer.parseInt(scanner.nextLine()); // Reads coupon
                } catch (NumberFormatException e) { // Handles invalid input
                    System.out.println("Invalid coupon format."); // Prints error
                    continue; // Loops again
                }
                checkOut(couponCode); // Calls check-out
            } else if (choice == 3) { // Handles export
                System.out.print("Enter date (yyyy-MM-dd): "); // Prompts date
                String date = scanner.nextLine(); // Reads date
                System.out.print("Enter plate number (or press Enter for all): "); // Prompts plate
                String plateInput = scanner.nextLine(); // Reads plate
                Integer plateNumber = null; // Initializes plate
                if (!plateInput.isEmpty()) { // Checks if plate provided
                    try {
                        plateNumber = Integer.parseInt(plateInput); // Parses plate
                    } catch (NumberFormatException e) { // Handles invalid input
                        System.out.println("Invalid plate format."); // Prints error
                        continue; // Loops again
                    }
                }
                exportRecords(date, plateNumber); // Calls export
            } else { // Handles invalid choice
                scanner.close(); // Closes scanner
                System.out.println("Exiting..."); // Prints exit
                break; // Exits loop
            }
        }
    }
}