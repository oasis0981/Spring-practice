package bitedu.bipa.book.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmServiceImg;
import bitedu.bipa.book.utils.PageInfo;
import bitedu.bipa.book.utils.PagingbarGenerator;
import bitedu.bipa.book.vo.BookCopy;
import bitedu.bipa.book.vo.PageData;

@Controller("bookController4")
@RequestMapping("/mybatisdb")
public class BookControllerImg {
	
	@Autowired
	private BlmServiceImg blmService;

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) String group,
			@RequestParam(required = false) String page) {

		ModelAndView mav = new ModelAndView();
		
		if (group == null) {
			group = "1";
		}
		
		if (page == null) {	
			page = "1";
		}
		
		int cnt = blmService.countBooks();
		
		// 한 페이지에 표시할 도서 목록
		ArrayList<BookCopy> posts =null;
		if (cnt <= BlmServiceImg.ITEMS_PER_PAGE) {
			posts = blmService.searchBookAll();
		} else {
			posts = blmService.selectBookByPage(page, group);
		}
		
		// 페이징 바 
		String result = blmService.makeNavbar(page, group);
		
		mav.addObject("posts", posts);
		mav.addObject("nav", result);
		mav.setViewName("./manager/book_list");
		return mav;
	}

	@RequestMapping(value = "/view_regist.do", method = RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("./manager/book_regist");
		return mav;
	}

	@RequestMapping(value = "/view_detail.do", method = RequestMethod.GET)
	public ModelAndView viewDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		System.out.println(copy);
		mav.addObject("copy", copy);
		mav.setViewName("./manager/book_detail");
		return mav;
	}

	@RequestMapping(value = "/view_update.do", method = RequestMethod.POST)
	public ModelAndView viewUpdate(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy", copy);
		mav.setViewName("./manager/book_update");
		return mav;
	}

	@RequestMapping(value = "/remove.do", method = RequestMethod.GET)
	public ModelAndView remove(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		boolean flag = blmService.removeBook(bookSeq);

		mav.setViewName("redirect:list.do");
		return mav;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookCopy copy = new BookCopy();
		copy.setBookSeq(Integer.parseInt(request.getParameter("book_seq")));
		copy.setIsbn(request.getParameter("isbn"));
		copy.setTitle(request.getParameter("book_title"));
		copy.setAuthor(request.getParameter("author"));
		String date = request.getParameter("publish_date");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(date);
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = blmService.modifyBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
		return mav;
	}

	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookCopy copy = new BookCopy();
		copy.setIsbn(request.getParameter("isbn"));
		copy.setTitle(request.getParameter("book_title"));
		copy.setAuthor(request.getParameter("author"));
		copy.setPublisher(request.getParameter("publisher"));
		String date = request.getParameter("publish_date");
		System.out.println("date " + date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(date);
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blmService.registBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
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
		BookCopy copy = null;
		try {
			copy = blmService.upload(items);
			System.out.println(copy);
			blmService.modifyBook(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

//	@ExceptionHandler(value = Exception.class)
//	public ModelAndView exceptionHandler(Exception e) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("e",e);
//		mav.setViewName("./error/exception");
//		return mav;
//	}

}
