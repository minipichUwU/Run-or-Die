-- user
INSERT INTO users (username, password, role, licensed)
VALUES ('mastermind@epita.fr',             '$2a$12$fxoYjjc6q1u78.PM/tgISeFy2W6uT0fAeuyfJxM5GDL4qFm/WDx5i', 'ORGANIZER', FALSE), -- brains
       ('forrest@epita.fr',                '$2a$12$zX4b5tkVrFVUG9nN8DpHO.ngi/VEYxySl.3Mr6Gf2vk0oym5Js/Se', 'USER',      FALSE), -- run
       ('lola@epita.fr',                   '$2a$12$4bln27a9cl6xo.q/ckCjNOccCuu58oAW2DMGPIXoJ/..smJakklKm', 'USER',      TRUE ), -- run
       ('sparrow@epita.fr',                '$2a$12$lX/UAn41aTuzqOxgReACn.kVyvbqqeJfBH4zKl.cvCqnbh5akQ81K', 'USER',      TRUE ), -- captain
       ('T_1000@epita.fr',                 '$2a$12$ufMZodDo85GpuiIcZowUo.FNOGrVf/1Lhg6SsHLPH52ihI3qJe5Dy', 'USER',      TRUE ), -- terminator
       ('le_coureur_du_dimanche@epita.fr', '$2a$12$eQq8XyQymAK8hraBxzDNQeL/mFOdeEIg/zcKzxbiTnlJCW8dGA/XK', 'USER',      TRUE ), -- dormir
       ('la_meilleure_coureuse@epita.fr',  '$2a$12$tN5WCjkCZj30qvo7JZ2blOs7KlNdHgulvHBWaarQJmT.BYfaJeWTm', 'USER',      TRUE ), -- sprint
       ('jackson@epita.fr',                '$2a$12$8LTSBUdlMY2Tft8nXIXsN.vwxuddr/o43N9tGV5NtXlPd925T3Ft6', 'USER',      FALSE), -- hee hee
       ('le_zombie_du_dimanche@epita.fr',  '$2a$12$L.7uGu7vqDA97PJBPVKubeZ6RHSmoZMRptF6bRv4xX6BTSz.TSIum', 'USER',      FALSE), -- dormir
       ('la_meilleure_zombie@epita.fr',    '$2a$12$tFZBBboPCkM11wfoIqkeWOv18Nnpr0nIIUkWgkcdd8/4U8IFNtG06', 'USER',      FALSE);
-- ramper


-- edition
INSERT INTO editions (name,
                      edition_date,
                      start_hour,
                      end_hour,
                      location,
                      max_runners,
                      max_zombies,
                      cancelled)
VALUES ('Run or Die — Édition Octobre 2025',             '2025-10-31', 13, 20, 'Antarctique du Sud',       67, 67, FALSE),
       ('Run or Die — Édition Très Limité Janvier 2050', '2050-01-31', 13, 20, 'Antarctique de l''Ouest ', 1,  1,  FALSE),
       ('Run or Die — Édition Octobre 2026',             '2026-10-31', 13, 20, 'Antarctique de l''Est',    10, 10, FALSE);


-- zombie
INSERT INTO zombies (start_hour, end_hour, edition_id, user_id)
VALUES (15, 17, 'Run or Die — Édition Très Limité Janvier 2050', 'le_zombie_du_dimanche@epita.fr'),
       (14, 17, 'Run or Die — Édition Octobre 2026',             'jackson@epita.fr'              ),
       (18, 20, 'Run or Die — Édition Octobre 2026',             'jackson@epita.fr'              ),
       (13, 15, 'Run or Die — Édition Octobre 2026',             'le_zombie_du_dimanche@epita.fr'),
       (17, 20, 'Run or Die — Édition Octobre 2026',             'le_zombie_du_dimanche@epita.fr'),
       (13, 20, 'Run or Die — Édition Octobre 2026',             'la_meilleure_zombie@epita.fr'  ),
       (15, 20, 'Run or Die — Édition Octobre 2025',             'jackson@epita.fr'              ),
       (15, 20, 'Run or Die — Édition Octobre 2025',             'le_zombie_du_dimanche@epita.fr'),
       (13, 20, 'Run or Die — Édition Octobre 2025',             'la_meilleure_zombie@epita.fr'  );


-- coureur
INSERT INTO runners (edition_id, user_id)
VALUES ('Run or Die — Édition Très Limité Janvier 2050', 'le_coureur_du_dimanche@epita.fr'),
       ('Run or Die — Édition Octobre 2026',             'T_1000@epita.fr'                ),
       ('Run or Die — Édition Octobre 2026',             'lola@epita.fr'                  ),
       ('Run or Die — Édition Octobre 2026',             'sparrow@epita.fr'               ),
       ('Run or Die — Édition Octobre 2026',             'le_coureur_du_dimanche@epita.fr'),
       ('Run or Die — Édition Octobre 2026',             'la_meilleure_coureuse@epita.fr' ),
       ('Run or Die — Édition Octobre 2025',             'lola@epita.fr'                  ),
       ('Run or Die — Édition Octobre 2025',             'sparrow@epita.fr'               ),
       ('Run or Die — Édition Octobre 2025',             'le_coureur_du_dimanche@epita.fr'),
       ('Run or Die — Édition Octobre 2025',             'la_meilleure_coureuse@epita.fr' );
