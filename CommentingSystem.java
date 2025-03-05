import java.util.*;
import java.sql.*;


public class CommentingSystem {
	static final String DB_URL = "jdbc:mysql://localhost:3306/focalcxm?user=root";
	static final String USER = "admin";
	static final String PASS = "admin";
	static Connection connection = null;
	
	public static void main(String[] args) {
		//Initializing connection to database...
		try {
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Succesfull...!!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Scanner scanner = new Scanner(System.in);
			
			while (true) {
				
				System.out.println("Select your action");
	            System.out.println("1. Comment on the post");
	            System.out.println("2. Remove a comment");
	            System.out.println("3. View comments");
	            System.out.println("4. Remove all comments");
	            System.out.println("5. Exit");
	            
	            int Choice = scanner.nextInt();
	            
	            switch(Choice) {
	            case 1:
	            	System.out.println("Name: ");
	            	String name = scanner.next();
	            	System.out.println("comment: ");
	            	scanner.nextLine();
	            	String comment = scanner.nextLine();
	            	AddComment(connection,name,comment);
	            	break;
	            case 2:
	            	System.out.println("Enter Name to get comments list: ");
	            	String nametosearch = scanner.next();
	            	Display(connection,nametosearch,scanner);
	            	break;
	            case 3:
	            	AllComments(connection);
	            	break;
	            case 4:
	            	System.out.println("Enter Name");
	            	scanner.nextLine();
	            	String nametoremove = scanner.nextLine(); 
	            	RemoveAll(connection,nametoremove);
	            	break;
	            default:
	            	scanner.close();
	            	connection.close();
	            	System.out.println("Exiting..");
	            }    
			}	
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	
	}//End of main method...
	
	public static void AddComment(Connection connection,String name,String comment) throws SQLException {
		String insertQuery = "INSERT INTO comments (Name, Comment) VALUES (?, ?)";
		PreparedStatement preparedstatement = connection.prepareStatement(insertQuery);
		preparedstatement.setString(1, name);
		preparedstatement.setString(2, comment);
		preparedstatement.executeUpdate();
		System.out.println(name+ " commented:-" + comment);	
	}
	
	public static void Display(Connection connection,String nametosearch,Scanner scanner) throws SQLException {
		String ListQuery = "SELECT * FROM comments WHERE Name = ?";
		PreparedStatement liststatement = connection.prepareStatement(ListQuery);
		liststatement.setString(1,nametosearch);
		ResultSet resultset = liststatement.executeQuery();
		
		while(resultset.next()) {
			System.out.println("S.No: " + resultset.getInt("Sno"));
			System.out.println("Name: " + resultset.getString("Name"));
			System.out.println("comment: "+ resultset.getString("Comment"));	
		}
		
		System.out.println("Enter comment s.No to be deleted");
		int comm = scanner.nextInt();
		Removecomment(connection,comm);
	}
	
	public static void Removecomment(Connection connection,int comm) throws SQLException {
		String RemoveQuery = "DELETE FROM comments WHERE Sno = ?";
		PreparedStatement removestatement = connection.prepareStatement(RemoveQuery);
		removestatement.setLong(1,comm);
		removestatement.executeUpdate();
		System.out.println("Comment removed succesfully....!!!");	
	}
	
	public static void AllComments(Connection connection) throws SQLException {
		String SelectQuery = "SELECT * FROM comments";
		PreparedStatement selectstatement = connection.prepareStatement(SelectQuery);
		ResultSet resultset = selectstatement.executeQuery();
		
		while (resultset.next()) {
			System.out.print(resultset.getString("Name")+" : ");
			System.out.println(resultset.getString("Comment"));
		}	
	}
	
	public static void RemoveAll(Connection connection , String name) throws SQLException {
		String DeleteQuery = "DELETE FROM comments WHERE Name = ?";
		PreparedStatement deletestatement = connection.prepareStatement(DeleteQuery);
		deletestatement.setString(1, name);
		deletestatement.executeUpdate();
		System.out.println("Records under name "+ name + " are removed");	
	}
	

}
