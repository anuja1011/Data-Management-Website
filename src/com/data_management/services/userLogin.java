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

@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
       
 
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
			
			}else if(user.getType().equals("a")) {
				request.setAttribute("loginMessage", "This is not for Admins, Login using Admin Login");
				request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
			
			}else {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId",user.getUserId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("fullName",user.getFullName());
			session.setAttribute("userType", user.getType());
			request.getRequestDispatcher("Welcome.jsp").forward(request,response);
		}
	}

}
