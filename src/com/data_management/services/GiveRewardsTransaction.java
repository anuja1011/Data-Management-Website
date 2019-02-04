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
import com.data_management.bean.ReceivingUsersListPOJO;
import com.data_management.bean.UserPOJO;
import com.data_management.dao.RewardDAO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Receiver;

/**
 * Servlet implementation class GiveRewardsTransaction
 */
@WebServlet("/GiveRewardsTransaction")
public class GiveRewardsTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiveRewardsTransaction() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			request.setAttribute("sessionExpiredMessage", "Please Login");
			request.getRequestDispatcher("LoginUser.jsp").forward(request, response);
		} else {
			
			ReceivingBalancePOJO receiver = new ReceivingBalancePOJO();
			

			
			String currentUserId=(String)session.getAttribute("userId"); //gets the logged in user - username from the Session
			String receivingUserName=(String)request.getParameter("selected_r_user");
			
			RewardDAO rd = new RewardDAO();

			GivingBalancePOJO user_giving_details = new GivingBalancePOJO();
			user_giving_details=rd.givingBalance(currentUserId); //get the details of user giving table
			
			ArrayList<ReceivingBalancePOJO> arr = new ArrayList<ReceivingBalancePOJO>();
			arr=rd.receivingUsersList(currentUserId); //get the details of all the available receiving user
			
			RewardDAO rewardDAO = new RewardDAO();
			ArrayList<ReceivingBalancePOJO> user_receiving_details_arr	= new ArrayList<ReceivingBalancePOJO>();
					
			for(int x=0;x<arr.size();x++) {
				if((arr.get(x).getFullName().equals(receivingUserName))){
					receiver=arr.get(x);
				}
			}
			
			if((String)request.getParameter("selected_r_amt") != null) {
				
				String temp = request.getParameter("selected_r_amt");
				double value = Double.parseDouble(temp);
				
				if(value<= user_giving_details.getAmount() && value>0) {
					double amt = value;
					rd.transactionOfRewards(currentUserId,receiver.getUserId(),amt,"transfer");
					user_giving_details = rd.fetchCurrentGivingUserDetails(currentUserId);
					//receiver=rd.fetchCurrentReceivingUserDetails(receiver.getUserId()); //the receiver info is not needed right now
				}else {
					user_receiving_details_arr=rewardDAO.receivingUsersList(currentUserId); //get the details of all the available receiving user
					request.setAttribute("user_receiving_details_arr", user_receiving_details_arr);
					request.setAttribute("user_giving_details", user_giving_details);
					request.setAttribute("errorMessage_GiveRewards", "Entered Amount is not Valid: Please Check!");
					request.getRequestDispatcher("GiveRewards.jsp").forward(request, response);
					
				}

			}else {
				
				String temp=request.getParameter("selected_r_amt_other_value");
				double value = Double.parseDouble(temp);
				if(value<= user_giving_details.getAmount() && value>0) {
					double amt = value;
					rd.transactionOfRewards(currentUserId,receiver.getUserId(),amt,"t");
					user_giving_details = rd.fetchCurrentGivingUserDetails(currentUserId);
					//receiver=rd.fetchCurrentReceivingUserDetails(receiver.getUserId()); //the receiver info is not needed right now
				}else {
					user_receiving_details_arr=rewardDAO.receivingUsersList(currentUserId); //get the details of all the available receiving user
					request.setAttribute("user_receiving_details_arr", user_receiving_details_arr);
					request.setAttribute("user_giving_details", user_giving_details);
					request.setAttribute("errorMessage_GiveRewards", "Entered Amount is not Valid: Please Check!");
					request.getRequestDispatcher("GiveRewards.jsp").forward(request, response);
					
				}
				
			}
			
			user_receiving_details_arr=rewardDAO.receivingUsersList(currentUserId); //get the details of all the available receiving user
			
			request.setAttribute("user_receiving_details_arr", user_receiving_details_arr);
			request.setAttribute("user_giving_details", user_giving_details);
			request.getRequestDispatcher("GiveRewards.jsp").forward(request, response);
			
		}
		
		
	}

}
