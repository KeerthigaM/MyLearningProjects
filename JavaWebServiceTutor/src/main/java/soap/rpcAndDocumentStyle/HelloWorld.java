package soap.rpcAndDocumentStyle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface  
@WebService
//@SOAPBinding(style = Style.RPC)//for RPC Style
@SOAPBinding(style = Style.DOCUMENT)//for Document Style
public interface HelloWorld 
{
	@WebMethod
	String getHelloWorldAsString(String name);
}