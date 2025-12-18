# 🏦 Banking Service - Skypay Technical Test

> Implementation of a simple banking system with deposit, withdrawal, and statement printing capabilities.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/HoucineBousfiha/skypay-banking-test)
[![Tests](https://img.shields.io/badge/tests-14%2F14%20passing-success.svg)](https://github.com/HoucineBousfiha/skypay-banking-test)
[![Coverage](https://img.shields.io/badge/coverage-64%25-yellow.svg)](https://github.com/HoucineBousfiha/skypay-banking-test)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-blue.svg)](https://maven.apache.org/)

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Code Coverage](#code-coverage)
- [Project Structure](#project-structure)
- [Design Decisions](#design-decisions)
- [Technical Requirements](#technical-requirements)
- [Author](#author)

---

## 🎯 About the Project

This project is a solution to **Technical Test 1** for the Java Developer position at Skypay. It implements the core functions of a banking system:

- **Deposit money** into an account
- **Withdraw money** from an account
- **Print transaction statements** in reverse chronological order

The implementation follows software craftsmanship principles with clean code, comprehensive testing, and SOLID design principles.

---

## ✨ Features

- ✅ **Deposit & Withdrawal Operations** - Manage account transactions
- ✅ **Transaction History** - Track all operations with dates and balances
- ✅ **Statement Printing** - Display transactions in reverse chronological order
- ✅ **Exception Handling** - Validates inputs and prevents invalid operations
- ✅ **Comprehensive Testing** - 14 unit and acceptance tests with 64% code coverage
- ✅ **SOLID Principles** - Clean architecture with separation of concerns
- ✅ **Demo Application** - Interactive demonstration of the banking service

---

## 🚀 Getting Started

### Prerequisites

Before running this project, ensure you have:

- **JDK 17** or higher ([Download here](https://adoptium.net/))
- **Maven 3.6+** ([Download here](https://maven.apache.org/download.cgi))
- **Git** ([Download here](https://git-scm.com/downloads))

Verify installations:

```bash
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
git --version    # Should show Git version
```

### Installation

1. **Clone the repository**

```bash
   git clone https://github.com/HoucineBousfiha/skypay-banking-test.git
   cd skypay-banking-test
```

2. **Build the project**

```bash
   mvn clean install
```

3. **Verify installation**

```bash
   mvn test
```

You should see: `Tests run: 14, Failures: 0, Errors: 0, Skipped: 0`

---

## 💻 Usage

### Running the Demo Application

```bash
mvn compile exec:java -Dexec.mainClass="ma.skypay.banking.BankingDemo"
```

**Expected Output:**

```
==============================================
   Banking Service - Skypay Technical Test
==============================================

Executing acceptance test scenario...

1. Depositing 1000...
2. Depositing 2000...
3. Withdrawing 500...

--- Account Statement ---

Date       || Amount || Balance
18/12/2025 || -500 || 2500
18/12/2025 || 2000 || 3000
18/12/2025 || 1000 || 1000

==============================================
Current Balance: 2500
==============================================
```

### Using the Banking Service in Code

```java
import ma.skypay.banking.domain.Account;

public class Example {
    public static void main(String[] args) {
        // Create a new account
        Account account = new Account();

        // Perform operations
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        // Print statement
        account.printStatement();

        // Check balance
        System.out.println("Balance: " + account.getBalance()); // 2500
    }
}
```

---

## 🧪 Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=AccountTest
mvn test -Dtest=BankingAcceptanceTest
```

### Run Tests with Detailed Output

```bash
mvn test -X
```

### Test Results Summary

| Test Suite                | Tests  | Passed    | Failed   | Skipped  |
| ------------------------- | ------ | --------- | -------- | -------- |
| **AccountTest**           | 10     | ✅ 10     | ❌ 0     | ⏭️ 0     |
| **BankingAcceptanceTest** | 4      | ✅ 4      | ❌ 0     | ⏭️ 0     |
| **Total**                 | **14** | **✅ 14** | **❌ 0** | **⏭️ 0** |

---

## 📊 Code Coverage

### Generate Coverage Report

```bash
mvn clean test jacoco:report
```

### View Coverage Report

Open `target/site/jacoco/index.html` in your browser.

### Coverage Summary

| Package                     | Coverage | Status       |
| --------------------------- | -------- | ------------ |
| `ma.skypay.banking.domain`  | **75%**  | ✅ Excellent |
| `ma.skypay.banking.util`    | **100%** | ✅ Perfect   |
| `ma.skypay.banking.service` | **100%** | ✅ Perfect   |
| **Overall**                 | **64%**  | ✅ Good      |

> **Note:** The `BankingDemo` class (main entry point) is not covered by tests, which is expected. The core business logic has excellent coverage.

---

## 📁 Project Structure

```
banking-service/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── ma/skypay/banking/
│   │           ├── domain/                    # Business entities
│   │           │   ├── Account.java           # Account implementation
│   │           │   └── Transaction.java       # Transaction entity
│   │           │
│   │           ├── service/                   # Interfaces
│   │           │   └── AccountService.java    # Public interface
│   │           │
│   │           ├── util/                      # Utilities
│   │           │   └── StatementPrinter.java  # Statement formatting
│   │           │
│   │           └── BankingDemo.java           # Demo application
│   │
│   └── test/
│       └── java/
│           └── ma/skypay/banking/
│               ├── acceptance/                # Acceptance tests
│               │   └── BankingAcceptanceTest.java
│               │
│               └── domain/                    # Unit tests
│                   └── AccountTest.java
│
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
└── .gitignore                                 # Git ignore rules
```

---

## 🏗️ Design Decisions

### Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

1. **Domain Layer** (`domain/`)

   - Contains core business logic and entities
   - `Account` implements the business rules for deposits, withdrawals, and balance management
   - `Transaction` is an immutable entity representing a single operation

2. **Service Layer** (`service/`)

   - Defines the public contract through `AccountService` interface
   - Enforces interface segregation principle

3. **Utility Layer** (`util/`)
   - `StatementPrinter` handles all statement formatting logic
   - Follows Single Responsibility Principle
   - Makes the code more testable and maintainable

### Key Design Choices

#### ✅ **Immutable Transaction Objects**

```java
public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final int balance;
}
```

- Prevents accidental modification
- Thread-safe by design
- Simplifies reasoning about code

#### ✅ **Dependency Injection**

```java
public Account(StatementPrinter statementPrinter) {
    this.statementPrinter = statementPrinter;
}
```

- Enables easy testing with mocks
- Reduces coupling between components
- Follows Dependency Inversion Principle

#### ✅ **ArrayList for Storage**

- As per requirements: "Do not use repositories. Use ArrayLists"
- Provides O(1) append operations for new transactions
- Simple and efficient for the use case

#### ✅ **Exception Handling**

```java
if (amount <= 0) {
    throw new IllegalArgumentException("Deposit amount must be positive");
}
if (balance < amount) {
    throw new IllegalStateException("Insufficient funds");
}
```

- Validates all inputs
- Prevents invalid states
- Clear, descriptive error messages

#### ✅ **Test-Driven Development (TDD)**

- Tests written alongside implementation
- High test coverage (64% overall, 75% domain layer)
- Both unit tests and acceptance tests included

---

## 📋 Technical Requirements

This implementation satisfies all technical requirements:

| Requirement                   | Implementation                                    | Status |
| ----------------------------- | ------------------------------------------------- | ------ |
| **Interface Implementation**  | `Account implements AccountService`               | ✅     |
| **Cannot change interface**   | Interface unchanged                               | ✅     |
| **Exception Handling**        | Validates all inputs, prevents invalid operations | ✅     |
| **Performance Consideration** | Uses ArrayList (O(1) append)                      | ✅     |
| **Well-tested code**          | 14 tests, 64% coverage                            | ✅     |
| **No repositories**           | Uses ArrayList                                    | ✅     |
| **Uses int for amounts**      | All amounts are `int` type                        | ✅     |
| **Simplest solution**         | Clean, readable, maintainable code                | ✅     |

### Acceptance Test Scenario

✅ **Given** a client makes a deposit of 1000 on 10-01-2012  
✅ **And** a deposit of 2000 on 13-01-2012  
✅ **And** a withdrawal of 500 on 14-01-2012  
✅ **When** they print their bank statement  
✅ **Then** they would see:

```
Date       || Amount || Balance
14/01/2012 || -500   || 2500
13/01/2012 || 2000   || 3000
10/01/2012 || 1000   || 1000
```

---

## 🛠️ Technologies Used

- **Java 17** - Programming language
- **Maven 3.9+** - Build automation and dependency management
- **JUnit 5** (5.10.1) - Testing framework
- **AssertJ** (3.24.2) - Fluent assertions for tests
- **Mockito** (5.8.0) - Mocking framework (available if needed)
- **JaCoCo** (0.8.11) - Code coverage analysis

---

## 👤 Author

**Houcine Bousfiha**

- 📧 Email: [bousfihahoucine9@gmail.com]
- 💼 LinkedIn: [https://www.linkedin.com/in/houcine-bousfiha/]
- 🐱 GitHub: [@HoucineBousfiha](https://github.com/HoucineBousfiha)

**Position:** Candidate for Java Developer (Spring Boot / React) at Skypay  
**Submission Date:** December 19, 2024

---

## 📝 Notes

### Development Approach

This project was developed using:

- **Test-Driven Development (TDD)** - Tests written first
- **Clean Code principles** - Readable, maintainable code
- **SOLID principles** - Good object-oriented design
- **Git best practices** - Meaningful commit messages

### Future Enhancements

If this were a production system, potential improvements would include:

- 🔐 Thread-safety for concurrent transactions
- 💾 Persistent storage (database integration)
- 💰 Use `BigDecimal` instead of `int` for precise monetary calculations
- 🔒 Authentication and authorization
- 📅 Custom date injection for testing specific dates
- 🌍 Internationalization (i18n) for multi-language support
- 📊 More detailed reporting and analytics

---

## 📄 License

This project is submitted as part of Skypay's technical recruitment process.

---

## 🙏 Acknowledgments

Thank you to the Skypay recruitment team for this interesting technical challenge. The kata-style problem provided an excellent opportunity to demonstrate software craftsmanship principles and clean code practices.

---

<div align="center">

**Made with ❤️ for Skypay**

[⬆ Back to Top](#-banking-service---skypay-technical-test)

</div>
