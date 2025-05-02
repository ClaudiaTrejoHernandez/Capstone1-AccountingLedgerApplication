package com.pluralsight;
import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);    //Global Scanner
    public static Ledger ledger = new Ledger();     //Ledger object to manage transactions

    public static void main(String[] args) {
        ledger.loadTransactionsCSV();       //Loads transactions from csv file to ledger; must be called before any displays are triggered (so I placed it at the very top before calling the home menu as well)
        homeScreen();       //Calling homeScreen method located in Main to start user loop

    }

    //Create a Home Screen for user:
    public static void homeScreen() {
        while (true) {      //Will continue to run until the user chooses to exit
            System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
            System.out.println("      🏠 Welcome to the Home Screen 🏡");
            System.out.println("✦━━━━━━━━━━━━━༺｡⋆｡☾｡⋆｡☁︎｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦\n");
            System.out.println("D) Add Deposit ");
            System.out.println("P) Make Payment (Debit) ");
            System.out.println("L) Ledger ");
            System.out.println("X) Exit ");
            System.out.println("\nPlease type the letter of your command: ");
            String userCommand = read.nextLine().trim().toUpperCase();      //Reads and stores user input

            switch (userCommand) {
                case "D":
                    addDeposit();       //Calling method to deposit
                    break;
                case "P":
                    makePayment();      //Calling method for a payment
                    break;
                case "L":
                    ledgerMenu();       //Opening ledger menu located in Main
                    break;
                case "X":
                    System.out.println("👋 Exiting program. See you soon! 🌙✨");
                    return;     //Completely exits this method and closes the program
                default:
                    System.out.println("❌ Invalid command. Please try again ❌");        //Will continue looping until valid input is inserted

            }
        }
    }

    //Method for prompting user input and records deposit in ledger
    public static void addDeposit() {

        System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
        System.out.println("Enter description of deposit: ");
        String depositDescription = read.nextLine();        //Stores user description
        System.out.println("Enter vendor: ");
        String depositVendor = read.nextLine();     //Stores vendor name
        System.out.println("Enter Deposit Amount: ");
        double depositAmount = read.nextDouble();       //Stores deposit amount
        read.nextLine();        //Necessary after input read is not a string

        ledger.addDeposit(depositDescription, depositVendor, depositAmount);        //Adds the deposit to ledger file

    }
    //Method for prompting user input and records payment in ledger
    public static void makePayment() {

        System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
        System.out.println("Enter description of payment: ");
        String paymentDescription = read.nextLine();        //Stores user description
        System.out.println("Enter vendor: ");
        String paymentVendor = read.nextLine();       //Stores vendor name
        System.out.println("Enter payment amount");
        double paymentAmount = read.nextDouble();       //Stores payment amount (negative amount)
        read.nextLine();        //Necessary after input read is not a string

        ledger.addPayment(paymentDescription, paymentVendor, paymentAmount);        //Adds payment to ledger file

    }

    //Method for displaying dedger menu options
    public static void ledgerMenu() {
        while (true) {
            System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
            System.out.println("               💰 Ledger Menu 🧾");
            System.out.println("✦━━━━━━━━━━━━━༺｡⋆｡☾｡⋆｡☁︎｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦\n");
            System.out.println("A) Display All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Return to Home Screen");
            System.out.println("\nPlease type the letter of your command: ");
            String userCommand = read.nextLine().trim().toUpperCase();

            switch (userCommand) {
                case "A":
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.displayAll();
                    System.out.println();
                    break;
                case "D":
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.displayDeposit();
                    System.out.println();
                    break;
                case "P":
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.displayPayment();
                    System.out.println();
                    break;
                case "R":
                    reportsMenu();
                    System.out.println();
                    break;
                case "H":
                    System.out.println("\n🏡 Returning to Home Menu 🏠\n");
                    return;
                default:
                    System.out.println("\n❌ Invalid command. Please try again ❌\n");

            }
        }
    }

    //Method to display reports menu
    public static void reportsMenu() {
        boolean stayInReports = true;
        while (stayInReports) {
            System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
            System.out.println("                📋 Reports 🗂️");
            System.out.println("✦━━━━━━━━━━━━━༺｡⋆｡☾｡⋆｡☁︎｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦\n");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("\nPlease type the number of your command: ");
            int ledgerCommand = read.nextInt();
            read.nextLine();        //Necessary after input read is not a string

            switch (ledgerCommand) {
                case 1:
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.monthToDateReport();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.previousMonthReport();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.yearToDateReport();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.previousYearReport();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
                    ledger.searchByVendor();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("\n🔙 Returning to Ledger Menu 📒\n");
                    return;
                default:
                    System.out.println("\n❌ Invalid command. Please try again ❌\n");
            }
        }
    }
}



