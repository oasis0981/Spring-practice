package bitedu.bipa.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.test.service.MemberService;
import bitedu.bipa.test.vo.MemberVO;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService ms;
	
	// 전체 조회(only admin)
	@RequestMapping("/list.do")
	public ModelAndView viewAll() {
		ModelAndView mav = new ModelAndView();
		ArrayList<MemberVO> list = ms.selectAll(); 
		mav.addObject("memberList", list);
		mav.setViewName("/member/list");
		return mav;
	}
	
	// 상세 조회(only admin)
	@RequestMapping("/detail.do")
	public ModelAndView viewUser(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = ms.selectUser(seq);
		mav.addObject("userInfo", member);
		mav.setViewName("/member/detail");
		return mav;
	}
	
	// 회원가입(only user)
	
	// 회원정보수정(only user)
	@RequestMapping("/view_update.do")
	public ModelAndView viewUpdate(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = ms.selectUser(seq);
		mav.addObject("user", member);
		mav.setViewName("/member/view_update");
		return mav;
	}
	
	// 회원 삭제(only admin)
	@RequestMapping("/delete.do")
	public ModelAndView deleteUser(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		boolean flag = ms.deleteUser(seq);
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	
	// 아이디 중복 확인
	
	// 로그인
	
	// 로그아웃
	
	// 인증 확인
}
