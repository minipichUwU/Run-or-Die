# JeeFrameworks2026

# Projet : Run or Die 🧟

---

## Rôles et fonctionnalités

### Organisateur

Afin de ne pas embrouiller les utilisateurs avec plusieurs éditions portant le même nom, pour des dates et/ou horaires différents,  
l'organisateur devra choisir un nom unique pour chaque édition.  
Pour réutiliser un nom d'édition, il devra d'abord supprimer (pas seulement annuler) l'édition portant le nom.

### Zombie

Lorsqu'un Zombie veut s'affecter de nouveau à la même édition, pile avant ou à la suite d'une affectation déjà réalisée,  
cette dernière demande ne crééra pas une nouvelle affectation, mais combinera l'/les affectation.s collée.s à l'affectation de la demande.  
La demande de désaffectation n'étant pas demandée, cela ne posera pas de problème.

### Utilisateurs

Un utilisateur possède maintenant un booléen représentant l'attribution de la licence de la fédération des survivants.  
Cette license pourra lui être remise et/ou destitué via un API de gestion de licenses, bouchonnée par un `System.out.println`,
ce qui mettra à jour ledit booléen.

### Plages horaires

Une plage horaire va de X (inclus) à Y (exclus). (X et Y des entiers allant de 0 à 23 inclus, représentant une heure).  
=> une plage horaire donné par 13 (pour le début) et 20 (pour la fin), ne va en réalité seulement de 13:00 à 19:59.

---

## Sécurité

Seulement à propos des endpoints non demandés et les cas spéciaux (swagger + h2 console).

### Pour tout le monde

Afin de facilité la correction :  
 - `http://localhost:8080/swagger/swagger-ui/index.html` : L'URL du Swagger UI   
il faudra se connecter par le swagger pour pouvoir utiliser la majorité des endpoints


 - `http://localhost:8080/database/h2-console` : L'URL de la console H2  
`User Name`:`sa`  
`Password`:`p`

### Organisateur

 - `/api-user/users` : afin de récupérer les données de tous les utilisateurs inscrits

### Utilisateur 

 - `/api-user/user` (GET) : afin de récupérer ses propres données
 - `/api-user/user/license` : voir si l'utilisateur connecté est licencié
 - `/api-user/user/license/**` : toutes les gestions des licences pour soi-même

### Ne doit pas être connecté

- `/api-user/user` (POST) : afin de se créer un compte utilisateur

### Informations bonus 

Les rôles ont changés de noms pour une perspective d'ouverture sur le monde.
 - `Organisateur` -> `ORGANIZER`
 - `Utilisateur` -> `USER`

---

## Jeu de données initial

### Éditions

| name                                            | edition_date | location                 | max_runners | max_zombies | start_hour | end_hour | cancelled |
|-------------------------------------------------|--------------|--------------------------|-------------|-------------|------------|----------|-----------|
| `Run or Die — Édition Octobre 2025`             | `2025-10-31` | `Antarctique du Sud`     | 67          | 67          | 13         | 20       | false     |
| `Run or Die — Édition Octobre 2026`             | `2026-10-31` | `Antarctique de l'Est`   | 10          | 10          | 13         | 20       | false     |
| `Run or Die — Édition Très Limité Janvier 2050` | `2050-01-31` | `Antarctique de l'Ouest` | 1           | 1           | 13         | 20       | false     |

### Users

| Username              | Password | Rôle      | LICENSED |
|-----------------------|----------|-----------|----------|
| `mastermind@epita.fr` | `brains` | ORGANIZER | false    |
| `forrest@epita.fr`    | `run`    | USER      | false    |

D'autres utilisateurs se sont enregistrés pour les différentes occasions.  
Personne ne s'est porté volontaire pour aider `mastermind` pour l'organisation.

---

## Lancement

Clonez le repo github.  
Depuis la racine du repo, lancez :

```bash
java -jar .\jar\packaging-FINAL.jar
```