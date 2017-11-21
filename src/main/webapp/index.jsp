<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
	<link rel="stylesheet" href="css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/style.css"> <!-- Resource style -->

	<title>Schedule Template | CodyHouse</title>
</head>
<body>
<div class="top-info"><a href = "${pageContext.request.contextPath}/addSchedule"><span>Add Schedule</span></a></div>
<div class="cd-schedule loading">
	<div class="timeline">
		<ul>
			<li><span>09:00</span></li>
			<li><span>09:30</span></li>
			<li><span>10:00</span></li>
			<li><span>10:30</span></li>
			<li><span>11:00</span></li>
			<li><span>11:30</span></li>
			<li><span>12:00</span></li>
			<li><span>12:30</span></li>
			<li><span>13:00</span></li>
			<li><span>13:30</span></li>
			<li><span>14:00</span></li>
			<li><span>14:30</span></li>
			<li><span>15:00</span></li>
			<li><span>15:30</span></li>
			<li><span>16:00</span></li>
			<li><span>16:30</span></li>
			<li><span>17:00</span></li>
			<li><span>17:30</span></li>
			<li><span>18:00</span></li>
		</ul>
	</div> <!-- .timeline -->

	<div class="events">
		<ul>
			<li class="events-group">
				<div class="top-info"><span>Akhilesh</span></div>



				<ul>
			<c:forEach items="${map['akhilesh']}" var="item">
					<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">${item.title}</em>
                                                       <b class="event-name">Start: ${item.startDate}</b>
                                                       <b class="event-name">End: ${item.endDate}</b>
						</a>
					</li>

			</c:forEach>

				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Diwakar</span></div>

				<ul>
<c:forEach items="${map['diwakar']}" var="item">

	<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-2">
		<a href="#0">
			<em class="event-name">${item.title}</em>
			<b class="event-name">Start: ${item.startDate}</b>
			<b class="event-name">End: ${item.endDate}</b>
		</a>
	</li>

</c:forEach>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Rachit</span></div>

				<ul>
					<c:forEach items="${map['rachit']}" var="item">

						<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-3">
							<a href="#0">
								<em class="event-name">${item.title}</em>
								<b class="event-name">Start: ${item.startDate}</b>
								<b class="event-name">End: ${item.endDate}</b>
							</a>
						</li>

					</c:forEach>

				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Sorabh</span></div>

				<ul>
                    <c:forEach items="${map['sorabh']}" var="item">

                        <li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-4">
                            <a href="#0">
                                <em class="event-name">${item.title}</em>
                                <b class="event-name">Start: ${item.startDate}</b>
                                <b class="event-name">End: ${item.endDate}</b>
                            </a>
                        </li>

                    </c:forEach>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Meeting</span></div>

			</li>
		</ul>
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

		<a href="#0" class="close">Close</a>
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
