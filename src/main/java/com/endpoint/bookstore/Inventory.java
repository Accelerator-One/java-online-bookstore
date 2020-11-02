package com.endpoint.bookstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// Book Repository storing Inventory stock of Books available
public class Inventory {

	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	// Setting 'bookId' as field
	@Column(unique = true)
	private Integer bookId;

	private Integer quantity;
  	private String name;

	protected Inventory() {}

	// Initialize Object
	Inventory(Integer bookId, String name, Integer quantity) {
        this.bookId = bookId;
        this.name = name;
        this.quantity = quantity;
    }

	// Getter Methods
	public Integer getBookId() {
		return bookId;
	}
    public String getName() {
		return name;
    }
    public Integer getQuantity() {
		return quantity;
	}
}