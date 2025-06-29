# Employee Data Protection

Employee Data Protection is a robust application designed to ensure the utmost security of employee salary details by leveraging AES Format-Preserving Encryption and Secure Stateless Tokenization APIs. This comprehensive solution features a Spring Boot backend for API handling and a responsive Angular frontend, providing a user-friendly platform for managing sensitive salary information.

## Features

-   **AES-256 Encryption with Salted Key Derivation:** Employee salary data is protected using AES-256 encryption in CBC mode with PKCS5 padding. The encryption key is derived using PBKDF2 with HMAC-SHA256, incorporating a salt for enhanced security. This ensures strong symmetric encryption for sensitive fields like salary.
    
-   **Format-Preserving Encryption (FPE):** The application uses Format-Preserving Encryption (FPE) via the FF3 algorithm to securely encrypt structured numeric data such as salaries while retaining their original format. This allows encrypted values to remain compatible with database constraints and data validation rules.
    
-   **Spring Boot Backend:** Built on the Spring Boot framework, the backend handles API requests, manages encryption/decryption logic, and interacts with the MySQL database efficiently. It serves as the core processing engine for secure employee data handling.
    
-   **Angular Frontend:** The frontend is developed using Angular, offering a responsive, modern user interface. It allows users to log in, interact with encrypted salary information, and manage employee data in a secure and intuitive way.

## Prerequisites

Before deploying Employee Data Protection, make sure the following prerequisites are met:

-   Java Development Kit (JDK) 11 or higher
-   Node.js and npm (for Angular)
-   MySQL database

## Installation

1.  **Backend:**
    
    -   Clone the backend repository:
        
        `git clone https://github.com/yourusername/EmployeeDataProtection-backend.git` 
        
    -   Navigate to the backend directory and build the project:
        
        `cd EmployeeDataProtection-backend
        ./mvnw clean install` 
        
    -   Run the backend server:
        
        `./mvnw spring-boot:run` 
        
2.  **Frontend:**
    
    -   Clone the frontend repository:
        
        `git clone https://github.com/yourusername/EmployeeDataProtection-frontend.git` 
        
    -   Navigate to the frontend directory and install dependencies:
        
        `cd EmployeeDataProtection-frontend
        npm install` 
        
    -   Run the frontend application:
        
        `ng serve` 
        
3.  Access the application at `http://localhost:4200` (or the specified port).
    

## Usage

1.  Register an account and log in with the appropriate credentials.
2.  Based on the assigned role, navigate through the application to view or manage salary details securely.
3.  The application ensures that sensitive information is encrypted or tokenized, providing a secure environment for salary data.

## Contributing

Contributions to Employee Data Protection are welcome.

## License

This project is licensed under the MIT License.
