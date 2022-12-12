package com.example.demo06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo06.config.auth.PrincipalUser;
import com.example.demo06.model.Board;
import com.example.demo06.model.Comment;
import com.example.demo06.service.CommentService;

@RestController
@RequestMapping("/reply/*")
public class CommentController {
 @Autowired
 private CommentService commentService;
 
 //댓글리스트
 @GetMapping("list/{num}")
 public  List<Comment> list(@PathVariable Long num) {
	 System.out.println("num : " + num);
	 List<Comment> clist = commentService.list(num);
	 return clist;
	 
 }
 
 //댓글추가
 @PostMapping("insert/{num}")
 public ResponseEntity<String> commentInsert(@PathVariable Long num,
		 @RequestBody Comment comment,
		 @AuthenticationPrincipal  PrincipalUser principal) {  
	 Board b = new Board();
	 b.setNum(num);  // bnum
	 comment.setBoard(b); 
	 ///---------------  User
	//PrincipalUser p = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// comment.setUser(p.getUser());
	 
	 comment.setUser(principal.getUser());
	 
	 commentService.insert(comment);
	 return new ResponseEntity<String>("success", HttpStatus.OK);
	 
 }
}
