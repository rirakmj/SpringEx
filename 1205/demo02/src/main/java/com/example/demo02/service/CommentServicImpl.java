package com.example.demo02.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo02.dao.BoardMapper;
import com.example.demo02.dao.CommentMapper;
import com.example.demo02.dto.CommentDTO;

@Service
public class CommentServicImpl  implements CommentService{
@Autowired
private CommentMapper cmapper;
@Autowired
private BoardMapper bmapper;

	@Override
	@Transactional
	public void insert(CommentDTO comment) {
		//댓글추가
		cmapper.insert(comment);
		//댓글개수증가
		bmapper.updateReplyCnt(comment.getBnum(),1);
//		HashMap<String, Integer> hm = new HashMap<>();
//		hm.put("bnum", comment.getBnum());
//		hm.put("amount", 1);
//		bmapper.updateReplyCnt(hm);
		
	
	}

	@Override
	public List<CommentDTO> getList(int bnum) {
			return cmapper.getList(bnum);
	}

	@Override
	@Transactional
	public void delete(int cnum) {
		CommentDTO comment = cmapper.findByNum(cnum);
		bmapper.updateReplyCnt(comment.getBnum(), -1);
		cmapper.delete(cnum);
		
	}

}
