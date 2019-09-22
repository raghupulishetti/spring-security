<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clearfix"></div>
<c:if test="${status ne null }">
	<div class="alert alert-success">${status}</div>
</c:if>

<div>
	<h2>Available Upcoming Courses</h2>

	<table id="courses">
		<tr>
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Descriptions</th>
			<th>Instructor</th>
			<th>Course Start Date</th>
			<th>Duration(In Days)</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${courses}" var="course">
			<td>${course.id}</td>
			<td>${course.name }</td>
			<td>${course.description }</td>
			<td>${course.instructor }</td>
			<td>${course.courseStartDate }</td>
			<td>${course.durationInDays }</td>
			<td><button class="button button2"
					onclick="window.location='enroll?courseId=${course.id}'">Enroll</button></td>
		</c:forEach>

	</table>
</div>