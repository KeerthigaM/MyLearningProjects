package dependencyInjectionByXML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIMainAppByXML {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("DI.xml");
      TextEditor tec = (TextEditor) context.getBean("textEditorConstructorBased");
      System.out.println("Constructor Based");
      tec.spellCheck();
      TextEditor tes = (TextEditor) context.getBean("textEditorSetterBased");
      System.out.println("Setter Based");
      tes.spellCheck();      
   }
}
