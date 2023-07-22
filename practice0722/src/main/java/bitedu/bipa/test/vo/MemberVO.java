package bitedu.bipa.test.vo;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class MemberVO {
	private int seq;
	private String id;
	private String password;
	private String phoneNumber;
	private String status;
	private String grade;
	private int maxBook;
	private Timestamp stopDate;
	private int isAdmin;
	
	public MemberVO() {
		
	}
	
	public MemberVO(int seq, String id, String password, String phoneNumber, String status, String grade, int maxBook,
			Timestamp stopDate, int isAdmin) {
		super();
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.grade = grade;
		this.maxBook = maxBook;
		this.stopDate = stopDate;
		this.isAdmin = isAdmin;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getMaxBook() {
		return maxBook;
	}
	public void setMaxBook(int maxBook) {
		this.maxBook = maxBook;
	}
	public Timestamp getStopDate() {
		return stopDate;
	}
	public void setStopDate(Timestamp stopDate) {
		this.stopDate = stopDate;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "MemberVO [seq=" + seq + ", id=" + id + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", status=" + status + ", grade=" + grade + ", maxBook=" + maxBook + ", stopDate=" + stopDate
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	
}
