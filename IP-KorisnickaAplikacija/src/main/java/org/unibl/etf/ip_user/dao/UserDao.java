package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.unibl.etf.ip_user.dto.User;

public class UserDao {

	private static String selectByEmail = "select * from korisnik where email=?";
	private static String insert = "insert into korisnik (email, korisnicko_ime, lozinka, ime, prezime, adresa, vrsta_naloga, drzava) values (?,?,?,?,?,?,?,?)";
	private static String selectByUsername = "select * from korisnik where korisnicko_ime=?";
	
	public static boolean insertUser(User user) {
		Connection conn = null;
		user.setPassword(user.generateHash());
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getSurname());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getAccount());
			stmt.setString(8, user.getCountry());
			
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
	
	public static User getUserByEmail(String email) {
		return getUser(selectByEmail, email);
	}
	
	public static User getUserByUsername(String username) {
		return getUser(selectByUsername, username);
	}
	
	
	private static User getUser(String query, String param) {
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, param);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return user;
	}
}
