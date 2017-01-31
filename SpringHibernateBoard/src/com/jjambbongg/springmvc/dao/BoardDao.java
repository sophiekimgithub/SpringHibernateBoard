package com.jjambbongg.springmvc.dao;

import java.util.List;

import com.jjambbongg.springmvc.model.Board;

public interface BoardDao {

	List<Board> findAllList(int rowNum, int pageNum);
	
	int totalCount();
	
	Board findBoardByNo(int no);
	
	void saveBoard(Board board);
	
	void deleteBoardByNo(int no);
}
