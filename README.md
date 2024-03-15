# Guide de Configuration et d'Utilisation de l'Application

Ce guide fournit les instructions pour configurer une application avec Keycloak pour l'authentification et l'autorisation, ainsi que pour l'utilisation de la pile Elastic (ELK) pour la journalisation et la surveillance.

## Configuration de l'Application avec Keycloak

### Accueil de l'Application
![Accueil](screenshots/Accueil.png)
Vue de l'écran d'accueil de l'application avec des options pour naviguer entre différentes sections.

### Ajout d'un Client dans Keycloak
![Ajout Client Realm](screenshots/add_client_realm.png)
Étapes pour ajouter un client dans le realm Keycloak, avec les configurations nécessaires.

### Création d'un Nouveau Realm
![Ajout Realm](screenshots/add_realm.png)
Processus de création d'un nouveau realm dans Keycloak.

### Ajout de Rôles
![Ajout Rôles](screenshots/add_roles.png)
Comment ajouter des rôles dans Keycloak qui peuvent être attribués aux utilisateurs.

### Ajout d'un Utilisateur dans Keycloak
![Ajout Utilisateur Keycloak](screenshots/add_user_keycloak.png)
Ajout d'utilisateurs dans Keycloak et attribution de rôles.

### Écran de Connexion
![Écran de Connexion](screenshots/login.png)
Écran de connexion pour Keycloak où les utilisateurs saisiront leurs identifiants.

## Utilisation du Stack ELK

### Connexion à Elasticsearch
![Connexion Elasticsearch](screenshots/login_elasticsearch.png)
Connexion à l'instance Elasticsearch pour la gestion des données.

### Connexion à Kibana
![Connexion Kibana](screenshots/loginkibana.png)
Écran de connexion à Kibana pour la visualisation des données et la création de dashboards.

### Visualisation des Logs
![Logs](screenshots/logs.png)
Surveillance des logs en temps réel dans Kibana.

### Examen des Logs dans Kibana
![Vue des Logs Kibana](screenshots/ViewLogKibana.png)
Exploration et filtrage des logs dans Kibana pour une analyse détaillée.

---

Assurez-vous de bien suivre toutes les instructions et de configurer les permissions correctement dans Keycloak et votre application. Les logs doivent être configurés pour être collectés et indexés par la pile ELK pour permettre leur surveillance et analyse dans Kibana.
