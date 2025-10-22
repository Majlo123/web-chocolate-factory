# Web Application - Chocolate Factory Management

This repository contains the source code for a full-stack web application for managing chocolate factories, developed as a project for the Web Programming course. The application supports multiple user roles, including Customers, Workers, Managers, and Administrators, each with a specific set of permissions and functionalities.

The project is structured as a monorepo, containing both the backend and frontend code.

## Key Features

The application provides a comprehensive set of features for managing chocolate factories and user interactions:

### For All Users (including unregistered)
* **Browse Factories:** View a list of all chocolate factories, with their name, location, logo, and average rating.
* **Search and Filter:** Perform advanced search, sorting, and filtering of factories based on name, location, chocolate type, and rating.

### For Customers
* **Registration & Login:** Register for a new account and log in to the system.
* **Shopping Cart:** Browse chocolates within a specific factory, add them to a shopping cart, adjust quantities, and place an order.
* **Purchasing:** A point-based loyalty system where customers earn points on purchases and lose points on cancellations.
* **Commenting & Rating:** Leave comments and ratings for factories after a purchase is approved by a manager.
* **Order History:** View a history of all personal orders.

### For Workers
* **Inventory Management:** Update the quantity of chocolates available in the factory they are employed in.

### For Managers
* **Factory Management:** View and manage the details of the factory they are assigned to.
* **Product Management:** Add new chocolates, and edit or delete existing ones for their factory.
* **Employee Management:** Create new Worker accounts for their factory.
* **Order Processing:** View all orders for their factory and approve or reject them.
* **Comment Moderation:** Approve or reject comments left by customers.

### For Administrators
* **System Administration:** Create new chocolate factories and assign managers to them.
* **User Management:** View a filterable and sortable list of all registered users in the system.

## Repository Structure

This is a monorepo containing both the backend and frontend applications:
## Technology Stack

* **Backend:**
    * **Framework:** Node.js (with Express) **or** Java (with JAX-RS/Jersey)
    * **Data Persistence:** All data is persisted in text files (JSON/CSV format), as per project requirements. No databases are used.
    * **Authentication:** JWT (JSON Web Tokens) or session-based authentication.

* **Frontend:**
    * **Framework:** Vue.js
    * **Styling:** CSS / SCSS (with optional libraries like Bootstrap)
    * **API Communication:** Axios or Fetch API

## Setup and Installation

### Prerequisites
* Node.js and npm (for both frontend and a Node.js backend)
* Java JDK & Maven/Gradle (for a Java backend)

### Backend Setup

1.  **Navigate to the backend directory:**
    ```bash
    cd backend
    ```
2.  **Install dependencies:**
    ```bash
    # For Node.js
    npm install
    ```
    ```bash
    # For Java (Maven)
    mvn install
    ```
3.  **Run the server:**
    ```bash
    # For Node.js
    npm start
    ```
    ```bash
    # For Java (Maven)
    mvn spring-boot:run 
    # or run the main application class from your IDE
    ```
The backend server will be running on a specified port (e.g., `http://localhost:8080`).

### Frontend Setup

1.  **Navigate to the frontend directory:**
    ```bash
    cd frontend
    ```
2.  **Install dependencies:**
    ```bash
    npm install
    ```
3.  **Configuration:**
    * Update the API endpoint URL in the configuration file to point to your running backend server.
4.  **Run the development server:**
    ```bash
    npm run serve
    ```
The frontend application will be available at `http://localhost:8081` (or another specified port).
