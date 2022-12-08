package com.example.demo06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo06.config.auth.PrincipalUser;
import com.example.demo06.model.Board;
import com.example.demo06.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	  @GetMapping("insert")
	  @PreAuthorize("isAuthenticated()")
	  public String insert() {
		  return "/board/insert";
	  }
	  @PostMapping("insert")
	  public String insert(Board board,
			  @AuthenticationPrincipal PrincipalUser principal) {
		  boardService.insert(board, principal.getUser());
		  return "redirect:list";
	  }
	  //게시글전체보기
//	  @GetMapping("list")
//	  public String list(Model model) {
//		  model.addAttribute("count", boardService.count());
//		  model.addAttribute("boards", boardService.findAll());
//		  return "/board/list";
//	  }
	  
	  //게시글전체보기 : 페이징 ===>페이징 정보 + data(게시글 정보)
	  @GetMapping("list")
	  public String list(Model model,
			  @PageableDefault(size = 5, sort = "num",
			                   direction = Direction.DESC)Pageable  pageable ) {
		  
		  Page<Board> lists = boardService.findAll(pageable);
		  System.out.println("lists : " + lists);
		  model.addAttribute("count", boardService.count());
		  model.addAttribute("boards", lists);
		  return "/board/list";
	  }
	  
	  
	  
	  
	  //상세보기
	  @GetMapping("view/{num}")
	  public String view(@PathVariable Long num, Model model ) {
		  model.addAttribute("board", boardService.findById(num));
		  return "/board/view";
	  }
	  
	  
	  
}
