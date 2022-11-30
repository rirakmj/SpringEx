package com.myboard.app007;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboard.dto.BoardDTO;
import com.myboard.model.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private BoardService bserivce;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//			
//		return "boardInsert";
//	}

	@GetMapping({"/", "list"})
	public String list(Model model) {
		List<BoardDTO> boards = bserivce.findAll(null);
		int count = bserivce.getCount(null);
		model.addAttribute("boards", boards);
		model.addAttribute("count", count);
		
		return "boardList";
	}
	
	@GetMapping("insert")
	public String insert() {
		return "boardInsert";
	}
	
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		bserivce.insert(board);
		return "redirect:list";
	}
	@GetMapping("view/{num}")
	public String view(@PathVariable  int num , Model model) {
		BoardDTO board = bserivce.findByNum(num);
		model.addAttribute("board", board);
		return "view";
		
	}
	//»èÁ¦
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable int num) {
		bserivce.delete(num);
		return "success";
	}
	//¼öÁ¤Æû
	@GetMapping("update/{num}")
	public String update(@PathVariable int num, Model model) {
		 BoardDTO board =   bserivce.findByNum(num);
		 model.addAttribute("board", board);
		return "update";
	}
	
	
//	@GetMapping("delete/{num}")
//	public String delete(@PathVariable int num) {
//		bserivce.delete(num);
//		
//		return "redirect:/list";
//	}
	
	
}
