package bitedu.bipa.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.test.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<MemberVO> selectAll(){
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
		if (affected> 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean updateUser(MemberVO member) {
		boolean flag = false;
		int affected = sqlSession.update("mapper.member.updateUser", member);
		if (affected> 0) {
			flag = true;
		}
		return flag;
	}
	
	
}
