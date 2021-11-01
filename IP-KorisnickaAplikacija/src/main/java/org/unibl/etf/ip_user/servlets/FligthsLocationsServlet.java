package org.unibl.etf.ip_user.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.ip_user.beans.ReservationBean;
import org.unibl.etf.ip_user.dto.Schedule;

import com.google.gson.Gson;

/**
 * Servlet implementation class FligthsLocationServlet
 */
@WebServlet("/fligths-locations")
public class FligthsLocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FligthsLocationsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ReservationBean bean;
		String result = "";

		String account = request.getParameter("account");
		if ("Putnicki".equals(account) || "Teretni".equals(account)) {

			if (session.isNew()) {
				bean = new ReservationBean(account);
			} else if ((bean = (ReservationBean) session.getAttribute("bean")) == null) {
				bean = new ReservationBean(account);
			}
			Gson gson = new Gson();
			String action = request.getParameter("action");

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			if ("getStartCities".equals(action)) {
				String country = request.getParameter("country");

				if (country != null) {
					bean.setStartCities(country);
					result = gson.toJson(bean.startCities);
				}
			} else if ("getEndCountries".equals(action)) {
				String country = request.getParameter("country");
				String city = request.getParameter("city");
				if (country != null && city != null) {
					bean.setEndCountries(country, city);
					result = gson.toJson(bean.endCountries);
				}
			} else if ("getEndCities".equals(action)) {
				String country = request.getParameter("country");
				if (country != null) {
					bean.setEndCities(country);
					result = gson.toJson(bean.endCities);
				}
			} else if ("schedules".equals(action)) {
				String startCountry = request.getParameter("startCountry");
				String startCity = request.getParameter("startCity");
				String endCountry = request.getParameter("endCountry");
				String endCity = request.getParameter("endCity");

				if (startCountry != null && startCity != null && endCountry != null && endCity != null) {
					List<Schedule> schedules = bean.getFligthSchedules(startCountry, startCity, endCountry, endCity);

					if (schedules.size() > 0) {
						result = gson.toJson(schedules);
					} else {
						result = "Trazeni let nema definisanih termina.";
						response.setStatus(400);
					}
				}
			}

			session.setAttribute("bean", bean);
		} else {
			result = "Vrsta naloga nije ispravna.";
			response.setStatus(400);
		}

		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
