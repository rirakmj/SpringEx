package com.myboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.FileBoardDTO;

public interface BoardMapper {
	//추가
	@Insert("insert into board(title, writer, content) values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	
	//@Select("select * from board")
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
 
	// @Select("select count(*) from board")
	 public int getCount(HashMap<String, Object> hm);

    //상세보기
	 @Select("select * from board where num=#{num}")
	  public BoardDTO findByNum(int num);
	 //삭제
	 @Delete("delete from board where num=#{num}")
	 public void delete(int num);
	 //수정
	public void update(BoardDTO board) ;
	//댓글 수  증감
	public void updateReplyCnt(@Param("bnum") int bnum,
			@Param("amount")  int amount);
	//조회수 증가
	@Update("update board set hitcount= hitcount+1 where num=#{num}")
	public void upReadCount(int num);
	
	//파일 업로드 추가
	@Insert("insert into fileboard(title, writer,content, fileimage)"
			+ "  values(#{title}, #{writer},#{content},#{fileImage})")
	public void fileInsert(FileBoardDTO fboard);
	
	//파일 리스트
	@Select("select * from fileboard")
	public List<FileBoardDTO>  fileList();

	
	
	
}
