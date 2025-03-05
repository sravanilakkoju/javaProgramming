import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//Item Class...
/*class Item{
	String Id;
	String ItemName;
	String EmployeeName;
	String DateTime;
	
	//Constructor...
	public Item(String ItemId, String Name, String EmpName, String Date) {
		this.Id = ItemId;
		this.ItemName = Name;
		this.EmployeeName = EmpName;
		this.DateTime = Date;
		System.out.println("Item "+Id+ " is assigned to " +EmployeeName + "on "+DateTime);
	}	
}
*/
//Main Class....
public class InventoryManagement {
	 static final String DB_URL = "jdbc:mysql://localhost:3306/focalcxm?user=root";
	 static final String USER = "admin";
	 static final String PASS = "admin";
	 static Connection connection = null;
	 
	 //method to return date..
	 public static String GetDate(){
	     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	     Date date = new Date();
	     return (formatter.format(date));
	 }
	 
	 //Main method..
	 public static void main(String[] args) {
		 //Initializing connection to database...
		 try {
			 connection = DriverManager.getConnection(DB_URL, USER, PASS);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 //Writing the main Display program...
		 try {
			 Scanner scanner = new Scanner(System.in);
			 while(true) {
				 
				 System.out.println("Enter your choice: ");
	             System.out.println("1. Add an Item");
	             System.out.println("2. Update an Item");
	             System.out.println("3. Remove an Item");
	             System.out.println("4. List of all Items");
	             System.out.println("5. Exit..");
	             int choice = scanner.nextInt();
	             
	             switch(choice) {
	             case 1:
	            	 System.out.println("Enter the details");
	            	 System.out.println("Item Id: ");
	            	 scanner.nextLine();
	            	 String Id = scanner.nextLine();	 
	            	 System.out.println("Enter the item name: ");	 
	            	 String Name = scanner.nextLine();	 
	            	 System.out.println("empployee name: ");	 
	            	 String AssignedTo = scanner.nextLine();	 
	            	 String DateTime = GetDate();	 
	            	 AddItemToDatabase(connection,Id,Name,AssignedTo,DateTime);	 
	            	 break;
		            //End of case1..
	             case 2:
	            	 System.out.println("enter the details for updating");
	            	 System.out.println("Enter the ID:");
	            	 scanner.nextLine();
	            	 String IdToUpdate = scanner.nextLine();
	            	 if (UpdateItemInDatabase(connection,IdToUpdate, scanner)) {
                         System.out.println("Record updated successfully!");
                     } else {
                         System.out.println("No Such Record exists.");
                     }
                     break;
	             case 3:
	            	 System.out.print("Enter the record id to remove: ");
                     scanner.nextLine();
                     String KeyToRemove = scanner.nextLine();
                     RemoveItemFromDatabase(connection,KeyToRemove);
                     System.out.println("Record removed..!!");
                     break;
	             case 4:
	            	 ListAllItemsFromDatabase(connection);
                     break;
                 default:
                	 System.out.println("Exited");
                	 scanner.close();
                	 System.exit(0);
                	 break;	 
	             }	 
			 }
			 
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }finally {
			 
		 }	 
	 }
	 
	 //All the CRUD implementation methods are defined here...
	 public static void AddItemToDatabase(Connection connection,String id,String name, String assignedto,String Date) throws SQLException {
		 String insertQuery = "INSERT INTO inventorymanagement (`Item ID`, `Item Name`, `Assigned To`, `DateTime`) VALUES (?, ?, ?, ?)";
         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
         preparedStatement.setString(1, id);
         preparedStatement.setString(2, name);
         preparedStatement.setString(3, assignedto);
         preparedStatement.setString(4, Date);
         preparedStatement.executeUpdate();
         System.out.println(name + " has been assigned to " + assignedto + " successfully with Id:- " + id);
	 }
	 
	 public static boolean UpdateItemInDatabase(Connection connection,String id, Scanner scanner) throws SQLException {
         String selectQuery = "SELECT * FROM inventorymanagement WHERE `Item ID` = ?";
         PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
         selectStatement.setString(1, id);
         if (selectStatement.executeQuery().next()) {
             System.out.println("Updated Item Name: ");
             String name1 = scanner.nextLine();
             System.out.println("Updated Employee name: ");
             String employee = scanner.nextLine();
             String date = GetDate();
             String updateQuery = "UPDATE inventorymanagement SET `Item Name` = ?, `Assigned To` = ?, `DateTime` = ? WHERE `Item Id` = ?";
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             updateStatement.setString(1, name1);
             updateStatement.setString(2, employee);
             updateStatement.setString(3, date);
             updateStatement.setString(4, id);
             updateStatement.executeUpdate();
             return true;
         }
     return false;
	 }
	 
	 public static void RemoveItemFromDatabase(Connection connection,String id) throws SQLException {
         String deleteQuery = "DELETE FROM inventorymanagement WHERE `Item Id` = ?";
         PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
         preparedStatement.setString(1, id);
         preparedStatement.executeUpdate();
	 }
	 
	 public static void ListAllItemsFromDatabase(Connection connection) throws SQLException {
	 	String selectQuery = "SELECT * FROM inventorymanagement";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString("Item ID");
            String name = resultSet.getString("Item Name");
            String assignedTo = resultSet.getString("Assigned To");
            String date = resultSet.getString("DateTime");

            System.out.println("ID: " + id);
            System.out.println("Item name: " + name);
            System.out.println("Assigned To: " + assignedTo);
            System.out.println("Assigned Date: " + date);
            System.out.println();
        }	 
	 }	 
}
