package com.mysecurity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mysecurity.dto.BoardDTO;

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
	
	//댓글개수증감  updateReplyCnt()
	@Update("update board "
			+ "set replycnt = replycnt + #{amount} where num= #{bnum}")
	public void updateReplyCnt(@Param("bnum")  int bnum,
			   @Param("amount") int amount);
	

}
