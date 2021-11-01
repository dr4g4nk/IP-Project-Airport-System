package org.unibl.etf.ip_user.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.unibl.etf.ip_user.beans.ScheduleBean;
import org.unibl.etf.ip_user.dto.Schedule;

import com.google.gson.Gson;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScheduleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		String day = request.getParameter("day");
		ScheduleBean scheduleBean = new ScheduleBean();
		List<Schedule> schedules = new ArrayList<Schedule>();

		if ("today".equals(day)) {
			if ("arrivals5".equals(action)) {
				schedules = scheduleBean.get5Arrivals(new Date(System.currentTimeMillis()));
			} else if ("departures5".equals(action)) {
				schedules = scheduleBean.get5Departures(new Date(System.currentTimeMillis()));
			} else if ("arrivals".equals(action)) {
				schedules = scheduleBean.getArrivals(new Date(System.currentTimeMillis()));
			} else if ("departures".equals(action)) {
				schedules = scheduleBean.getDepartures(new Date(System.currentTimeMillis()));
			}
		} else if("yesterday".equals(day)) {
			if ("arrivals".equals(action)) {
				schedules = scheduleBean.getArrivals(new Date(System.currentTimeMillis() - 24*3600*1000));
			} else if ("departures".equals(action)) {
				schedules = scheduleBean.getDepartures(new Date(System.currentTimeMillis() - 24*3600*1000));
			}
		}else if("tomorrow".equals(day)) {
			if ("arrivals".equals(action)) {
				schedules = scheduleBean.getArrivals(new Date(System.currentTimeMillis() + 24*3600*1000));
			} else if ("departures".equals(action)) {
				schedules = scheduleBean.getDepartures(new Date(System.currentTimeMillis() + 24*3600*1000));
			}
		}
		
		Gson gson = new Gson();

		String result = gson.toJson(schedules);
		response.setContentType("application/json");
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
