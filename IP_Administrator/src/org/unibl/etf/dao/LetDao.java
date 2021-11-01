package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dto.Let;
import org.unibl.etf.dto.Lokacija;
import org.unibl.etf.dto.Putanja;


public class LetDao {

	private static String selectAll = "SELECT let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM let let join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id)";
	private static String insert = "insert into let (tip, putanja_id) values (?,?)";
	private static String updateLokacije= "update putanja set polazna_lokacija_id=?, odredisna_lokacija_id=? where id=?";
	private static String delete = "delete from let where id=?";
	private static String insertPutanja = "insert into putanja (polazna_lokacija_id, odredisna_lokacija_id) values (?,?)";
	private static String selectById = "SELECT let.id, let.tip, putanja.id, pol.id, pol.grad, pol.drzava, odr.id, odr.grad, odr.drzava FROM let let join putanja putanja on(let.putanja_id=putanja.id) join lokacija pol on(putanja.polazna_lokacija_id=pol.id) join lokacija odr on (putanja.odredisna_lokacija_id=odr.id) where let.id=?";
	
	public static List<Let> getLetovi(){
		Connection conn = null;
		List<Let> letovi = new ArrayList<Let>();
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				letovi.add(new Let(rs.getLong(1), rs.getString(2),
								new Putanja(rs.getLong(3), new Lokacija(rs.getLong(4), rs.getString(5), rs.getString(6)),
									new Lokacija(rs.getLong(7), rs.getString(8), rs.getString(9)))));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return letovi;
	}
	public static Let getLetById(Long id){
		Connection conn = null;
		Let let = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectById);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				let = new Let(rs.getLong(1), rs.getString(2),
						new Putanja(rs.getLong(3), new Lokacija(rs.getLong(4), rs.getString(5), rs.getString(6)),
								new Lokacija(rs.getLong(7), rs.getString(8), rs.getString(9))));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return let;
	}
	
	public static boolean insertLet(Let let) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insertPutanja, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, let.getPutanja().getPolaznaLokacija().getId());
			stmt.setLong(2, let.getPutanja().getOdredisnaLokacija().getId());
			
			stmt.executeUpdate();
			
			long id = 0l;
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next())
				id = rs.getLong(1);
			rs.close();
			stmt.close();
			
			stmt=conn.prepareStatement(insert);
			stmt.setString(1, let.getTip());
			stmt.setLong(2, id);
			
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
	
	public static boolean updateLokacije(Let let) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(updateLokacije);
			
			stmt.setLong(1, let.getPutanja().getPolaznaLokacija().getId());
			stmt.setLong(2, let.getPutanja().getOdredisnaLokacija().getId());
			stmt.setLong(3, let.getPutanja().getId());
			
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
	
	public static boolean delete(Let let) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(delete);
			
			stmt.setLong(1, let.getId());
			
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
