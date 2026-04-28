package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts[0].equalsIgnoreCase("date")) {
                    continue;
                }

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction t = new Transaction(date, time, description, vendor, amount);
                transactions.add(t);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        System.out.println("Loaded: " + transactions.size() + " transactions");
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

