import java.sql.*;
import java.util.*;

public class EmployeeManagement {
	static final String DB_URL = "jdbc:mysql://localhost:3306/focalcxm?user=root";
	static final String USER = "admin";
	static final String PASS = "admin";
	static Connection connection = null;
	
	public static void main(String[] args) {
		//Initializing Database connection...
		try {
			connection = DriverManager.getConnection(DB_URL,USER,PASS); 
		}catch(SQLException e){
			e.printStackTrace();
		}
		//Writing main display functions...
		try {
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.println("Enter your choice: ");
	            System.out.println("1. Enter a New Employee");
	            System.out.println("2. Update an Employee");
	            System.out.println("3. Remove an Employee");
	            System.out.println("4. List of All Employees");
	            System.out.println("5. Exit..");
	            int choice = scanner.nextInt();
	            
	            switch(choice) {
	            case 1:
	            	 System.out.println("Enter the details");
		           	 System.out.println("Employee Id: ");
		           	 scanner.nextLine();
		           	 int Id = scanner.nextInt();	 
		           	 System.out.println("Employee Name: ");
		           	 scanner.nextLine();
		           	 String Name = scanner.nextLine();
		           	 System.out.println("Employee Contact: ");	 
		           	 String Contact = scanner.nextLine();	 
		           	 System.out.println("Employee Email: ");
		           	 String Email = scanner.nextLine();
		           	 System.out.println("Branch: ");
		           	 String Branch = scanner.nextLine();
		           	 AddEmployee(connection,Id,Name,Contact,Email,Branch);	 
		           	 break;
	            case 2:
	            	 System.out.println("enter the details for updating");
	            	 System.out.println("Enter the ID:");
	            	 scanner.nextLine();
	            	 int updateid = scanner.nextInt();
	            	 if (UpdateEmployee(connection,updateid, scanner)) {
                         System.out.println("Record updated successfully!");
                     } else {
                         System.out.println("No Such Record exists.");
                     }
                     break;
	            case 3:
	            	 System.out.print("Enter the Emoployee Id to be Remove: ");
                     scanner.nextLine();
                     int KeyToRemove = scanner.nextInt();
                     RemoveEmployee(connection,KeyToRemove);
                     System.out.println("Record removed..!!");
                     break;
	            case 4:
	            	ListofEmployees(connection);
                    break;
                default:
                	System.out.println("Exited");
               	 	scanner.close();
               	 	System.exit(0);
               	 	break;	
	            }     
			}
		}catch(Exception e) {
			System.out.println(e.toString());	
		}
	}//End of main method...
	
	public static void AddEmployee(Connection connection, int id, String name, String contact, String email, String branch ) throws SQLException {
		String insertQuery = "INSERT INTO employees (Id, Name, Contact, Email, Branch) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, contact);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, branch);
        preparedStatement.executeUpdate();
        System.out.println(name+ " has been added to the list successfully with Id:- " + id);	
	}
	
	public static boolean UpdateEmployee(Connection connection, int updateid, Scanner scanner) throws SQLException {
		String selectQuery = "SELECT * FROM employees WHERE Id = ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setLong(1, updateid);
        if (selectStatement.executeQuery().next()) {
            System.out.println("Updated Employee Name: ");
            scanner.nextLine();
            String name1 = scanner.nextLine();
            System.out.println("Updated Employee contact: ");
            String contact = scanner.nextLine();
            System.out.println("Updated Employee Email: ");
            String email = scanner.nextLine();
            System.out.println("Updated branch: ");
            String branch = scanner.nextLine();
            String updateQuery = "UPDATE employees SET Name = ?, Contact = ?, Email = ?, Branch = ? WHERE Id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, name1);
            updateStatement.setString(2, contact);
            updateStatement.setString(3, email);
            updateStatement.setString(4, branch);
            updateStatement.setLong(5, updateid);
            updateStatement.executeUpdate();
            return true;
        }
        return false;
	 }
	
	public static void RemoveEmployee(Connection connection, int idtoremove) throws SQLException {
		String deleteQuery = "DELETE FROM employees WHERE Id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setLong(1, idtoremove);
        preparedStatement.executeUpdate();
	}
	
	public static void ListofEmployees(Connection connection) throws SQLException {
		String selectQuery = "SELECT * FROM employees";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
            String contact = resultSet.getString("Contact");
            String email = resultSet.getString("Email");
            String branch = resultSet.getString("Branch");

            System.out.println("ID: " + id);
            System.out.println("Employee Name: " + name);
            System.out.println("Employee Contact: " + contact);
            System.out.println("Email: " + email);
            System.out.println("Branch: "+ branch);
        }	 
	 }
	
}
