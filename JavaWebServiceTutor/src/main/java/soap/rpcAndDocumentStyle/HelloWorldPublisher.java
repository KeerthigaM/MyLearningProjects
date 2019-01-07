package soap.rpcAndDocumentStyle;

import javax.xml.ws.Endpoint;  

//Endpoint publisher  
public class HelloWorldPublisher
{  
  public static void main(String[] args) 
  {  
     Endpoint.publish("http://localhost:6464/ws/hello", new HelloWorldImpl());     
  }  
}

/*
After running the publisher code, you can see the generated WSDL file by visiting the URL:
http://localhost:6464/ws/hello?wsdl  
*/