package com.jjambbongg.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jjambbongg.springmvc.model.Board;

@Repository("boardDao")
public class BoardDaoImpl extends AbstractDao<Integer, Board> implements BoardDao{
	 
	@SuppressWarnings("unchecked")
	public List<Board> findAllList(int rowNum, int pageNum) {
		/** Board List **/
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(rowNum * (pageNum - 1));
		criteria.setMaxResults(rowNum);	
		criteria.addOrder(Order.desc("no"));		
		return (List<Board>)criteria.list();
	}
	
	public int totalCount() {
		int totalCount = 0;
		Criteria creteriaTotal = createEntityCriteria();
		totalCount = Integer.parseInt(creteriaTotal.setProjection(Projections.rowCount()).uniqueResult().toString());
		return totalCount;
	}
	
	public Board findBoardByNo(int no) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("no", no));
		return (Board)criteria.uniqueResult();
	}
	
	public void saveBoard(Board board) {
		persist(board);
	}
	
	public void deleteBoardByNo(int no) {
		Query query = getSession().createSQLQuery("delete from board where no = :no");
		query.setInteger("no", no);
		query.executeUpdate();
	}
}
