package inversionOfControl;

import org.springframework.beans.factory.xml.XmlBeanFactory; 
import org.springframework.core.io.ClassPathResource;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;


public class IoCMainApp { 
   public static void main(String[] args) { 
	  //BeanFactory container approach
      XmlBeanFactory factory = new XmlBeanFactory (new ClassPathResource("IoC.xml")); 
      HelloWorld objfac = (HelloWorld) factory.getBean("helloWorld");
      objfac.getMessage();
      //ApplicationContext container approach - FileSystemXmlApplicationContext
      ApplicationContext filecontext = new FileSystemXmlApplicationContext("C:/Users/886758/Documents/MyTask/Latest Java learning/Learning Workspace/SpringLearning/src/main/resources/IoC.xml");    	      
      HelloWorld objfilecxt = (HelloWorld) filecontext.getBean("helloWorld");
      objfilecxt.getMessage(); 
      //ApplicationContext container approach - ClassPathXmlApplicationContext
      ApplicationContext classpathcontext = new ClassPathXmlApplicationContext("IoC.xml");    	      
      HelloWorld objclaspathcxt = (HelloWorld) classpathcontext.getBean("helloWorld");
      objclaspathcxt.getMessage(); 
   }
}   

