package com.myboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myboard.dto.BoardDTO;

public interface BoardMapper {
	//추가
	@Insert("insert into board(title, writer, content) values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	
	@Select("select * from board")
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
 
	 @Select("select count(*) from board")
	 public int getCount(HashMap<String, Object> hm);

    //상세보기
	 @Select("select * from board where num=#{num}")
	  public BoardDTO findByNum(int num);
	 //삭제
	 @Delete("delete from board where num=#{num}")
	 public void delete(int num);
}
