package com.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.user.model.User;
import com.user.repository.UserRepository;

@SpringBootApplication
public class UserDemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserDemoApplication.class, args);
	}
	
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

    	return args -> {
    		
    		User c = new User ();
    		c.setId(1L);
    		c.setName("DemoUser");
    		c.setAge(27);
    		c.setEmail("DemoUser@test.com");
    		c.setCountry("France");
    		c.setLogin("DemoUser@test.com");
    		c.setPassword("spring");
    		
    		userRepository.save(c);
            
            
            c = new User ();
    		c.setId(2L);
    		c.setName("morgan");
    		c.setAge(32);
    		c.setEmail("morgan@test.com");
    		c.setCountry("Belgique");
    		c.setLogin("morgan@test.com");
    		c.setPassword("java");
    		
    		userRepository.save(c);
        };
    }
}
