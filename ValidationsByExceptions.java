import java.util.*; 

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
// main class.....
public class ValidationsByExceptions {
    //main method.....
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            
            String name;
            Boolean flag;
            System.out.println("Enter your Name: ");
            name = sc.nextLine();
            flag = CheckChar(name);
            if(!flag){
                throw new CustomException("Name should contain only alphabets");
            }
            System.out.println("Name is: " + name);
        }
        catch (CustomException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        try {
            String number;
            Boolean flag;
            System.out.println("Enter your number: ");
            number = sc.nextLine();
            flag = CheckNum(number);
            if(!flag){
                throw new CustomException("Invalid number,Number much contain 10 digits");
            }
            System.out.println("Number is: " + number);
        }
        catch (CustomException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        try {
            String Email;
            Boolean flag;
            System.out.println("Enter your Email: ");
            Email = sc.nextLine();
            flag = CheckEmail(Email);
            
            if(!flag){
                throw new CustomException("Incorrect Email format");
            }
            System.out.println("Email is: " + Email);
        }
        catch (CustomException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } 
        sc.close();  
    }
    
    public static boolean CheckChar(String name){
        
        if(name == "" || name == null){
            return false;
        }
        for( int i=0;i<name.length();i++){
            if(!Character.isLetter(name.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public static boolean CheckNum(String number){
        
        if(number == "" || number == null || number.length()!=10){
            return false;
        }
        for(int i=0;i<number.length();i++){
            if(!Character.isDigit(number.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public static boolean CheckEmail(String Email){
        
        
        if(Email == "" || Email == null){
            return false;
        }
        for(int i=0;i<Email.length();i++){
            if(Email.contains("@gmail.com")){
                return true;
            }
        }
        return false;
    }
}
