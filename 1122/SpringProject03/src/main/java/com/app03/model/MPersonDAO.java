package com.app03.model;

import java.util.List;

public interface MPersonDAO {
	//추가
		public void per_insert(PersonDTO person);
		//전체보기
		public List<PersonDTO> per_list();
		//개수
		public int per_count();
		//상세보기
		public PersonDTO per_view(String id);
		//수정
		public void per_update(PersonDTO person);
		//삭제
		public void per_delete(String id);
}
