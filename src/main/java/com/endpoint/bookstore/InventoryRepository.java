package com.endpoint.bookstore;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.endpoint.bookstore.Inventory;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
@Transactional
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    // Checking Book entry if present
    List <Inventory> findByBookId(Integer bookId);

    @Modifying
    @Query("UPDATE Inventory i SET i.quantity=:quantity WHERE i.bookId=:bookId")
	void updateEntry(@Param("bookId") Integer bookId,@Param("quantity") Integer quantity);

}
