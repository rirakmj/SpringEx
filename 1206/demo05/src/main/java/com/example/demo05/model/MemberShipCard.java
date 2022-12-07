package com.example.demo05.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MemberShipCard {
	@Id
	private String cardNumber;
	@JoinColumn(name="user_email")
	@OneToOne
	private User owner;
	private Date expiryDate;
	private boolean enambed;
	
	

}
