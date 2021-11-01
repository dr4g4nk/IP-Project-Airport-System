package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.unibl.etf.ip_user.dto.Message;

public class MessageDao {

	private static String insert = "insert into poruka (status, datum, email, naslov, sadrzaj) values ('Neprocitana',?,?,?,?)";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	public static boolean insertMessage(Message message) {
		Connection conn = null;
		try {
			conn=ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(insert);
			stmt.setString(1, dateFormat.format(new Date(System.currentTimeMillis())));
			stmt.setString(2, message.getEmail());
			stmt.setString(3, message.getSubject());
			stmt.setString(4, message.getContent());
			
			stmt.executeUpdate();
			stmt.close();
			
			return true;
		}catch (Exception e) {
			System.err.println(e);
			return false;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
}
