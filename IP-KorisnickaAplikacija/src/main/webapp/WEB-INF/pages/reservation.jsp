<%@page import="org.unibl.etf.ip_user.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="reservationBean"
	type="org.unibl.etf.ip_user.beans.ReservationBean" scope="session"></jsp:useBean>
<jsp:useBean id="userBean" type="org.unibl.etf.ip_user.beans.UserBean"
	scope="session"></jsp:useBean>

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
<script src="js/reservation.js"></script>
<link href="css/style.css" rel="stylesheet">
<title>Rezervacija leta</title>
</head>

<body
	onload="getStartCities('<%=reservationBean.getUser().getAccount()%>')">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="content flex-container">
		<div class="container text-white">
			<form method="post" action="?action=reservation"
				enctype="multipart/form-data">

				<div class="conteiner form-group">
					<h5 class="ms-3 ps-5">Polazna lokacija</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"><label
								class="form-control text-white bg-transparent border-0">Drzava&nbsp;</label></span>
							<span class="col-6"><select class="form-control"
								name="startCountry" id="startCountry" required="required"
								onchange="getStartCities('<%=reservationBean.getUser().getAccount()%>')">
									<%
									for (String country : reservationBean.startCountries) {
									%>
									<option><%=country%></option>
									<%
									}
									%>
							</select></span>
						</div>
					</div>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"><label
								class="form-control text-white bg-transparent border-0">Grad&nbsp;</label></span>
							<span class="col-6"><select class="form-control"
								name="startCity" id="startCity" required="required"
								onchange="getEndCountries('<%=reservationBean.getUser().getAccount()%>')">
							</select></span>
						</div>
					</div>
				</div>
				<br />

				<div class="conteiner form-group">
					<h5 class="ms-3 ps-5">Odrediste</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"><label
								class="form-control text-white bg-transparent border-0">Drzava&nbsp;</label></span>
							<span class="col-6"><select class="form-control"
								name="endCountry" id="endCountry" required="required"
								onchange="getEndCities('<%=reservationBean.getUser().getAccount()%>')">
							</select></span>
						</div>
					</div>

					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"><label
								class="form-control text-white bg-transparent border-0">Grad&nbsp;</label></span>
							<span class="col-6"><select class="form-control"
								name="endCity" id="endCity" required="required">
							</select></span>
						</div>
					</div>
				</div>
				<br />
				<div class="container form-group">
					<h5 class="ms-3 ps-5">Termini</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"></span> <span class="col-6"><select
								class="form-control" name="schedule" id="schedule" required="required">
							</select></span>
						</div>
					</div>
				</div>
				<br />
				<%
				if (userBean.getUser().getAccount().equals("Putnicki")) {
				%>
				<div class="container form-group">
					<h5 class="ms-3 ps-5">Broj mjesta</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"></span> <span class="col-6"><input
								type="number" class="form-control" name="seat" id="seat"
								required="required"></span>
						</div>
					</div>
				</div>
				<br />
				<%
				} else {
				%>
				<div class="container form-group">
					<h5 class="ms-3 ps-5">Opis robe</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"></span> <span class="col-md-6"><textArea
									rows="5" class="form-control" name="description"
									id="description" required="required"></textArea></span>
						</div>
					</div>
				</div>

				<br />
				<div class="container form-group">
					<h5 class="ms-3 ps-5">Specifikacija</h5>
					<div class="row">
						<div class="form-group d-flex justify-content-center">
							<span class="col-2 col-sm-2"></span> <span class="col-6"><input
								type="file" class="form-control" name="specification"
								id="specification" required="required"></span>
						</div>
					</div>
				</div>
				<br />
				<%
				}
				%>
				<div class="form-group d-flex justify-content-center">
					<p class="text-danger"><%=session.getAttribute("notification")%></p>
				</div>

				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-3"><input
							class="form-control btn btn-success" type="submit"
							value="Rezervisi"></span>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>