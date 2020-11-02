package com.endpoint.bookstore;

import org.springframework.data.repository.CrudRepository;
import com.endpoint.bookstore.Purchase;
import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    
    // Interface methods for projection on bookStore.Purchase table
    List <Purchase> findByEmail(String email);
    List <Purchase> findByBookId(Integer bookId);

}
