package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");     
            
            // Connexion à la base de données externe
            String url = "jdbc:postgresql://100.87.38.32:5432/gesusers";
            String user = "extuser";
            String password = "Pg@2026";
            
            return DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException e) {
            // Propage l'erreur pour un meilleur diagnostic
            throw new SQLException("Driver PostgreSQL non trouvé", e);
        }
    }
}