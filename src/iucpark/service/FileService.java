package iucpark.service; // Defines package
import iucpark.model.Motorbike; // Imports Motorbike
import java.io.BufferedReader; // Reads text
import java.io.FileReader; // Reads files
import java.io.FileWriter; // Writes files
import java.io.IOException; // Handles file errors
import java.time.LocalDateTime; // Represents date-time
import java.time.format.DateTimeFormatter; // Formats date-time
import java.util.ArrayList; // Stores vehicles

public class FileService { // Manages CSV operations
    private static final String CSV_FILE = "vehicles.csv"; // CSV file name
    private final ArrayList<Motorbike> vehicles = new ArrayList<>(); // Stores vehicles

    public FileService() { // Constructor
        loadFromCSV(); // Loads CSV data
    }

    public ArrayList<Motorbike> getVehicles() { // Returns vehicles
        return vehicles; // Returns vehicle list
    }

    private void loadFromCSV() { // Loads CSV data
        vehicles.clear(); // Clears list
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) { // Opens CSV
            String line; // Stores line
            reader.readLine(); // Skips header
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Date-time formatter
            while ((line = reader.readLine()) != null) { // Reads lines
                String[] parts = line.split(",", -1); // Splits by commas
                if (parts.length >= 4) { // Checks for 4 fields
                    try {
                        int couponCode = Integer.parseInt(parts[0].trim()); // Parses coupon
                        int plateNumber = Integer.parseInt(parts[1].trim()); // Parses plate
                        LocalDateTime checkInTime = LocalDateTime.parse(parts[2].trim(), formatter); // Parses check-in
                        LocalDateTime checkOutTime = parts[3].trim().isEmpty() ? null : LocalDateTime.parse(parts[3].trim(), formatter); // Parses check-out

                        Motorbike motorbike = new Motorbike(couponCode, plateNumber, checkInTime); // Creates motorbike
                        if (checkOutTime != null) { // If check-out exists
                            motorbike.setCheckOutTime(checkOutTime); // Sets check-out
                        }
                        vehicles.add(motorbike); // Adds motorbike
                    } catch (Exception e) { // Handles parsing errors
                        System.out.println("Error parsing CSV line: " + line); // Prints error
                    }
                }
            }
        } catch (IOException e) { // Handles file errors
            System.out.println("No records found in CSV file."); // Prints error
        }
    }

    public void saveToCSV(ArrayList<Motorbike> vehicles) { // Saves to CSV
        try (FileWriter writer = new FileWriter(CSV_FILE)) { // Opens CSV
            writer.write("coupon_code,plate_number,check_in_time,check_out_time\n"); // Writes header
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Date-time formatter
            for (Motorbike m : vehicles) { // Loops through vehicles
                String checkOutTime = m.getCheckOutTime() != null ? m.getCheckOutTime().format(formatter) : ""; // Formats check-out
                writer.write(String.format("%d,%d,%s,%s\n", m.getCouponCode(), m.getPlateNumber(), m.getCheckInTime().format(formatter), checkOutTime)); // Writes row
            }
        } catch (IOException e) { // Handles file errors
            System.out.println("Error saving to CSV: " + e.getMessage()); // Prints error
        }
    }
}