<%@page import="com.data_management.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Login Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" />
</head>
<body>
	<div class="header">
		<div class="top-header">
			<div class="top-header-left">
				<img src="images/newlogo.png" />
			</div>
			<%
				if (session.getAttribute("userId") == null) {
			%>
			<div class="top-header-right">
				<ul>
					<li><a href="LoginUser.jsp"><i class="fa fa-user"></i>  User Login</a></li>
					<li><a href="LoginAdmin.jsp"><i class="fa fa-user"></i>  Admin Login</a></li>
					<li><a href="SignUp.jsp"><i class="fa fa-sign-in"></i>  Sign Up</a></li>

				</ul>
			</div>
			<%
				} else {
			%>
			<div class="top-header-right">
				<ul>
					<li><i class="fa fa-user"><a></i>Welcome, <%=session.getAttribute("fullName")%></a></li>
					<li><a href="Controller?action=ViewProfile"><i
							class="fa fa-address-book"></i> View Profile</a></li>
					<li><a href="ChangePassword.jsp"><i
							class="fa fa-pencil-square"></i> Change Password</a></li>
					<li><a href="Controller?action=Logout"><i
							class="fa fa-sign-out"></i> Sign Out</a></li>
				</ul>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<div class="navigation">
		<ul>
			<li><a href="Welcome.jsp">Home</a></li>
			<li>
				<div class="dropdown">
					<a href="#">Services</a>
					<div class="dropdown-content">
					<% if (session.getAttribute("userType")==null || session.getAttribute("userType").equals("u")){%>
						<a href="GiveRewards.jsp">Give Rewards</a>
						<a href="Controller?action=TransactionHistory">View Transaction History</a>
						<a href="Controller?action=RedeemRewards">Redeem Rewards</a>
						<%}else{ %>
						<a href="ErrorPageAdmin.jsp">Give Rewards</a>
						<a href="ErrorPageAdmin.jsp">View Transaction History</a>
						<a href="ErrorPageAdmin.jsp">Redeem Rewards</a>
						<%} %>
					</div>
				</div>
			</li>
			<li><a href="About.jsp">About Us</a></li>
			<li><a href="Contact.jsp">Contact</a></li>
		</ul>
	</div>


	<div class="content">
		<h3 align="center">Available Giving Balance</h3>
		<table class="table">
			<tr>
				<th>User Name</th>
				<th>Available Balance</th>
			</tr>
			<tr>
				<td>
				<% GivingBalancePOJO user_g_details =(GivingBalancePOJO)request.getAttribute("user_giving_details");%>
				<%=session.getAttribute("fullName")%>
				</td>
				<td>
				<%=user_g_details.getAmount()%>
				</td>
			</tr>
		</table>
		<h3 align="center">Select the User | Amount to be Transferred</h3>
		
		<form name="transactionForm" action="Controller" method="post" id="form">
		<input type="hidden" name="action" value="GiveRewardsTransaction">
		<table class="table">
			<tr>
				<th>User Name</th>
				<th>Amount</th>
			</tr>
			
			<%
			  ArrayList<ReceivingBalancePOJO> receiversList = (ArrayList<ReceivingBalancePOJO>) request.getAttribute("user_receiving_details_arr");
			  double amt=100.00;
			  double sum=0;
			  int counter=0;
			  for (ReceivingBalancePOJO receiver : receiversList) { 
			  counter++;%>
			  
			  <tr>
			  	<td>
				<input type="radio" name="selected_r_user" value=<%=receiver.getFullName() %>>  <%=receiver.getFullName() %><br>
			  	</td>
			  		
			  		<%sum=sum+amt; %>
			  	<td>
			  	<%if(counter< receiversList.size()){%>
			  	<input type="radio" name="selected_r_amt" value=<%=sum %>> <%= sum%></td>
			  	<%}else{ %>
			  	<input type="radio" name="selected_r_amt_other">Other:
			  	<input type="text" name="selected_r_amt_other_value">
			  	<%} %>
			  	<%} %>
			  
			  </tr>
			 
		</table>
		<input type="submit" class="button_giving" value="Submit">

		</form>
		
	<div class="content1">
		<%
			if (request.getAttribute("errorMessage_GiveRewards") != null) {
		%>
		<h4 align="center"><%=request.getAttribute("errorMessage_GiveRewards")%></h4>
		<%
			}
		%>
	</div>
	</div>
		<div class="footer">
		<p>Copyright&copy; - Anuja Srivastava | MSITM | McCombs School of Business </p>
		<p>University Of Texas at Austin</p>
		<p>ALL RIGHTS RESERVED</p>
	</div>
</body>
</html>