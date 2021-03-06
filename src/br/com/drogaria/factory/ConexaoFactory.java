package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USER = "root", PASSWORD = "", URL = "jdbc:mysql://localhost:3306/drogaria";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	public static void main(String[] args) {
		try {
			Connection connection = ConexaoFactory.conectar();
			System.out.println("Conex?o realizada com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
