package dependecnyInjectionByAnnotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXMLApplication{
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");		
		MyXMLApplication app = (MyXMLApplication) context.getBean("MyXMLApp1");
		app.processMessage("Hi Kirthi", "Kirthi@abc.com");
		app = (MyXMLApplication) context.getBean("MyXMLApp2");
		app.processMessage("Hi Kirthi", "Kirthi@abc.com");
		context.close();
	}
}
