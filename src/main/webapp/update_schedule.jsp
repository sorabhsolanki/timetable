<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
	<link rel="stylesheet" href="css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/style.css"> <!-- Resource style -->

	<title>Time Table</title>
</head>
<body>
<div class="cd-schedule loading">
	<form name="myForm" action="${pageContext.request.contextPath}/addSchedule?query=update" method="post">
	<div class="timeline">
		<ul>
			<li>
				User Name:
				<input type="text" placeholder="User Name" name="sourceUserName" size="40" value="${sourceUserName}" readonly>
			</li>

			<li>

				Start Time:
				<select name='startTime'>
					<option value="${startTimeSelected}" selected>${startTimeSelected}</option>
					<c:forEach items="${startTimes}" var="role">
						<c:if test="${role != startTimeSelected}">
							<option value="${role}">${role}</option>
						</c:if>
					</c:forEach>
				</select>
			</li>

			<li>
				End Time:
				<select name='endTime'>
					<option value="${endTimeSelected}" selected>${endTimeSelected}</option>
					<c:forEach items="${endTimes}" var="role">
						<c:if test="${role != endTimeSelected}">
							<option value="${role}">${role}</option>
						</c:if>
					</c:forEach>
				</select>

				Do not select the same time as StartTime
			</li>
			<li>
				<input type="text" placeholder="Title" name="title" size="40" value="${title}">
			</li>

			<li>
				<input type="text" placeholder="start date in DD/MM/YYYY" name="startDate" size="40" value="${startDate}">
			</li>

			<li>
				<input type="text" placeholder="end date in DD/MM/YYYY" name="endDate" size="40" value="${endDate}">
			</li>

			<li>
				<textarea rows="3" cols="50" name="description" placeholder="Description">
					${description}
				</textarea>
			</li>
			<li>
				Status:
				<select name="workStatus">
					<option value="${workStatusSelected}" selected>${workStatusSelected}</option>
					<c:forEach items="${workStatusList}" var="item">
						<option value="${item}">${item}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<input type="submit">
			</li>


		</ul>
	</div> <!-- .timeline -->
		<input type="hidden" name="scheduleId" value="${scheduleId}">
	</form>

</div>


<div class="event-modal">

	<header class="header">
		<div class="content">
			<span class="event-date"></span>
			<h3 class="event-name"></h3>
		</div>

		<div class="header-bg"></div>
	</header>

	<div class="body">
		<div class="event-info"></div>
		<div class="body-bg"></div>
	</div>

</div>


<div class="cover-layer"></div>
</div> <!-- .cd-schedule -->
<script src="js/modernizr.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script>
  if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>