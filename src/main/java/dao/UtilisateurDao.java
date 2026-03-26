package dao;

import beans.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UtilisateurDao {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";

    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM utilisateur";
    private static final String ADD_USER_QUERY = "INSERT INTO utilisateur (" 
        + COLUMN_NOM + ", " + COLUMN_PRENOM + ", " + COLUMN_LOGIN + ", " + COLUMN_PASSWORD 
        + ") VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE utilisateur SET " 
        + COLUMN_NOM + " = ?, " + COLUMN_PRENOM + " = ?, " 
        + COLUMN_LOGIN + " = ?, " + COLUMN_PASSWORD + " = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM utilisateur WHERE id = ?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM utilisateur WHERE id = ?";

    // Ajouter un utilisateur
    public static boolean addUser(Utilisateur utilisateur) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(ADD_USER_QUERY)) {
            
            pstmt.setString(1, utilisateur.getNom());
            pstmt.setString(2, utilisateur.getPrenom());
            pstmt.setString(3, utilisateur.getLogin());
            pstmt.setString(4, utilisateur.getPassword());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // L'erreur précise s'affichera dans la console Eclipse
            return false;
        }
    }

    // Récupérer tous les utilisateurs
    public static ArrayList<Utilisateur> getUsers() {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL_USERS_QUERY)) {

            while (rs.next()) {
                liste.add(new Utilisateur(
                    rs.getInt(COLUMN_ID),
                    rs.getString(COLUMN_NOM),
                    rs.getString(COLUMN_PRENOM),
                    rs.getString(COLUMN_LOGIN),
                    rs.getString(COLUMN_PASSWORD)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    // Supprimer un utilisateur par ID
    public static boolean deleteUser(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(DELETE_USER_QUERY)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
            
    // Récupérer un utilisateur par ID
    public static Utilisateur getUserById(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_ID_QUERY)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Utilisateur(
                        rs.getInt(COLUMN_ID),
                        rs.getString(COLUMN_NOM),
                        rs.getString(COLUMN_PRENOM),
                        rs.getString(COLUMN_LOGIN),
                        rs.getString(COLUMN_PASSWORD)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mettre à jour un utilisateur par ID
    public static boolean updateUser(int id, Utilisateur user) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER_QUERY)) {
            
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, id);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}