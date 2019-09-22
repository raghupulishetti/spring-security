<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize access="authenticated" var="isAuthenticated"></security:authorize>
<nav class="nav">
	<a href="${pageContext.request.contextPath}/"></a>
	<ul id="menu">



		<li><a href="${pageContext.request.contextPath}/home">Home</a></li>

		<security:authorize url="/user">
			<li><a href="${pageContext.request.contextPath}/user">User</a></li>
		</security:authorize>

		<security:authorize url="/admin">
			<li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
		</security:authorize>


		<c:if test="${isAuthenticated }">
			<li><a href='<spring:url value="/logout"/>'>Logout</a></li>
		</c:if>

		<c:if test="${!isAuthenticated }">
			<li><a href='<spring:url value="/login"/>'>Login</a></li>
		</c:if>
	</ul>
</nav>