<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
	<link rel="stylesheet" href="css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/style.css"> <!-- Resource style -->

	<title>Time Table | Score Nation</title>

	<script src="js/modernizr.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script>
      if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
	</script>
	<script src="js/main.js"></script> <!-- Resource jQuery -->

	<script>
      $(document).ready(function () {

        $("a").click(function(event) {
          var href = $(this).attr('href');
          if (typeof href === "undefined"){
             alert("i got it");
             return;
          }
          if(href != '#0'){
            $.post("${pageContext.request.contextPath}/ajax?hideTag=" + href, function(data, status){
              $("#somediv").html(data);
            });
            $.post("${pageContext.request.contextPath}/ajax?hideTag=" + href + "&status=true", function(data, status){
              $("#statusOfWork").text("Status : " + data);
            });
          }
        });

      });

	</script>

</head>
<body>

<div class="top-info"><a href = "${pageContext.request.contextPath}/addSchedule?query=add"><span>Add Schedule</span></a></div>
<div class="top-info"><a href = "${pageContext.request.contextPath}/addSchedule?query=search"><span>Update Schedule</span></a></div>
<div class="top-info"><a href = "${pageContext.request.contextPath}/addSchedule?query=delete"><span>Remove Schedule</span></a></div>
<div class="top-info"><a href = "${pageContext.request.contextPath}/addSchedule?query=move"><span>Move Schedule</span></a></div>

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
			<li><span>18:30</span></li>
			<li><span>19:00</span></li>
			<li><span>19:30</span></li>
			<li><span>20:00</span></li>
			<li><span>20:30</span></li>
			<li><span>21:00</span></li>
			<li><span>21:30</span></li>
			<li><span>22:00</span></li>
			<li><span>22:30</span></li>
			<li><span>23:00</span></li>
			<li><span>23:30</span></li>
			<li><span>24:00</span></li>
		</ul>
	</div> <!-- .timeline -->

	<div class="events">
		<ul>
			<li class="events-group">
				<div class="top-info"><span>Done</span></div>



				<ul>
			<c:forEach items="${map['Done']}" var="item">
					<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-1">
						<a href="${item.id}">
							<em class="event-name">${item.title}</em>
                            <b class="event-name">Start: ${item.startDate}</b>
                            <b class="event-name">End: ${item.endDate}</b>
						</a>
					</li>

			</c:forEach>

				</ul>
			</li>


			<li class="events-group">
				<div class="top-info"><span>InProgress</span></div>

				<ul>
					<c:forEach items="${map['InProgress']}" var="item">

						<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-3">
							<a href="${item.id}">
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
                            <a href="${item.id}">
                                <em class="event-name">${item.title}</em>
                                <b class="event-name">Start: ${item.startDate}</b>
                                <b class="event-name">End: ${item.endDate}</b>
                            </a>
                        </li>

                    </c:forEach>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Misc</span></div>

				<ul>
					<c:forEach items="${map['misc']}" var="item">

						<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-2">
							<a href="${item.id}">
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

				<ul>
					<c:forEach items="${map['meeting']}" var="item">
						<li class="single-event" data-start="${item.startTime}" data-end="${item.endTime}" data-content="event-abs-circuit" data-event="event-4">
							<a href="${item.id}">
								<em class="event-name">${item.title}</em>
								<b class="event-name">Start: ${item.startDate}</b>
								<b class="event-name">End: ${item.endDate}</b>
							</a>
						</li>

					</c:forEach>
				</ul>
			</li>
		</ul>
	</div>



	<div class="event-modal">
		<header class="header">
			<div class="content">
				<span class="event-date"></span>
				<h3 class="event-name"></h3>
				<h3 style="color:white;"><b id="statusOfWork"></b></h3>
			</div>

			<div class="header-bg"></div>
		</header>

		<div class="body">
			<div class="event-info" id="somediv"></div>
			<div class="body-bg"></div>
		</div>

		<a href="#0" class="close">Close</a>
	</div>


	<div class="cover-layer"></div>
</div> <!-- .cd-schedule -->
</body>
</html>
