package dependencyInjectionByXML;

public class TextEditor 
{
	   private SpellChecker spellChecker;
	   
	   public void spellCheck() {
		      spellChecker.checkSpelling();
	   }
	   
	   //Constructor based DI
	   public TextEditor(SpellChecker spellChecker) {
	      System.out.println("Inside TextEditor constructor." );
	      this.spellChecker = spellChecker;
	   }
	   
	   //Setter based DI
	   public TextEditor() {	
		// default constructor is mandatory in settered based approach	
		   }
	   // a setter method to inject the dependency.
	   public void setSpellChecker(SpellChecker spellChecker) {
	      System.out.println("Inside setSpellChecker." );
	      this.spellChecker = spellChecker;
	   }
	   // a getter method to return spellChecker
	   public SpellChecker getSpellChecker() {
	      return spellChecker;
	   }	   
}