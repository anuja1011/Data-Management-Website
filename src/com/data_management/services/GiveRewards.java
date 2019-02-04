package com.data_management.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import com.data_management.bean.GivingBalancePOJO;
import com.data_management.bean.ReceivingBalancePOJO;
import com.data_management.bean.UserPOJO;
import com.data_management.dao.RewardDAO;

@WebServlet("/GiveRewards")
public class GiveRewards extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			request.setAttribute("sessionExpiredMessage", "Please Login");
			request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
		} else {
			UserPOJO user = new UserPOJO();
			RewardDAO rewardDAO = new RewardDAO();
			
			String currentUser=(String)session.getAttribute("username"); //gets the logged in user - username from the Session
			user = rewardDAO.login(currentUser); //get all the details of the user from user table
			
			GivingBalancePOJO user_giving_details = new GivingBalancePOJO();
			user_giving_details=rewardDAO.givingBalance(user.getUserId()); //get the details of user giving table
			
			ArrayList<ReceivingBalancePOJO> user_receiving_details_arr	= new ArrayList<ReceivingBalancePOJO>();
			user_receiving_details_arr=rewardDAO.receivingUsersList(user.getUserId()); //get the details of all the available receiving user
			
			
			
			request.setAttribute("user_receiving_details_arr", user_receiving_details_arr);
			request.setAttribute("user_giving_details", user_giving_details);
			request.getRequestDispatcher("GiveRewards.jsp").forward(request, response);
		}
	}

}
