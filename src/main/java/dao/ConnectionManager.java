package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            
            String url = "jdbc:postgresql://100.87.38.32:5432/gesusers";
            String utilisateur = "extuser"; 
            String motDePasse = "Pg@2026"; 
            
            connection = DriverManager.getConnection(url, utilisateur, motDePasse);
            
            if (connection != null) {
                System.out.println("Connexion réussie à PostgreSQL !");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver PostgreSQL introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : Vérifiez les identifiants ou le port 5432.");
            e.printStackTrace();
        }
        
        return connection;
    }
}