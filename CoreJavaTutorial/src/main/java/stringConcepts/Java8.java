package stringConcepts;
import java.util.*;
public class Java8 {

	public static void main(String[] args) {
		foreachmethod();
        stringjoiner();
	}

	private static void foreachmethod() {
		List<String> gamesList = new ArrayList<String>();  
        gamesList.add("Football");  
        gamesList.add("Cricket");  
        gamesList.add("Chess");  
        gamesList.add("Hocky");  
        System.out.println("------------Iterating by passing lambda expression--------------");  
        gamesList.forEach(games->System.out.println(games));
        //listname.forEach(variable->action);
		
	}

	private static void stringjoiner() {
		StringJoiner joinNames = new StringJoiner(","); // passing comma(,) as delimiter  
		 StringJoiner joinNames1 = new StringJoiner(":", "[", "]");   // passing comma(,) and square-brackets as delimiter 
        
        // Adding values to StringJoiner  
        joinNames.add("A");  
        joinNames.add("B");  
        joinNames.add("C");  
        joinNames.add("D");  
                  
        System.out.println(joinNames);  
		
     // Adding values to StringJoiner  
        joinNames1.add("a");  
        joinNames1.add("b");  
        joinNames1.add("c");  
        joinNames1.add("d");  
                  
        System.out.println(joinNames1);
        
        StringJoiner merge = joinNames1.merge(joinNames);  
        System.out.println(merge);
	}

}
