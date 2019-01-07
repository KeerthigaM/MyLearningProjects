package soap.rpcAndDocumentStyle;

import java.net.URL;  
import javax.xml.namespace.QName;  
import javax.xml.ws.Service;  

public class HelloWorldClient
{  
    public static void main(String[] args) throws Exception 
    {  
    	URL url = new URL("http://localhost:6464/ws/hello?wsdl");
        //1st argument service URI, refer to wsdl document above  
    	//2nd argument is service name, refer to wsdl document above  
        QName qname = new QName("http://rpcAndDocumentStyle.soap/", "HelloWorldImplService");  
        Service service = Service.create(url, qname);  
        HelloWorld hello = service.getPort(HelloWorld.class);  
        System.out.println(hello.getHelloWorldAsString("SOAP RPC and Document Style Webservice"));  
     }  
}
/*Please run this class after running publisher class.
publisher class generated WSDL file once that is generated, then only service can be started
output will be shiwn in console.*/