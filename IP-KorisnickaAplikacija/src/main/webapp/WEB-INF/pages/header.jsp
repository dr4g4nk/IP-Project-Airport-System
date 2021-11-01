<jsp:useBean id="userBean" type="org.unibl.etf.ip_user.beans.UserBean"
	scope="session" />

<header class="p-3 bg-dark text-white">
		<div class="row d-flex flex-grow justify-content-center">
			<div class="col-7 flex-column">
				<jsp:include page="header_navbar.jsp" />
			</div>
			<div class="col-5 d-flex justify-content-end">
				<%
				if (userBean.isLoggedIn()) {
				%>
				<form method="post" action="?action=logout" accept-charset="utf-8">
					<div class="mb-3">
						<div><%=userBean.getUser().getName()%>
							<%=userBean.getUser().getSurname()%></div>
						<input class="btn btn-primary btn-sm float-end" type="submit"
							value="Logout">
					</div>
				</form>
				<%
				} else {
				%>
				<form method="post" action="?action=login" accept-charset="utf-8">
					<div class="mb-3 float-right">
						<input class="form-control form-control-sm" type="text"
							name="email" id="email" placeholder="Email"> <input
							class="form-control form-control-sm" type="password"
							name="password" id="password" placeholder="Lozinka"> <input
							class="btn btn-primary btn-sm float-end" type="submit"
							value="Login">
						<div class="form-control-sm">
							<a href="?action=registration">Registruj se</a>
						</div>
					</div>

				</form>
				<%
				}
				%>
			</div>
		</div>
	</header>