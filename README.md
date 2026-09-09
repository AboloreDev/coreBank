# Corebank

A terminal-based banking system built in Java to practice core object-oriented programming concepts: class design, inheritance, composition, collections, and control flow — no UI, just a console you can run and test against.

## Description

CoreBank models a simplified banking structure where a `Bank` manages multiple `Customer`s, each of whom can hold one or more `Account`s. Accounts come in two flavors — `SavingsAccount` and `CurrentAccount` — each with its own rules for withdrawals (a withdrawal limit for savings, an overdraft limit for current). Every deposit and withdrawal is logged as a `Transaction`, so each account keeps a real history rather than just a raw balance.

This project was built as a self-directed learning exercise to apply and consolidate:

- **Object-Oriented Programming** — class design, inheritance (`Account` → `SavingsAccount` / `CurrentAccount`), method overriding, encapsulation
- **Composition ("has-a" relationships)** — `Bank` has-a list of `Customer`s, `Customer` has-a list of `Account`s
- **Collections** — `ArrayList`, `LinkedList`, iteration, searching
- **Control flow** — loops, switch statements, a terminal menu-driven interface
- **Boxing/unboxing** — working with wrapper types alongside primitives in collections

## Project Structure

```
Bank
 └── List<Customer>
      Customer
       └── List<Account>
            Account (base class)
             ├── SavingsAccount
             └── CurrentAccount
                  └── LinkedList<Transaction>
```

## Features

- Open and manage multiple customers, each with multiple accounts
- Deposit and withdraw funds, with rules enforced per account type
- View transaction history per account
- Search for a customer or account
- Simple terminal menu — no external dependencies, no UI framework

## How to Run

```bash
javac Main.java
java Main
```

## Status

Actively being built as a learning project — expect incremental additions as new concepts are practiced (exception handling, interfaces, and file-based persistence are next on the list).

## Author

Built while learning Java — feedback and suggestions welcome.
