package iucpark.service; // Defines package
import iucpark.model.Motorbike; // Imports Motorbike
import java.time.LocalDateTime; // Represents date-time
import java.util.ArrayList; // Stores vehicles

public class CheckInService { // Handles check-in logic
    private final FileService fileService; // Manages file operations
    private final ArrayList<Motorbike> vehicles; // Stores vehicles
    private final boolean[] couponAvailability = new boolean[300]; // Tracks coupons

    public CheckInService(FileService fileService) { // Constructor
        this.fileService = fileService; // Sets file service
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

    private boolean isCouponInUse(int couponCode) { // Checks if coupon in use
        for (Motorbike m : vehicles) { // Loops through vehicles
            if (m.getCouponCode() == couponCode && m.getCheckOutTime() == null) { // Checks coupon and status
                return true; // Coupon is used
            }
        }
        return false; // Coupon not used
    }

    public void checkIn(int couponCode, int plateNumber) { // Records check-in
        if (couponCode < 1 || couponCode > 300) { // Checks coupon range
            System.out.println("Coupon out of range (001-300)."); // Prints error
            return; // Exits
        }
        if (isCouponInUse(couponCode)) { // Checks if coupon in use
            System.out.println("Coupon in use."); // Prints error
            return; // Exits
        }
        if (plateNumber <= 0) { // Checks plate number
            System.out.println("Plate cannot be empty or negative."); // Prints error
            return; // Exits
        }
        int index = couponCode - 1; // Converts coupon to index
        couponAvailability[index] = false; // Marks coupon in use
        vehicles.add(new Motorbike(couponCode, plateNumber, LocalDateTime.now())); // Adds motorbike
        fileService.saveToCSV(vehicles); // Saves to CSV
        System.out.println("Check-in: Coupon " + couponCode + ", Plate " + plateNumber); // Prints confirmation
    }
}