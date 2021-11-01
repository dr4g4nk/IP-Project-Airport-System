package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.ip_user.dto.Fligth;
import org.unibl.etf.ip_user.dto.Location;
import org.unibl.etf.ip_user.dto.Route;

public class FligthDao {
	private static String selectAll = "SELECT let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM etfbl_ip_aero.let let join putanja putanja on (putanja.id=let.putanja_id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where let.tip=?";

	public static List<Fligth> getFligths(String type) {
		Connection conn = null;
		List<Fligth> fligths = new ArrayList<Fligth>();

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fligths.add(new Fligth(rs.getLong(1), rs.getString(2),
						new Route(rs.getLong(3), new Location(rs.getLong(4), rs.getString(5), rs.getString(6)),
								new Location(rs.getLong(7), rs.getString(8), rs.getString(9)))));
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return fligths;
	}

}
