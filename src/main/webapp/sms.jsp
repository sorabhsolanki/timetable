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
	<form name="myForm" action="${pageContext.request.contextPath}/addSchedule?query=sms" method="post">
	<div class="timeline">
		<ul>
			<li>
				<input type="text" placeholder="message" name="message" size="40">
			</li>

			<li>
				<input type="text" placeholder="phone" name="phone" size="40">
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