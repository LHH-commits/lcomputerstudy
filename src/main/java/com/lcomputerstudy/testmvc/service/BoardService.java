package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardService {

	private static BoardService service = null;
	private static BoardDAO dao = null;
	
	private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Board> getBoards(Pagination pagination) {
		return dao.getBoards(pagination);
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public Board detailBoard(int b_idx) {
		return dao.detailBoard(b_idx);
	}
	
	public void countViews(int b_idx) {
		dao.countViews(b_idx);
	}
	
	public void deleteBoard(Board board) {
		dao.deleteBoard(board);
	}
	
	public Board editBoard(Board board) {
		return dao.editBoard(board);
	}
	
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}
	
	public int getBoardCount() {
		return dao.getBoardCount();
	}
}
