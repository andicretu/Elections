package projects.Elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import projects.Elections.Service.DatabaseCleanupService;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@EntityScan("projects.Elections")
public class ElectionsApplication implements CommandLineRunner {
	@Autowired
	private DatabaseCleanupService databaseCleanupService;
	@Autowired
	private Environment environment;
	private final String url = "jdbc:postgresql://localhost:5432/elections";
	private final String user = "andicretu";
	private final String password = "";

	public static void main(String[] args) {
		SpringApplication.run(ElectionsApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception {
		if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")) {
			databaseCleanupService.deleteAllRecords();
			System.out.println("All recorde deleted on startup");
		} else {
			System.out.println("Skipping record deletion on statup");
		}
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
