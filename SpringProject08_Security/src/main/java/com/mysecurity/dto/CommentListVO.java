package com.mysecurity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
@AllArgsConstructor
public class CommentListVO {
	private List<CommentDTO> carr;
	private int count;

}
