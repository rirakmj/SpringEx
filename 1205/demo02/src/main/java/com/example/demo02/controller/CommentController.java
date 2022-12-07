package com.example.demo02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo02.dto.CommentDTO;
import com.example.demo02.service.CommentService;

@RequestMapping("/reply/*")
@RestController  //@Controller + @ResponseBody
public class CommentController {
	@Autowired
	private CommentService cservcie;
	
	//commentInsert
	@PostMapping("commentInsert")
	public String commentInsert(@RequestBody CommentDTO comment) {
		comment.setUserid("aa");
		cservcie.insert(comment);
		return "success";
	}
	@GetMapping("commentList")
	public List<CommentDTO> getList(int bnum) {
	  return 	cservcie.getList(bnum);
	}
	@DeleteMapping("del/{cnum}")
	public int delete(@PathVariable int cnum) {
		cservcie.delete(cnum);
		return cnum;
	}

}
