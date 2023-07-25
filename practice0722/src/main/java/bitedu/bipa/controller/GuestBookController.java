package bitedu.bipa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.service.GuestBookService;
import bitedu.bipa.vo.BoardVO;

@Controller("guestBookController")
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	GuestBookService bs;

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView viewList() {
		ModelAndView mav = new ModelAndView();
		ArrayList<BoardVO> list = bs.viewAllList();
		mav.addObject("list", list);
		mav.setViewName("/board/list");
		return mav;
	}

	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public ModelAndView viewUser(@RequestParam("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		BoardVO post = bs.selectPost(seq);
		System.out.println(post);
		mav.addObject("post", post);
		mav.setViewName("/board/detail");
		return mav;
	}

	// req, res 관련된 것은 서비스가 아닌 컨트롤러에서 처리하는 것이 맞음
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("upload");

		// 업로드할 위치 설정
		String path = "D:\\dev\\upload_files\\images";

		// enctype form-data : 바이너리로 받기 때문에 서버에서 처리하는 방법이 다름. 그걸 처리하는 역할이
		// DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024 * 10);

		// req 폼 구성요소를 파일 아이템으로 만들어서 리턴
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		BoardVO copy = null;
		try {
			copy = bs.upload(items);
			System.out.println(copy);
			bs.registPost(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("업로드실패");
		}
		mav.setViewName("redirect:list.do");

		return mav;
	}

	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName, HttpServletResponse resp) {
		File downloadFile = new File("D:\\dev\\upload_files\\images\\" + fileName);
		ModelAndView mav = new ModelAndView();
		try {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename=" + fileName);

		try {
			FileInputStream fis = new FileInputStream(downloadFile);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[256];
			int length = 0;
			while ((length = fis.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/view_regist.do", method = RequestMethod.GET)
	public ModelAndView view_regist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/view_regist");
		return mav;
	}

	@RequestMapping("/search.do")
	public ModelAndView searchList(@RequestParam(required = false) String selectBox,
			@RequestParam(required = false) String searchBox) {

		System.out.println("searchList");
		ModelAndView mav = new ModelAndView();
		selectBox = selectBox == "" ? "title" : selectBox;
		List<BoardVO> list = bs.showListWithSearch(selectBox, searchBox);

		System.out.println("검색");
		mav.addObject("list", list);
		mav.setViewName("/board/list");
		return mav;
	}

//	@RequestMapping(value="/regist.do", method = RequestMethod.POST)
//	public ModelAndView regist(@ModelAttribute("post") BoardVO post) {
//		ModelAndView mav = new ModelAndView();
//		boolean flag = bs.registPost(post);
//		return mav;
//		
//	}
}
