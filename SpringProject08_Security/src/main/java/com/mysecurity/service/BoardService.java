package com.mysecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysecurity.dto.BoardDTO;
import com.mysecurity.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper bmapper;
	//추가
	public void insert(BoardDTO board) {
		bmapper.insert(board);
	}

	//전체보기
	public List<BoardDTO> list(){
		return bmapper.list();
	}
	//개수
	public int getCount() {
		return bmapper.getCount();
	}
	//상세보기
	public BoardDTO findByNum(int num) {
		return bmapper.findByNum(num);
	}
	

}
