package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");     
            
            // Connexion à ma BD pgAdmin (port 5432)
            return DriverManager.getConnection("jdbc:postgresql://100.87.38.32:5432/gesusers", "extuser", "Pg@2026");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion (Vérifiez le port 5432 et le mot de passe) : " + e.getMessage());
        }
        return null;
    }
}