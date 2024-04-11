CREATE DATABASE IF NOT EXISTS employee_payroll;

USE employee_payroll;

CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(50),
    hourly_rate DECIMAL(10, 2),
    salary DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS salaries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    salary DECIMAL(10, 2),
    transaction_date DATE
);

CREATE TABLE IF NOT EXISTS payroll_transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    amount DECIMAL(10, 2),
    transaction_date DATE
);

-- Insert dummy values into the employees table
INSERT INTO employees (name, type, hourly_rate, salary) VALUES
('John Doe', 'Full-time', 25.00, 0),
('Jane Smith', 'Part-time', 15.00, 0),
('Michael Johnson', 'Full-time', 30.00, 0),
('Emily Brown', 'Part-time', 18.00, 0),
('David Wilson', 'Full-time', 28.00, 0),
('Sarah Taylor', 'Part-time', 20.00, 0),
('James Martinez', 'Full-time', 32.00, 0),
('Jennifer Anderson', 'Part-time', 17.00, 0),
('Robert Lee', 'Full-time', 26.00, 0),
('Lisa Garcia', 'Part-time', 19.00, 0),
('William Rodriguez', 'Full-time', 29.00, 0),
('Mary Hernandez', 'Part-time', 16.00, 0),
('John Jackson', 'Full-time', 31.00, 0),
('Patricia Thomas', 'Part-time', 21.00, 0),
('Christopher Young', 'Full-time', 27.00, 0);

-- Insert dummy values into the salaries table
INSERT INTO salaries (employee_id, salary, transaction_date) VALUES
(1, 2500.00, '2024-04-01'),
(2, 800.00, '2024-04-01'),
(3, 3000.00, '2024-04-01'),
(4, 720.00, '2024-04-01'),
(5, 2800.00, '2024-04-01'),
(6, 850.00, '2024-04-01'),
(7, 3200.00, '2024-04-01'),
(8, 680.00, '2024-04-01'),
(9, 2600.00, '2024-04-01'),
(10, 760.00, '2024-04-01');

-- Insert dummy values into the payroll_transactions table
INSERT INTO payroll_transactions (employee_id, amount, transaction_date) VALUES
(1, 2500.00, '2024-04-01'),
(2, 800.00, '2024-04-01'),
(3, 3000.00, '2024-04-01'),
(4, 720.00, '2024-04-01'),
(5, 2800.00, '2024-04-01'),
(6, 850.00, '2024-04-01'),
(7, 3200.00, '2024-04-01'),
(8, 680.00, '2024-04-01'),
(9, 2600.00, '2024-04-01'),
(10, 760.00, '2024-04-01');
