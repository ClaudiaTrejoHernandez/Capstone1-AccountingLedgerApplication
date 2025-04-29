package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);
    public static Ledger ledger = new Ledger();

    public static void main(String[] args) {
        homeScreen();

    }

    //Create a Home Screen:
    public static void homeScreen() {
        while (true) {                                                                  //Will continue to run until the user chooses to exit
            System.out.println("Welcome to the Home Screen:");
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
//                case "L":
//                    ledgerMenu();
//                    break;
//                case "X":
//                    System.out.println("Exiting program. See you soon!");
//                    return;
//                default:
//                    System.out.println("Invalid command. Please try again");

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

           if (depositAmount <= 0) {
               System.out.println("Invalid deposit. Deposit amount must be positive.");
               return;
           }

           ledger.addTransaction(depositDescription, depositVendor, depositAmount);

       }

       public static void makePayment() {

            System.out.println("Enter description of payment: ");
            String paymentDescription = read.nextLine();
            System.out.println("Enter vendor: ");
            String paymentVendor = read.nextLine();
            System.out.println("Enter payment amount");
            double paymentAmount = read.nextDouble();
            read.nextLine();

            if (paymentAmount >= 0) {
                System.out.println("Invalid payment. Payment amount must be negative.");
                return;
            }

            ledger.addTransaction(paymentDescription, paymentVendor, paymentAmount);

       }

        




}