public class Exceptions{  
  public static void main(String args[]){  
   try{  
      //code that may raise exception  
      int data =100/0;  
   }catch(ArithmeticException e){
       System.out.println(e);
   }finally{
       System.out.println("program ended");
       
   } 
  }
}
