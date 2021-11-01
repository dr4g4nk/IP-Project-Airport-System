package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.dto.Zaposleni;


public class ZaposleniDao {

	private static String selectAllZaposleni = "select * from zaposleni where role=\"ROLE_USER\"";
	private static String selectByIdZaposleni = "select * from zaposleni where korisnicko_ime=?";
	private static String insert = "insert into zaposleni (korisnicko_ime, lozinka, ime, prezime, role, enabled) values (?,?,?,?,\"ROLE_USER\",?)";
	private static String update = "update zaposleni set ime=?, prezime=?, enabled=? where korisnicko_ime=?";
	private static String delete = "delete from zaposleni where korisnicko_ime=?";
	private static String updatePass = "update zaposleni set lozinka=? where korisnicko_ime=?";
	private static String selectAdministratorById = "select * from zaposleni where korisnicko_ime=? and role=\"ROLE_ADMIN\" and enabled=true";
	
	public static Zaposleni getAdministrator(String korisnickoIme) {
		Zaposleni administrator = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAdministratorById);
			stmt.setString(1, korisnickoIme);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				administrator = new Zaposleni(rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(2));
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return administrator;
	}
	
	public static List<Zaposleni> getZaposlene(){
		List<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAllZaposleni);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				zaposleni.add(new Zaposleni(rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(2), rs.getBoolean(6)));
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return zaposleni;
	}
	
	public static Zaposleni getZaposleniById(String korisnickoIme){
		Zaposleni zaposleni = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectByIdZaposleni);
			stmt.setString(1, korisnickoIme);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				zaposleni = new Zaposleni(rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(2), rs.getBoolean(6));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
		return zaposleni;
	}

	public static boolean insertZaposleni(Zaposleni zaposleni) {
		Connection conn = null;
		zaposleni.generateHash();
		try {
			conn= ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert);
			stmt.setString(1, zaposleni.getKorisnickoIme());
			stmt.setString(2, zaposleni.getLozinka());
			stmt.setString(3, zaposleni.getIme());
			stmt.setString(4, zaposleni.getPrezime());
			stmt.setBoolean(5, zaposleni.isEnabled());
			
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
	
	public static boolean updateZaposleni(Zaposleni newZaposleni, Zaposleni oldZaposleni) {
		newZaposleni.generateHash();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, newZaposleni.getIme());
			stmt.setString(2, newZaposleni.getPrezime());
			stmt.setBoolean(3, newZaposleni.isEnabled());
			stmt.setString(4, oldZaposleni.getKorisnickoIme());
			
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
	
	public static boolean deleteZaposleni(Zaposleni zaposleni) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(delete);
			stmt.setString(1, zaposleni.getKorisnickoIme());
			
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
	
	public static boolean updateLozinka(Zaposleni zaposleni) {
		zaposleni.generateHash();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(updatePass);
			stmt.setString(1, zaposleni.getLozinka());
			stmt.setString(2, zaposleni.getKorisnickoIme());
			
			int num = stmt.executeUpdate();
			stmt.close();
			
			if(num == 1) {
				System.out.println("Promjena lozinke");
				return true;
			}
		} catch (Exception e) {
			System.err.println(e);
			return false;
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return false;
	}

}
