package com.user.service;

import java.util.List;

import com.user.exception.UserException;
import com.user.model.ui.UserUI;


public interface UserService {


	/**
	 * create a new user 
	 * 
	 * @param user to create/add into database
	 * 
	 * @return created user 
	 * 
	 * @throws @Code UserDemoException thrown if an error occur while persisting user into database
	 * 
	 */
    UserUI createUser(UserUI user) throws UserException;

    /**
     * Get list of whole user from database
     * 
     * @return list of @Code UserUI or empty list if no result
     * 
     * @throws UserException thrown if an error occur while adding user to database
     */
    List<UserUI> getUsers() throws UserException;

    /**
     * 
     * get user by id 
     * 
     * @param id of the user to select
     * 
     * @return an Instance of @Code UserUI or null according to if found or not found
     * 
     * @throws @Code UserDemoException thrown if an error occur while selecting user from database
     */
    UserUI getUser(Long id) throws UserException;
    
    /**
     * delete user by id
     * 
     * @param id of user to delete 
     * 
     * @throws @Code UserDemoException thrown if an error occur while deleting user from database
	 *
     */
    
    void deleteUser(Long id) throws UserException;
    
    /**
     * Specific get for delelte action 
     * @param id
     * @return
     */
    public UserUI getUserForDeleteAction (Long id);
    

}