# Employee Data Protection

Employee Data Protection is a robust application designed to ensure the utmost security of employee salary details by leveraging AES Format-Preserving Encryption and Secure Stateless Tokenization APIs. This comprehensive solution features a Spring Boot backend for API handling and a responsive Angular frontend, providing a user-friendly platform for managing sensitive salary information.

## Features

-   **AES Format-Preserving Encryption:** Employee Data Protection employs the Advanced Encryption Standard (AES) with Format-Preserving Encryption (FPE) to encrypt employee salary details. This ensures that the encrypted data maintains its original format, allowing for secure storage and retrieval.
    
-   **Secure Stateless Tokenization:** The application incorporates Secure Stateless Tokenization (SST) to tokenize sensitive information securely. This process generates unique tokens for each piece of sensitive data, enhancing the security of employee salary details.
    
-   **Spring Boot Backend:** The backend of Employee Data Protection is built using the Spring Boot framework, providing a scalable and efficient server-side solution. It handles API requests, interacts with the MySQL database, and ensures the overall functionality of the application.
    
-   **Angular Frontend:** The frontend of the application is developed using Angular, offering a responsive and intuitive user interface. Users can securely log in, navigate through the application, and interact with encrypted or tokenized salary information.

## Prerequisites

Before deploying Employee Data Protection, make sure the following prerequisites are met:

-   Java Development Kit (JDK) 11 or higher
-   Node.js and npm (for Angular)
-   MySQL database
-   AES Format-Preserving Encryption API access
-   Secure Stateless Tokenization API access

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

Contributions to Employee Data Protection are welcome. Please follow the [contribution guidelines](https://chat.openai.com/c/CONTRIBUTING.md) when submitting changes.

## License

This project is licensed under the MIT License.
