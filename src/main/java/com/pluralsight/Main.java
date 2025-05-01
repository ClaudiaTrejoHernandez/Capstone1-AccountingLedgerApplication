package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);
    public static Ledger ledger = new Ledger();

    public static void main(String[] args) {
        ledger.loadTransactionsCSV();
        homeScreen();

    }

    //Create a Home Screen:
    public static void homeScreen() {
        while (true) {                                                                  //Will continue to run until the user chooses to exit
            System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
            System.out.println("      🏠 Welcome to the Home Screen 🏡");
            System.out.println("✦━━━━━━━━━━━━━༺｡⋆｡☾｡⋆｡☁︎｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦\n");
            System.out.println("D) Add Deposit ");
            System.out.println("P) Make Payment (Debit) ");
            System.out.println("L) Ledger ");
            System.out.println("X) Exit ");
            System.out.println("\nPlease type the letter of your command: ");
            String userCommand = read.nextLine().trim().toUpperCase();

            switch (userCommand) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    System.out.println("👋 Exiting program. See you soon! 🌙✨");
                    return;
                default:
                    System.out.println("❌ Invalid command. Please try again ❌");

            }
        }
    }

    public static void addDeposit() {

        System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
        System.out.println("Enter description of deposit: ");
        String depositDescription = read.nextLine();
        System.out.println("Enter vendor: ");
        String depositVendor = read.nextLine();
        System.out.println("Enter Deposit Amount: ");
        double depositAmount = read.nextDouble();
        read.nextLine();

        ledger.addDeposit(depositDescription, depositVendor, depositAmount);

    }

    public static void makePayment() {

        System.out.println("✦━━━━━━━━━━━━━༺☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆｡☁︎༻━━━━━━━━━━━━━━━✦");
        System.out.println("Enter description of payment: ");
        String paymentDescription = read.nextLine();
        System.out.println("Enter vendor: ");
        String paymentVendor = read.nextLine();
        System.out.println("Enter payment amount");
        double paymentAmount = read.nextDouble();
        read.nextLine();

        ledger.addPayment(paymentDescription, paymentVendor, paymentAmount);

    }

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
            read.nextLine();

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



