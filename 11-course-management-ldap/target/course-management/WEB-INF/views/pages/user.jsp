<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:if test="${param.status != null}">
		<div class="alert alert-error">${message }</div>
	</c:if>
	<h2>Your Enrolled Courses</h2>

	<table id="courses">
		<tr>
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Instructor</th>
			<th>Withdraw</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Spring MVC</td>
			<td>Raghu</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>
		<tr>
			<td>2</td>
			<td>Hibernate</td>
			<td>Ramu</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>
		<tr>
			<td>3</td>
			<td>Sprig Data JPA</td>
			<td>Sathish</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>
		<tr>
			<td>4</td>
			<td>Spring Boot</td>
			<td>Raghu</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>
		<tr>
			<td>5</td>
			<td>Struts</td>
			<td>Ravi</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>
		<tr>
			<td>6</td>
			<td>Spring Security</td>
			<td>Raghu</td>
			<td><button class="button button3">Withdraw</button></td>
		</tr>

	</table>
</div>
