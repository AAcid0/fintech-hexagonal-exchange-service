> **An in-memory, thread-safe transactional core built with Clean Architecture.**

## 🌌 The Mission (Context)

**Nexus-66** has become the central trading hub between Earth, Mars, and Venus. Due to high latency in interplanetary communications, the central banking system has collapsed.

**The Objective:** Develop a robust **Emergency Transactional Core (MVP)** that operates 100% in-memory to manage multi-currency digital wallets and enable real-time currency exchange between different species, applying dynamic business rules and ensuring data integrity under high concurrency.

---

## 🛠️ Tech Stack & Key Concepts

* **Language:** Java 17+
* **Framework:** Spring Boot 3
* **Architecture:** Hexagonal / Clean Architecture (Strict separation of concerns).
* **Persistence:** In-Memory (using `ConcurrentHashMap` for thread safety).
* **Validation:** Jakarta Validation (Constraints).
* **Tools:** Lombok, Maven/Gradle.

---

## 💼 Business Rules

### 1. Trader Registration & Welcome Bonuses
The system allows the registration of traders from different species. Upon registration, the system automatically assigns a **Welcome Bonus** based on the trader's origin:

| Species | Base Currency | Welcome Bonus |
| :--- | :--- | :--- |
| **HUMAN** 🌎 | `USD` | **1,000.00** USD |
| **MARTIAN** 👽 | `MARS_COIN` | **500.00** MARS_COIN |
| **VENUSIAN** 👾 | `VENUS_COIN` | **0.00** (Austerity Policy) |

### 2. The Exchange Engine
Traders can exchange any currency. The system calculates the exchange rate and applies a dynamic **Commission Fee** based on the trader's species (**Strategy Pattern**).

**Market Exchange Rates:**
* `1.00 MARS_COIN` = `1.50 USD`
* `1.00 VENUS_COIN` = `3.00 USD`

**Fee Policies (Strategy):**
* **Humans:** 5% fee on the source amount.
* **Martians:** 2% fee on the source amount.
* **Venusians:** 0% fee (Tax-exempt).

---