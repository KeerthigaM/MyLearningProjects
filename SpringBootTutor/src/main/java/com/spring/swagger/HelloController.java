package com.spring.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.swagger.Hello;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HelloController 
{	
	@RequestMapping(value = "/kirthilearning/springswagger.hello")
	public String hello() {
		return "Hello !!!";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/kirthilearning/springswagger.welcome")
	public String welcome() {
		return "Welcome Swagger!!!";
	}
	
	@ApiOperation(value = "Operations", notes="get api Operations",nickname = "ApiOperations")	
	@RequestMapping(method = RequestMethod.GET, value = "/kirthilearning/springswagger.operations")
	public ArrayList<Hello> operation() {
		ArrayList<Hello> arrayList= new ArrayList<Hello>();
		arrayList.add(new Hello());
		return arrayList;
	}
	
	@ApiOperation(value = "Responses", notes="get api Responses",nickname = "ApiResponses")
	@ApiResponses(value =
						{
	        			 @ApiResponse(code = 500, message = "Server error"),
	        			 @ApiResponse(code = 404, message = "Service not found"),
	        			 @ApiResponse(code = 200, message = "Successful retrieval",
	        			 response = Hello.class, responseContainer = "List") 
	        			 }
				  )
	@RequestMapping(method = RequestMethod.GET, value = "/kirthilearning/springswagger.responses")
	public ArrayList<Hello> Response() 
	{
		ArrayList<Hello> arrayList= new ArrayList<Hello>();
		arrayList.add(new Hello());
		return arrayList;
	}
	
	private static final String ID = "id";
	@ApiOperation(value = "Params", notes="get api Params",nickname = "ApiParams")
	 @ApiResponses(value = 
 						 {
 						  @ApiResponse(code = 500, message = "Server error"),
 						  @ApiResponse(code = 200, message = "Successful retrieval",
 						  response = Hello.class, responseContainer = "List") 
 						 }
			 	   )
	@RequestMapping(method = RequestMethod.GET, value = "/kirthilearning/springswagger.params")
	public List<Hello> sayHello(@ApiParam(value = "testId",required = true, defaultValue = "111")  @PathVariable(ID) final int institutuionId) 
	{
		ArrayList<Hello> arrayList= new ArrayList<Hello>();
		arrayList.add(new Hello());
		return arrayList;
	}
	
}