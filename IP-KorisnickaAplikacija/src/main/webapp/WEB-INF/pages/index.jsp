<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
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
<title>IP korisnik</title>
</head>

<body
	onload='get();<%if (session.getAttribute("success") != null) {%>alert("<%=session.getAttribute("success")%>")<%}%>'
	onunload="clear()">
	<jsp:include page="header.jsp"></jsp:include>
		<div class="container bg-image-airport text-dark">
			<div class="row">
				<div class=" col-lg-6 border border-dark">
					<h5>
						<strong>Dolasci</strong>
					</h5>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead class="thead-light">
								<tr>
									<th>Polazna lokacija</th>
									<th>Odrediste</th>
									<th>Vrijeme polijetanja</th>
									<th>Vrijeme slijetanja</th>
									<th>Status</th>
									<th>Vrsta leta</th>
								</tr>
							</thead>
							<tbody class="" id="arrivals">
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-lg-6 border border-dark">
					<h5>
						<strong>Odlasci</strong>
					</h5>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Polazna lokacija</th>
									<th>Odrediste</th>
									<th>Vrijeme polijetanja</th>
									<th>Vrijeme slijetanja</th>
									<th>Status</th>
									<th>Vrsta leta</th>
									<th>
								</tr>
							</thead>
							<tbody id="departures">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="container text-white">
			<div class="row">
				<div class="col-lg-6">
					<div id="map">
						<script defer
							src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDzGfKRK0un3Ys6HAszW3itFsfkO3pd5UI&callback=initMap">
						
					</script>
					</div>
					<br />
				</div>
				<div class="col-lg-6">
					<div class="row">
						<div class="col-12 border-left-warning">
							<p>Tel: 051-535-210</p>
							<p>Fax: 051-535-210</p>
						</div>
						<div class="border-left-primary">
							<br />
							<p>Posaljite poruku:</p>
							<form method="post" action="?action=message">

								<div class="form-group">
									<span class="col-sm-6"><input class="form-control"
										type="email" name="email" id="email" placeholder="Email"
										required="required"
										pattern="[a-zA-Z][a-zA-Z0-9\-\_]+\@.+\.[a-zA-Z]+"></span>
								</div>
								<div class="form-group">
									<span class="col-sm-6"><input class="form-control"
										type="text" name="subject" id="subject" placeholder=Naslov
										required="required"></span>
								</div>
								<div class="form-group">
									<span class="col-sm-6"><textarea class="form-control"
											name="message" id="message" rows="4" placeholder="Poruka"
											required="required"></textarea></span>
								</div>
								<div>
									<p><%=session.getAttribute("notification")%></p>
								</div>
								<div class="form-group">
									<span class="col-sm-6"><input class="btn btn-primary"
										type="submit" value="Posalji"></span>
								</div>

							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>