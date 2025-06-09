package iucpark.manager; // Defines package
import iucpark.service.*; // Imports service classes
import java.util.Scanner; // Reads user input

public class Iuc_Park_Main implements RecordManager { // Manages parking system
    private final CheckInService checkInService; 
    private final CheckOutService checkOutService; 
    private final ExportService exportService; 
    private final AuthService authService; 
    private final Scanner scanner = new Scanner(System.in); 

    public Iuc_Park_Main() { // Constructor
        FileService fileService = new FileService(); // Creates file service
        checkInService = new CheckInService(fileService); 
        checkOutService = new CheckOutService(fileService); 
        exportService = new ExportService(fileService);
        authService = new AuthService(); 
    }

    @Override
    public void checkIn(int couponCode, int plateNumber) { // Records check-in
        checkInService.checkIn(couponCode, plateNumber); 
    }

    @Override
    public void checkOut(int couponCode) { // Records check-out
        checkOutService.checkOut(couponCode, scanner); 
    }

    @Override
    public void exportRecords(String date, Integer plateNumber) { // Exports records
        exportService.exportRecords(date, plateNumber); 
    }

    public void run() { // Runs main loop
        if (!authService.authenticate(scanner)) { // Checks authentication
            System.out.println("Wrong credentials."); 
            return; // Exits
        }

        while (true) { // Starts menu loop
            System.out.println("\nSmart Motor Coupon System"); 
            System.out.println("1. Check In"); 
            System.out.println("2. Check Out"); 
            System.out.println("3. Export Records"); 
            System.out.print("Choose: "); 
            int choice; // Stores choice
            try {
                choice = scanner.nextInt(); // Reads choice
                scanner.nextLine(); 
            } catch (Exception e) { // Handles invalid input
                scanner.nextLine(); // Clears input
                System.out.println("Enter a number."); 
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
                    System.out.println("Invalid coupon format."); 
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
                        System.out.println("Invalid plate format."); 
                        continue; 
                    }
                }
                exportRecords(date, plateNumber); // Calls export
            } else { 
                scanner.close(); 
                System.out.println("Exiting..."); 
                break; // Exits loop
            }
        }
    }
}
