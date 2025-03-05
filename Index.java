public class Index
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		try{
		    int a[]=new int[5];  
            a[10]=50; //ArrayIndexOutOfBoundsException  
		}
		catch(IndexOutOfBoundsException in){
		    System.out.println(in);
		}
		finally{
		    System.out.println("Program Ended");
		}
	}
}
