package iucpark.model; // Defines package

import java.time.LocalDateTime; // Represents date-time
import java.time.format.DateTimeFormatter; // Formats date-time

public class Motorbike extends Vehicle { // Represents motorbike records
    private LocalDateTime checkOutTime; // Stores check-out time

    public Motorbike(int couponCode, int plateNumber, LocalDateTime checkInTime) { // Constructor
        super(couponCode, plateNumber, checkInTime); // Calls Vehicle constructor
        this.checkOutTime = null; // Sets check-out to null
    }

    public LocalDateTime getCheckOutTime() { return checkOutTime; } // Returns check-out time
    public void setCheckOutTime(LocalDateTime time) { this.checkOutTime = time; } // Sets check-out time

    @Override
    public String getDetails() { // Returns motorbike details
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formats date-time
        String checkOut = checkOutTime != null ? checkOutTime.format(formatter) : "Not checked out"; // Formats check-out
        return "Coupon: " + couponCode + ", Plate: " + plateNumber + ", Check-in: " + checkInTime.format(formatter) + ", Check-out: " + checkOut; // Returns details
    }
}