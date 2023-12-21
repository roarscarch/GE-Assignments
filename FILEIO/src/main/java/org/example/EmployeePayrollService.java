package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeePayrollService {
    static String filePath = "employee_payroll.csv";
    public static void writeToFile(List<Employee> employeeList){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))){
            for (Employee employee : employeeList){
                writer.println(employee.toString());
            }
            System.out.println("Data Added");
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static List<Employee> readDataFromFile(){
        List<Employee> employeeList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine())!=null){
                String[] parts = line.split(",");
                if (parts.length == 3){
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double salary = Double.parseDouble(parts[2]);
                    employeeList.add(new Employee(id,name,salary));
                }
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return employeeList;
    }

    public static int countEntries(){
        int count = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while(reader.readLine()!=null){
                count++;
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return count;
    }

    public static void sortByName(){
        List<Employee> employeeList = readDataFromFile();
        employeeList.sort(Comparator.comparing(Employee::getName));
        employeeList.forEach(System.out::println);
    }

    public static void sortBySalary(){
        List<Employee> employeeList = readDataFromFile();
        employeeList.sort(Comparator.comparing(Employee::getSalary));
        employeeList.forEach(System.out::println);
    }
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"Sample",50000.0));
        employeeList.add(new Employee(2,"Abc",60000.0));
        employeeList.add(new Employee(3,"AAAAA",40000.0));

        writeToFile(employeeList);
        System.out.println(readDataFromFile());
        System.out.println("Number of entries: " +countEntries());

        System.out.println("Sort By Name\n");
        sortByName();

        System.out.println("Sort By Salary");
        sortBySalary();
    }
}
