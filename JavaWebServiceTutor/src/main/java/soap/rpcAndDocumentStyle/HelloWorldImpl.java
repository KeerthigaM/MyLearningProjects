package soap.rpcAndDocumentStyle;

import javax.jws.WebService;  

//Service Implementation  
@WebService(endpointInterface = "soap.rpcAndDocumentStyle.HelloWorld")  
public class HelloWorldImpl implements HelloWorld
{  
  public String getHelloWorldAsString(String name) 
  {  
      return "Hello JAX-WS- " + name;  
  }  
}  