package com.jjambbongg.springmvc.service;

import java.util.List;

import com.jjambbongg.springmvc.model.Board;

public interface BoardService {

	List<Board> findAllList(int rowNum, int pageNum);
	
	int totalCount();
	
	Board findBoardByNo(int no);
	
	void saveBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoardByNo(int no);
	
	//Board findById(int no);
	//boolean isBoardNoUnique(int no, Board board);
}
