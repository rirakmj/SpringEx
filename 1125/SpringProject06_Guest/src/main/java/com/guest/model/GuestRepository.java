package com.guest.model;

import java.util.HashMap;
import java.util.List;

public interface GuestRepository {
	//�߰�
	public void dao_guestInsert(GuestVO guest); 
//��ü����
	public List<GuestVO> dao_guestList(HashMap<String, String> hm);
	//�󼼺���
	public GuestVO dao_findByNum(int num); 
	//����
	public void dao_guestUpdate(GuestVO guest);
	//����
	public void dao_guestDelete(int num); 
	//����
	public int dao_guestCount(HashMap<String, String> hm);
}
