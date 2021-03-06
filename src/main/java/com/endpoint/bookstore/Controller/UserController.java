package com.endpoint.bookstore.Controller;

import java.time.Instant;
import java.util.regex.Pattern;

import com.endpoint.bookstore.Entity.User;
import com.endpoint.bookstore.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/users")
public class UserController {
    
    @Autowired // Get the bean called userRepository auto-generated by Spring to handle data
	private UserRepository userRepository;

	@PostMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String email, @RequestParam String password) {
		
		// Validation
		if(password.length()<8)
			return "invalid_password";
		
		if(!validEmail(email))
			return "invalid_email";

		// Start Generating Entry
		User user;
		
		// Check if entry not present already
		try {
			
			Instant instant = Instant.now();
			user = new User(email,password,instant.toEpochMilli());
			userRepository.save(user);

		} catch(Exception e) {
			return "user_already_present";
		}

        return "successful";        
	}

	// Validate Email
	private boolean validEmail(String email) {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9._-]+");
		java.util.regex.Matcher matcher = pattern.matcher(email);
		return matcher.find();
	
	}

	@PostMapping(path = "/list")
	public @ResponseBody Iterable<User> getAllUsers(@RequestParam String secretKey) {
		
		// Returns JSON/XML of Users if secret-key matches (accessible to admins only)
		if(secretKey.equals("SECRET_KEY"))
			return userRepository.findAll();

		return null;
		
	}
}