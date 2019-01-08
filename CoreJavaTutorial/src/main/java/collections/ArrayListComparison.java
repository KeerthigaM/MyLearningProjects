package collections;
import java.util.*;

public class ArrayListComparison 
{
	public static boolean compareList(List ls1,List ls2){
        return ls1.toString().contentEquals(ls2.toString())?true:false;
    }
    public static void main(String[] args) {

        ArrayList<String> one  = new ArrayList<String>();
        ArrayList<String> two  = new ArrayList<String>();

        one.add("one");
        one.add("two");
        one.add("six");

        two.add("one");
        two.add("two");
        two.add("six");

        System.out.println("Output1 :: "+compareList(one,two));

        two.add("ten");

        System.out.println("Output2 :: "+compareList(one,two));
    }
}
