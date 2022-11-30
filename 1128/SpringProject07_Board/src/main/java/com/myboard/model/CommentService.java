package com.myboard.model;

import java.util.List;

import com.myboard.dto.CommentDTO;

public interface CommentService {
	   	public void insert(CommentDTO comment); //추가
		public List<CommentDTO> getList(int bnum);//전체보기
		public void delete(int cnum);	//삭제
}
