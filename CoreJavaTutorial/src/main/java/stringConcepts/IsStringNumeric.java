package stringConcepts;

public class IsStringNumeric 
{
	public static void main(String[] args) 
	{		
		System.out.println(isNumeric("123"));
		System.out.println(isNumeric("ABC"));
	}
	public static boolean isNumeric(String s) 
	{
		s=s.trim();
		if (s.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
		    return true;
		} else {
			return false;
		}			
	}
}
