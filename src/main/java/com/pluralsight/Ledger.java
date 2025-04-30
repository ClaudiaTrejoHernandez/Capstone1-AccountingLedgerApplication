package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ledger {

    public static Scanner read = new Scanner(System.in);

    private List<TransactionHelper> transactions;

    //Method to create an array list
    public Ledger() {
        transactions = new ArrayList<>();
    }

    //Method to add transactions to Array List:
    public void addDeposit(String description, String vendor, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit. Deposit amount must be positive.");
            return;
        }

        TransactionHelper deposit = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(deposit);
        transactions.add(deposit);

        System.out.println("\nDeposit of $" + amount + " was successfully processed.");

    }

    public void addPayment(String description, String vendor, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment. Payment amount must be positive.");
            return;
        }

        amount = -Math.abs(amount);
        TransactionHelper payment = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(payment);
        transactions.add(payment);

        System.out.println("\nPayment of $" + Math.abs(amount) + " was successfully processed.\n");

    }

    public void loadTransactionsCSV() {
        File file = new File("transactions.csv");
        if (!file.exists()) {
            System.out.println("No transaction file exists. Will begin new empty ledger.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
                String description = parts[1];
                String vendor = parts[2];
                double amount = Double.parseDouble(parts[3]);

                transactions.add(new TransactionHelper(dateTime, description, vendor, amount));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


        public void displayDeposit () {
            System.out.println("\nDisplaying Deposits:\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                TransactionHelper t = transactions.get(i);

                if (t.getAmount() > 0) {
                    System.out.println(t.transactionString());
                }
            }
        }


        public void displayPayment () {
            System.out.println("\nDisplaying Payments:\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                TransactionHelper t = transactions.get(i);

                if (t.getAmount() < 0) {
                    System.out.println(t.transactionString());
                }
            }
        }

        public void displayAll () {
            System.out.println("\nAll Transactions\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                TransactionHelper t = transactions.get(i);

                System.out.println(t.transactionString());
            }
        }

        //Method to write user input into csv file
        public void saveTransaction (TransactionHelper t){

            String filePath = "transactions.csv";
            File file = new File(filePath);
            boolean newFile = !file.exists() || file.length() == 0;
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

                if (newFile) {
                    bufferedWriter.write("date|time|description|vendor|amount");
                    bufferedWriter.newLine();
                }

                bufferedWriter.write(t.transactionString());
                bufferedWriter.newLine();

                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        public void monthToDateReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();

            System.out.println("\nMonth-to-Date Report\n");

            boolean found = false;

            for (TransactionHelper t : transactions) {
                LocalDateTime date = t.getDateTime();
                int year = date.getYear();
                int month = date.getMonthValue();

                if (year == currentYear && month == currentMonth) {
                    System.out.println(t.transactionString());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found for this month.");
            }


        }

        public void previousMonthReport () {
            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime startOfCurrentMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime startOfLastMonth = now.withDayOfMonth(1).minusNanos(1);
            LocalDateTime endOfLastMonth = now.withDayOfMonth(1).minusNanos(1);

            System.out.println("\nPrevious Month Report\n");

            boolean found = false;
            for (TransactionHelper t : transactions) {
                if (!t.getDateTime().isBefore(startOfLastMonth) && !t.getDateTime().isAfter(endOfLastMonth)) {
                    System.out.println(t);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found for the previous month.");
            }


        }

        public void yearToDateReport () {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thisYear = now.withDayOfYear(1);

            System.out.println("\nYear-to-Date Report\n");

            boolean found = false;
            for (TransactionHelper t : transactions) {
                if (!t.getDateTime().isBefore(thisYear) && !t.getDateTime().isAfter(now)) {
                    System.out.println(t);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found for this year.");
            }


        }

        public void previousYearReport () {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfCurrentYear = now.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime startOfLastYear = startOfCurrentYear.minusYears(1);
            LocalDateTime endOfLastYear = startOfLastYear.minusNanos(1);

            System.out.println("\nPrevious Year Report\n");

            boolean found = false;
            for (TransactionHelper t : transactions) {
                if (!t.getDateTime().isBefore(startOfLastYear) && !t.getDateTime().isAfter(endOfLastYear)) {
                    System.out.println(t);
                    found = true;
                }
            }

            System.out.println("No transactions found for the previous year.");
        }


        public void searchByVendor () {
            System.out.println("Enter the vendor name you're looking for: ");
            String searchVendor = read.nextLine().trim().toLowerCase();

            boolean found = false;
            for (TransactionHelper t : transactions) {
                if (t.getVendor().toLowerCase().trim().contains(searchVendor)) {
                    if (!found) {
                        System.out.println("Here's what I found for " + searchVendor + ": ");
                    }
                    System.out.println(t.transactionString());
                    found = true;

                }

                if (!found){
                    System.out.println("No vendors found by that name.");
                }

            }
        }

}

