package iucpark.service; // Defines package
import iucpark.model.Motorbike; // Imports Motorbike
import java.util.ArrayList; // Stores vehicles
import java.util.Scanner; // Reads user input
import java.time.LocalDateTime; // Represents date-time

public class CheckOutService { // Handles check-out logic
    private final FileService fileService; 
    private final ArrayList<Motorbike> vehicles; 
    private final boolean[] couponAvailability = new boolean[300]; 

    public CheckOutService(FileService fileService) { // Constructor
        this.fileService = fileService; 
        this.vehicles = fileService.getVehicles(); // Loads vehicles
        for (int i = 0; i < 300; i++) { // Loops through coupons
            couponAvailability[i] = true; // Marks coupons available
        }
        updateCouponAvailability(); // Updates coupon status
    }

    private void updateCouponAvailability() { // Updates coupon status
        for (Motorbike m : vehicles) { // Loops through vehicles
            if (m.getCheckOutTime() == null) { // Checks if not checked out
                int index = m.getCouponCode() - 1; // Gets coupon index
                if (index >= 0 && index < 300) { // Checks valid index
                    couponAvailability[index] = false; // Marks coupon in use
                }
            }
        }
    }

    public void checkOut(int couponCode, Scanner scanner) { // Records check-out
        if (couponCode < 1 || couponCode > 300) { 
            System.out.println("Invalid coupon."); 
            return; // Exits
        }
        for (int i = vehicles.size() - 1; i >= 0; i--) { // Loops backward
            Motorbike m = vehicles.get(i);
            if (m.getCouponCode() == couponCode && m.getCheckOutTime() == null) { // Checks coupon and status
                System.out.println("Plate: " + m.getPlateNumber()); // Shows plate
                System.out.print("Confirm (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) { 
                    m.setCheckOutTime(LocalDateTime.now()); // Sets check-out time
                    int index = couponCode - 1; // Converts coupon to index
                    couponAvailability[index] = true; 
                    fileService.saveToCSV(vehicles); // Saves to CSV
                    System.out.println("Check-out: " + m.getDetails()); // Prints details
                } else {
                    System.out.println("Cancelled."); // Prints cancellation
                }
                return; // Exits
            }
        }
        System.out.println("No active record for coupon " + couponCode); 
    }
}
