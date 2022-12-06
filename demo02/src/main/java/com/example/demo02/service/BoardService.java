package com.example.demo02.service;

import java.util.List;

import com.example.demo02.dto.BoardDTO;


public interface BoardService {
	  //추가하기
			public void insert(BoardDTO board);
		  //전체보기
			public List<BoardDTO> list();
			//상세보기
			public BoardDTO findByNum(int num);
			//수정
			public void update(BoardDTO board);
			//삭제
			public void delete(int num);
			//개수
			public int getCount();
}
