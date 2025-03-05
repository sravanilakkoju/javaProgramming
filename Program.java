import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Employee{
    
    public String employeeId;
    public String name;
    public String department;
    public String contactNumber;


    public Employee(String empId, String name, String dept, String contactNumber) {
        this.contactNumber = contactNumber;
        this.employeeId = empId;
        this.name = name;
        this.department = dept;
    }

    public String toString(){
        return "Name: "+ this.name+" Employee ID: "+ this.employeeId+" Department: "+this.department+" Contact: "+this.contactNumber;
    }
}

class EmployeeManager{
    private HashMap<String, Employee> employees;

    public EmployeeManager(){
        this.employees = new HashMap<>();
    }

    public String addEmployee(Employee emp){
        if( this.employees.containsKey(emp.employeeId)){
            return "EMployee with the given ID: "+ emp.employeeId+" already exists";
        }
        this.employees.put(emp.employeeId, emp);
        return "Employee Added - Success!";
    }

    public String addEmployee(String empId, String name, String dept, String contactNumber){
        if( this.employees.containsKey(empId)){
            return "EMployee with the given ID: "+ empId+" already exists";
        }
        Employee emp = new Employee(empId, name, dept, contactNumber);
        this.employees.put(emp.employeeId, emp);
        return "Employee Added - Success! "+ emp.toString();
    }

     public String removeEmployee(String empId){
        if( this.employees.containsKey(empId)){
            this.employees.remove(empId);
            return "EMployee with the given ID: "+ empId+" Deleted";
        }
        return "Employee Not FOund!";
    }

    public Employee getEmployee(String empId){
        if( this.employees.containsKey(empId)){
            return employees.get(empId);
        }
        return null;
    }


    public List<Employee> getAllEmployees(){
        return Arrays.asList( this.employees.values().toArray(new Employee[]{}));
    }

    public String updateEmployee(String empId, String name, String dept, String contactNumber) {
        if( this.employees.containsKey(empId)){
            Employee e = this.employees.get(empId);
            e.contactNumber = contactNumber;
            e.department = dept;
            e.name = name;
            return "EMployee with the given ID: "+ empId+" Updated";
        }
        return "EMployee with the given ID: "+ empId+" Not Found";
    }



}




public class Program {

public static void main(String args[]){
    try{
        Scanner scanner = new Scanner(System.in);
        int choice;

        EmployeeManager empManager = new EmployeeManager();

        while(true){
            System.out.println("            Employee Management System            ");
            System.out.println("Choose your Action:");
            System.out.println("1. Add Employee");
            System.out.println("2. Update");
            System.out.println("3. Remove");
            System.out.println("4. List of employees");

            System.out.print("Enter your Choice: ");
            choice = scanner.nextInt();

            switch(choice){
                case 1:
                System.out.println("Provide Name, Employee ID, Department, MObile number in each line");scanner.nextLine();
                
                String name = scanner.nextLine(), empId = scanner.nextLine(), dept = scanner.nextLine(), mob = scanner.nextLine();
                System.out.println(empManager.addEmployee(empId, name, dept, mob));
                break;

                case 2:
                System.out.println("Provide EmployeeID in next line");scanner.nextLine();
                String emp = scanner.nextLine();
                Employee employee = empManager.getEmployee(emp);
                if(employee== null){
                    System.out.println("EMployee NOT FOUND in the Manager");
                }
                else{
                    System.out.println("Employee Details Found:\n"+employee.toString());
                    System.out.println("Provide Name, Department, MObile number to Update in each line");scanner.nextLine();
                    String eName = scanner.nextLine();
                    String department = scanner.nextLine();
                    String contactNumber = scanner.nextLine();
                    String op = empManager.updateEmployee(emp,eName, department, contactNumber );
                    System.out.println(op);
                }
                break;

                case 3:
                System.out.println("Provide EmployeeID in next line");scanner.nextLine();
                String emplId = scanner.nextLine();
                System.out.println(empManager.removeEmployee(emplId));
                break;

                case 4:
                    System.out.println("All EMployees List :: ");
                List<Employee> emps = empManager.getAllEmployees();
                
                for (Employee empl : emps) {
                    System.out.println(empl.toString());
                }
                break;
                default: scanner.close(); return;
            }
        }
    }
    catch(Exception e){
        System.out.println(e.toString());
    }
    finally{

    }
}
    
}
