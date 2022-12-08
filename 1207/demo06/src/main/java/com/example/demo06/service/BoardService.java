package com.example.demo06.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo06.model.Board;
import com.example.demo06.model.User;
import com.example.demo06.repository.BoardRepository;

@Transactional(readOnly = true)
@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void insert(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
	}
	//페이징 없이 전체보기
	public List<Board> findAll(){
		return boardRepository.findAll();
	}
	//페이징 포함 전체보기
	public Page<Board> findAll(Pageable pageable ){
		return boardRepository.findAll(pageable);
	}
	public Long count() {
		return boardRepository.count();
	}
	
	@Transactional
	public Board findById(Long num) {
		Board board = boardRepository.findById(num).get();
		board.setHitcount(board.getHitcount()+1);
		return board;
		
	}

}
