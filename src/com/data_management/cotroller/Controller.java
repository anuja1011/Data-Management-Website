package com.data_management.cotroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action=request.getParameter("action");
		if (action.equals("registerUser")) {
			request.getRequestDispatcher("registerUser").forward(request, response);
		}else if (action.equals("userLogin")) {
			request.getRequestDispatcher("userLogin").forward(request, response);
		}else if(action.equals("AdminLogin")) {
			request.getRequestDispatcher("AdminLogin").forward(request,response);
		}else if(action.equals("GiveRewards")) {
			request.getRequestDispatcher("GiveRewards").forward(request, response);
		}else if(action.equals("GiveRewardsTransaction")) {
			request.getRequestDispatcher("GiveRewardsTransaction").forward(request, response);
		}else if(action.equals("TransactionHistory")) {
			request.getRequestDispatcher("TransactionHistory").forward(request, response);
		}else if (action.equalsIgnoreCase("redeemRewardTrasaction")) {
			request.getRequestDispatcher("redeemRewardTrasaction").forward(request, response);
		}else if (action.equalsIgnoreCase("RedeemRewards")) {
			request.getRequestDispatcher("redeemRewards").forward(request, response);
		}else if(action.equals("GenerateReport1")) {
			request.getRequestDispatcher("GenerateReport1").forward(request, response);
		}else if(action.equals("GenerateReport2")) {
			request.getRequestDispatcher("GenerateReport2").forward(request, response);
		}else if(action.equals("GenerateReport3")) {
			request.getRequestDispatcher("GenerateReport3").forward(request, response);
		}else if(action.equals("RefreshBalance")) {
			request.getRequestDispatcher("RefreshBalance").forward(request, response);	
		}else if (action.equals("ChangePassword")) {
			request.getRequestDispatcher("ChangePassword").forward(request, response);
		}else if (action.equals("ViewProfile")) {
			request.getRequestDispatcher("ViewProfile").forward(request, response);
		} else if (action.equals("Logout")) {
			request.getRequestDispatcher("Logout").forward(request, response);
		}
		
		}

}
