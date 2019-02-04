<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
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
					<li><a><i class="fa fa-user"></i>Welcome, <%=session.getAttribute("fullName")%></a></li>
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
			<li><a href="WelcomeAdmin.jsp">Home</a></li>
			<li>
				<div class="dropdown">
					<a href="#">Services</a>
					<div class="dropdown-content">
						<% if (session.getAttribute("userType")==null || session.getAttribute("userType").equals("a")){%>
						<a href="Controller?action=GenerateReport1">Generate Report 1</a>
						<a href="Controller?action=GenerateReport2">Generate Report 2</a>
						<a href="Controller?action=GenerateReport3">Generate Report 3</a>
						<a href="Refresh.jsp">Refresh Giving Balance</a>
						
						<%}else{ %>
						<a href="ErrorPage.jsp">Generate Report 1</a>
						<a href="ErrorPage.jsp">Generate Report 2</a>
						<a href="ErrorPage.jsp">Generate Report 3</a>
						<a href="ErrorPage.jsp">Refresh Giving Balance</a>
						
						<%} %>
						
						
					</div>
				</div>
			</li>
			<li><a href="AboutAdmin.jsp">About Us</a></li>
			<li><a href="Contact.jsp">Contact</a></li>
		</ul>
	</div>
	
	<div class="content">
		<h4 align="center">You are not authorized to view this page</h4>
		<div class="image">
			<img src="images\notAuthorized.png">
		</div>
		
		

	<h3>This Page is for Users Only</h3>
	</div>

	<div class="footer">
		<p>Copyright&copy; - Anuja Srivastava | MSITM | McCombs School of Business </p>
		<p>University Of Texas at Austin</p>
		<p>ALL RIGHTS RESERVED</p>
	</div>
</body>
</html>

</html>