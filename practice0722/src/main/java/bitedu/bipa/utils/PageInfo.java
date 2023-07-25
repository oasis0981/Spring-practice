package bitedu.bipa.utils;

import org.springframework.stereotype.Repository;

public class PageInfo {
	private int itemsPerPage;
	private int groupsPerPage;
	private int endPage;

	public PageInfo(int itemsPerPage, int groupsPerPage) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
	}
	
	public PageInfo(int itemsPerPage, int groupsPerPage, int totalCount) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
		this.endPage = (int)(Math.ceil(totalCount/(float)itemsPerPage));
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public int getGroupsPerPage() {
		return groupsPerPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	// 페이지별 게시물의 총 갯수
	public void setCount(int count) {
		this.endPage = (int)(Math.ceil(count/(float)itemsPerPage));
	}
	
	// 페이지별 첫 게시물 index
	public int calcuOrderOfPage(String page) {
		int result = 0;
		page = page==null?"1":page;
		result = (Integer.parseInt(page)-1)*this.itemsPerPage;
		return result;
	}
	
	@Override
	public String toString() {
		return "{'itemsPerPage':'" + itemsPerPage + "', groupsPerPage':'" + groupsPerPage + "', endPae':'" + endPage
				+ "}";
	}
	
}
