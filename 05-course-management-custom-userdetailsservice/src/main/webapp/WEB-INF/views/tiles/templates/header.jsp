<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<header>
	<div style="width: 100%; float: left;">
		<div  style="width: 75%; float: left;font-weight: bold;font-size: 30px">
			Raghu's Online Training
		</div>
		<div style="width: 20%; float: left;">
			Welcome
			<security:authentication property="name" />
		</div>
	</div>
</header>
<hr>