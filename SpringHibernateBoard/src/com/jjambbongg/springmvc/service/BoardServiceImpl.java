package com.jjambbongg.springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjambbongg.springmvc.dao.BoardDao;
import com.jjambbongg.springmvc.model.Board;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;

	public List<Board> findAllList(int rowNum, int pageNum) {
		return dao.findAllList(rowNum, pageNum);
	}

	public int totalCount() {
		return dao.totalCount();
	}

	public Board findBoardByNo(int no) {
		return dao.findBoardByNo(no);
	}
	
	public void saveBoard(Board board) {
		dao.saveBoard(board);
	}
	
	public void updateBoard(Board board) {
		Board entity = dao.findBoardByNo(board.getNo());
		if(entity!=null) {
			entity.setTitle(board.getTitle());
			entity.setContents(board.getContents());
			entity.setModify_date(board.getModify_date());
		}
	}
	
	public void deleteBoardByNo(int no) {
		dao.deleteBoardByNo(no);
	}
	
}
