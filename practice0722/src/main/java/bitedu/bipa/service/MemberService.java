package bitedu.bipa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.dao.MemberDAO;
import bitedu.bipa.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;
	
	public ArrayList<MemberVO> selectAll() {
		ArrayList<MemberVO> list = dao.selectAll();
		return list;
	}
	
	public MemberVO selectUser(String seq) {
		MemberVO user = dao.selectUser(Integer.parseInt(seq));
		return user;
	}

	public boolean deleteUser(String seq) {
		boolean flag = dao.deleteUser(Integer.parseInt(seq));
		return flag;
	}
	
	public boolean updateUser(MemberVO member) {
		boolean flag = dao.updateUser(member);
		return flag;
	}
	
	public boolean insertUser(MemberVO member) {
		boolean flag = dao.insertUser(member);
		return flag;
	}
	
	public boolean checkUser(String id) {
		boolean flag = dao.selectUserById(id);
		return flag;
	}
	
	public MemberVO checkLogin(String id, String pwd) {
		MemberVO member = dao.selectLoginUser(id, pwd);
		return member;
	}
}
