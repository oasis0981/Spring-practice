package bitedu.bipa.book.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.book.vo.BookCopy;

@Repository("blmDAOImg")
public class BlmDAOImg implements IBlmDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean insertBook(BookCopy copy){
		boolean flag = false;

		int affectedCount1 = sqlSession.insert("mapper.book.insertBook",copy);
		int affectedCount2 = sqlSession.insert("mapper.book.insertCopy",copy.getIsbn());
		if(affectedCount1>0&&affectedCount2>0) {
			flag = true;
		}

		return flag;
	}
	
	@Override
	public ArrayList<BookCopy> selectBookAll(){
		ArrayList<BookCopy> list = null;
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllBook");
		System.out.println(list.size());
		return list;
	}
	
	@Override
	public boolean deleteBook(int parseInt) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int affectedCount = sqlSession.delete("mapper.book.deleteBook",parseInt);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public BookCopy selectBook(int parseInt) {
		// TODO Auto-generated method stub
		BookCopy copy = null;
		copy = sqlSession.selectOne("mapper.book.selectBookBySeq",parseInt);
		return copy;
	}
	
	@Override
	public boolean updateBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int affectedCount = sqlSession.update("mapper.book.updateBook",copy);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}

	public ArrayList<BookCopy> selectBookByPage(int startIdx, int cnt) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIdx", startIdx);
        map.put("cnt", cnt);
        
		ArrayList<BookCopy> list = (ArrayList)sqlSession.selectList("mapper.book.selectBookByPage", map);
		
		return list;
	}

	public int countAllBooks() {
		int cnt = sqlSession.selectOne("mapper.book.countAll");
		return cnt;
	}
}







