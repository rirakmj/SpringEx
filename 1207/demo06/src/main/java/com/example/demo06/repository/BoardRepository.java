package com.example.demo06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo06.model.Board;

public interface BoardRepository 
        extends JpaRepository<Board, Long>{

}
