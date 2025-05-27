package iucpark.manager; // Defines package

public interface RecordManager { // Defines parking management methods
    void checkIn(int couponCode, int plateNumber); // Records vehicle entry
    void checkOut(int couponCode); // Records vehicle exit
    void exportRecords(String date, Integer plateNumber); // Exports records
}