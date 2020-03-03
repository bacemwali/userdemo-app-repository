package com.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.UserException;
import com.user.loggin.UserPerfLog;
import com.user.model.ui.UserUI;
import com.user.service.UserService;


/**
 * 
 * 
 * 
 * @author Bw
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired 
    private UserService userService;

    /**
     * Get all the user available in the underlying system
     * 
     * @return list of users
     */
    
    @GetMapping
    public ResponseEntity<?> getUsers() throws UserException {
    	
    	List<UserUI> users = new ArrayList<UserUI>();

    	users = userService.getUsers(); 
    	
    	return new ResponseEntity<List<UserUI>>(users, HttpStatus.OK);
    }

    /**
     * Create a user
     * 
     * @param user
     * @return created user
     */
    @UserPerfLog
    @PostMapping(value = "/registrateuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserUI user)  throws UserException {
    	
    	UserUI userData = userService.createUser(user);
        
        return new ResponseEntity<UserUI>(userData, HttpStatus.CREATED);
    }

    /**
     * Deleted user.
     * @param id
     * @return Http 500 or 200 according if it success or fails
     */
    
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, 
    									@RequestParam(name= "applicationId", required = false, 
    												  	defaultValue = "UserDemoAppId") String applicationId)   throws UserException {
    	
    	// delete demo user to experiment default, optional request param
    	// USerApp and UserDemoAppId are both arbitrary values
    	if (!applicationId.equals("UserApp")) {
				id = 1L;
    	}

    	// Specific use
		UserUI user = userService.getUserForDeleteAction(id);
		if (user != null) {
			userService.deleteUser(id);
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("User is not existing", HttpStatus.OK);
        
    }

    /**
     * Get the user detail by id.
     * @param id
     * 
     * @return user details
     */
    
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getUser (@PathVariable Long id) throws UserException  {
        
    	UserUI user  = null; 
   		user = userService.getUser(id);

    	return new ResponseEntity<>(user, HttpStatus.OK);
    }
}