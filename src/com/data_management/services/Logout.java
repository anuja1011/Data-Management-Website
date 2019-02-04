package com.data_management.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String exitUserType=null;
		if (session != null){
			exitUserType = (String)session.getAttribute("userType");
			System.out.println("------"+exitUserType);
			session.removeAttribute("userId");
			session.invalidate();
		}
		request.setAttribute("exitUserType", exitUserType);
		if(exitUserType.equals("u")) {
			request.getRequestDispatcher("SignOut.jsp")
			.forward(request, response);
		}else {
			request.getRequestDispatcher("SignOutAdmin.jsp")
			.forward(request, response);
		}
		
	}

}

