package com.example.demo02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo02.dao.BoardMapper;
import com.example.demo02.dto.BoardDTO;
@Service
public class BoardServiceImpl implements BoardService {

	 @Autowired
	 private BoardMapper bmapper;
	@Override
	public void insert(BoardDTO board) {
		bmapper.insert(board);
		
	}

	@Override
	public List<BoardDTO> list() {
		// TODO Auto-generated method stub
		return bmapper.list();
	}

	@Override
	public BoardDTO findByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		return bmapper.getCount();
	}

}
