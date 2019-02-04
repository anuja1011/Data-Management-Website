package com.data_management.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data_management.bean.UserPOJO;
import com.data_management.dao.RewardDAO;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		
		RewardDAO rewardDAO = new RewardDAO();
		UserPOJO user = rewardDAO.login(username);
		
		if((user.getPassword()==null)||(!user.getPassword().equals(password))) {
			request.setAttribute("loginMessage", "Invalid Username/Password");
			request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
			
			}else if(user.getType().equals("u")) {
				request.setAttribute("loginMessage", "Oops...You are not an Admin. This page is for Admins, Login using User Login");
				request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
			
			}else {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId",user.getUserId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("fullName",user.getFullName());
			session.setAttribute("userType", user.getType());
			request.getRequestDispatcher("WelcomeAdmin.jsp").forward(request,response);
		}
	}

}
