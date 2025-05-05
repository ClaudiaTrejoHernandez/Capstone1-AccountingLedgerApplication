package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ledger {

    public static Scanner read = new Scanner(System.in);    //Global Scanner

    private List<TransactionHelper> transactions;       //List that will be used to store all transactions imputed and saved

    //Method to create an empty array list for transactions
    public Ledger() {
        transactions = new ArrayList<>();
    }

    //Method to add transactions to Array List:
    public void addDeposit(String description, String vendor, double amount) {

        if (amount <= 0) {      //Makes sure deposit amount is positive
            System.out.println("\n❌ Invalid deposit. Deposit amount must be positive.\n");
            return;
        }

        //Creating new transaction with the time it was imputed
        TransactionHelper deposit = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(deposit);       //Calling method to save the transaction to csv file
        transactions.add(deposit);      //Adding deposit to the list

        System.out.println("\nDeposit of $" + amount + " was successfully processed.\n");

    }

    //Method to add payment (turns input into a negative amount)
    public void addPayment(String description, String vendor, double amount) {
        if (amount <= 0) {      //Making sure that payment is positive (app will make payments negative)
            System.out.println("\n❌ Invalid payment. Payment amount must be positive.\n");
            return;
        }

        amount = -Math.abs(amount);     //Turns that user input into a negative amount (to simulate a 'withdrawl')
        TransactionHelper payment = new TransactionHelper(LocalDateTime.now(), description, vendor, amount);
        saveTransaction(payment);       //Calling method to save the transaction to the csv file
        transactions.add(payment);      //Adding payment to the list

        System.out.println("\nPayment of $" + Math.abs(amount) + " was successfully processed.\n");

    }

    //Method to load transactions saved in the csv file into the list
    public void loadTransactionsCSV() {
        File file = new File("transactions.csv");       //Making the file object for the csv
        if (!file.exists()) {       //If file does not exist, skip loading and make a new file:
            System.out.println("📂 No transaction file exists. 📝 Starting a new empty ledger.\n");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();        //Skips the header line while reading file (if it exists)

            while ((line = reader.readLine()) != null) {        //Reads each line (that is not empty)
                String[] parts = line.split("\\|", -1);     //Splitting each line by the pipe '|'
                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);     //Parsing the date
                String description = parts[1];
                String vendor = parts[2];
                double amount = Double.parseDouble(parts[3]);

                transactions.add(new TransactionHelper(dateTime, description, vendor, amount));     //Adding each transaction

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        //Displays all deposits (positive amounts)
        public void displayDeposit () {
            System.out.println("\nDisplaying Deposits:\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {        //Print out every transaction from most recent to oldest
                TransactionHelper t = transactions.get(i);

                if (t.getAmount() > 0) {
                    System.out.println(t.transactionString());
                }
            }
        }

        //Displays all deposits (negative amounts)
        public void displayPayment () {
            System.out.println("\nDisplaying Payments:\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {        //Print out every transaction from most recent to oldest
                TransactionHelper t = transactions.get(i);

                if (t.getAmount() < 0) {
                    System.out.println(t.transactionString());
                }
            }
        }

        //Method to display all transactions
        public void displayAll () {
            System.out.println("\nAll Transactions:\n");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                TransactionHelper t = transactions.get(i);

                System.out.println(t.transactionString());
            }
        }

        //Method to save user input into csv file
        public void saveTransaction (TransactionHelper t){

            String filePath = "transactions.csv";
            File file = new File(filePath);
            boolean newFile = !file.exists() || file.length() == 0;     //Checking if file is new or empty:
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));       //Making sure to append ('ture' = do append) to the file (append makes sure to add to the existing file instead of overwriting what's already been saved inside the file)

                //If the file is new (empty), write this following header
                if (newFile) {
                    bufferedWriter.write("date|time|description|vendor|amount");
                    bufferedWriter.newLine();
                }

                bufferedWriter.write(t.transactionString());        //Write the transaction line
                bufferedWriter.newLine();

                bufferedWriter.close();     //Close to ensure the data was saved into the file

            } catch (IOException e) {
                System.out.println("\n⚠️ Unexpected error: " + e.getMessage() + " 🛠️\n");
            }
        }

        //Method to displa transactions for the current month
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

        //Method to display transactions from the previous month
        public void previousMonthReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();

            int previousYear;
            int previousMonth;

            if (currentMonth == 1) {        //If it is currently January, then the previous month is December (12)
                previousMonth = 12;
                previousYear = currentYear -1;      //Takes into account that the previous year could be 12 AND a year before; so the 'previous year' is the 'current year' - 1
            } else {
                previousMonth = currentMonth -1;        //Takes into account that the previous month could be in the same year so the 'previous year' IS the 'current month'
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

        //Method to display transactions from the beginning of the year up to this current day
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

        //Method to display all transactions from the previous year
        public void previousYearReport () {
            LocalDateTime now = LocalDateTime.now();
            int currentYear = now.getYear();

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

        //Method to look for vendors by vendor name inputed from vendor
        public void searchByVendor () {
            System.out.println("Enter the name of the vendor you're looking for: ");
            String searchVendor = read.nextLine().toLowerCase();

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

