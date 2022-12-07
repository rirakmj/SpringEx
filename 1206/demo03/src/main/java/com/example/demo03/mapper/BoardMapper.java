package com.example.demo03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo03.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	//추가     insert()
	@Insert("insert into board(title, writer,content) "
			+ "values(#{title},#{writer},#{content})")
	public void insert(BoardDTO board);
 	
	//전체보기  list()
	@Select("select * from board")
	public List<BoardDTO> list();
	
	//개수
	@Select("select count(*) from board")
	public int getCount();
	//상세보기  findByNum()
	@Select("select * from board where num=#{num}")
	public BoardDTO findByNum(int num);

	
}
