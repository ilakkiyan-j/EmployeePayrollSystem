package proj;

import java.util.Scanner;
import java.sql.*;

public class EmployeePayrollSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_payroll";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin"; // Replace with your MySQL password

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            createTables();

            int choice;
            System.out.println("Employee Payroll System");
            System.out.print("1. Add Employee  ");
            System.out.print("2. Update Salary  ");
            System.out.print("3. Process Payroll  ");
            System.out.print("4. View Employees  ");
            System.out.println("0. Exit");
            
            do {
            	 System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.print("Enter your choice: ");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        updateSalary();
                        break;
                    case 3:
                        processPayroll();
                        break;
                    case 4:
                        viewEmployees();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTables() throws SQLException {
        String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "type VARCHAR(50)," +
                "hourly_rate DECIMAL(10, 2)," +
                "salary DECIMAL(10, 2))";
        String createSalariesTable = "CREATE TABLE IF NOT EXISTS salaries (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "employee_id INT," +
                "salary DECIMAL(10, 2)," +
                "transaction_date DATE)";
        String createPayrollTransactionsTable = "CREATE TABLE IF NOT EXISTS payroll_transactions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "employee_id INT," +
                "amount DECIMAL(10, 2)," +
                "transaction_date DATE)";
        statement.executeUpdate(createEmployeesTable);
        statement.executeUpdate(createSalariesTable);
        statement.executeUpdate(createPayrollTransactionsTable);
    }

    private static void addEmployee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee type (e.g., Full-time, Part-time): ");
        String type = scanner.nextLine();
        System.out.print("Enter hourly rate: ");
        double hourlyRate = scanner.nextDouble();
        String query = "INSERT INTO employees (name, type, hourly_rate, salary) VALUES (?, ?, ?, 0)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, type);
        preparedStatement.setDouble(3, hourlyRate);
        preparedStatement.executeUpdate();
        System.out.println("Employee added successfully.");
    }

    private static void updateSalary() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();
        String query = "UPDATE employees SET salary = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, salary);
        preparedStatement.setInt(2, employeeId);
        preparedStatement.executeUpdate();
        System.out.println("Salary updated successfully.");
    }

    private static void processPayroll() throws SQLException {
    	 String query = "SELECT id, hourly_rate FROM employees";
    	    resultSet = statement.executeQuery(query);
    	    
    	    while (resultSet.next()) {
    	        int employeeId = resultSet.getInt("id");
    	        double hourlyRate = resultSet.getDouble("hourly_rate");
    	        
    	        double hoursWorked = 40; // Assuming 40 hours per week
    	        double salary = hourlyRate * hoursWorked;
    	        
    	        String updateQuery = "UPDATE employees SET salary = ? WHERE id = ?";
    	        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
    	        updateStatement.setDouble(1, salary);
    	        updateStatement.setInt(2, employeeId);
    	        updateStatement.executeUpdate();
    	        System.out.println("Employee ID: " + employeeId + ", Updated Salary: " + salary);
    	    }
    	    
    	    System.out.println("Payroll processed successfully.");
    }

    private static void viewEmployees() throws SQLException {
        String query = "SELECT * FROM employees";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id") +
                    ", Name: " + resultSet.getString("name") +
                    ", Type: " + resultSet.getString("type") +
                    ", Hourly Rate: " + resultSet.getDouble("hourly_rate") +
                    ", Salary: " + resultSet.getDouble("salary"));
        }
    }
}
