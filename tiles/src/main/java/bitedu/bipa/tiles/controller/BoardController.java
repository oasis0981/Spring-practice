package bitedu.bipa.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("boardController")
@RequestMapping("/guestbook")
public class BoardController {

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		mav.addObject("data","Guestbook");
		mav.setViewName("/board/list");

		return mav;
	}
}
