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

        //HOME SCREEN

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

                if (choice.equals("L")) {
                    runLedgerScreen(transactions);
                } else {
                    System.out.println("You chose: " + choice);
                }
            }
        }

    public static void runLedgerScreen(ArrayList<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean running = true;

        while (running) {
            System.out.println("\n=== LEDGER SCREEN ===");
            System.out.println("A) All Transactions");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    for (Transaction t : transactions) {
                        System.out.printf("%s %s | %s | %s | $%.2f%n",
                                t.getDate(),
                                t.getTime(),
                                t.getDescription(),
                                t.getVendor(),
                                t.getAmount());
                    }
                    break;

                case "H":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }
}
