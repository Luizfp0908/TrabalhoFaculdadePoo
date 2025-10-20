package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String PORTA = "3306";
    private static final String DATABASE = "listadeprodutos";
    private static final String URL = "jdbc:mysql://localhost:" + PORTA + "/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {

            System.err.println("ERRO: Driver JDBC não encontrado. Verifique se o mysql-connector-j.jar foi adicionado ao seu projeto.");
            throw new SQLException("Driver JDBC não disponível.", e);
        }
    }
}