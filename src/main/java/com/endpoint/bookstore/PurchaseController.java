package com.endpoint.bookstore;

import java.time.Instant;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/purchase")
public class PurchaseController {
    
    @Autowired
	private PurchaseRepository purchase;

	// Validate Email
	private boolean validEmail(String email) {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9._-]+");
		java.util.regex.Matcher matcher = pattern.matcher(email);
		return matcher.find();
	
	}

    // Add Purchase entry
	@PostMapping(path="/add")
	public @ResponseBody String addPurchaseEntry(@RequestParam Integer bookId, @RequestParam String email, @RequestParam Integer quantity) {
		
		if(!validEmail(email))
			return "invalid_email";
		if(quantity<=0)
			return "invalid_quantity";
		if(bookId<0)
			return "invalid_bookId";

		// TODO : Validate user credentials
		// TODO : Check if stock available in inventory

		// Start Generating Entry
		Purchase transaction;
		
		// Check for Invalid operations
		try {
            
            Instant instant = Instant.now();
			transaction = new Purchase(bookId,email,quantity,instant.toEpochMilli());
			purchase.save(transaction);

		} catch(Exception e) {
			return "invalid_operation";
		}

        return "purchase_complete";        
	}

    // List books for Users
	@PostMapping(path = "/list/all")
	public @ResponseBody Iterable<Purchase> getAllPurchases(@RequestParam String secretKey) {
		
		if(secretKey.equals("SECRET_KEY"))
			return purchase.findAll();

		return null;
    }
    
    // List books for bookId
	@PostMapping(path = "/list/book")
	public @ResponseBody Iterable<Purchase> getAllUsers(@RequestParam Integer bookId,@RequestParam String secretKey) {
		
		if(secretKey.equals("SECRET_KEY"))
			return purchase.findByBookId(bookId);
		
		return null;
	}
	
    // List books for Email
	@PostMapping(path = "/list/user")
	public @ResponseBody Iterable<Purchase> getAllBooks(@RequestParam String email) {
		// TODO : Verify user credentials before fetching data
		return purchase.findByEmail(email);
    }

}