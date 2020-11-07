package com.endpoint.bookstore.Repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.endpoint.bookstore.Entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    List <User> findByEmail(String email);

}
