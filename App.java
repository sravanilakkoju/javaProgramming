// importing libraries..
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

// class to create Accounts..
class CreateAccount {
    String acnt_no;
    int pin_no;
    //constructor with a hashmap as the argument type..
    public CreateAccount(HashMap<String, ArrayList<Integer>> map, Scanner scanner){
        System.out.println("Enter Account No:");
        this.acnt_no = ""+ scanner.nextInt();
        System.out.println("Enter Pin No:");
        this.pin_no = scanner.nextInt();
        //declaring an ArrayList..
        ArrayList<Integer> details = new ArrayList<>();
        details.add(pin_no);
        details.add(0);
        //inserting elements into the arrayList..
        map.put(acnt_no, details);
        System.out.println(map);
    }
}
// class for transactions
class Transfer {
    String acnt_no1;
    String acnt_no2;
    int pin;
    int amount;
    
    public Transfer(HashMap<String,ArrayList<Integer>> map, Scanner scanner){
        System.out.println("Enter your account number");
        this.acnt_no1 = ""+scanner.nextInt();
        System.out.println("Enter your pin");
        this.pin = scanner.nextInt();
        
        if(map.containsKey(acnt_no1)){
            // validating the credentials
            if((map.get(acnt_no1).get(0)) == pin){
                System.out.println("Account verified");
                System.out.println("Enter recipient Account number: ");
                this.acnt_no2 = scanner.next();
                //validating the account info..
                if(map.containsKey(acnt_no2)){
                    System.out.println("Enter the amount to be transfered");
                    this.amount = scanner.nextInt();
                    int balance1 = map.get(acnt_no1).get(1);
                    int balance2 = map.get(acnt_no2).get(1);
                    // verifing the balnace for the transation to take place..
                    if (balance1 >= amount){
                        balance1 = (balance1 - amount);
                        balance2 = (balance2 + amount);
                        //updating the balance info after transaction..
                        map.get(acnt_no1).set(1,balance1);
                        map.get(acnt_no2).set(1,balance2);
                        
                        System.out.println("The transaction is succesful");
                        System.out.println(map);// just for information..
                    }
                    else{
                        System.out.println("No sufficient funds in your account");
                        this.amount = 0;
                    }
                }
                else{
                    System.out.println("Acount not found ,Please verify");
                }
            }
            else{
                System.out.println("Incorrect pin");
            }
            
        }
        else{
            System.out.println("Account not found ,please verify");
        }
    }
    
}
// class to hold withdraw actions
class Withdraw {
    String acnt_no;
    int pin;
    int amount;
    public Withdraw(HashMap<String, ArrayList<Integer>> map, Scanner scanner){
        System.out.println("Enter your account number");
        this.acnt_no = ""+scanner.nextInt();
        System.out.println("Ener your pin");
        this.pin = scanner.nextInt();
        // validating the account credentials..
        if(map.containsKey(acnt_no)){
            if((map.get(acnt_no).get(0))==pin){
                System.out.println("Account verified.");
                System.out.println("Enter the withdraw amount");
                this.amount = scanner.nextInt();
                int balance;
                balance=map.get(acnt_no).get(1);
                // verifing the account balance..
                if(balance >= amount){
                    balance = balance - amount;
                    // updating..
                    map.get(acnt_no).set(1,balance);
                    System.out.println(map);
                    System.out.println("The amount is withdrawn from your account succesfully.!!");
                }
                else{
                    System.out.println("Sorry.!! No sufficient funds in your account");
                    this.amount = 0;
                }
            }
            else{
                System.out.println("Incorrect pin");
            }
            
        }
    }
    
}
// class to hold deposit actions
class Deposit {
    String acnt_no;
    Integer amount;
    public Deposit(HashMap<String, ArrayList<Integer>> map, Scanner scanner){
        System.out.println("Enter your Account Number:");
        this.acnt_no = ""+scanner.nextInt();
        if (map.containsKey(acnt_no)){
            System.out.println("Account verified.");
            System.out.println("Enter the deposite amount");
            this.amount = scanner.nextInt();
            int balance = -1;
            balance=map.get(acnt_no).get(1);
            balance=balance+amount;
            map.get(acnt_no).set(1,balance);
            System.out.println(map);
        }
        
        else{
            System.out.println("Invalid account number");
        }
 
    }
    
}

//class for checking balance
class Checkbalance{
    int balance;
    String acnt_no;
    int pin;
    public Checkbalance(HashMap<String,ArrayList<Integer>> map, Scanner scanner){
        System.out.println("Enter your Accounnt Number");
        this.acnt_no = ""+scanner.nextInt();
        System.out.println("Enter your pin:");
        this.pin = scanner.nextInt();
        if (map.containsKey(acnt_no)){
            if ((map.get(acnt_no).get(0))==pin){
                System.out.println("Account Verified");
                this.balance = map.get(acnt_no).get(1);
            }
            else{
                System.out.println("Incorrect pin");
            }
            
        }
    }
}

//Action class to show how polymorphism works
class Action {
    // giving Create_Account class as an argument and a variable 
    // pay() takes class type as arguments data type..
    public void  pay(CreateAccount gpay){
        System.out.println("Your Account no is "+gpay.acnt_no+" and the account is created.");
    }
    public void  pay(Deposit gpay){
        System.out.println("Your Deposit amount is "+gpay.amount+" and processed.");
    }
    public void  pay(Withdraw gpay){
        if(gpay.amount > 0){
             System.out.println("You withdrawed "+gpay.amount);
        }
        else{
            System.out.println("No amount withdrawed.");
        }
    }
    public void  pay(Transfer gpay){
        if(gpay.amount > 0){
            System.out.println("Your transasction of amount "+gpay.amount+" is processed.");
        }
        else{
            System.out.println("No Transactions");
        }
    }
    public void pay(Checkbalance gpay){
        System.out.println("Your balance amount is: " + gpay.balance);
    }
}


// Main class..
public class App { 
    //Main function..
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Choose your Action:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
        
        // Declaring a HashMap with key as a string and value as an arraylist to store the account info..
        HashMap<String,ArrayList<Integer>> map = new HashMap<>();
        
        Action pay_obj= new Action();
        
        while(true){//boolean to run the infinite loop...
            
            System.out.println("Enter Choice:");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    CreateAccount cod_obj=new CreateAccount(map, scanner);// passing map as an argument for the class
                    pay_obj.pay(cod_obj);// passing a object to the pay()- polymorphism..
                    break;
                case 2:
                    Deposit dep_obj=new Deposit(map, scanner);
                    pay_obj.pay(dep_obj);
                    break;
                case 3:
                    Withdraw with_obj = new Withdraw(map, scanner);
                    pay_obj.pay(with_obj);
                    break;
                case 4:
                    Transfer tr_obj=new Transfer(map, scanner);
                    pay_obj.pay(tr_obj);
                    break;
                case 5:
                    Checkbalance bal_obj = new Checkbalance(map, scanner);
                    pay_obj.pay(bal_obj);
                    break;
                default:
                    //any choice othet than 1 to 5 will stop the program..
                    System.out.println("Invalid choice.");
                    scanner.close();// closing the scanner class..
                    return;
            }
        }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        finally{
            scanner.close();
        }
    }
}
