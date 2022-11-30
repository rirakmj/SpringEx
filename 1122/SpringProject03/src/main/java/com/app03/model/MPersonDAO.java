package com.app03.model;

import java.util.List;

public interface MPersonDAO {
	//�߰�
		public void per_insert(PersonDTO person);
		//��ü����
		public List<PersonDTO> per_list();
		//����
		public int per_count();
		//�󼼺���
		public PersonDTO per_view(String id);
		//����
		public void per_update(PersonDTO person);
		//����
		public void per_delete(String id);
}
