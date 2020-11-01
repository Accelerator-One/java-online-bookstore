package com.endpoint.bookstore;

import org.springframework.data.repository.CrudRepository;
import com.endpoint.bookstore.Inventory;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
    // TODO : Add Functionality to query data on certain regex in search box
    // OPTIONAL : Add Filter data on certain params (as present in online stores)
}
