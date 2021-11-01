package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.dto.Lokacija;
import org.unibl.etf.dto.Putanja;

public class PutanjaDao {
	
	private static String selectAll = "SELECT putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM putanja putanja join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id)";
	private static String insert = "insert into putanja (polazna_lokacija_id, odredisna_lokacija_id) values (?,?)";
	private static String delete = "delete from putanja where id=?";
	private static String selectById = "SELECT putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM putanja putanja join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where putanja.id=?";
	private static String update= "update putanja set polazna_lokacija_id=?, odredisna_lokacija_id=? where id=?";
	public static String selectByLokacije = "select putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM putanja putanja join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where pol.drzava=? and pol.grad=? and odr.drzava=? and odr.grad=?";

	public static List<Putanja> getPutanje(){
		Connection conn = null;
		List<Putanja> putanje = new ArrayList<Putanja>();
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				putanje.add(new Putanja(rs.getLong(1), new Lokacija(rs.getLong(2), rs.getString(3), rs.getString(4)),
									new Lokacija(rs.getLong(5), rs.getString(6), rs.getString(7))));
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return putanje;
	}
	
	public static Putanja getPutanjaById(Long id){
		Connection conn = null;
		Putanja putanja = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectById);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				putanja = new Putanja(rs.getLong(1), new Lokacija(rs.getLong(2), rs.getString(3), rs.getString(4)),
						new Lokacija(rs.getLong(5), rs.getString(6), rs.getString(7)));
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return putanja;
	}
	
	public static Putanja check(String polaznaDrzava, String polazniGrad, String odredisnaDrzava, String odredisniGrad){
		Connection conn = null;
		Putanja putanja = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectByLokacije);
			stmt.setString(1, polaznaDrzava);
			stmt.setString(2, polazniGrad);
			stmt.setString(3, odredisnaDrzava);
			stmt.setString(4, odredisniGrad);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				putanja = new Putanja(rs.getLong(1), new Lokacija(rs.getLong(2), rs.getString(3), rs.getString(4)),
						new Lokacija(rs.getLong(5), rs.getString(6), rs.getString(7)));
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return putanja;
	}
	
	public static boolean insertPutanja(Putanja putanja) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert);
			stmt.setLong(1, putanja.getPolaznaLokacija().getId());
			stmt.setLong(2, putanja.getOdredisnaLokacija().getId());
			
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
	
	public static boolean updateLokacije(Putanja putanja) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(update);
			
			stmt.setLong(1, putanja.getPolaznaLokacija().getId());
			stmt.setLong(2, putanja.getOdredisnaLokacija().getId());
			stmt.setLong(3, putanja.getId());
			
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
	
	public static void delete(Putanja putanja) {
		Connection conn = null;

		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(delete);
			stmt.setLong(1, putanja.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}

}
