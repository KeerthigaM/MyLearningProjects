package com.springboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RestController;  
import java.util.List;  
import java.util.Optional;  

@RestController  
public class UserController 
{  
    @Autowired  
    private UserService userService; 
    
    @RequestMapping("/")  
    public String hello(){
    	String message = "HOW TO USE POSTMAN:\r\n" + 
    			"1.Open Postman desktop application or please add postman extension in chrome and open it.\r\n" + 
    			"2.Enter http://localhost:8081/getAllUser on URL textbox and select GET from dropdown and press send/submit\r\n" + 
    			"3.You will get empty array([]) as output\r\n" + 
    			"4.Enter http://localhost:8081/add-user on URL textbox and select POST from dropdown\r\n" + 
    			"5.click raw button and select JSON in dropdown and enter following JSON input in textarea below and click send/submit.   \r\n" + 
    			"{\r\n" + 
    			"\"id\": 1,\r\n" + 
    			"\"name\": \"kirthi\",\r\n" + 
    			"\"email\": \"kirthi@gmail.com\"\r\n" + 
    			"}";
        return message;  
    }
    
    @RequestMapping("/getAllUser")  
    public List<UserRecord> getAllUser(){  
        return userService.getAllUsers();  
    }
    
    @RequestMapping(value="/addUser", method=RequestMethod.POST)  
    public void addUser(@RequestBody UserRecord userRecord){  
        userService.addUser(userRecord);  
    }
    
    @RequestMapping(value="/getUserByID/{id}", method=RequestMethod.GET)  
    public Optional<UserRecord> getUserByID(@PathVariable String id){  
        return userService.getUser(id);  
    }  
}  
