package com.person.model;

import java.util.ArrayList;
import java.util.List;

public interface PersonDAO {
	//�߰�
	public void personInsert(Person person);
	//��ü����
	public List<Person> personList();
	//�󼼺���
	public Person personView(String id);
	//����
	public void personUpdate(Person person);
	//����
	public void personDelete(String id);

}
