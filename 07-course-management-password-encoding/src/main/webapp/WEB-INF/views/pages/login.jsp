<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: 100%;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

.alert {
	font-weight: bold;
	font-size: 15px;
	margin: auto 5px;
	text-align: center;
	padding: 4px;
	border-radius: 5px;
}

.alert-success {
	color: green;
	background-color: #bcf4b5;
}

.alert-error {
	color: red;
	background-color: #ffcccc;
}
/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>

	<!-- <h2>Login Form</h2> -->

	<c:url value="/login" var="loginUrl" />
	<form action="${loginUrl}" method="post">

		<div class="imgcontainer">
			<h3>Login</h3>
		</div>
		<c:if test="${param.error != null}">
			<div class="alert alert-error">
				Failed to login.
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
					Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</c:if>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div class="alert alert-success">${message }</div>
		</c:if>
		<c:if test="${param.status != null}">
			<div class="alert alert-error">${message }</div>
		</c:if>
		<div class="container">

			<label for="username"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="username" required> <label
				for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required>

			<button type="submit">Login</button>

			<label> <input type="checkbox" checked="checked"
				id="remember" name="remember-me"> Remember me
			</label> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
		<div class="container" style="background-color: #f1f1f1">

			<button type="button" onclick="window.location='signup'"
				class="cancelbtn">Signup</button>
			<!-- Don't have an account?.<span class="psw">Forgot <a href="signup" cla>Sign Up</a></span> -->
			<!-- <span class="psw">Forgot <a href="#">password?</a></span> -->
		</div>


	</form>

</body>
</html>



