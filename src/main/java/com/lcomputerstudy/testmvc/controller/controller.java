package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.Board;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		
		BoardService boardService = null;
		UserService userService = null;
		int uIdx = 0; 
		int bIdx = 0;
		User user = null;
		Board board = null;
		int count = 0;
		int page = 1;
		String pw = null;
		String idx = null;
		HttpSession session = null;
		command = checkSession(request, response, command);
		
		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null) {
					page = Integer.parseInt(reqPage);
				} else {
					page = 1; // 페이지 정보가 없을 경우 기본 페이지는 1
				}
				userService = UserService.getInstance();
				count = userService.getUsersCount(); // 총 사용자 수를 가져옴
				Pagination pagination = new Pagination();
				pagination.setPage(page); // 현재 페이지 설정
				pagination.setCount(count); // 총 사용자 수 설정
				pagination.build(); // 페이지네이션 빌드
				// 페이지네이션 정보를 기반으로 사용자 목록 가져옴
				ArrayList<User> list = userService.getUsers(pagination);
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				
				view = "user/list";
				break;
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
				
				view = "user/insert-result";
				break;
			case "/user-detail.do":
				userService = UserService.getInstance();
				//User userDetail = new User();
				//userDetail.setU_idx(Integer.parseInt(request.getParameter("u_idx"))); //u_idx 파라미터 확인
				//userDetail = userService.detailUser(userDetail);
				uIdx = Integer.parseInt(request.getParameter("u_idx"));
				user = userService.detailUser(uIdx);
				
				view = "user/detail";
				request.setAttribute("detail", user);
				break;
			case "/user-delete.do":
				userService = UserService.getInstance();
				User userDelete = new User();
				userDelete.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userService.deleteUser(userDelete);
				view = "user/delete";
				request.setAttribute("delete", userDelete);
				break;
			case "/user-edit.do":
				userService = UserService.getInstance();
				User userEdit = new User();
				userEdit.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userService.editUser(userEdit);
				view = "user/edit";
				request.setAttribute("edit", userEdit);
				break;
			case "/user-update.do":
				userService = UserService.getInstance();
				User userUpdate = new User();
				userUpdate.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userUpdate.setU_id(request.getParameter("edit_id"));
				userUpdate.setU_pw(request.getParameter("edit_password"));
				userUpdate.setU_name(request.getParameter("edit_name"));
				userUpdate.setU_tel(request.getParameter("edit_tel1") + "-"
									+ request.getParameter("edit_tel2") + "-"
									+ request.getParameter("edit_tel3"));
				userUpdate.setU_age(request.getParameter("edit_age"));
				
				userService.updateUser(userUpdate);
				
				view = "user/update";
				break;
			case "/user-login.do":
				view = "user/login";
				break;
			case "/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
				
				if(user != null) {
					session = request.getSession();
					session.setAttribute("user", user);
					
					view = "user/login-result";
				} else {
					view = "user/login-fail";
				}
				break;
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view = "user/login";
				break;
			case "/access-denied.do":
				view = "user/access-denied";
				break;
			
			case "/board-list.do":
				String reqPage1 = request.getParameter("page");
				if(reqPage1 != null) {
					page = Integer.parseInt(reqPage1);
				} else {
					page = 1; // 페이지 정보가 없을 경우 기본 페이지는 1
				}
				boardService = BoardService.getInstance();
				count = boardService.getBoardCount();
				Pagination b_pagination = new Pagination();
				b_pagination.setPage(page);
				b_pagination.setCount(count);
				b_pagination.build();
				ArrayList<Board> b_list = boardService.getBoards(b_pagination);
				view = "board/list";
				request.setAttribute("b_list", b_list);
				break;
			case "/board-insert.do":
				view = "board/insert";
				break;
			case "/board-insert-process.do":
				board = new Board();
				board.setB_title(request.getParameter("title"));
				board.setB_content(request.getParameter("content"));
				board.setU_idx(Integer.parseInt(request.getParameter("writer_idx")));
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
				
				view = "board/insert-result";
				break;
			case "/board-detail.do":
				boardService = BoardService.getInstance();
				bIdx = Integer.parseInt(request.getParameter("b_idx"));
				boardService.countViews(bIdx);
				board = boardService.detailBoard(bIdx);
				
				view = "board/detail";
				request.setAttribute("detail", board);
				break;
			case "/board-delete.do":
				boardService = BoardService.getInstance();
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService.deleteBoard(board);
				
				view = "board/delete";
				break;
			case "/board-edit.do":
				boardService = BoardService.getInstance();
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService.editBoard(board);
				
				view = "board/edit";
				request.setAttribute("b_edit", board);
				break;
			case "/board-update.do":
				boardService = BoardService.getInstance();
				board = new Board();
				
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setB_title(request.getParameter("edit_title"));
				board.setB_content(request.getParameter("edit_content"));
				board.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				boardService.updateBoard(board);
				
				view = "board/update";
				break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}