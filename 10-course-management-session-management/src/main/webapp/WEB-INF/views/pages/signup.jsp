<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f4efef;
}

form {
	border: 3px solid #b7b5b5;
	width: 30%;
	margin: auto;
	background-color: #fff;
	top: 100px;
}

.error {
	color: red;
}

* {
	box-sizing: border-box
}
/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Set a style for all buttons */
button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
	padding: 14px 20px;
	background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
	float: left;
	width: 50%;
}

/* Add padding to container elements */
.container {
	padding: 16px;
}

/* The Modal (background) */
.modal {
	display: block; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: #474e5d;
	padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 50%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* The Close Button (x) */
.close {
	position: absolute;
	right: 35px;
	top: 15px;
	font-size: 40px;
	font-weight: bold;
	color: #f1f1f1;
}

.close:hover, .close:focus {
	color: #f44336;
	cursor: pointer;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .signupbtn {
		width: 100%;
	}
}
</style>
<body>

	<div id="id01">

		<form:form class="modal-content" action="signup" method="post"
			commandName="signupForm">
			<div class="container">
				<h1>Sign Up</h1>
				<p>Please fill in this form to create an account.</p>
				<hr>
				<label for="username"><b>Username</b></label>
				<form:errors path="username" cssClass="error" />
				<form:input placeholder="Enter User Name" path="username" />



				<label for="password"><b>Password</b></label>
				<form:errors path="password" cssClass="error" />
				<form:password placeholder="Enter Password" path="password" />

				<label for="repeatPassword"><b>Repeat Password</b></label>
				<form:errors path="repeatPassword" cssClass="error" />

				<form:password placeholder="Repeat Password" path="repeatPassword" />

				<label for="firstName"><b>First Name</b></label>
				<form:errors path="firstName" cssClass="error" />
				<form:input placeholder="Enter User Name" path="firstName" />


				<label for="email"><b>Email</b></label>
				<form:errors path="firstName" cssClass="error" />
				<form:input placeholder="Enter Email" path="email" />





				<!-- <label> <input type="checkbox" checked="checked"
					name="remember" style="margin-bottom: 15px"> Remember me
				</label> -->
	
				<!-- <p>
					By creating an account you agree to our <a href="#"
						style="color: dodgerblue">Terms & Privacy</a>.
				</p> -->

				<div class="clearfix">
					<button type="button" onclick="window.location='login'"
						class="cancelbtn">Login</button>
					<button type="submit" class="signupbtn">Sign Up</button>
				</div>
			</div>
		</form:form>
	</div>


</body>
</html>
