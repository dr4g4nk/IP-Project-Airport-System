<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userBean" type="org.unibl.etf.ip_user.beans.UserBean"
	scope="session" />


<h1>ETFBL IP Aero</h1>
<small>Kompanija za avio prevoz putnika i robe</small>

<div class="row">
	<ul
		class="nav nav-bar col-xs-4 justify-content-start">
		<li><a href="?action=arrivals" class="nav-link px-2 text-white">Svi
				dolasci</a></li>
		<li><a href="?action=departures" class="nav-link px-2 text-white">Svi
				odlasci</a></li>
		<%
		if (userBean.isLoggedIn()) {
		%>
		<li><a href="?action=reservation"
			class="nav-link px-2 text-white">Rezervacija leta</a></li>
		<li><a href="?action=reservations"
			class="nav-link px-2 text-white">Sve rezervacije</a></li>
		<%
		}
		%>
	</ul>
</div>