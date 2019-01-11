package dependecnyInjectionByAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationApplication {

	//field-based dependency injection
	//@Autowired
	private MessageService service;
	
    //constructor-based dependency injection	
/*	@Autowired
	public MyAnnotationApplication(MessageService svc){
		this.service=svc;
	}
*/
	//method/setter-based dependency injection
	@Autowired
	public void setService(MessageService svc){
		this.service=svc;
	}
	
	public boolean processMessage(String msg, String rec){
		return this.service.sendMessage(msg, rec);
	}
}


