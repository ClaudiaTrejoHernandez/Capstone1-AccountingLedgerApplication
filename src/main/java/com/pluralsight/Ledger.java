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
            System.out.println("\n❌ Invalid deposit. Deposit amount must be positive.\n");
            return;
        }

        TransactionHelper deposit = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(deposit);
        transactions.add(deposit);

        System.out.println("\nDeposit of $" + amount + " was successfully processed.\n");

    }

    public void addPayment(String description, String vendor, double amount) {
        if (amount <= 0) {
            System.out.println("\n❌ Invalid payment. Payment amount must be positive.\n");
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
            System.out.println("📂 No transaction file exists. 📝 Starting a new empty ledger.\n");
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
            System.out.println("\nAll Transactions:\n");
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
                System.out.println("\n⚠️ Unexpected error: " + e.getMessage() + " 🛠️\n");
            }
        }

        public void monthToDateReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();

            System.out.println("\n📑 Month-to-Date Report\n");

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
                System.out.println("\nNo transactions found for this month.");
            }


        }

        public void previousMonthReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();

            int previousYear;
            int previousMonth;

            if (currentMonth == 1) {
                previousMonth = 12;
                previousYear = currentYear -1;
            } else {
                previousMonth = currentMonth -1;
                previousYear = currentYear;

            }
            System.out.println("\n📑 Previous Month Report\n");

            boolean found = false;

            for (TransactionHelper t : transactions) {
                LocalDateTime date = t.getDateTime();
                int year = date.getYear();
                int month = date.getMonthValue();

                if (year == previousYear && month == previousMonth) {
                    System.out.println(t.transactionString());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("\nNo transactions found for the previous month.\n");
            }


        }

        public void yearToDateReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();
            int currentDay = now.getDayOfMonth();

                System.out.println("\n📑 Year-to-Date Report\n");

                boolean found = false;

                for (TransactionHelper t : transactions) {
                    LocalDateTime date = t.getDateTime();
                    int year = date.getYear();
                    int month = date.getMonthValue();
                    int day = date.getDayOfMonth();

                    if (year == currentYear) {
                        if (month < currentMonth) {
                            System.out.println(t.transactionString());
                            found = true;
                        } else if (month == currentMonth && day <= currentDay) {
                            System.out.println(t.transactionString());
                            found = true;
                        }
                    }
                }
                if (!found) {
                    System.out.println("\nNo transactions found for the current year-to-date.\n");
                }

        }

        public void previousYearReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int previousYear;

            System.out.println("\n📑 Previous Year Report\n");

            boolean found = false;

            for (TransactionHelper t : transactions) {
                LocalDateTime date = t.getDateTime();
                int year = date.getYear();

                if (year == currentYear - 1) {
                    System.out.println(t.transactionString());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("\nNo transactions found for the previous year.\n");
            }
        }


        public void searchByVendor () {
            System.out.println("Enter the name of the vendor you're looking for: ");
            String searchVendor = read.nextLine().trim().toLowerCase();

            boolean found = false;
            for (TransactionHelper t : transactions) {
                if (t.getVendor().toLowerCase().trim().contains(searchVendor)) {
                    if (!found) {
                        System.out.println("\nHere's what I found for " + searchVendor + ": \n");
                    }
                    System.out.println(t.transactionString());
                    found = true;
                    }
            }
                if (!found){
                    System.out.println("\nNo transactions found by the name: " + searchVendor + "\n");
                }


        }

}

