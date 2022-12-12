package com.example.demo06.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo06.model.Board;

public interface BoardRepository 
        extends JpaRepository<Board, Long>{
	
	public Page<Board> findByTitleContaining(String title, Pageable pageable);
	public Page<Board> findByContentContaining(String title, Pageable pageable);
	
	//제목 검색 개수
	@Query(value="select count(*) from tbl_board6 where title like CONCAT('%',:word,'%')",
			nativeQuery=true)
	public Long cntTitleSearch(@Param("word") String word);
	
	@Query(value="select count(*) from tbl_board6 where content like CONCAT('%',:word,'%')",
			nativeQuery=true)
	public Long cntContentSearch(@Param("word") String word);
	
	//public void  deleteByNum(Long num);

}
