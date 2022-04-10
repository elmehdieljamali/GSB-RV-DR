# GSB-RV-DR
Application d'enregistrement et de suivi des rapports de visite.
## Contexte
Le laboratoire Galaxy Swiss Bourdin (GSB) est issu de la fusion entre le géant américain Galaxy (spécialisé dans le secteur des maladies virales dont le SIDA et les hépatites) et le conglomérat européen Swiss Bourdin (travaillant sur des médicaments plus conventionnels). <br>
L'application permet de centraliser les rapports de visite. En fournissant une description des produits du laboratoire, les coordonnées précises des praticiens et des informations détaillées les concernant. Elle sert aussi à la mise en relation de la hiérarchie de la force commerciale, des visiteurs aux responsables de secteur en passant par les délégués régionaux.  
![index](https://user-images.githubusercontent.com/85114414/162618326-2e40b81f-0cff-4dbb-bd60-739cf37fb76d.jpg)
## Modélisation de la base de données
![Capture](https://user-images.githubusercontent.com/85114414/162581569-592f4144-f56d-4862-a1bc-10cb4aef8f68.PNG)
## Description des cas d'utilisation
**S'authentifier**
| **Nom du cas d'utilisation :** S'authentifier |
|:----------|
| **Acteur déclencheur :** Délégué régional |
| Le cas commence quand le délégué régional demande à se connecter. |
| **Pré-conditions :** néant |
| **Post-conditions :** Le délégué régional est authentifié et a accès à l'ensemble des fonctionalités |
| **Scénario nominal :** |
| 1- Le système demande le matricule. |
| 2- Le délégué régional saisit son matricule. |
| 3- Le système demande le mot de passe. |
| 4- Le délégué régional saisit son mot de passe. |
| 5- Le système contrôle le matricule et le mot de passe. |
| 6- Le système active l'interface utilisateur |
| **Scénario alternatif :** Le matricule est inconnu ou le mot de passe est incorrect |
| 1. Le système informe le délégué régional de l'échec de l'authentification. |
| 2. Le cas d'utilisation reprend à l'étape 1 du scénario nominal. |  

**Consulter la liste des praticiens hésitants**
| **Nom du cas d'utilisation :** Consulter la liste des praticiens hésitants |
|:----------|
| **Acteur déclencheur :** Délégué régional |
| Le cas commence quand le délégué régional demande à consulter la liste des praticiens hésitants. |
| **Pré-conditions :** Le délégué régional est authentifié |
| **Post-conditions :** néant |
| **Scénario nominal :** |
| 1- Le système affiche les critères de tri. |
| 2- Le délégué régional sélectionne le critére de tri. |
| 3- Le système affiche la liste des praticiens hésitants triée |
| **Contraintes :** |
| Les critères de tri sont : |
| - Le coefficient de confiance (affichage dans l'ordre croissant) ; |
| - Le temps écoulé depuis la dernière visite (affichage dans l'ordre chronologique inverse) ; |
| - Le coefficient de notoriété du praticien (affichage dans l'ordre décroissant). |
| La liste des praticiens hésitants fait apparaître le nom et la ville du praticien . |
