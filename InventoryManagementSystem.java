import java.util.*;
import java.text.SimpleDateFormat;

class Item{
    String Id;
    String Name;
    String AssignedTo;
    String Date;
    public Item(String itemid,String itemName,String empName,String date){
        this.Id = itemid;
        this.Name = itemName;
        this.AssignedTo = empName;
        this.Date = date;
        System.out.println("Item "+Id+" is assigned to "+ AssignedTo+" on "+Date);
    } 
}
//Main Class.............................
public class InventoryManagementSystem {
    public static String GetDate(){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            return (formatter.format(date));
        }
    public static void main(String[]Args){
        try {
            Scanner scanner = new Scanner(System.in);
            LinkedHashMap<String,Item> DataMap = new LinkedHashMap<>();
            while(true){
                System.out.println("Enter your choice: ");
                System.out.println("1. Add an Item");
                System.out.println("2. Update an Item");
                System.out.println("3. Remove an Item");
                System.out.println("4. List of all Items");
                System.out.println("5. Exit..");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Enter the detils");
                        System.out.println("Item ID: ");
                        scanner.nextLine();
                        String Id = scanner.nextLine();
                        System.out.println("Item Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Employee name: ");
                        String employee = scanner.nextLine();
                        String date = GetDate();
                        Item NewItem = new Item(Id, name, employee, date);
                        DataMap.put(Id, NewItem);
                        break;
                    case 2:
                        System.out.println("Enter the details for updating..");
                        System.out.println("Enter the Item id to be updated");
                        scanner.nextLine();
                        String Idtoupdate = scanner.nextLine();
                        if(DataMap.containsKey(Idtoupdate)){
                            System.out.println("Updated Item Name: ");
                            String nametoupdate = scanner.nextLine();
                            System.out.println("Updated Employee name: ");
                            String employeetoupdate = scanner.nextLine();
                            String update = GetDate();
                            Item NewItemtoadd = new Item(Idtoupdate, nametoupdate, employeetoupdate, update);
                            DataMap.put(Idtoupdate, NewItemtoadd);
                        }else{
                            System.out.println("No Such Record exits choose 1 to create..");
                        }
                        break;
                    case 3:
                        System.out.print("enter the redord id to remove: ");
                        scanner.nextLine();
                        String Keytoremove = scanner.nextLine();
                        DataMap.remove(Keytoremove);
                        System.out.println("Record removed..!!");
                        break;
                    case 4:
                        System.out.println("List of all Items:");
                        for (Map.Entry<String, Item> entry : DataMap.entrySet()) {
                            String key = entry.getKey();
                            Item value = entry.getValue();
                            System.out.println("Id:-" + key + ":>>>("+value.Name +","+value.AssignedTo+","+value.Date+")");
                        }
                        break;
                    default:
                        System.out.println("Exited");
                        scanner.close();
                        System.exit(0);
                        break;
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally{

        }    
    }            
}
