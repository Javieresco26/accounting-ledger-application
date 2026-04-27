package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;


        while (true) {

            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextLine().toUpperCase();

            if (choice.equals("X")) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.println("You chose: " + choice);
        }
    }
}

