class OuterClass {
    int a = 12;
    public void greet(){
        System.out.println("hii this is outer class");
        class LocalInnerClass{
            String LocalClassVariable = "this is local inner class";

            public void PrintLocalInnerClass(){
                System.out.println(LocalClassVariable);
            }
        }
        LocalInnerClass lic = new LocalInnerClass();
        lic.PrintLocalInnerClass();
    }
    public class innerClass{
        int b = 3;
        public void greet(){
            System.out.println("hi this is inner class");
        }

    }    
}
public class NestedClass{
    public static void main(String[] args){
      OuterClass out = new OuterClass();
      out.greet();
      OuterClass.innerClass inner = out.new innerClass();
      inner.greet();


    }
}
