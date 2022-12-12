package com.example.demo06.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name="tbl_comment6")
public class Comment {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnum;
	private String content;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regdate")
	//@JsonFormat(pattern ="yyyy-MM-dd", timezone = "Asia/Seoul")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date regdate;
	//bnum
	@ManyToOne
	@JoinColumn(name="bnum")
	private Board board;
	//userid
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
