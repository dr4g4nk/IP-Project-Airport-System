package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.unibl.etf.dto.Korisnik;

public class KorisnikDao {

	private static String selectAll = "select * from korisnik";
	private static String selectByEmail = "select * from korisnik where email=?";
	private static String selectByKorisnickoIme = "select * from korisnik where korisnicko_ime=?";
	private static String insert = "insert into korisnik (email, korisnicko_ime, lozinka, ime, prezime, adresa, vrsta_naloga, drzava) values (?,?,?,?,?,?,?,?)";
	private static String update = "update korisnik set ime=?, prezime=?, adresa=?, vrsta_naloga=?, drzava=? where email=?";
	private static String delete = "delete from korisnik where email=?";
	private static String updatePass = "update korisnik set lozinka=? where email=?";
	
	public static List<Korisnik> getKorisnici(){
		List<Korisnik> korisnici = new ArrayList<Korisnik>();
		Connection conn = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				korisnici.add(new Korisnik(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return korisnici;
	}
	
	public static boolean insertKorisnik(Korisnik korisnik) {
		return insertNewKorisnik(korisnik, insert);
	}
	
	private static boolean insertNewKorisnik(Korisnik korisnik, String query) {
		Connection conn = null;
		korisnik.generateHash();
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, korisnik.getEmail());
			stmt.setString(2, korisnik.getKorisnickoIme());
			stmt.setString(3, korisnik.getLozinka());
			stmt.setString(4, korisnik.getIme());
			stmt.setString(5, korisnik.getPrezime());
			stmt.setString(6, korisnik.getAdresa());
			stmt.setString(7, korisnik.getVrstaNaloga());
			stmt.setString(8, korisnik.getDrzava());
			
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
	
	public static boolean updateKorisnik(Korisnik korisnikNew, Korisnik korisnikOld) {
		korisnikNew.generateHash();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(update);
			stmt.setString(1, korisnikNew.getIme());
			stmt.setString(2, korisnikNew.getPrezime());
			stmt.setString(3, korisnikNew.getAdresa());
			stmt.setString(4, korisnikNew.getVrstaNaloga());
			stmt.setString(5, korisnikNew.getDrzava());
			stmt.setString(6, korisnikOld.getEmail());
			
			int num = stmt.executeUpdate();
			stmt.close();
			
			if(num == 1)
				return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return false;
	}
	
	public static boolean updateLozinka(Korisnik korisnik) {
		korisnik.generateHash();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(updatePass);
			stmt.setString(1, korisnik.getLozinka());
			stmt.setString(2, korisnik.getEmail());
			
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
	
	public static boolean deleteKorisnik(Korisnik korisnik) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(delete);
			stmt.setString(1, korisnik.getEmail());
			
			int num = stmt.executeUpdate();
			stmt.close();
			if(num == 1)
				return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return false;
	}
	
	public static Korisnik getKorisnikById(String email) {
		return getKorisnik(selectByEmail, email);
	}
	
	public static Korisnik getKorisnikByKorisnickoIme(String korisnickoIme){
		return getKorisnik(selectByKorisnickoIme, korisnickoIme);
	}
	
	private static Korisnik getKorisnik(String query, String param) {
		Korisnik korisnik = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, param);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				korisnik = new Korisnik(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return korisnik;
	}
}
