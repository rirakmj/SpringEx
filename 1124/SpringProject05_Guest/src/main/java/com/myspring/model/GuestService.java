package com.myspring.model;

import java.util.HashMap;
import java.util.List;
public interface GuestService {
		public void guestInsert(GuestDTO guest);	//추가
		public List<GuestDTO> guestList(HashMap<String, String> hm);//전체보기
    	public int guestCount(HashMap<String, String> hm);	//개수
}
