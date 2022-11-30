package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import com.myspring.vo.PersonVO;

public interface PersonDAO {
	//�߰�
		public void dao_insert(String mid, PersonVO person);
		//��ü����
		public List<PersonVO>  dao_list(String mid);
		public List<PersonVO> dao_searchList(String mdi, HashMap<String, String> hm);
		//�󼼺���
		public PersonVO  dao_view(String mid,String id);
		//����
		public void  dao_update(String mid,PersonVO person);
		//����
		public void  dao_delete(String mid,String id);
		//����
		public int  dao_count(String mid);
		public int  dao_searchCount(String mid,HashMap<String, String> hm);
}
