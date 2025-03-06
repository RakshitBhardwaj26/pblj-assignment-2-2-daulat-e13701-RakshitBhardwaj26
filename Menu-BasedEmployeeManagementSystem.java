import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name, designation;
    double salary;

    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

class EmployeeManagement {
    static final String FILE = "employees.dat";

    static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Designation: ");
        String des = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        
        List<Employee> employees = readEmployees();
        employees.add(new Employee(id, name, des, salary));
        saveEmployees(employees);
        System.out.println("Employee Added!");
    }

    static List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            employees = (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous records found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return employees;
    }

    static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void displayEmployees() {
        List<Employee> employees = readEmployees();
        if (employees.isEmpty()) System.out.println("No Employees Found.");
        else employees.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Employee\n2. Display All\n3. Exit\nChoose: ");
            int choice = sc.nextInt();
            if (choice == 1) addEmployee();
            else if (choice == 2) displayEmployees();
            else if (choice == 3) break;
            else System.out.println("Invalid Choice.");
        }
        sc.close();
    }
}
