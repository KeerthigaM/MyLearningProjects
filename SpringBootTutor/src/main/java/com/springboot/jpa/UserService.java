package com.springboot.jpa;

import java.util.List;  
import java.util.Optional;  
import java.util.ArrayList;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

@Service  
public class UserService {  
    @Autowired  
    private UserRepository userRepository;
    
    public List<UserRecord> getAllUsers()
    {  
        List<UserRecord> allUserList = new ArrayList<UserRecord>();  
        userRepository.findAll().forEach(allUserList::add);  
        return allUserList;  
    }
    
    public Optional<UserRecord> getUser(String id){  
        return userRepository.findById(id);  
    }
    
    public void addUser(UserRecord userRecord){  
        userRepository.save(userRecord);  
    }
    
    public void delete(UserRecord userRecord){  
        userRepository.delete(userRecord);  
    }  
}  
