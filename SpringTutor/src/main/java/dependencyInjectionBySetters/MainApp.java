package dependencyInjectionBySetters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) 
	{				
		 ApplicationContext context = new ClassPathXmlApplicationContext("dependencyInjectionBySetters/DependencyInjectionBySettersXML.xml");
		 Employee obj = (Employee) context.getBean("emp5");
	     obj.show();
	}
}
