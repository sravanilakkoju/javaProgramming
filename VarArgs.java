import java.util.HashMap;
class AverageWithVarArgs{
    public static int Average(int...numbers){
        int total = 0;
        int avg;
        for(int x : numbers){
            total = total+x;
        }
        avg = total/numbers.length;
        return avg;
    }
}
/*class to store the key value pair of polygon shapes..and print
the values by taking keys input from varArgs... */ 
class Shapes{
   
    public static void EditShapesMap(HashMap<Integer,String> Map){
        Map.put(1,"Line");
        Map.put(2,"No shape");
        Map.put(3,"Triangle");
        Map.put(4,"Rectangale");
        Map.put(5,"Pentagon");
        Map.put(6,"Hexagon");
        Map.put(7,"Heptagon");
        Map.put(8,"Octogon");
        Map.put(9,"Nonagon");
        Map.put(10,"Decagone");
        System.out.println(Map);
    }

    public static void PrintShapes(HashMap<Integer,String>Map,int...keys){
      for (int key : keys) {
        if(Map.containsKey(key)){
            String Value = Map.get(key);
            System.out.println("No of Sides: "+ key +"the shape is: "+ Value);
        }
        else{
            System.out.println("Key not found: "+key);
        } 
      }
    }
}
public class VarArgs {
    public static void main(String[] args){
        System.out.printf("The average is %d ",AverageWithVarArgs.Average(12,13,14,15,16,17,18,19));
        System.out.println();
        HashMap<Integer,String> ShapesMap = new HashMap<>();
        Shapes.EditShapesMap(ShapesMap);
        Shapes.PrintShapes(ShapesMap,5,6,7,8,9,10);  
    }
}
