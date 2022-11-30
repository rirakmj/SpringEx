package com.myspring.model;

import java.util.List;

import com.myspring.vo.PersonVO;

public interface PersonService {
	//�߰�
	public void insert(PersonVO person);
	//��ü����
	public List<PersonVO> listAll();
	public List<PersonVO> list(String field ,String word);
	//�󼼺���
	public PersonVO view(String id);
	//����
	public void update(PersonVO person);
	//����
	public void delete(String id);
	//����
	public int countAll();
	public int count(String field, String word);

}
