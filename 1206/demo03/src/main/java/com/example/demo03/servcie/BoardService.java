package com.example.demo03.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo03.dto.BoardDTO;
import com.example.demo03.mapper.BoardMapper;

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
