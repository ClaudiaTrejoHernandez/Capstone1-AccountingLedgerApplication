# 📋 Capstone 1: Accounting Ledger Application 🏦 

## 📖 Description ✏️
This is an application that can be used to track all financial transactions for a business or for personal use. It has features such as showing several screens (Home, Ledger, and Reports screens), that allow users to input their own information that will be stored in an accounting ledger.

## ✅ Features
  - **📒 Home Screen:**    
    - **Add Deposit:** Prompts user for deposit information
    - **Make Payment:** Prompts user for payment information
    - **Ledger:** Displays the ledger screen
    - **Exit:** Exits application
      - Application will continue to run until the user chooses to exit
   
  - **📗 Ledger:** All entries will show from newest to oldest
    - **All:** Displays all entries in ledger
    - **Deposits:** Displays only deposit entries (positive amounts).
    - **Payments:** Displays only payment entries (negative amounts).
    - **📘 Reports:** Opens new screen that allows users to run pre-defined reports
      - **Month-to-Date:**
      - **Previous Month:**
      - **Year-to-Date:**
      - **Previous Year:**
      - **Search by Vendor:** Prompts the user to type in the vendor they are looking for and displays all entries for that vendor
      - **Back:** Returns back to Ledger Menu
    - **Home:** Returns backk to Home Screen
   
## 🖥️ Application Screens ☑
![Capstone1Screenshot](https://github.com/user-attachments/assets/6215cd85-7f85-488c-b91b-aeea192a3f89)

## 📗 Ledger Example 📜
![Capstone1LedgerInput](https://github.com/user-attachments/assets/3d508349-91ba-4edd-9dbc-330a014f486a)

## 👩‍💻 An interesting piece of code 🔢
- **This method takes transactions and saves it to a file called "transactions.csv":**
  
![Capstone1InterestingPieceOfCode](https://github.com/user-attachments/assets/2a4c86b6-3c5d-4aac-9c57-bbceeb26316e)
- It checks if the file already exists and if it's empty.
  - If the file is brand new or completely empty, it adds the header "dateTtime|description|vendor|amount".
  - If there's already an existing file with data inside, it skips this step and continues to add data under the header and any pre-existing lines of data.
- It uses the "BufferedWriter" to write new inputs into the file, each one in it's own new line.
- It closes to safely save the data into the designated file.
  - The "try/catch" statements allows for the program to catch unexpected issues, and makes sure to print out an error message to let the user know of the issue.

## 🌱 Resources 🏗️
- YearUp instructor
- Fellow YearUp peers
- Workbooks 1-3
- <a href="https://chatgpt.com/g/g-6800332fde008191822e81c0f54c8321-java-trainer-remsey">Java Trainer Remsey</a>
- Google Search
- GitHub

