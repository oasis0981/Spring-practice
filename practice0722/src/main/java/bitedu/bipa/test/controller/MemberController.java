package bitedu.bipa.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView viewAll() {
		ModelAndView mav = new ModelAndView();
		ArrayList<MemberVO> list = ms.selectAll();
		mav.addObject("memberList", list);
		mav.setViewName("/member/list");
		return mav;
	}

	// 상세 조회(only admin)
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public ModelAndView viewUser(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = ms.selectUser(seq);
		mav.addObject("userInfo", member);
		mav.setViewName("/member/detail");
		return mav;
	}

	// 회원가입(only user)
	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
	public ModelAndView viewRegister() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/view_register");
		return mav;
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = new MemberVO();
		member.setId(req.getParameter("id"));
		member.setPhoneNumber(req.getParameter("phone_number"));
		member.setPassword(req.getParameter("pwd"));
		member.setIsAdmin(Integer.parseInt(req.getParameter("is_admin")));
		
		if (member.getIsAdmin() == 1) {
			System.out.println("관리자 가입");
		} else {
			System.out.println("일반회원 가입");
		}
		
		boolean flag = ms.insertUser(member);
		mav.addObject("isRegistered", flag);
		mav.setViewName("redirect:../"); // TODO: 처리 필요(메인으로? 방명록으로?)
		return mav;
	}

	// 회원정보수정(only user)
	@RequestMapping(value = "/view_update.do", method = RequestMethod.POST)
	public ModelAndView viewUpdate(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = ms.selectUser(seq);
		mav.addObject("userInfo", member);
		mav.setViewName("/member/view_update");
		return mav;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		MemberVO member = new MemberVO();
		member.setSeq(Integer.parseInt(req.getParameter("user_seq")));
		member.setId(req.getParameter("id"));
		member.setPhoneNumber(req.getParameter("phone_number"));
		member.setPassword(req.getParameter("pwd"));
		boolean flag = ms.updateUser(member);
		if (flag) {
			System.out.println("수정 성공");
		}
		mav.setViewName("redirect:list.do");
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

	// 로그인

	// 로그아웃

	// 인증 확인
}
