package bitedu.bipa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.dao.BlmDAO;
import bitedu.bipa.vo.BookCopy;

@Service("blmService")
public class BlmService {
	
	@Autowired
	private BlmDAO dao;
	
	public ArrayList<BookCopy> selectAllBook() {
		ArrayList<BookCopy> list = dao.selectAll();
		return list;
	}

}
