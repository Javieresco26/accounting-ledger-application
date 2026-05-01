This is a Java console-based ledger application that tracks financial transactions such as deposits and payments. Users can view, filter, and search transactions through different report options. All transactions are stored in a CSV file so data is saved between runs.


#Features
- Add deposits and payments
- View all transactions in a ledger
- Filter transactions by:
  - Deposits
  - Payments
- Generate reports:
  - Month-to-Date
  - Previous Month
  - Year-to-Date
  - Previous Year
- Search transactions by vendor (exact match, case-insensitive)
- Data persistence using CSV file storage
  
#Technologies Used
- Java
- ArrayList
- File I/O (BufferedReader, FileWriter)
- LocalDate / LocalTime
- Scanner (user input)

# How to Run the Program
1. Open the project in IntelliJ
2. Make sure `transactions.csv` is in the project folder
3. Run `LedgerApp.java`
4. Use the console menu to navigate:
   - Add deposits or payments
   - View ledger
   - Run reports
