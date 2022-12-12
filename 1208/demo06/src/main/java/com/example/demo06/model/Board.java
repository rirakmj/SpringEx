package com.example.demo06.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
@Entity(name="tbl_board6")
public class Board {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	private String title;
	//private String writer;
	private String content;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regdate")
	private Date regdate;
	private Long hitcount;
	private Long replycnt;
	
	@OneToMany(mappedBy = "board")
	@JsonIgnoreProperties("board")
	private List<Comment> comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@PrePersist
	public void prePerist()	{
		this.hitcount = this.hitcount==null?0 : this.hitcount;
		this.replycnt = this.replycnt==null?0 : this.replycnt;
	}

}
