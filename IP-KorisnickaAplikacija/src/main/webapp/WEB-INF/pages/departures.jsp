<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous"></script>
<script src="js/script.js"></script>
<link href="css/style.css" rel="stylesheet">
<title>Svi dolasci</title>
</head>
<body class="text-white" onload='getTodayDeparturesFligths()'
	onunload="clear()">
	<jsp:include page="header.jsp"></jsp:include>

	<br />
	<div class="content">
		<div class="flex-container">
			<div class="container">
				<div class="row d-flex justify-content-center">
					<div class="col-12">
						<div class="d-flex justify-content-center">
							<h4 class="col-10">
								<strong>Odlasci&nbsp;</strong><strong id="day">danas</strong>
							</h4>
							<div class="col-2 d-flex justify-content-end">
								<a href="xml/rss.xml" class="float-end"> <img
									src="images/rss.png" width="36" height="36">
								</a>
							</div>
						</div>
						<br />
						<div class="table-responsive">
							<table class="table table-striped table-white">
								<thead class="text-white">
									<tr>
										<th>Polazna lokacija</th>
										<th>Odrediste</th>
										<th>Vrijeme polijetanja</th>
										<th>Vrijeme slijetanja</th>
										<th>Status</th>
										<th>Vrsta leta</th>
									</tr>
								</thead>
								<tbody id="departures">


								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<button class="btn btn-primary float-start" id="yesterday"
					onclick="getYesterdayDeparturesFligths()">&lt;Juce</button>
				<button class="btn btn-primary float-end" id="tomorrow"
					onclick="getTomorrowDeparturesFligths()">Sutra&gt;</button>
			</div>
		</div>
	</div>
</body>
</html>