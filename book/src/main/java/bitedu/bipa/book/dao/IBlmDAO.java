package bitedu.bipa.book.dao;

import java.util.ArrayList;

import bitedu.bipa.book.vo.BookCopy;

public interface IBlmDAO {
	
	public boolean insertBook(BookCopy copy);	
	public ArrayList<BookCopy> selectBookAll();
	public boolean deleteBook(int parseInt);
	public BookCopy selectBook(int parseInt);
	public boolean updateBook(BookCopy copy);
}
