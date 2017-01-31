package com.jjambbongg.springmvc.util;

public class PageNavigator {

	/*
	 * totalCount : total count of list
	 * listCount : page count of one page
	 * pagePerBlock : showing block
	 * pageNum : current page number
	 * searchText : search text
	 */
	
	public String getPageNavigator(int totalCount, int listCount, int pagePerBlock, int pageNum) {
		
		StringBuffer sb = new StringBuffer();
		if(totalCount > 0) {
			int pageTemp = (totalCount % listCount);
			int totalNumOfPage = 0;
			if(pageTemp==0) {
				totalNumOfPage = (totalCount / listCount );
			} else {
				totalNumOfPage = (totalCount / listCount ) + 1;
			}
			int totalNumOfBlock = (totalNumOfPage / pagePerBlock) + 1;
			int currentBlock = ((pageNum-1) / pagePerBlock) + 1;
		
			int startPage = (currentBlock - 1) * pagePerBlock + 1;
			int endPage = startPage + pagePerBlock - 1;
			
			if(endPage > totalNumOfPage) {
				endPage = totalNumOfPage;
			}
			boolean hasNext = false;
			boolean hasPrev = false;
			
			if(currentBlock < totalNumOfBlock) {
				hasNext = true;
			}
			if(currentBlock > 1) {
				hasPrev = true;
			}
			if(totalNumOfBlock == 1) {
				hasNext = false;
				hasPrev = false;
			}
			
			if(pageNum > 1) {
				sb.append("<a href=\"").append("list?pageNum=1");
				sb.append("\" title=\"<<\"><<</a>&nbsp;");
			}
			
			if (hasPrev) {
				int goPrevPage = startPage - pagePerBlock;			
				sb.append("&nbsp;&nbsp;<a href=\"").append("list?pageNum="+goPrevPage);
				sb.append("\" title=\"<\"><</a>");
			} else {
				
			}
			
			for (int i = startPage; i <= endPage; i++) {
				if (i == pageNum) {
					sb.append("<a href=\"#\"><strong>").append(i).append("</strong></a>&nbsp;&nbsp;");
				} else {
					sb.append("<a href=\"").append("list?pageNum="+i);
					sb.append("\" title=\""+i+"\">").append(i).append("</a>&nbsp;&nbsp;");
				}
			}
			if (hasNext) {
				int goNextPage = startPage + pagePerBlock;
	
				sb.append("<a href=\"").append("list?pageNum="+goNextPage);
				sb.append("\" title=\">\">></a>");
			} else {
				
			}
			if(totalNumOfPage > pageNum){
				sb.append("&nbsp;&nbsp;<a href=\"").append("list?pageNum="+totalNumOfPage);
				sb.append("\" title=\">>\">>></a>");
			}
		}
		return sb.toString();
	}
}
