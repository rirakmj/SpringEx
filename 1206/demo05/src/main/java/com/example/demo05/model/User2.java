package com.example.demo05.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user")
public class User2 {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	private String username;
	private String nickname;
    private String address;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
  

}
