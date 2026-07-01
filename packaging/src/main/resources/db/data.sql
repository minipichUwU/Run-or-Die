INSERT INTO users (username, password, role, licensed)
VALUES ('mastermind@epita.fr', '$2a$12$fxoYjjc6q1u78.PM/tgISeFy2W6uT0fAeuyfJxM5GDL4qFm/WDx5i', 'ORGANIZER', FALSE),
       ('forrest@epita.fr',    '$2a$12$zX4b5tkVrFVUG9nN8DpHO.ngi/VEYxySl.3Mr6Gf2vk0oym5Js/Se', 'USER',      FALSE);


INSERT INTO editions (name,
                      edition_date,
                      start_hour,
                      end_hour,
                      location,
                      max_runners,
                      max_zombies,
                      cancelled)
VALUES ('Run or Die — Édition Octobre 2025', '2025-10-31', 13, 20, 'Antarctique du Sud',    67, 67, FALSE),
       ('Run or Die — Édition Octobre 2026', '2026-10-31', 13, 20, 'Antarctique du Centre', 10, 1,  FALSE);