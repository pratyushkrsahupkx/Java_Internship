CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Create employees table
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data for testing
INSERT INTO employees (name, email, department, salary) VALUES
('John Doe', 'john.doe@company.com', 'IT', 75000.00),
('Jane Smith', 'jane.smith@company.com', 'HR', 65000.00),
('Mike Johnson', 'mike.johnson@company.com', 'Finance', 70000.00),
('Sarah Wilson', 'sarah.wilson@company.com', 'IT', 80000.00),
('David Brown', 'david.brown@company.com', 'Marketing', 60000.00);

-- Verify table structure
DESCRIBE employees;

-- Display sample data
SELECT * FROM employees;