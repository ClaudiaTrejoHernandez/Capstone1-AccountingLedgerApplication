package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Ledger {

    private List<TransactionHelper> transactions;

    //Method to create an array list
    public Ledger(){
        transactions = new ArrayList<>();
    }

    //Method to add transactions to Array List:
    public void addTransaction(String description, String vendor, double amount){
        LocalDateTime dateTime = LocalDateTime.now();
        TransactionHelper userTransactions = new TransactionHelper(dateTime, description, vendor, amount);
        transactions.add(userTransactions);

        String transactionString = userDeposit.transactionString();
        String filePath = "transactions.csv";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            File file = new File(filePath);

            if (!file.exists() || file.length() == 0) {
                bufferedWriter.write("date|time|description|vendor|amount");
                bufferedWriter.newLine();
            }
            bufferedWriter.write(transactionString);
            bufferedWriter.newLine();


            if (amount > 0) {
                System.out.println("Deposit of $" + amount + " was successfully processed.\n");
            }

            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Unexpected error occurred: " + e.getMessage() + "\n");
        }
    }

    //Method to view ledger info:
    public List<TransactionHelper> getTransactions() {
        return transactions;
    }
}
