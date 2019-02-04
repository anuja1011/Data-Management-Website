package com.data_management.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data_management.bean.GivingBalancePOJO;
import com.data_management.bean.ReceivingBalancePOJO;
import com.data_management.bean.UserPOJO;
import com.data_management.dao.RewardDAO;

/**
 * Servlet implementation class RedeemRewards
 */
@WebServlet(name = "redeemRewards", urlPatterns = { "/redeemRewards" })
public class RedeemRewards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedeemRewards() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			request.setAttribute("sessionExpiredMessage", "Please Login");
			request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
		} else {
			UserPOJO user = new UserPOJO();
			RewardDAO rewardDAO = new RewardDAO();
			
			String currentUser=(String)session.getAttribute("username"); //gets the logged in user - username from the Session
			user = rewardDAO.login(currentUser); //get all the details of the user from user table
			
			ReceivingBalancePOJO user_receiving_details = new ReceivingBalancePOJO();
			user_receiving_details=rewardDAO.getReceivingBalance(user.getUserId()); //get the details of user giving table
			
			
			request.setAttribute("user_receiving_details", user_receiving_details);
			request.getRequestDispatcher("RedeemRewards.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
