package com.myspring.model;

import java.util.HashMap;
import java.util.List;

public interface GuestDAO {
	public void dao_guestInsert(String mid, GuestDTO guest); //insert
	public List<GuestDTO> dao_guestList(String mid,HashMap<String, String> hm);//list
	public int dao_guestCount(String mid,HashMap<String, String> hm);//count
}
