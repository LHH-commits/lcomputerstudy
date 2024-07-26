package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

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
		
		UserService userService = null;
		int uIdx = 0; 
		User user = null;
		int count = 0;
		int page = 1;
		
		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null) {
					page = Integer.parseInt(reqPage);
				}
				userService = UserService.getInstance();
				count = userService.getUsersCount();
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(count);
				pagination.build();
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
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
}
