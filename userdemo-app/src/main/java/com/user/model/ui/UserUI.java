package com.user.model.ui;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

public class UserUI {

	private Long id;
	
	private String name;
	
    @Min(value = 18, message = "- Age should be more or equal 18 year ! Thx")  
    private int age;
    
    @NotEmpty(message = "- Sorry but only Frensh users can proceed to registration ! Thx")  
    @Value("France")
    private String country;
    
    @Email
    private String email;
    
    @NotEmpty
    private String login;
    
    @NotEmpty
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}