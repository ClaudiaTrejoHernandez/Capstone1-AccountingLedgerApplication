package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Ledger {

    private List<TransactionHelper> transactions;
    private static String filePath = "transactions.csv";

    //Method to create an array list
    public Ledger(){
        transactions = new ArrayList<>();
    }

    //Method to add transactions to Array List:
    public void addDeposit(String description, String vendor, double amount){

        if (amount <= 0) {
            System.out.println("Invalid deposit. Deposit amount must be positive.");
            return;
        }

            amount = -Math.abs(amount);
            TransactionHelper deposit = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
            saveTransaction(deposit);

        System.out.println("Deposit of $" + Math.abs(amount) + " was successfully processed.");

    }

    public void addPayment(String description, String vendor, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment. Payment amount must be positive.");
            return;
        }

        amount = -Math.abs(amount);
        TransactionHelper payment = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(payment);

        System.out.println("Payment of $" + Math.abs(amount) + " was successfully processed.\n");

    }

    public void displayDeposit() {
        for (TransactionHelper t : transactions) {
            if (t.getAmount() > 0);
            System.out.println(t.transactionString());
        }
    }

    public void displayPayment() {
        for (TransactionHelper t : transactions) {
            if (t.getAmount() < 0);
            System.out.println(t.transactionString());
        }
    }

    public void displayAll() {
        for (TransactionHelper t : transactions) {
            System.out.println(t.transactionString());
        }
    }

    //Method to write user input into csv file
    public void saveTransaction(TransactionHelper t) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            File file = new File(filePath);

            if (!file.exists() || file.length() == 0) {
                bufferedWriter.write("date|time|description|vendor|amount");
                bufferedWriter.newLine();
            }

            bufferedWriter.write(t.transactionString());
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
