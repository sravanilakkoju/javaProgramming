public class Null
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		try{
		    String s=null;  
            System.out.println(s.length());//NullPointerException  
		}
		catch(NullPointerException nu){
		    System.out.println(nu);
		}
		finally{
		    System.out.println("Program ended");
		}
	}
}
