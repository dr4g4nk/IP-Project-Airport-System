package org.unibl.etf.ip_user.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.unibl.etf.ip_user.beans.AccessBean;
import org.unibl.etf.ip_user.beans.MessageBean;
import org.unibl.etf.ip_user.beans.ReservationBean;
import org.unibl.etf.ip_user.beans.UserBean;
import org.unibl.etf.ip_user.dao.ScheduleDao;
import org.unibl.etf.ip_user.dto.Access;
import org.unibl.etf.ip_user.dto.Message;
import org.unibl.etf.ip_user.dto.Reservation;
import org.unibl.etf.ip_user.dto.Schedule;
import org.unibl.etf.ip_user.dto.User;

import com.google.gson.Gson;

@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private String UPLOAD_DIRECTORY = System.getProperty("user.home") + File.separator + "specifications";

	public Controller() {
		super();
	}

	static {
		Thread t = new Thread() {
			public void run() {
				ScheduleDao.updateAll();
				try {
					System.out.println("Spava");
					sleep(15 * 60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		t.setDaemon(true);
		t.start();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String page = "WEB-INF/pages/index.jsp";
		UserBean userBean = new UserBean();
		if (session.isNew()) {
			new AccessBean().addAccess(new Access(new Date(System.currentTimeMillis()), 1));
			session.setAttribute("userBean", userBean);
		} else if ((userBean = (UserBean) session.getAttribute("userBean")) != null) {
			if (!userBean.isLoggedIn() && action == null)
				new AccessBean().addAccess(new Access(new Date(System.currentTimeMillis()), 1));
		} else {
			System.out.println(userBean);
		}
		session.removeAttribute("success");
		session.setAttribute("notification", "");

		if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (userBean.login(email, password)) {
				session.setAttribute("userBean", userBean);
				page = "WEB-INF/pages/index.jsp";
			}
		} else if ("logout".equals(action)) {
			userBean = new UserBean();
			session.setAttribute("userBean", userBean);
			session.removeAttribute("reservationBean");
			page = "WEB-INF/pages/index.jsp";
		} else if ("registration".equals(action)) {
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String passwordRepeated = request.getParameter("passwordRepeated");
			String country = request.getParameter("country");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String account = request.getParameter("account");
			String address = request.getParameter("address");

			if (email != null && name != null && surname != null && country != null && username != null
					&& password != null && passwordRepeated != null && account != null && address != null) {
				if (password.equals(passwordRepeated)) {
					boolean emailAllowed = userBean.isEmailAllowed(email);
					boolean usernameAllowed = userBean.isUsernameAllowed(username);
					if (emailAllowed && usernameAllowed) {
						if (userBean.addUser(
								new User(email, username, password, name, surname, address, account, country))) {
							userBean.login(email, password);
							System.out.println("Uspjesna registracija");
							session.setAttribute("userBean", userBean);
							page = "WEB-INF/pages/index.jsp";
						}
					} else if (!emailAllowed && !usernameAllowed) {
						session.setAttribute("notification", "Email i korisnicko ime su zauzeti.");
					} else if (!emailAllowed) {
						session.setAttribute("notification", "Email je zauzet.");
						page = "WEB-INF/pages/registration.jsp";
					} else {
						session.setAttribute("notification", "Korisnicko ime je zauzeto.");
						page = "WEB-INF/pages/registration.jsp";
					}
				} else {
					session.setAttribute("notification", "Lozinke nisu iste.");
					page = "WEB-INF/pages/registration.jsp";
				}

			} else
				page = "WEB-INF/pages/registration.jsp";
		} else if ("message".equals(action)) {
			String email = request.getParameter("email");
			String subject = request.getParameter("subject");
			String message = request.getParameter("message");

			if (email != null && subject != null && message != null && !email.isBlank() && !subject.isBlank()
					&& !message.isBlank()) {
				MessageBean messageBean = new MessageBean();
				if (messageBean.addMessage(new Message(email, subject, message)))
					System.out.println("Poruka upisanna");
			} else
				session.setAttribute("notification", "Niste unijeli sve potrebne podatke.");
		} else if ("arrivals".equals(action)) {
			page = "WEB-INF/pages/arrivals.jsp";
		} else if ("departures".equals(action)) {
			page = "WEB-INF/pages/departures.jsp";
		} else if ("reservation".equals(action)) {
			session.removeAttribute("success");
			userBean = (UserBean) session.getAttribute("userBean");
			if (userBean != null && userBean.isLoggedIn()) {

				ReservationBean reservationBean;
				if ((reservationBean = (ReservationBean) session.getAttribute("reservationBean")) == null) {
					reservationBean = new ReservationBean(userBean.getUser());
					session.setAttribute("reservationBean", reservationBean);
				}

				String startCountry = request.getParameter("startCountry");
				String startCity = request.getParameter("startCity");
				String endCountry = request.getParameter("endCountry");
				String endCity = request.getParameter("endCity");
				String sch = request.getParameter("schedule");
				String seatString = request.getParameter("seat");
				String description = request.getParameter("description");

				boolean flag = true;

				if (startCountry != null && startCity != null && endCity != null && endCountry != null && sch != null
						&& (seatString != null || description != null)) {
					try {
						Schedule schedule = new Gson().fromJson(sch, Schedule.class);

						Reservation reservation = new Reservation();
						reservation.setSchedule(schedule);
						reservation.setFligth(schedule.getFligth());

						if (seatString != null) {
							int seat = Integer.parseInt(seatString);
							reservation.setSeat(seat);
						} else if (description != null) {
							File dir = new File(UPLOAD_DIRECTORY);
							if (!dir.exists())
								dir.mkdir();

							reservation.setDescription(description);

							Part filePart = request.getPart("specification"); // Retrieves <input
																				// type="specification"
																				// name="specification">
							String fileName = getSubmittedFileName(filePart); // MSIE fix.

							if (fileName != null) {
								InputStream fileContent = filePart.getInputStream();
								FileOutputStream stream = new FileOutputStream(new File(dir, fileName));
								stream.write(fileContent.readAllBytes());
								reservation.setSpecification(fileName);
							} else {
								session.setAttribute("notification", "Specifikacija nije proslijedjena.");
								page = "WEB-INF/pages/reservation.jsp";
								flag = false;
							}
						}
						if (flag && reservationBean.addReservation(reservation)) {
							page = "WEB-INF/pages/index.jsp";
							session.setAttribute("success", "Uspjesna rezervacija leta.");
						}

					} catch (Exception e) {
						System.err.println(e);
						session.setAttribute("notification", "Niste proslijedili odgovarajuci termin.");
					}
				}

				else
					page = "WEB-INF/pages/reservation.jsp";
			}
		} else if ("reservations".equals(action)) {
			userBean = (UserBean) session.getAttribute("userBean");
			if (userBean != null && userBean.isLoggedIn()) {
				String param = request.getParameter("id");
				ReservationBean reservationBean = new ReservationBean(userBean.getUser());
				if (param != null) {
					long id = Long.parseLong(param);
					reservationBean.changeStatus(id);
				}
				session.setAttribute("reservationBean", reservationBean);
				page = "WEB-INF/pages/reservations.jsp";
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
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

	private static String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}
}
