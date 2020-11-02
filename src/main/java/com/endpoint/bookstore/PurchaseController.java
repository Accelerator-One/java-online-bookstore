package com.endpoint.bookstore;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/purchase")
public class PurchaseController {
    
    @Autowired
	private PurchaseRepository purchase;

    // Add Purchase entry
	@PostMapping(path="/add")
	public @ResponseBody String addPurchaseEntry(@RequestParam Integer bookId, @RequestParam String email, @RequestParam Integer quantity) {
		
		// TODO : Validation check in later iteration


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
	@GetMapping(path = "/list/all")
	public @ResponseBody Iterable<Purchase> getAllPurchases() {
		return purchase.findAll();
    }
    
    // List books for bookId
	@PostMapping(path = "/list/book")
	public @ResponseBody Iterable<Purchase> getAllUsers(@RequestParam Integer bookId) {
		return purchase.findByBookId(bookId);
    }
    // List books for Email
	@PostMapping(path = "/list/user")
	public @ResponseBody Iterable<Purchase> getAllBooks(@RequestParam String email) {
		return purchase.findByEmail(email);
    }

}