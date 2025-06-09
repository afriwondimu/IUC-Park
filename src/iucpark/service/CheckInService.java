package iucpark.service; // Defines package
import iucpark.model.Motorbike; // Imports Motorbike
import java.time.LocalDateTime; // Represents date-time
import java.util.ArrayList; // Stores vehicles

public class CheckInService { // Handles check-in logic
    private final FileService fileService; 
    private final ArrayList<Motorbike> vehicles; 
    private final boolean[] couponAvailability = new boolean[300]; 

    public CheckInService(FileService fileService) { // Constructor
        this.fileService = fileService; 
        this.vehicles = fileService.getVehicles(); 
        for (int i = 0; i < 300; i++) { 
            couponAvailability[i] = true; 
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
        for (Motorbike m : vehicles) { 
            if (m.getCouponCode() == couponCode && m.getCheckOutTime() == null) { 
                return true; // Coupon is used
            }
        }
        return false; // Coupon not used
    }

    public void checkIn(int couponCode, int plateNumber) { // Records check-in
        if (couponCode < 1 || couponCode > 300) {
            System.out.println("Coupon out of range (001-300)."); 
            return; // Exits
        }
        if (isCouponInUse(couponCode)) { // Checks if coupon in use
            System.out.println("Coupon in use."); 
            return; // Exits
        }
        if (plateNumber <= 0) { // Checks plate number
            System.out.println("Plate cannot be empty or negative."); 
            return; // Exits
        }
        int index = couponCode - 1; // Converts coupon to index
        couponAvailability[index] = false; // Marks coupon in use
        vehicles.add(new Motorbike(couponCode, plateNumber, LocalDateTime.now())); // Adds motorbike
        fileService.saveToCSV(vehicles); // Saves to CSV
        System.out.println("Check-in: Coupon " + couponCode + ", Plate " + plateNumber); // Prints confirmation
    }
}
