package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.ip_user.dto.Fligth;
import org.unibl.etf.ip_user.dto.Location;
import org.unibl.etf.ip_user.dto.Route;
import org.unibl.etf.ip_user.dto.Schedule;
import org.unibl.etf.ip_user.dto.Reservation;
import org.unibl.etf.ip_user.dto.User;


public class ReservationDao {

	private static String selectAllPassengerResForUser = "select p.id, r.status, r.razlog, r.datum_kreiranja, l.id, l.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, p.broj_mjesta, termin.id, termin.dan, termin.polazak, termin.dolazak from putnicka_rezervacija p natural join rezervacija r join let l on(r.let_id=l.id) join termin termin on(termin.id=r.termin_id) join putanja putanja on(l.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on(putanja.odredisna_lokacija_id=odr.id) where r.email=? order by r.datum_kreiranja";
	private static String selectAllCargoResResForUser = "select t.id, r.status, r.razlog, r.datum_kreiranja, l.id, l.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, t.opis_tereta, t.specifikacija, termin.id, termin.dan, termin.polazak, termin.dolazak from teretna_rezervacija t natural join rezervacija r join let l on(r.let_id=l.id) join termin termin on(termin.id=r.termin_id) join putanja putanja on(l.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on(putanja.odredisna_lokacija_id=odr.id) where email=? order by r.datum_kreiranja";
	private static String insert = "insert into rezervacija(status, datum_kreiranja, let_id, email, termin_id) values(\"Nova\",?,?,?, ?)";
	private static String insertPassengerRes = "insert into putnicka_rezervacija (id, broj_mjesta) values (?, ?)";
	private static String insertCargoRes = "insert into teretna_rezervacija (id, opis_tereta, specifikacija) values (?,?,?)";
	private static String update = "update rezervacija set status=\"Ponistena\" where id=?";

	public static List<Reservation> getReservationsForUser(User user) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();

			PreparedStatement stmt = null;
			boolean isPassenger = "Putnicki".equals(user.getAccount());

			if (isPassenger) {
				stmt = conn.prepareStatement(selectAllPassengerResForUser);
			} else
				stmt = conn.prepareStatement(selectAllCargoResResForUser);

			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Reservation res = null;
				if (isPassenger)
					res = new Reservation(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), user,
							new Fligth(rs.getLong(5), rs.getString(6),
								new Route(rs.getLong(7),	new Location(rs.getLong(8), rs.getString(9), rs.getString(10)),
									new Location(rs.getLong(11), rs.getString(12), rs.getString(13)))),
							rs.getInt(14), new Schedule(rs.getLong(15), rs.getDate(16), rs.getTime(17), rs.getTime(18), null, null));
				else
					res = new Reservation(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), user,
							new Fligth(rs.getLong(5), rs.getString(6),
									new Route(rs.getLong(7),	new Location(rs.getLong(8), rs.getString(9), rs.getString(10)),
										new Location(rs.getLong(11), rs.getString(12), rs.getString(13)))),
							rs.getString(14), rs.getString(15), new Schedule(rs.getLong(16), rs.getDate(17), rs.getTime(18), rs.getTime(19), null, null));

				reservations.add(res);
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return reservations;
	}

	public static boolean insertReservation(Reservation reservation) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			stmt.setDate(1, new Date(System.currentTimeMillis()));
			stmt.setLong(2, reservation.getFligth().getId());
			stmt.setString(3, reservation.getUser().getEmail());
			stmt.setLong(4, reservation.getSchedule().getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				long id = rs.getLong(1);
				rs.close();
				stmt.close();

				boolean isPassenger = "Putnicki".equals(reservation.getUser().getAccount());
				if (isPassenger) {
					PreparedStatement preparedStatement = conn.prepareStatement(insertPassengerRes);
					preparedStatement.setLong(1, id);
					preparedStatement.setInt(2, reservation.getSeat());
					
					preparedStatement.executeUpdate();
					preparedStatement.close();
				} else {
					PreparedStatement preparedStatement = conn.prepareStatement(insertCargoRes);
					preparedStatement.setLong(1, id);
					preparedStatement.setString(2, reservation.getDescription());
					preparedStatement.setString(3, reservation.getSpecification());

					preparedStatement.executeUpdate();
					preparedStatement.close();
				}
			}
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
	public static boolean changeStatus(long id) {
		Connection conn = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(update);
			
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			
			stmt.close();
			
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
}
