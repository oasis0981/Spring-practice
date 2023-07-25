package bitedu.bipa.service;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.dao.GuestBookDAO;
import bitedu.bipa.vo.BoardVO;

@Service("guestBookService")
public class GuestBookService {
	
	@Autowired
	GuestBookDAO dao;
	
	public ArrayList<BoardVO> viewAllList() {
		ArrayList<BoardVO> list = dao.selectAll();
		return list;
	}

	public BoardVO selectPost(String seq) {
		BoardVO post = dao.selectPost(Integer.parseInt(seq));
		return post;
	}
	
	public BoardVO upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		BoardVO copy = null;
		copy = new BoardVO();
		for(FileItem item : items) {
			// FormField: 일반적인 데이터
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("title")) {
					copy.setTitle(item.getString("UTF-8"));
				} else if(fieldName.equals("writer")) {
					copy.setWriter(item.getString("UTF-8"));
				} else if(fieldName.equals("content")) {
					copy.setContent(item.getString("UTF-8"));
				} else if(fieldName.equals("password")) {
					copy.setPassword(item.getString("UTF-8"));
				}
			} else {
				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("upload_file")) {
					String temp = item.getName();
					System.out.println("temp : " + temp);
					if(temp != "") {
						System.out.println("upload_file "+temp);
						int index = temp.lastIndexOf("\\");
						String fileName = temp.substring(index+1);
						copy.setUploadFile(fileName);
						File uploadFile = new File("D:\\dev\\upload_files\\images\\"+fileName);
						item.write(uploadFile);
					}
					
				}
			}
		}
		LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
		copy.setUploadTime(timestamp);
		System.out.println("service copy " + copy);
		return copy;
	}

	public boolean registPost(BoardVO post) {
		boolean flag = dao.insertPost(post);
		return flag;
	}

	public List<BoardVO> showListWithSearch(String selectBox, String searchBox) {
		return dao.selectListWithSearch(selectBox, searchBox);
	}
}
