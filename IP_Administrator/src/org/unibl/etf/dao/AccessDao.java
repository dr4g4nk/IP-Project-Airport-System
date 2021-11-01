package org.unibl.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dto.Access;

public class AccessDao {

	private static String selectAll = "select * from access order by datum desc";

	public static List<Access> getAccesses() {
		Connection conn = null;
		List<Access> accesses = new ArrayList<Access>();
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (count++ < 30 && rs.next())
				accesses.add(new Access(rs.getDate(1), rs.getInt(2)));
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}

		return accesses;
	}

}
