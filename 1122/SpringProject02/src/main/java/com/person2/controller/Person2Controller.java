package com.person2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.person2.model.Person2DAOImpl;
import com.person2.model.PersonVO;

@Controller
public class Person2Controller {
	@Autowired
	private Person2DAOImpl dao;
	//추가폼
	@RequestMapping("person_insert.go")
	public String person_insert() {
		return "personForm";
	}
	//추가
	@RequestMapping(value = "person_insert.go", method = RequestMethod.POST)
	public String person_insert(PersonVO person) {
		dao.person_insert(person);
		return "redirect:person_list.go";
	}
	//전체보기
	@RequestMapping("person_list.go")
	public String list(Model model) {
		List<PersonVO> arr =  dao.person_list();
		model.addAttribute("personlist", arr);
		return "personList";
	}
	
//	public ModelAndView list() {
//		List<PersonVO> arr =  dao.person_list();
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("personlist", arr);
//		mv.setViewName("personList");
//		return mv;
//	}
	
	//상세보기
	@RequestMapping("person_view.go")
	public String view(String id, Model model) {
		PersonVO person = dao.person_view(id);
		model.addAttribute("person", person);
		return "personView";
	}
//	public  ModelAndView view(String id) {
//		ModelAndView mv = new ModelAndView();
//		PersonVO person = dao.person_view(id);
//		mv.addObject("person",person);
//		mv.setViewName("personView");
//		return mv;
//	}
	
	//수정폼
	@RequestMapping("person_update.go")
	public String update(String id, Model model) {
		model.addAttribute("person", dao.person_view(id));
		return "personUpdate";
	}
	//수정
	@RequestMapping(value = "person_update.go", method = RequestMethod.POST)
	public String update(PersonVO person) {
		dao.person_update(person);
		return "redirect:person_list.go";
	}
	//삭제
	@RequestMapping("person_delete.go")
	 public String delete(String id) {
		dao.person_delete(id);
		return "redirect:person_list.go";
	}

}







