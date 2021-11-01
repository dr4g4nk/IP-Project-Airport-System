package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.unibl.etf.ip_user.dto.Fligth;
import org.unibl.etf.ip_user.dto.Location;
import org.unibl.etf.ip_user.dto.Route;
import org.unibl.etf.ip_user.dto.Schedule;

public class ScheduleDao {

	private static String select5Departures = "SELECT termin.id, termin.dan, termin.polazak, termin.dolazak, let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, termin.status FROM termin termin join let let on(termin.l_id=let.id) join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where pol.id=1 and termin.dan=? and termin.polazak>? and let.tip=\"Putnicki\" order by termin.polazak limit 5";
	private static String select5Arrivals = "SELECT termin.id, termin.dan, termin.polazak, termin.dolazak, let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, termin.status FROM termin termin join let let on(termin.l_id=let.id) join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where odr.id=1  and termin.dan=? and termin.dolazak>? and let.tip=\"Putnicki\" order by termin.dolazak limit 5";

	private static String selectDepartures = "SELECT termin.id, termin.dan, termin.polazak, termin.dolazak, let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, termin.status FROM termin termin join let let on(termin.l_id=let.id) join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where pol.id=1 and termin.dan=? order by termin.polazak";
	private static String selectArrivals = "SELECT termin.id, termin.dan, termin.polazak, termin.dolazak, let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava, termin.status FROM termin termin join let let on(termin.l_id=let.id) join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where odr.id=1  and termin.dan=? order by termin.dolazak";
	private static String updateFlew ="update termin set `status`=\"Poletio\" where `status`!=\"Sletio\" and dan<? or(dan=? and polazak<?)";
	private static String updateLanded = "update termin set `status`=\"Sletio\" where `status`!=\"Sletio\" and dan<? or (dan=? and dolazak<?)";
	private static String selectByFligthId ="SELECT termin.id, termin.dan, termin.polazak, termin.dolazak, termin.l_id, termin.status FROM termin termin where termin.l_id=? and termin.dan>? order by termin.dan, termin.polazak";
	
	
	public static List<Schedule> getSchedules(Fligth fligth){
		List<Schedule> schedules = new ArrayList<Schedule>();
		Connection conn = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectByFligthId);
			
			stmt.setLong(1, fligth.getId() );
			stmt.setDate(2, new Date(System.currentTimeMillis()));

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				schedules.add(new Schedule(rs.getLong(1), rs.getDate(2), rs.getTime(3), rs.getTime(4), fligth, rs.getString(6)));
			}
			
		}catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return schedules;
	}
	
	public static void updateAll() {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(updateFlew);

			Date date = new Date(System.currentTimeMillis());
			Time time = new Time(System.currentTimeMillis());
			stmt.setDate(1, date);
			stmt.setDate(2, date);
			stmt.setTime(3, time);
			
			stmt.executeUpdate();
			stmt.close();
			
			stmt=conn.prepareStatement(updateLanded);
			stmt.setDate(1, date);
			stmt.setDate(2, date);
			stmt.setTime(3, time);
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
	private static List<Schedule> get(String query, Date date){
		List<Schedule> schedules = new ArrayList<Schedule>();
		Connection conn = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDate(1, date);
			Time time = new Time(date.getTime());

			if(query.equals(select5Arrivals) || query.equals(select5Departures))
				stmt.setTime(2, time);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				schedules.add(new Schedule(rs.getLong(1), rs.getDate(2), rs.getTime(3), rs.getTime(4), new Fligth(rs.getLong(5), rs.getString(6),
					new Route(rs.getLong(7),	new Location(rs.getLong(8), rs.getString(9), rs.getString(10)),
						new Location(rs.getLong(11), rs.getString(12), rs.getString(13)))),rs.getString(14)));
			}
			
		}catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return schedules;
	}
	public static List<Schedule> get5Arrivals(Date date){
		return get(select5Arrivals, date);
	}
	public static List<Schedule> get5Departures(Date date){
		return get(select5Departures, date);
	}
	
	public static List<Schedule> getArrivals(Date date) {
		return get(selectArrivals, date);
	}
	
	public static List<Schedule> getDepartures(Date date){
		return get(selectDepartures, date);
	}
}
