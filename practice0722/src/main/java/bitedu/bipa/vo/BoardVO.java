package bitedu.bipa.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int seq;
	private String title;
	private String content;
	private String writer;
	private int view;
	private Timestamp uploadTime;
	private String password;
	private String uploadFile;
	
	public BoardVO() {}
	
	public BoardVO(int seq, String title, String content, String writer, int view, Timestamp uploadTime,
			String password, String uploadFile) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.view = view;
		this.uploadTime = uploadTime;
		this.password = password;
		this.uploadFile = uploadFile;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer + ", view="
				+ view + ", uploadTime=" + uploadTime + ", password=" + password + ", uploadFile=" + uploadFile + "]";
	}
	

	
}
