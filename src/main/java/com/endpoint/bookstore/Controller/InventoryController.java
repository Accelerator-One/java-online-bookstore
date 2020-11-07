package com.endpoint.bookstore.Controller;

import com.endpoint.bookstore.Entity.Inventory;
import com.endpoint.bookstore.Entity.User;
import com.endpoint.bookstore.Repository.InventoryRepository;
import com.endpoint.bookstore.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(path="/books")
public class InventoryController {
    
    @Autowired
	private InventoryRepository bookEntry;

	@Autowired
	private UserRepository user;

	private Boolean authFlag;
	private Boolean entryPresent;
	private Integer getQuantity;

	// private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);


	// Adds new book
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

		entryPresent = false;

		// Check if record exists and update if present
		Iterable <Inventory> info = bookEntry.findByBookId(bookId);
		info.forEach(data->{
			entryPresent = true;
			getQuantity = data.getQuantity();
		});

		if(entryPresent) {
			// Add to current entry if present
			bookEntry.updateEntry(bookId,quantity+getQuantity);
			return "entry_updated";
		}

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