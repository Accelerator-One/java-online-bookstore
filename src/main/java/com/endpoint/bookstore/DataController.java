package com.endpoint.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/books")
public class DataController {
    
    @Autowired
	private InventoryRepository bookEntry;

	@Autowired
	private UserRepository user;

	private Boolean authFlag;

	// Adds new book
	// TODO : If book already present with same 'bookId' & 'name', update entry ('entry_updated') otherwise 'invalid_operation'
	@PostMapping(path="/add")
	public @ResponseBody String addNewBook (@RequestParam Integer bookId, @RequestParam String secretKey,
				 @RequestParam String name, @RequestParam Integer quantity) {
		
		if(!secretKey.equals("SECRET_KEY"))
		    return "not_authorized";

		// Validation
		if(name.length()<2)
			return "invalid_book_name";
        if(bookId < 0)
            return "invalid_book_id";
        if(quantity <= 0)
            return "invalid_book_quantity";

		// Start Generating Entry
		Inventory book;
		
		// Check for Invalid operations
		try {
			
			book = new Inventory(bookId,name,quantity);
			bookEntry.save(book);

		} catch(Exception e) {
			return "invalid_operation";
		}

        return "book_added";        
	}

    // List books for Users
	@PostMapping(path = "/list")
	public @ResponseBody Iterable<Inventory> getAllUsers(@RequestParam String email,@RequestParam String password) {
		
		Iterable <User> it = user.findByEmail(email);

		it.forEach(data->{
			authFlag = data.getPassword().equals(password);
		});
		
		if(authFlag)
			return bookEntry.findAll();

		return null;
	}
}