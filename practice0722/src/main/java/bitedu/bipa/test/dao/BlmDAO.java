package bitedu.bipa.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.test.vo.BookCopy;

@Repository("blmDAO")
public class BlmDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<BookCopy> selectAll() {
		ArrayList<BookCopy> list = (ArrayList) sqlSession.selectList("mapper.book.selectAllBook");
		return list;
	}
}







