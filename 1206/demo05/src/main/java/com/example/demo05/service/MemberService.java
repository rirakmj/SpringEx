package com.example.demo05.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo05.model.JpaMemeber;
import com.example.demo05.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	//추가
	public void save(JpaMemeber member) {
		memberRepository.save(member);
	}
	//전체보기
	public List<JpaMemeber> list(){
		return memberRepository.findAll();
	}
	//상세보기(Optional)  select * from jpa_member where id = 3
	public JpaMemeber detail(Long id) {
		return memberRepository.findById(id).get();
	}
	//삭제
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	//수정(더티체킹)
	@Transactional
	public void update(JpaMemeber member) {
		 JpaMemeber m = memberRepository.findById(member.getId()).get();
		 m.setAddr(member.getAddr());
		 m.setEmail(member.getEmail());
		 m.setMemo(member.getMemo());
		 m.setName(member.getName());
		 m.setPassword(member.getPassword());
	}
}






