<%@include file="inc/header.jsp" %>

<main class="container">
    <h2>Mettre a jour un utilisateur</h2>

    <%-- Affichage des messages de session --%>
    <c:if test="${!empty sessionScope.statusMessage}">
        <div class="${sessionScope.status ? 'success' : 'error'}">${sessionScope.statusMessage}</div>
        <c:remove var="statusMessage" scope="session" />
        <c:remove var="status" scope="session" />
    </c:if>

    <%-- Affichage des messages de validation imm�diate des champs --%>
    <c:if test="${!empty requestScope.statusMessage}">
        <div class="${requestScope.status ? 'success' : 'error'}">${requestScope.statusMessage}</div>
    </c:if>

    <form method="post" id="userForm">
        <label for="nom">Nom</label>
        <input type="text" name="nom" id="nom" value="${utilisateur.nom}">
        <c:if test="${ !empty erreurs.nom }">
            <span class="error" id="error-nom">${ erreurs.nom }</span>
        </c:if>

        <label for="prenom">Prenom</label>
        <input type="text" name="prenom" id="prenom" value="${utilisateur.prenom}">
        <c:if test="${ !empty erreurs.prenom }">
            <span class="error" id="error-prenom">${ erreurs.prenom }</span>
        </c:if>

        <label for="login">Login</label>
        <input type="text" name="login" id="login" value="${utilisateur.login}">
        <c:if test="${ !empty erreurs.login }">
            <span class="error" id="error-login">${ erreurs.login }</span>
        </c:if>

        <label for="password">Mot de passe</label>
        <input type="password" name="password" id="password" value="${utilisateur.password}">
        <c:if test="${ !empty erreurs.password }">
            <span class="error" id="error-password">${ erreurs.password }</span>
        </c:if>

        <label for="confirmation">Confirmer mot de passe</label>
        <input type="password" name="confirmation" id="confirmation">
        <c:if test="${ !empty erreurs.confirmation }">
            <span class="error" id="error-confirmation">${ erreurs.confirmation }</span>
        </c:if>

        <input type="submit" value="Mettre A jour">
    </form>

    <script>
        // Fonction pour masquer les messages d'erreur d�s que l'utilisateur commence � saisir
        function hideErrorOnInput(inputId, errorId) {
            const inputField = document.getElementById(inputId);
            const errorField = document.getElementById(errorId);

            if (inputField && errorField) {
                inputField.addEventListener('input', function () {
                    if (errorField) {
                        errorField.remove(); // Supprime compl�tement l'�l�ment de la page
                    }
                });
            }
        }

        // Validation pour interdire les caract�res sp�ciaux dans les champs "Nom" et "Pr�nom"
        function validateSpecialChars(inputId, errorId) {
            const inputField = document.getElementById(inputId);
            const specialCharsPattern = /[^a-zA-Z�-�\s]/; // Permet seulement lettres et espaces

            inputField.addEventListener('input', function() {
                // Supprimer l'�l�ment d'erreur si pr�sent
                let errorField = document.getElementById(errorId);
                if (errorField) {
                    errorField.remove();
                }

                // Si des caract�res sp�ciaux sont d�tect�s
                if (specialCharsPattern.test(inputField.value)) {
                    // Cr�er l'�l�ment d'erreur si n�cessaire
                    errorField = document.createElement('span');
                    errorField.classList.add('error');
                    errorField.id = errorId;
                    errorField.textContent = "Ce champ ne doit contenir que des lettres.";
                    inputField.insertAdjacentElement('afterend', errorField); // Ins�re l'�l�ment d'erreur apr�s le champ
                    inputField.setCustomValidity("Ce champ ne doit contenir que des lettres.");
                } else {
                    // Si le champ est valide, r�initialiser la validit�
                    inputField.setCustomValidity(""); // R�initialise la validit�
                }
            });
        }

        // Associer la fonction aux champs "Nom" et "Pr�nom"
        hideErrorOnInput('nom', 'error-nom');
	    hideErrorOnInput('prenom', 'error-prenom');
	    hideErrorOnInput('login', 'error-login');
	    hideErrorOnInput('password', 'error-password');
	    hideErrorOnInput('confirmation', 'error-confirmation');

        // Validation des caract�res sp�ciaux pour "Nom" et "Pr�nom"
        validateSpecialChars('nom', 'error-nom');
        validateSpecialChars('prenom', 'error-prenom');
    </script>
</main>


<%@include file="inc/footer.jsp" %>
