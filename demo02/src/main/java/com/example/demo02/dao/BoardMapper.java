package com.example.demo02.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo02.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	//추가
		@Insert("insert into board(title, writer, content) "
				+ "values(#{title}, #{writer}, #{content})")
		public void insert(BoardDTO board);
		
		//전체보기
		//@Select("select * from board")
		public List<BoardDTO> list();
		
		//개수
		public int getCount();
}
