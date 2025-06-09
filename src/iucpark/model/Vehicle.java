package iucpark.model;

import java.io.Serializable; // Allows serialization
import java.time.LocalDateTime; // Represents date-time

public abstract class Vehicle implements Serializable { // Blueprint for vehicles
    protected int couponCode; 
    protected int plateNumber; 
    protected LocalDateTime checkInTime; 

    public Vehicle(int couponCode, int plateNumber, LocalDateTime checkInTime) { // Constructor
        this.couponCode = couponCode; 
        this.plateNumber = plateNumber; 
        this.checkInTime = checkInTime; 
    }

    public int getCouponCode() { return couponCode; } // Returns coupon code
    public int getPlateNumber() { return plateNumber; } // Returns plate number
    public LocalDateTime getCheckInTime() { return checkInTime; } // Returns check-in time
    public abstract String getDetails(); // Returns vehicle details
}
