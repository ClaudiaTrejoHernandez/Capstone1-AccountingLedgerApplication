package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHelper {

    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;

    //Parameterized constructor:
    public TransactionHelper(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Get/Set Methods:

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    //Set method: formats the string of the transaction being printed on csv or display
    public String transactionString(){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + "|" + description + "|" + vendor + "|" + amount;
    }

}
