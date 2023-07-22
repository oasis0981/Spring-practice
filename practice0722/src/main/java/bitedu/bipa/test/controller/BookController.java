package bitedu.bipa.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.test.service.BlmService;
import bitedu.bipa.test.vo.BookCopy;

@Controller("bookController")
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BlmService bs;

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		ArrayList<BookCopy> list = bs.selectAllBook();
		mav.addObject("list", list);
		mav.setViewName("/book/list");
		
		return mav;
	}
}
