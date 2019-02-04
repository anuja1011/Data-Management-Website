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
import com.data_management.bean.UserTransactionPOJO;
import com.data_management.dao.RewardDAO;

/**
 * Servlet implementation class TransationHistory
 */
@WebServlet("/TransactionHistory")
public class TransactionHistory extends HttpServlet {
	
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
			UserPOJO user2 = new UserPOJO();

			RewardDAO rewardDAO = new RewardDAO();
			
			ArrayList<UserTransactionPOJO> userTran_arr = new ArrayList<UserTransactionPOJO>();
			ArrayList<UserTransactionPOJO> userTran_arr1 = new ArrayList<UserTransactionPOJO>();

			ArrayList<UserPOJO> user_details_arr =new ArrayList<UserPOJO>();
			ArrayList<UserPOJO> user_details_arr1 =new ArrayList<UserPOJO>();

			
			String currentUser=(String)session.getAttribute("username"); //gets the logged in user - username from the Session
			user = rewardDAO.login(currentUser); //get all the details of the user from user table
			
			
			userTran_arr = rewardDAO.fetchCurrentUserHistory(user.getUserId());
			user_details_arr = rewardDAO.login2();
			
			for(int x=0; x<userTran_arr.size();x++) {
				
				for(int y=0;y<user_details_arr.size();y++) {
					
					user2= user_details_arr.get(y);
					
					if((userTran_arr.get(x).getToUser()).equals(user2.getUserId())){
						
						userTran_arr.get(x).setToUserName(user2.getFullName());
					}
				}
			
			}
			
			userTran_arr1 = rewardDAO.fetchCurrentUserHistoryForReceived(user.getUserId());
			user_details_arr1 = rewardDAO.login2();
			
			for(int x=0; x<userTran_arr1.size();x++) {
				
				for(int y=0;y<user_details_arr1.size();y++) {
					
					user2= user_details_arr1.get(y);
					
					if((userTran_arr1.get(x).getFromUser()).equals(user2.getUserId())){
						
						userTran_arr1.get(x).setFromUserName(user2.getFullName());
					}
				}
			
			}
			
			request.setAttribute("userTranHist", userTran_arr);
			request.setAttribute("userTranHistReceived", userTran_arr1);			
			request.getRequestDispatcher("TransactionHistory.jsp").forward(request, response);
			
		}
	}

}
