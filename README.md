# Online Banking System

A console-based Java application simulating core banking operations — built as
part of the Sqrock IT Solutions internship, Project Phase 1.

## Features
- User registration and login with password authentication
- Create and manage bank accounts (Savings/Current)
- Deposit, withdraw, and transfer money between accounts
- Balance validation (prevents overdrafts and invalid transfers)
- Full transaction history per account
- Data persistence using file handling (accounts saved to accounts.txt)

## Java Concepts Used
- Object-Oriented Programming (classes, objects, encapsulation)
- Collections (ArrayList, List)
- File I/O (FileWriter, FileReader, BufferedReader)
- Exception handling (try/catch for file operations)
- Control flow (loops, switch statements, conditionals)

## How to Run
1. Make sure Java (JDK 17+) is installed.
2. Clone this repository.
3. Compile: `javac Account.java Bank.java Main.java`
4. Run: `java Main`
5. Follow the on-screen menu to register, log in, and manage your account.

## Notes
This version uses file-based storage instead of a MySQL database for
simplicity and speed of development. The data model (account number, holder
name, password, type, balance) is structured so it would map directly onto a
relational `accounts` table if extended with JDBC in future.

## Author
[Madhura Sawant] — Sqrock IT Solutions Internship, Java Track, Project Phase 1