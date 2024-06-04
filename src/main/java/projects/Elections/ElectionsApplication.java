package projects.Elections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.SQLException;

@SpringBootApplication
@EntityScan("projects.Elections")
public class ElectionsApplication {
	private final String url = "jdbc:postgresql://localhost:5432/elections";
	private final String user = "andicretu";
	private final String password = "";

	public static void main(String[] args) {
		SpringApplication.run(ElectionsApplication.class, args);
		ElectionsApplication app = new ElectionsApplication();
	}
	public static void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
