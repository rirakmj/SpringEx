package com.myboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.myboard.dto.CommentDTO;

public interface CommentMapper {
	public void insert(CommentDTO comment);
	public List<CommentDTO> getList(int bnum);
	@Delete("delete from commentboard where cnum=#{cnum}")
	public void delete(int cnum);
	//read
	@Select("select * from commentboard where cnum=#{cnum}")
	 public CommentDTO read(int cnum);

}
