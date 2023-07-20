package bitedu.bipa.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bookController")
@RequestMapping("/book")
public class BookController {

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		mav.addObject("data","Book");
		mav.setViewName("/book/list");

		return mav;
	}
}
