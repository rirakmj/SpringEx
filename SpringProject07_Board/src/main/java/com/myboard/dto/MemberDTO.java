package com.myboard.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter  @Setter
public class MemberDTO {
	private String id;
	private String name;
	private String pass;
	private String addr;
	private Date regdate;
}
