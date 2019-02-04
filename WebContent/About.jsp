<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
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
						<a href="Controller?action=GiveRewards">Give Rewards</a>
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
	<h3>... This Tool offers following Services ...</h3>
	<div class="image-container">
		<div class="image">
			<img src="images\user.png">
			<h3>User Login</h3>
		</div>
		<div class="image">
			<img src="images\transfer.png">
			<h3>Transfer Rewards</h3>
		</div>
		<div class="image">
			<img src="images\redeem.png">
			<h3>Redeem Rewards</h3>
		</div>
		<div class="image">
			<img src="images\admin.png">
			<h3>Admin Login - Fetch Reports for the user</h3>
		</div>
	</div>
</div>
	

	
	
	<div class="footer">
		<p>Copyright&copy; - Anuja Srivastava | MSITM | McCombs School of Business </p>
		<p>University Of Texas at Austin</p>
		<p>ALL RIGHTS RESERVED</p>
	</div>
</body>
</html>