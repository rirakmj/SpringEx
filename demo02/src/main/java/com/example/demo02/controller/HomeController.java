package com.example.demo02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo02.dto.BoardDTO;
import com.example.demo02.service.BoardService;

@Controller
public class HomeController {
	@Autowired
	private BoardService bservice;
	
	
 @GetMapping("/")
 public String home() {
	 return "home";
 }
 @GetMapping("insert")
 public String insert() {
	 return "insert";
 }
 @PostMapping("insert")
 public String insert(BoardDTO board) {
	 bservice.insert(board);
	 return "redirect:list";
	 
 }
 @GetMapping("list")
 public String list(Model model) {
	 model.addAttribute("count", bservice.getCount());
	 model.addAttribute("boards", bservice.list());
	 return "list";
 }
}
