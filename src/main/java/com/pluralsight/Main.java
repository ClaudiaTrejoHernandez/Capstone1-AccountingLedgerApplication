package com.pluralsight;

import java.util.Scanner;

public class Main {

    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();

    }

    //Create a Home Screen:
    public static void homeScreen(){
        while (true){
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

       public static void addDeposit(){

        }

    }

}