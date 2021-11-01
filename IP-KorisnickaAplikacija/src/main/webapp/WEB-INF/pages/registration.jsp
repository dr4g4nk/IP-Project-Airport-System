<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<title>Registracija</title>
</head>
<body onload="getCountries()">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="content flex-container">
		<div class="container">
			<form method="post" action="?action=registration">
				<br />
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-md-4"><input class="form-control"
							type="text" name="name" id="name" placeholder="Ime"
							required="required"></span> <span class="col-2-sm">&nbsp;</span>
						<span class="col-md-4"><input class="form-control"
							type="text" name="surname" id="surname" placeholder="Prezime"
							required="required"></span>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-md-2"><label
							class="form-control text-white bg-transparent border-0">Drzava&nbsp;</label></span>
						<span class="col-md-6"><select class="form-control"
							name="country" id="country">
						</select></span>
					</div>
				</div>

				<br />
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-8"><textarea class="form-control"
								name="address" id="address" placeholder="Adresa"
								required="required"></textarea></span>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-8"><input class="form-control"
							type="email" name="email" id="email" placeholder="Email"
							required="required"></span>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-8"><input class="form-control"
							type="text" name="username" id="username"
							placeholder="Korisnicko ime" required="required"></span>
					</div>
				</div>
				<br />

				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-8"><input class="form-control"
							type="password" name="password" id="password"
							placeholder="Lozinka" required="required"></span>
					</div>
				</div>
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-8"><input class="form-control"
							type="password" name="passwordRepeated" id="passwordRepeated"
							placeholder="Ponovite lozinku" required="required"></span>
					</div>
				</div>
				<br />
				<div class="row d-flex justify-content-center">
					<div class="col-sm-8  btn-group" role="group">
						<input type="radio" class="btn-check" name="account"
							id="btnradio1" autocomplete="off" checked> <label
							class="btn btn-outline-warning" for="btnradio1">Putnicki
							nalog</label> <input type="radio" class="btn-check" name="account"
							id="btnradio2" autocomplete="off"> <label
							class="btn btn-outline-warning" for="btnradio2">Teretni
							nalog</label>
					</div>
				</div>
				<br />
				<div class="form-group d-flex justify-content-center">
					<p class="text-danger"><%=session.getAttribute("notification")%></p>
				</div>
				<div class="row">
					<div class="form-group d-flex justify-content-center">
						<span class="col-sm-4"><input
							class="form-control btn btn-success" type="submit"
							value="Registruj" required="required"></span>
					</div>
				</div>
				<br />
			</form>
		</div>
	</div>
</body>
</html>