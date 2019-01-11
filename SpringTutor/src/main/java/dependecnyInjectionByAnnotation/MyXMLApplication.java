package dependecnyInjectionByAnnotation;

public class MyXMLApplication {

	private MessageService service;

	//constructor-based dependency injection
	public MyXMLApplication(MessageService svc) {
		this.service = svc;
	}
	
	//setter-based dependency injection
/*	public void setService(MessageService svc){
		this.service=svc;
	}
*/
	public boolean processMessage(String msg, String rec) {		
		return this.service.sendMessage(msg, rec);
	}
}
