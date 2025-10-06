# Sibank Console App

A lightweight **console-based banking system** built with **C# (.NET)**.  
This application simulates core banking operations such as account creation, deposits, withdrawals, and transaction history — all within a terminal interface.

---

## ✨ Features
- Create and manage user accounts  
- Deposit and withdraw funds  
- View account balance and transaction history  
- Input validation and error handling  
- Simple menu-driven interface  
- Modular code structure for easy extension  

---

## 🧱 Tech Stack
- **Language:** C#  
- **Framework:** .NET Core / .NET 6+  
- **Type:** Console Application  

---

## 📂 Project Structure
Sibank-consoleApp/ │── Program.cs # Entry point │── Models/ # Account and transaction models │── Services/ # Business logic (e.g. banking operations) │── Utils/ # Helper functions and validation │── Data/ # In-memory data store │── README.md # Project documentation

Code

---

## ⚙️ How to Run

### 1. Clone the repository
```bash
git clone https://github.com/mehdiarz/Sibank-consoleApp.git
cd Sibank-consoleApp
2. Open in Visual Studio or VS Code
Make sure you have the .NET SDK installed.

3. Build and run
bash
dotnet build
dotnet run
🧪 Sample Flow
Code
Welcome to Sibank!
1. Create Account
2. Deposit
3. Withdraw
4. View Balance
5. View Transactions
6. Exit
Each option leads to a guided input flow with validation and feedback.

🚀 Roadmap
Add persistent storage (e.g. JSON or SQLite)

Implement user authentication

Add support for multiple currencies

Export transaction history to file

Unit tests with xUnit

👨‍💻 Author
Developed and maintained by Mehdi Arz

📄 License
This project is licensed under the MIT License.
