INSERT INTO mixture (
                     name,
                     ingr_root,
                     ingr_mushroom,
                     ingr_feather,
                     ingr_chicken_leg,
                     point)
VALUES
('', 2, 2, 0, 0, 6),
('', 3, 2, 0, 0, 7),
('', 0, 4, 0, 0, 8),
('', 2, 0, 2, 0, 8),
('', 2, 3, 0, 0, 8),
('', 3, 0, 2, 0, 9),
('', 0, 2, 2, 0, 10),
('', 0, 5, 0, 0, 10),
('', 2, 0, 0, 2, 10),
('', 2, 0, 3, 0, 11),
('', 0, 3, 0, 2, 11),
('', 0, 0, 4, 0, 12),
('', 0, 2, 0, 2, 12),
('', 0, 3, 2, 0, 12),
('', 0, 2, 3, 0, 13),
('', 0, 0, 2, 2, 14),
('', 0, 3, 0, 2, 14),
('', 2, 0, 0, 3, 14),
('', 0, 0, 5, 0, 15),
('', 0, 0, 0, 4, 16),
('', 0, 2, 0, 3, 16),
('', 0, 0, 3, 2, 17),
('', 0, 0, 2, 3, 18),
('', 0, 0, 0, 5, 20),
('', 2, 1, 1, 0, 9),
('', 0, 2, 1, 2, 12),
('', 1, 0, 2, 1, 12),
('', 2, 2, 2, 0, 13),
('', 2, 2, 0, 2, 15),
('', 2, 0, 2, 2, 17),
('', 0, 2, 2, 2, 19),
('', 1, 1, 1, 1, 12),
('', 3, 1, 1, 1, 14),
('', 1, 3, 1, 1, 16),
('', 1, 1, 3, 1, 18),
('', 1, 1, 1, 3, 20);

INSERT INTO merchant (
                      dtype,
                      obtain_root,
                      obtain_mushroom,
                      obtain_feather,
                      obtain_chicken_leg)
VALUES
('obtain', 2, 0, 0, 0),
('obtain', 1, 1, 0, 0),
('obtain', 0, 0, 1, 0),
('obtain', 3, 0, 0, 0),
('obtain', 2, 1, 0, 0),
('obtain', 4, 0, 0, 0),
('obtain', 0, 0, 0, 1),
('obtain', 0, 2, 0, 0),
('obtain', 1, 0, 1, 0);

INSERT INTO merchant (
                      dtype,
                      trade_root_from,
                      trade_mushroom_from,
                      trade_feather_from,
                      trade_chicken_leg_from,
                      trade_root_to,
                      trade_mushroom_to,
                      trade_feather_to,
                      trade_chicken_leg_to)
VALUES
('trade', 3, 0, 0, 0, 0, 0, 0, 1),
('trade', 0, 1, 0, 0, 3, 0, 0, 0),
('trade', 0, 0, 2, 0, 2, 3, 0, 0),
('trade', 0, 0, 2, 0, 2, 1, 0, 1),
('trade', 0, 0, 0, 1, 3, 0, 1, 0),
('trade', 0, 2, 0, 0, 3, 0, 1, 0),
('trade', 0, 3, 0, 0, 2, 2, 0, 0),
('trade', 0, 0, 0, 1, 2, 2, 0, 0),
('trade', 4, 0, 0, 0, 0, 0, 2, 0),
('trade', 2, 0, 0, 0, 0, 0, 1, 0),
('trade', 1, 1, 0, 0, 0, 0, 0, 1),
('trade', 0, 0, 1, 0, 0, 2, 0, 0),
('trade', 0, 2, 0, 0, 2, 0, 0, 1),
('trade', 3, 0, 0, 0, 0, 1, 1, 0),
('trade', 0, 0, 2, 0, 0, 2, 0, 1),
('trade', 0, 3, 0, 0, 1, 0, 1, 1),
('trade', 0, 0, 0, 1, 0, 3, 0, 0),
('trade', 0, 3, 0, 0, 0, 0, 0, 2),
('trade', 0, 0, 0, 1, 1, 1, 1, 0),
('trade', 0, 0, 1, 0, 1, 2, 0, 0),
('trade', 0, 0, 1, 0, 4, 1, 0, 0),
('trade', 5, 0, 0, 0, 0, 0, 0, 2),
('trade', 4, 0, 0, 0, 0, 0, 1, 1),
('trade', 0, 0, 0, 2, 0, 3, 2, 0),
('trade', 0, 0, 0, 2, 1, 1, 3, 0),
('trade', 5, 0, 0, 0, 0, 0, 3, 0),
('trade', 2, 0, 1, 0, 0, 0, 0, 2),
('trade', 0, 0, 3, 0, 0, 0, 0, 3),
('trade', 0, 3, 0, 0, 0, 0, 3, 0),
('trade', 3, 0, 0, 0, 0, 3, 0, 0),
('trade', 2, 0, 0, 0, 0, 2, 0, 0),
('trade', 0, 0, 2, 0, 0, 0, 0, 2),
('trade', 0, 2, 0, 0, 0, 0, 2, 0),
('trade', 0, 0, 0, 1, 0, 0, 2, 0);
