package bitedu.bipa.test.utils;

public class PagingbarGenerator {
	public static String generatePagingInfo(int group, int page, PageInfo info) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		
		// 마지막 그룹 계산
		int endGroup = (int) Math.ceil((float) info.getEndPage() / info.getGroupsPerPage());
		// System.out.println("endGroup " + endGroup);
		
		// 페이지 수가 그룹 크기로 나누어 떨어지면 temp1=5, 아니면 나머지의 수로 temp1을 설정  
		int temp1 = info.getEndPage() % info.getGroupsPerPage() == 0 ? 5 : info.getEndPage() % info.getGroupsPerPage();
		
		// 현재 그룹이 마지막 그룹이면 limit=temp1, 마지막 그룹이 아니면 설정한 그룹크기로 limit 설정
		int limit = endGroup == group ? temp1 : (info.getGroupsPerPage());
		
		// System.out.println(limit + "," + info.getEndPage() + "," + endGroup);
		
		
		// sb에 이전 그룹으로 이동하는 링크 추가
		if (group > 1) {
			String prev = "<a href='./list.do?group=" + (group - 1) + "&page=" + ((group - 1) * info.getGroupsPerPage())
					+ "'>Prev</a>\n";
			sb.append(prev);
		}
		
		// sb에 페이지 이동하는 링크 추가 
		for (int i = 1; i <= limit; i++) {
			String temp = "<a href='./list.do?&group=" + group + "&page=" + ((group - 1) * info.getGroupsPerPage() + i)
					+ "'>" + ((group - 1) * info.getGroupsPerPage() + i) + "</a>\n";
			sb.append(temp);
		}
		
		// sb에 다음 그룹으로 이동하는 링크 추가
		if (group < endGroup) {
			String next = "<a href='./list.do?group=" + (group + 1) + "&page=" + ((group * info.getGroupsPerPage()) + 1)
					+ "'>Next</a>\n";
			sb.append(next);
		}
		
		result = sb.toString();
		return result;
	}
}
