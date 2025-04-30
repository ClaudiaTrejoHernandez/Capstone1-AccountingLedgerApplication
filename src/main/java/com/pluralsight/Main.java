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
            System.out.println("\nWelcome to the Home Screen: \n");
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
                    System.out.println("Exiting program. See you soon!");
                    return;
                default:
                    System.out.println("Invalid command. Please try again");

            }
        }
    }
       public static void addDeposit(){

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
               System.out.println("\nLedger Menu\n");
               System.out.println("A) Display All");
               System.out.println("D) Deposits");
               System.out.println("P) Payments");
               System.out.println("R) Reports");
               System.out.println("H) Return to Home Screen");
               System.out.println("\nPlease type the letter of your command: ");
               String userCommand = read.nextLine().trim().toUpperCase();

                switch (userCommand) {
                   case "A":
                       ledger.displayAll();
                       break;
                   case "D":
                       ledger.displayDeposit();
                       break;
                   case "P":
                       ledger.displayPayment();
                       break;
                   case "R":
                        while (true) {
                            System.out.println("\nReports\n");
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
                                    ledger.monthToDateReport();
                                    break;
                                case 2:
                                    ledger.previousMonthReport();
                                    break;
                                case 3:
                                    ledger.yearToDateReport();
                                    break;
                                case 4:
                                    ledger.previousYearReport();
                                    break;
                                case 5:
                                    ledger.searchByVendor();
                                    break;
                                case 0:
                                    System.out.println("Returning to Ledger Menu");
                                    return;
                                default:
                                    System.out.println("Invalid command. Please try again");
                            }
                        }

                   case "H":
                       System.out.println("Returning to Home Menu\n");
                       return;
                   default:
                       System.out.println("Invalid command. Please try again");

               }

           }
       }

}