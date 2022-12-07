package com.mysecurity.app008;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysecurity.dto.CommentDTO;
import com.mysecurity.dto.CommentListVO;
import com.mysecurity.service.CommentService;

@RestController
@RequestMapping("/reply/*")
public class CommentController {
	@Autowired
	private CommentService cservice;
  
	@PostMapping("commentInsert")
	public String insert(@RequestBody CommentDTO  comment) {
		cservice.insert(comment);
		return "success";
	}
	@GetMapping("commentList/{bnum}")
	public CommentListVO list(@PathVariable int bnum) {
		List<CommentDTO> carr = cservice.list(bnum);
		int count = cservice.getCount(bnum);
		CommentListVO cvo = new CommentListVO(carr, count);
		return  cvo;
	}
	@DeleteMapping("delete/{cnum}")
	public int delete(@PathVariable int cnum) {
		cservice.delete(cnum);
		return cnum;
	}
}






