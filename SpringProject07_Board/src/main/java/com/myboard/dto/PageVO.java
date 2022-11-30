package com.myboard.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter  @Setter
public class PageVO {
	private int totPage;
	private int blockPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	private String field;
	private String word;
	
	public PageVO(int count, int currentPage, int pageSize) {
		totPage = count/pageSize + (count%pageSize==0 ? 0 : 1);
		blockPage = 3 ; //[이전] 4 5 6 [다음]
		startPage = ((currentPage-1)/blockPage)*blockPage+1;
		endPage = startPage+blockPage-1;  //계산상 마지막 페이지
		if(endPage > totPage) endPage =totPage;  // totPage 실제 마지막페이지
		
		setBlockPage(blockPage);
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setTotPage(totPage);
	}

}
