package iucpark.model;

import java.io.Serializable; // Allows serialization
import java.time.LocalDateTime; // Represents date-time

public abstract class Vehicle implements Serializable { // Blueprint for vehicles
    protected int couponCode; // Stores coupon code
    protected int plateNumber; // Stores plate number
    protected LocalDateTime checkInTime; // Stores check-in time

    public Vehicle(int couponCode, int plateNumber, LocalDateTime checkInTime) { // Constructor
        this.couponCode = couponCode; // Sets coupon code
        this.plateNumber = plateNumber; // Sets plate number
        this.checkInTime = checkInTime; // Sets check-in time
    }

    public int getCouponCode() { return couponCode; } // Returns coupon code
    public int getPlateNumber() { return plateNumber; } // Returns plate number
    public LocalDateTime getCheckInTime() { return checkInTime; } // Returns check-in time
    public abstract String getDetails(); // Returns vehicle details
}