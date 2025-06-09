package iucpark.service; // Defines package
import iucpark.model.Motorbike; // Imports Motorbike
import java.io.FileWriter; // Writes to files
import java.io.IOException; // Handles file errors
import java.time.LocalDate; // Represents date
import java.time.format.DateTimeFormatter; // Formats date-time
import java.util.ArrayList; // Stores vehicles

public class ExportService { // Handles export logic
    private final FileService fileService; 
    private final ArrayList<Motorbike> vehicles; // Stores vehicles

    public ExportService(FileService fileService) { // Constructor
        this.fileService = fileService; 
        this.vehicles = fileService.getVehicles(); // Loads vehicles
    }

    public void exportRecords(String date, Integer plateNumber) { // Exports records
        try {
            LocalDate targetDate = LocalDate.parse(date); // Parses date
            String fileName = plateNumber == null ? "records_" + date + ".txt" : "records_" + date + "_" + plateNumber + ".txt"; // Sets file name
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Date-time formatter
            try (FileWriter writer = new FileWriter(fileName)) { // Opens export file
                writer.write("+-------+--------+---------------------+---------------------+\n"); 
                writer.write("| Plate | Coupon | Check-in            | Check-out           |\n");
                writer.write("+-------+--------+---------------------+---------------------+\n"); 

                boolean hasRecords = false; // Tracks records
                for (Motorbike m : vehicles) { 
                    if (m.getCheckInTime().toLocalDate().equals(targetDate)) { // Checks date
                        if (plateNumber == null || m.getPlateNumber() == plateNumber) { // Checks plate
                            String checkOut = m.getCheckOutTime() != null ? m.getCheckOutTime().format(formatter) : "Not checked out"; 
                            writer.write(String.format("| %-5d | %-6d | %-19s | %-19s |\n", m.getPlateNumber(), m.getCouponCode(), m.getCheckInTime().format(formatter), checkOut)); /
                            hasRecords = true; // Marks record found
                        }
                    }
                }
                if (!hasRecords) { // Checks if no records
                    String msg = plateNumber == null ? "No records for " + date : "No records for " + date + " and plate " + plateNumber; // Creates message
                    System.out.println(msg); // Prints message
                    return; // Exits
                }
                writer.write("+-------+--------+---------------------+---------------------+\n"); 
                System.out.println("Exported to " + fileName); // Prints success
            } catch (IOException e) { // Handles file errors
                System.out.println("Error writing to file: " + e.getMessage()); // Prints error
            }
        } catch (Exception e) { // Handles date errors
            System.out.println("Invalid date format. Use yyyy-MM-dd."); // Prints error
        }
    }
}
