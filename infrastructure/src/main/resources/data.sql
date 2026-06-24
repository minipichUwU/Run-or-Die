INSERT INTO users (username, password, enabled)
VALUES ('mastermind@epita.fr', '$2a$12$fxoYjjc6q1u78.PM/tgISeFy2W6uT0fAeuyfJxM5GDL4qFm/WDx5i', TRUE),
       ('forrest@epita.fr',    '$2a$12$zX4b5tkVrFVUG9nN8DpHO.ngi/VEYxySl.3Mr6Gf2vk0oym5Js/Se', TRUE);

INSERT INTO authorities (username, authority)
VALUES ('mastermind@epita.fr', 'ROLE_ORGANISATEUR'),
       ('mastermind@epita.fr', 'ROLE_UTILISATEUR' ),
       ('forrest@epita.fr',    'ROLE_UTILISATEUR' );