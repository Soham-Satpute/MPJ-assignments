package Training.Training;
import java.util.Scanner;

// Base class Employee
class Employee {
    protected String name;
    protected int empId;
    protected double baseSalary;

    // Constructor
    public Employee(String name, int empId, double baseSalary) {
        this.name = name;
        this.empId = empId;
        this.baseSalary = baseSalary;
    }

    // Method to display salary
    public void DisplaySalary() {
        System.out.println("\n========================================");
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + empId);
        System.out.println("Base Salary: Rs. " + baseSalary);
    }
}

// Derived class - FullTimeEmployee
class FullTimeEmployee extends Employee {
    private double hikePercentage = 50.0; // 50% hike
    private double salaryAfterHike;

    // Constructor
    public FullTimeEmployee(String name, int empId, double baseSalary) {
        super(name, empId, baseSalary);
    }

    // Method to calculate salary with hike
    public void CalculateSalary() {
        salaryAfterHike = baseSalary + (baseSalary * hikePercentage / 100);
    }

    // Overriding DisplaySalary to show before and after hike
    @Override
    public void DisplaySalary() {
        System.out.println("\n========================================");
        System.out.println("FULL-TIME EMPLOYEE DETAILS");
        System.out.println("========================================");
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + empId);
        System.out.println("Salary Before Hike: Rs. " + baseSalary);
        System.out.println("Hike Percentage: " + hikePercentage + "%");
        System.out.println("Salary After Hike: Rs. " + salaryAfterHike);
        System.out.println("========================================");
    }
}

// Derived class - InternEmployee
class InternEmployee extends Employee {
    private double hikePercentage = 25.0; // 25% hike
    private double salaryAfterHike;

    // Constructor
    public InternEmployee(String name, int empId, double baseSalary) {
        super(name, empId, baseSalary);
    }

    // Method to calculate salary with hike
    public void CalculateSalary() {
        salaryAfterHike = baseSalary + (baseSalary * hikePercentage / 100);
    }

    // Overriding DisplaySalary to show before and after hike
    @Override
    public void DisplaySalary() {
        System.out.println("\n========================================");
        System.out.println("INTERN EMPLOYEE DETAILS");
        System.out.println("========================================");
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + empId);
        System.out.println("Salary Before Hike: Rs. " + baseSalary);
        System.out.println("Hike Percentage: " + hikePercentage + "%");
        System.out.println("Salary After Hike: Rs. " + salaryAfterHike);
        System.out.println("========================================");
    }
}

// Main class to demonstrate hierarchical inheritance
public class EmployeeInheritanceDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*** HIERARCHICAL INHERITANCE DEMONSTRATION ***");
        System.out.println("*** Employee Salary Calculation System ***\n");

        // Input for FullTimeEmployee
        System.out.println("==== FULL-TIME EMPLOYEE ====");
        System.out.print("Enter Full-Time Employee Name: ");
        String fullTimeName = scanner.nextLine();

        System.out.print("Enter Full-Time Employee ID: ");
        int fullTimeId = scanner.nextInt();

        System.out.print("Enter Full-Time Employee Base Salary: ");
        double fullTimeSalary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Creating FullTimeEmployee object
        FullTimeEmployee fullTimeEmp = new FullTimeEmployee(fullTimeName, fullTimeId, fullTimeSalary);
        System.out.println("\nDisplaying Full-Time Employee Details Before Hike:");
        fullTimeEmp.DisplaySalary(); // Display before hike

        System.out.println("\nCalculating salary with 50% hike...");
        fullTimeEmp.CalculateSalary(); // Calculate salary with hike
        System.out.println("Displaying Full-Time Employee Details After Hike:");
        fullTimeEmp.DisplaySalary(); // Display after hike

        // Input for InternEmployee
        System.out.println("\n\n==== INTERN EMPLOYEE ====");
        System.out.print("Enter Intern Employee Name: ");
        String internName = scanner.nextLine();

        System.out.print("Enter Intern Employee ID: ");
        int internId = scanner.nextInt();

        System.out.print("Enter Intern Employee Base Salary: ");
        double internSalary = scanner.nextDouble();

        // Creating InternEmployee object
        InternEmployee internEmp = new InternEmployee(internName, internId, internSalary);
        System.out.println("\nDisplaying Intern Employee Details Before Hike:");
        internEmp.DisplaySalary(); // Display before hike

        System.out.println("\nCalculating salary with 25% hike...");
        internEmp.CalculateSalary(); // Calculate salary with hike
        System.out.println("Displaying Intern Employee Details After Hike:");
        internEmp.DisplaySalary(); // Display after hike

        System.out.println("\n*** End of Program ***\n");

        scanner.close();
    }
}
