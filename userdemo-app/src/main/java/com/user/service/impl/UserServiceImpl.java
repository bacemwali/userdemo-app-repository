package com.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.exception.UserException;
import com.user.model.User;
import com.user.model.ui.UserUI;
import com.user.repository.UserRepository;
import com.user.service.UserService;


/**
 * 
 * NB : the mapping entity/ui can be externalized into utility bean 
 * 
 * @author Bw
 *
 */
@Service ("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
    @Autowired 
    private UserRepository userRepository;

    
    @Override 
    public List<UserUI> getUsers() throws UserException {

		try { 
		
	    	List<User> users = new ArrayList<>();
	        userRepository.findAll().forEach(users::add);
	
	        List<UserUI> userList = new ArrayList<>();
	        for (User userModel : users) {
	            UserUI user = new UserUI();
	            BeanUtils.copyProperties(userModel, user);
	            userList.add(user);
	        }
	
	        return userList;
	    }
	    catch(Exception ex) { 
	    	logger.error(ex.getMessage());
	    	throw new UserException("- An error has occur while trying to get list users");
	    }

	}

    
    @Override 
    public UserUI getUser(Long id) throws UserException {

    	try { 

    		User user = userRepository.findOne(id);
	        UserUI userData = null;
	        userData = new UserUI();
		    BeanUtils.copyProperties(user, userData);
	        
	        return userData;
        }
	    catch(Exception ex) { 
	    	logger.error(ex.getMessage());
	    	throw new UserException("- An error has occur while trying to get user by id:" + id);
        }
    }

    @Override 
    public UserUI getUserForDeleteAction (Long id) {

    	
		User user = userRepository.findOne(id);
        UserUI userData = null;
        userData = new UserUI();
	    
        if (user != null)
	    	BeanUtils.copyProperties(user, userData);
	    else 
	    	return null;
	    		
        return userData;
        
    }

    @Override 
    public void deleteUser(Long id) throws UserException {
    
    	try { 
    		userRepository.delete(id);
        }
	    catch(Exception ex) { 
	    	logger.error(ex.getMessage());
	    	throw new UserException("- An error has occur while trying to delete user by id:" + id);
        }

   	}

    @Override 
    public UserUI createUser (UserUI user) throws UserException {

        try { 

        	User userModel = new User();
	        BeanUtils.copyProperties(user, userModel);
	        userModel = userRepository.save(userModel);
	        UserUI userData = new UserUI();
	        BeanUtils.copyProperties(userModel, userData);

	        return userData;
        }
	    catch(Exception ex) { 
	    	logger.error(ex.getMessage());
	    	throw new UserException("- An error has occur while creating user ");
        }
    }
}
