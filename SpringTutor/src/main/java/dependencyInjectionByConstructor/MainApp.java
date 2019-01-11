package dependencyInjectionByConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) 
	{				
		 ApplicationContext context = new ClassPathXmlApplicationContext("dependencyInjectionByConstructor/DependencyInjectionByConstructorXML.xml");
		 Employee obj = (Employee) context.getBean("emp6");
	     obj.show();
	}
}
