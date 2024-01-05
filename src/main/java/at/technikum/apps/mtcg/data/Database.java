package at.technikum.apps.mtcg.data;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private static Database instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/mtcgdb";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private Database(){
    }

    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
