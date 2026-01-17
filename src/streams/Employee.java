package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {

    private int id;

    private String name;

    private double salary;

    private String department;

    // No-arg constructor

    public Employee() {

    }

    // Parameterized constructor

    public Employee(int id, String name, double salary, String department) {

        this.id = id;

        this.name = name;

        this.salary = salary;

        this.department = department;

    }

    // Getters and Setters

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public double getSalary() {

        return salary;

    }

    public void setSalary(double salary) {

        this.salary = salary;

    }

    public String getDepartment() {

        return department;

    }

    public void setDepartment(String department) {

        this.department = department;

    }

    // toString() method

    @Override

    public String toString() {

        return "Employee{" +

                "id=" + id +

                ", name='" + name + '\'' +

                ", salary=" + salary +

                ", department='" + department + '\'' +

                '}';

    }

    public static void main(String[] args) {
       List<Employee> employeeList= Arrays.asList(

                new Employee(1, "A", 30000, "IT"),

                new Employee(2, "B", 25000, "IT"),

                new Employee(3, "C", 20000, "IT"),

                new Employee(4, "D", 15000, "IT"),

                new Employee(5, "E", 10000, "IT"),

                new Employee(6, "F", 5000,  "IT"),

                new Employee(7, "G", 40000, "HR"),

                new Employee(8, "H", 35000, "HR"),

                new Employee(9, "I", 30000, "HR"),

                new Employee(10,"J", 25000, "HR"),

                new Employee(11,"K", 20000, "HR"),

                new Employee(12,"L", 15000, "HR")

        );

        // ═══════════════════════════════════════════════════════════════════
        // Get least 5 employees by salary per department
        // ═══════════════════════════════════════════════════════════════════

        Map<String, List<Employee>> least5PerDept = employeeList.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).limit(5).collect(Collectors.toList()))
        ));
        /*Map<String, List<Employee>> least5PerDept = employeeList.stream()
                // Group employees by department
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        // For each department, collect to a list after sorting and limiting
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        // Sort by salary ascending (least first)
                                        .sorted(Comparator.comparingDouble(Employee::getSalary))
                                        // Take only 5 employees
                                        .limit(5)
                                        .collect(Collectors.toList())
                        )
                ));*/

        // Print the result (before increment)
        System.out.println("═".repeat(50));
        System.out.println("BEFORE 10% INCREMENT");
        System.out.println("═".repeat(50));
        least5PerDept.forEach((department, employees) -> {
            System.out.println("\n" + department + " - Least 5 Salaries:");
            System.out.println("─".repeat(40));
            employees.forEach(emp -> 
                    System.out.printf("  %-10s : %.2f%n", emp.getName(), emp.getSalary()));
        });

        // ═══════════════════════════════════════════════════════════════════
        // Increase salary by 10% for the least 5 employees per department
        // ═══════════════════════════════════════════════════════════════════
        
        least5PerDept.values().stream()
                .flatMap(List::stream)  // Flatten all employees from all departments
                .forEach(emp -> emp.setSalary(emp.getSalary() * 1.10));  // 10% increment

        // Print the result (after increment)
        System.out.println("\n" + "═".repeat(50));
        System.out.println("AFTER 10% INCREMENT");
        System.out.println("═".repeat(50));
        least5PerDept.forEach((department, employees) -> {
            System.out.println("\n" + department + " - Least 5 Salaries (Updated):");
            System.out.println("─".repeat(40));
            employees.forEach(emp -> 
                    System.out.printf("  %-10s : %.2f%n", emp.getName(), emp.getSalary()));
        });
       employeeList.stream().collect(Collectors.toUnmodifiableList());
    }

}