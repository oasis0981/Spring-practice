package bitedu.bipa.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.test.dao.MemberDAO;
import bitedu.bipa.test.vo.MemberVO;

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
}
