# Secret Key Encryption Lab

## 📌 Overview
This repository contains materials related to **Secret Key Encryption**, including encryption algorithms, encryption modes, padding techniques, and cryptographic programming using **Java**.

## 📂 Project Contents
- **SecretKeyEncryption_LabReport.docx** - A detailed report covering encryption techniques and observations.
- **Main.java** - Java implementation for brute-force key searching and AES-128-CBC encryption.
- **CS334-CourseProject.pdf** - The project description and guidelines.

## 🔑 Tasks Implemented
### ✅ Task 2: Encryption using Different Ciphers and Modes
- Implemented AES-128-CBC, AES-128-CTR, and Blowfish encryption.
- Compared different encryption modes (CBC vs. CTR).
- Used **OpenSSL** for testing encryption and decryption.

### ✅ Task 3: Encryption Mode - ECB vs. CBC
- Encrypted images using **AES-128-ECB** and **AES-128-CBC** to demonstrate vulnerabilities in ECB mode.
- Used GHex to analyze the encryption structure.

### ✅ Task 7: Programming Using the Crypto Library in Java
- Implemented a **dictionary attack** to brute-force AES keys.
- Used **Java Cryptography Architecture (JCA)** for encryption and decryption.
- Compared encrypted outputs to identify correct keys.

## 🛠️ How to Run the Java Code
1. Install **Java JDK 8+**:
   ```bash
   sudo apt-get install openjdk-8-jdk
📌 References
SEED Labs - Cryptography Lab
Java Cryptography Architecture (JCA)
