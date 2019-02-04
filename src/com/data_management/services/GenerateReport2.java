package com.data_management.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data_management.bean.ReportsPOJO;
import com.data_management.dao.RewardDAO;

/**
 * Servlet implementation class GenerateReport2
 */
@WebServlet("/GenerateReport2")
public class GenerateReport2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GenerateReport2() {
 
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			request.setAttribute("sessionExpiredMessage", "Please Login");
			request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
		} else {
			RewardDAO rewardDAO = new RewardDAO();
			
			ArrayList<ReportsPOJO> arr_rep = new ArrayList<ReportsPOJO>();
			
			arr_rep = rewardDAO.generateReports(2);
			
			request.setAttribute("report2", arr_rep);
			request.getRequestDispatcher("Report2.jsp").forward(request, response);
		}
	}

}
