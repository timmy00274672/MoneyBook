package com.diplab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class StoreDataService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Set<String> variableNames = execution.getVariableNames();
		for (String variable : variableNames) {
			System.out.println(variable);
		}
		Connection conn = prepareDatabase();
		Statement stmt = conn.createStatement();
		int updatedRow = stmt
				.executeUpdate(String
						.format("insert into MONEY_BOOK(NAME, EMAIL, AMOUNT, CLASS) VALUES('%s', '%s', '%d', '%s')",
								execution.getVariable("name"),
								execution.getVariable("email"),
								execution.getVariable("amount"),
								execution.getVariable("category")));
		System.out.format("Update %d rows \n", updatedRow);

		conn.close();
	}

	private Connection prepareDatabase() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/activiti2", "sa", "");

		Statement stmt = conn.createStatement();
		stmt.execute("create table if not exists MONEY_BOOK(Name varchar(255), AMOUNT bigint, EMAIL varchar(255), CLASS varchar(255))");
		return conn;
	}
}
