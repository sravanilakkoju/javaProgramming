import java.util.Scanner;
import java.util.HashMap;

// class to create Accounts..
// class for transactions



// Main class..
public class Main { 
    //Main function..
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your Action:");
        System.out.println("1. Add a Contact");
        System.out.println("2. Update a Contact");
        System.out.println("3. Remove a Contact");
        System.out.println("4. List all contacts");
        
        // Declaring a HashMap with key as a string and value as an arraylist to store the account info..
        HashMap<String,HashMap<String,String>> map = new HashMap<>();
        
        
        while(true){//boolean to run the infinite loop...
            
            System.out.println("Enter Choice:");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addContact(map);// passing map as an argument for the class
                    break;
                case 2:
                    updateContact(map);
                    break;
                case 3:
                    deleteContact(map);
                    break;
                case 4:
                    listContacts(map);
                    break;
                default:
                    //any choice othet than 1 to 5 will stop the program..
                    System.out.println("Invalid choice.");
                    scanner.close();// closing the scanner class..
                    return;
            }
        }
    }
    
    public static void addContact(HashMap<String, HashMap<String,String>> map){
        String contactName;
        String contactNo;
        String contactEmail;
        System.out.println("Enter Contact Name:");
        try (Scanner scanner = new Scanner(System.in)) {
            contactName = scanner.nextLine();
            System.out.println("Enter Contact No:");
            contactNo = scanner.nextLine();
            System.out.println("Enter Contact's Email:");
            contactEmail = scanner.nextLine();
        }
        //declaring an ArrayList..
        HashMap<String,String> details = new HashMap<>();
        details.put("contactNo",contactNo);
        details.put("contactEmail",contactEmail);
        //inserting elements into the arrayList..
        map.put(contactName, details);
        System.out.println(map);
    }
    
    public static void updateContact(HashMap<String, HashMap<String,String>> map){
        String contactName;
        String contactNo;
        String contactEmail;
        System.out.println("Enter Contact Name:");
        Scanner scanner = new Scanner(System.in);
        contactName = scanner.nextLine();
        if (map.containsKey(contactName)){
            
            int choice;
            System.out.println("Enter Choice of operation:");
            System.out.println("1. Update Contact Name");
            System.out.println("2. Update Contact Number");
            System.out.println("3. Update Contact Email");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String newContactName;
                    System.out.println("Enter Contact Name to be Updated:");
                    scanner.nextLine();
                    newContactName = scanner.nextLine();
                    map.put(newContactName,map.get(contactName));
                    map.remove(contactName);
                    break;
                case 2:
                    String newContactNo;
                    System.out.println("Enter Contact No to be Updated:");
                    scanner.nextLine();
                    newContactNo = scanner.nextLine();
                    map.get(contactName).put("contactNo",newContactNo);
                    break;
                case 3:
                    String newContactEmail;
                    System.out.println("Enter Contact Email to be Updated:");
                    scanner.nextLine();
                    newContactEmail = scanner.nextLine();
                    map.get(contactName).put("contactEmail",newContactEmail);
                    break;
                default:
                    //any choice othet than 1 to 5 will stop the program..
                    System.out.println("Invalid choice.");
                    scanner.close();// closing the scanner class..
                    return;
            }
            
            
        }
        else{
            System.out.println("No contact found!");
        }
        System.out.println(map);
    }
    public static void deleteContact(HashMap<String, HashMap<String,String>> map){
        String contactName;
        System.out.println("Enter Contact Name:");
        Scanner scanner = new Scanner(System.in);
        contactName = scanner.nextLine();
        if (map.containsKey(contactName)){
            map.remove(contactName);
            System.out.println("Contact "+ contactName +" deleted.");
        }
        else{
            System.out.println("No contact found!");
        }
        System.out.println(map);
        
    }
    public static void listContacts(HashMap<String, HashMap<String,String>> map){
        for (String key : map.keySet()) {
            System.out.println(key);
        }
    }
}
