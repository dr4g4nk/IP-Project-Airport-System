package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dto.Lokacija;

public class LokacijaDao {

	private static String selectAll = "select * from lokacija";
	private static String insert = "insert into lokacija (grad, drzava) values (?,?)";
	private static String update = "update lokacija set grad=?, drzava=? where id=?";
	private static String delete = "delete from lokacija where id=?";
	private static String selectLokacijeByDrzava = "select * from lokacija where drzava=?";
	private static String selectById = "select * from lokacija where id=?";

	
	public static List<Lokacija> getLokacije(){
		return get(selectAll);
	}
	
	private static List<Lokacija> get(String query) {
		Connection conn = null;
		List<Lokacija> lokacije = new ArrayList<Lokacija>();

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lokacije.add(new Lokacija(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return lokacije;
	}

	public static boolean insertLokacija(Lokacija lokacija) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert);

			stmt.setString(1, lokacija.getGrad());
			stmt.setString(2, lokacija.getDrzava());
			stmt.executeUpdate();
			stmt.close();

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			return false;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}

	public static boolean updateLokacija(Lokacija oldLokacija, Lokacija newLokacija) {
		Connection conn = null;

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(update);

			stmt.setString(1, newLokacija.getGrad());
			stmt.setString(2, newLokacija.getDrzava());
			stmt.setLong(4, oldLokacija.getId());

			stmt.executeUpdate();
			stmt.close();

			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}

	public static boolean deleteLokacija(Lokacija lokacija) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(delete);
			stmt.setLong(1, lokacija.getId());
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

	public static List<Lokacija> getLokacijeByDrzava(String drzava) {
		Connection conn = null;
		List<Lokacija> lokacije = new ArrayList<Lokacija>();

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectLokacijeByDrzava);

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				lokacije.add(new Lokacija(rs.getInt(1), rs.getString(2), rs.getString(3)));
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return lokacije;
	}

	public static Lokacija getLokacijeById(int id) {
		Connection conn = null;
		Lokacija lokacija = null;

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectById);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				lokacija = new Lokacija(rs.getInt(1), rs.getString(2), rs.getString(3));
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return lokacija;
	}
}
