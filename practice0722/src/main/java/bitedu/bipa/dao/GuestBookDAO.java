package bitedu.bipa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.vo.BoardVO;

@Repository("guestBookDAO")
public class GuestBookDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<BoardVO> selectAll() {
		ArrayList<BoardVO> list = (ArrayList) sqlSession.selectList("mapper.board.selectAllPosts");
		return list;
	}

	public BoardVO selectPost(int seq) {
		BoardVO post = sqlSession.selectOne("mapper.board.selectPost", seq);
		return post;
	}

	public boolean insertPost(BoardVO post) {
		boolean flag = false;
		int affected = sqlSession.insert("mapper.board.insertPost", post);
		if (affected > 0) {
			flag = true;
		}
		return flag;
	}

	public List<BoardVO> selectListWithSearch(String selectBox, String searchBox) {
		Map<String, String> map = new HashMap<String, String>();
	      map.put("selectBox", selectBox);
	      map.put("searchBox", searchBox);
	      List<BoardVO> list =  sqlSession.selectList("mapper.board.selectList2",map);
	      return list;
	}
}