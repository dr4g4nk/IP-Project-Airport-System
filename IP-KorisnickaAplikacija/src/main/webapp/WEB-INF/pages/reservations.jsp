<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.unibl.etf.ip_user.dto.Reservation"%>
<jsp:useBean id="reservationBean"
	type="org.unibl.etf.ip_user.beans.ReservationBean" scope="session" />
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
<title>Sve rezervacije</title>
</head>
<body class="text-white">
	<jsp:include page="header.jsp"></jsp:include>

	<br />
	<div class="content flex-container">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-12">
					<h5>
						<strong>Rezervacije&nbsp;</strong>
					</h5>
					<div class="table-responsive">
						<table class="table table-striped table-white">
							<thead class="text-white">
								<tr>
									<th>Let</th>
									<th>Datum kreiranja</th>
									<th>Termin</th>
									<th>Status</th>
									<th>Razlog</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (Reservation reservation : reservationBean.getReservations()) {
								%>
								<tr>
									<td><%=reservation.getFligth()%></td>
									<td><%=reservation.getDate()%></td>
									<td><%=reservation.getSchedule()%></td>
									<td><%=reservation.getStatus()%></td>
									<td><%=reservation.getReason()%></td>
									<%
									if (reservation.getStatus().equals("Nova")) {
									%>
									<td><a
										href="?action=reservations&id=<%=reservation.getId()%>"
										class="btn btn-danger btn-sm">Ponisti</a></td>
									<%
									} else {
									%>
									<td>&nbsp;</td>
									<%
									}
									%>
								</tr>
								<%
								}
								%>
							
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>