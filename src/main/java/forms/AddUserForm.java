package forms;

import beans.Utilisateur;
import dao.UtilisateurDao;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AddUserForm {
  private static final String CHAMP_NOM = "nom";
  private static final String CHAMP_PRENOM = "prenom";
  private static final String CHAMP_LOGIN = "login";
  private static final String CHAMP_PASSWORD = "password";
  private static final String CHAMP_CONFIRMATION = "confirmation";

  private static final String EMPTY_ERROR_MESSAGE = "Veuillez remplir ce champ.";
  private static final String PASSWORD_ERROR_MESSAGE = "Les mots de passe ne correspondent pas.";

  private HashMap<String, String> erreurs;
  private String statusMessage;
  private boolean status;
  private Utilisateur utilisateur;
  private HttpServletRequest request;

  public AddUserForm(HttpServletRequest request) {
    this.request = request;
    this.erreurs = new HashMap<>();
  }

  public boolean add() {
    String nom = getParameter(CHAMP_NOM);
    String prenom = getParameter(CHAMP_PRENOM);
    String login = getParameter(CHAMP_LOGIN);
    String password = getParameter(CHAMP_PASSWORD);
    String confirmation = getParameter(CHAMP_CONFIRMATION);

    this.utilisateur = new Utilisateur(nom, prenom, login, password);

    this.validateChamp(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_CONFIRMATION);
    this.validatePassword();

    if (erreurs.isEmpty() && UtilisateurDao.addUser(utilisateur)) {
      this.status = true;
      this.statusMessage = "Utilisateur ajouté avec succès.";
    } else {
      this.status = false;
      this.statusMessage = "Échec de l'ajout de l'utilisateur.";
    }
    

    return this.status;

  }

  private String getParameter(String parameter) {
    String value = this.request.getParameter(parameter);

    if (value == null || value.isBlank()) {
      return null;
    }

    return value.trim();
  }

  private void validateChamp(String... champs) {
    for (String champ : champs) {
      String value = getParameter(champ);

      if (value == null) {
        erreurs.put(champ, EMPTY_ERROR_MESSAGE);
      }
    }
  }

  private void validatePassword() {
    String password = this.request.getParameter(CHAMP_PASSWORD);
    String confirmation = this.request.getParameter(CHAMP_CONFIRMATION);

    if (password != null && !password.equals(confirmation)) {
      erreurs.put(CHAMP_PASSWORD, PASSWORD_ERROR_MESSAGE);
    }
  }

  public HashMap<String, String> getErreurs() {
    return erreurs;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public boolean getStatus() {
    return status;
  }

  public Utilisateur getUtilisateur() {
    return utilisateur;
  }

}
