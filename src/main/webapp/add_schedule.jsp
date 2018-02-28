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
	<form name="myForm" action="${pageContext.request.contextPath}/addSchedule?query=add" method="post">
	<div class="timeline">
		<ul>
			<li>
				User Name:
				<select name="userName">
					<c:forEach items="${userList}" var="item">
						<option value="${item}">${item}</option>
					</c:forEach>
				</select>
			</li>

			<li>
				Start Time:
				<select name="startTime">
					<option value="9:00">9:00</option>
					<option value="10:00">10:00</option>
					<option value="11:00">11:00</option>
					<option value="12:00">12:00</option>
					<option value="13:00">13:00</option>
					<option value="14:00">14:00</option>
					<option value="15:00">15:00</option>
					<option value="16:00">16:00</option>
					<option value="17:00">17:00</option>
					<option value="18:00">18:00</option>
					<option value="19:00">19:00</option>
					<option value="20:00">20:00</option>
					<option value="21:00">21:00</option>
					<option value="22:00">22:00</option>
					<option value="23:00">23:00</option>
					<option value="24:00">24:00</option>
				</select>
			</li>

			<li>
				End Time:
				<select name="endTime">
					<option value="9:00">9:00</option>
					<option value="10:00">10:00</option>
					<option value="11:00">11:00</option>
					<option value="12:00">12:00</option>
					<option value="13:00">13:00</option>
					<option value="14:00">14:00</option>
					<option value="15:00">15:00</option>
					<option value="16:00">16:00</option>
					<option value="17:00">17:00</option>
					<option value="18:00">18:00</option>
					<option value="18:00">19:00</option>
					<option value="19:00">19:00</option>
					<option value="20:00">20:00</option>
					<option value="21:00">21:00</option>
					<option value="22:00">22:00</option>
					<option value="23:00">23:00</option>
					<option value="24:00">24:00</option>
				</select>
				Do not select the same time as StartTime
			</li>
			<li>
				<input type="text" placeholder="Title" name="title" size="40">
			</li>

			<li>
				<input type="text" placeholder="start date in DD/MM/YYYY" name="startDate" size="40">
			</li>

			<li>
				<input type="text" placeholder="end date in DD/MM/YYYY" name="endDate" size="40">
			</li>

			<li>
				Status:
				<select name="workStatus">
					<c:forEach items="${workStatusList}" var="item">
						<option value="${item}">${item}</option>
					</c:forEach>
				</select>
			</li>

			<li>
				<textarea rows="3" cols="50" name="description" placeholder="Description">
				</textarea>
			</li>
			<li>
				<input type="submit">
			</li>


		</ul>
	</div> <!-- .timeline -->

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