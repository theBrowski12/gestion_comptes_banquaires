# BankService

## Description
BankService est une application Spring Boot qui permet de gérer des comptes bancaires.  
Elle offre une API REST complète pour créer, consulter, modifier, supprimer des comptes et effectuer des opérations de crédit/débit.

---

## Technologies utilisées
- Java 17
- Spring Boot 3.x
- Spring Web
- Maven
- Swagger/OpenAPI pour la documentation
- (Optionnel) Base de données H2/MySQL

---

## Fonctionnalités
- **Créer un compte** (POST `/v1/comptes`)
- **Lister tous les comptes** (GET `/v1/comptes`)
- **Récupérer un compte par ID** (GET `/v1/comptes/{id}`)
- **Mettre à jour un compte** (PUT `/v1/comptes/{id}`)
- **Supprimer un compte** (DELETE `/v1/comptes/{id}`)
- **Créditer un compte** (PATCH `/v1/comptes/crediter/{id}/{montant}`)
- **Débiter un compte** (PATCH `/v1/comptes/debiter/{id}/{montant}`)

---
## Screenshots 
Postman tests: 
getAll:
<img width="1170" height="895" alt="postman_getAll" src="https://github.com/user-attachments/assets/15a83fce-eb4d-4b8e-b4ae-c490c4071545" />

Post:
<img width="1167" height="912" alt="postman_post" src="https://github.com/user-attachments/assets/2fdada35-fcf0-492e-950e-5a86579b8bce" />

Crediter: 
<img width="1167" height="890" alt="postman_crediter" src="https://github.com/user-attachments/assets/11f4dc94-0b39-4628-93c4-2a0bec8a6058" />

Debiter:
<img width="1167" height="837" alt="postman_debiter" src="https://github.com/user-attachments/assets/0896de83-9cb5-409e-b5f6-c852f13e4ac4" />

Put:
<img width="1178" height="882" alt="postman_put" src="https://github.com/user-attachments/assets/e5c5be57-557f-48f7-8d32-bde60083e680" />

Swagger interface: 
<img width="1896" height="968" alt="image" src="https://github.com/user-attachments/assets/203ab0a4-8201-4b3e-b5b1-2a090cea12ea" />


<img width="1906" height="761" alt="image" src="https://github.com/user-attachments/assets/185274f6-f804-4cef-a201-629ab7ae3b80" />

Swagger test:
<img width="1892" height="972" alt="swagger1" src="https://github.com/user-attachments/assets/82b6e99d-f929-4fc1-9a86-7e8dd8722171" />

mysql Database:
<img width="1035" height="311" alt="database" src="https://github.com/user-attachments/assets/dc2c9e90-9a62-4731-8dfe-51333c58c91d" />




## Installation

1. Cloner le projet :
```bash
git clone https://github.com/theBrowski12/gestion_comptes_banquaires.git
