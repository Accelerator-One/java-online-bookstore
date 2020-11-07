package com.endpoint.bookstore.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    private Integer bookId,quantity;
	private String email;
	private Long timestamp;

    protected Purchase() {}

	// Initialization
	public Purchase(Integer bookId,String email,Integer quantity,Long timestamp) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.email = email;
        this.timestamp = timestamp;
    }

	// Getter Methods
	public String getEmail() {
		return email;
	}
	public Long getTimestamp() {
		return timestamp;
    }
    public Integer getBookId() {
		return bookId;
    }
    public Integer getQuantity() {
		return quantity;
	}
}