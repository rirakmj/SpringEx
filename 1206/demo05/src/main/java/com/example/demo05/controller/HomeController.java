package com.example.demo05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo05.model.JpaMemeber;
import com.example.demo05.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	private MemberService memberService;
	
  @GetMapping("/")
  public String home() {
	  return "home";
  }
  
  @GetMapping("join")
  public String join() {
	  return "join";
  }
  @PostMapping("join")
  public String join(JpaMemeber member) {
	  memberService.save(member);
	  return "redirect:/list";
  }
  @GetMapping("list")
  public String list(Model model) {
	  List<JpaMemeber> lists = memberService.list();
	  model.addAttribute("lists", lists);
	  return "list";
  }
  @GetMapping("detail/{id}")
  public String detail(@PathVariable Long id, Model model) {
	  model.addAttribute("member", memberService.detail(id));
	  
	  return "detail";
  }
  
  @GetMapping("update/{id}")
  public String update(@PathVariable Long id, Model model) {
	  model.addAttribute("member", memberService.detail(id));
	  return "update";
  }
  //삭제
  @DeleteMapping("delete/{id}")
  @ResponseBody
   public Long delete(@PathVariable Long id) {
	  memberService.delete(id);
	  return id;
  }
  //수정
  @PutMapping("update")
  @ResponseBody
  public String update(@RequestBody JpaMemeber member) {
	  System.out.println("addr : " + member.getAddr());
	  System.out.println("email : " + member.getEmail());
	  System.out.println("id : " + member.getId());
	  memberService.update(member);
	  return  "success";
  }
}













