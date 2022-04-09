# GSB-RV-DR
Application d'enregistrement et de suivi des rapports de visite
## Contexte
Le laboratoire Galaxy Swiss Bourdin (GSB) est issu de la fusion entre le géant américain Galaxy (spécialisé dans le secteur des maladies virales dont le SIDA et les hépatites) et le conglomérat européen Swiss Bourdin (travaillant sur des médicaments plus conventionnels).
## Description des cas d'utilisation
| **Nom du cas d'utilisation :** S'authentifier |
|:----------|
| **Acteur déclencheur :** Délégué régional |
| Le cas commence quand le délégué régional demande à se connecter. |
| **Pré-conditions :** néant |
| **Post-conditions :** Le délégué régional est authentifié et a accès à l'ensemble des fonctionalités |
| **Scénario nominal :** <br>
> 1- Le système demande le matricule. 
> 2- Le délégué régional saisit son matricule.
> 3- Le système demande le mot de passe.
> 4- Le délégué régional saisit son mot de passe.
> 5- Le système contrôle le matricule et le mot de passe.
> 6- Le système active l'interface utilisateur |
| **Scénario alternatif :** Le matricule est inconnu ou le mot de passe est incorrect
1. Le système informe le délégué régional de l'échec de l'authentification.
2. Le cas d'utilisation reprend à l'étape 1 du scénario nominal. |
