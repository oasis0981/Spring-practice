package bitedu.bipa.test.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.test.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	public ArrayList<MemberVO> selectAll() {
		ArrayList<MemberVO> list = (ArrayList) sqlSession.selectList("mapper.member.selectAllUser");
		return list;
	}

	public MemberVO selectUser(int seq) {
		MemberVO member = sqlSession.selectOne("mapper.member.selectUserBySeq", seq);
		return member;
	}

	public boolean deleteUser(int seq) {
		boolean flag = false;
		int affected = sqlSession.delete("mapper.member.deleteUserBySeq", seq);
		if (affected > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean updateUser(MemberVO member) {
		boolean flag = false;
		int affected = sqlSession.update("mapper.member.updateUser", member);
		if (affected > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean insertUser(MemberVO member) {
		boolean flag = false;
		int affected = sqlSession.insert("mapper.member.insertUser", member);
		if (affected > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean selectUserById(String id) {
		boolean flag = false;
		int isUsed = sqlSession.selectOne("mapper.member.checkId", id);
		if (isUsed > 0) {
			flag = true;
		}
		return flag;
		
	}
	
	public MemberVO selectLoginUser(String id, String pwd) {
		HashMap<String, String> loginSet = new HashMap<String, String>();
		loginSet.put("id", id);
		loginSet.put("password", pwd);
		MemberVO member = sqlSession.selectOne("mapper.member.login", loginSet);
		System.out.println(loginSet);
		return member;
		
	}

}
