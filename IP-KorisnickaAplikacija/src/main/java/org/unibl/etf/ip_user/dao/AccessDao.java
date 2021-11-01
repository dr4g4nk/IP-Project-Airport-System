package org.unibl.etf.ip_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.unibl.etf.ip_user.dto.Access;

public class AccessDao {

	private static String check = "select * from access where datum=?";
	private static String update = "update access set broj_pristupa=broj_pristupa+? where datum=?";
	private static String insert = "insert into access (datum, broj_pristupa) values(?,?)";

	public static void insertAccess(Access access) {
		Connection conn = null;
		try {
			boolean exist = false;
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(check);
			stmt.setDate(1, access.getDate());
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				exist = true;
			rs.close();
			stmt.close();

			if (exist) {
				PreparedStatement preparedStatement = conn.prepareStatement(update);
				preparedStatement.setInt(1, access.getNumber());
				preparedStatement.setDate(2, access.getDate());
				preparedStatement.executeUpdate();

				preparedStatement.close();
			} else {
				PreparedStatement preparedStatement = conn.prepareStatement(insert);
				preparedStatement.setDate(1, access.getDate());
				preparedStatement.setInt(2, access.getNumber());
				preparedStatement.executeUpdate();

				preparedStatement.close();
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
}
