package com.diplab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestDatabase {

	@Test
	public void testQuery() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/activiti2", "sa", "");
		Statement stmt = conn.createStatement();
		ResultSet set = stmt.executeQuery("SELECT * FROM MONEY_BOOK;");
		while (set.next()) {
			String name = set.getString("NAME");
			System.out.println(name);
		}
		conn.close();
	}

	@Test
	public void testInsert() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/activiti2", "sa", "");
		Statement stmt = conn.createStatement();
		int updatedRow = stmt.executeUpdate("insert into MONEY_BOOK(NAME) VALUES('timmy')");
		System.out.format("Update %d rows \n", updatedRow);
		conn.close();
	}
}
