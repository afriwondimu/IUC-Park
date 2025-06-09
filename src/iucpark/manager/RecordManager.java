package iucpark.manager; // Defines package

public interface RecordManager { 
    void checkIn(int couponCode, int plateNumber); 
    void checkOut(int couponCode); 
    void exportRecords(String date, Integer plateNumber); 
}
