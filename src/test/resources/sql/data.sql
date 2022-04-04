INSERT INTO users(id, name, email, phone)
VALUES (1, 'Artem', 'artem@gmail.com', '069-392-32-51'),
       (2, 'Dima', 'dima@gmail.com', '069-756-87-31'),
       (3, 'Vova', 'vova@gmail.com', '054-842-63-87');

INSERT INTO item(id, name)
VALUES (1, 'swimming'),
       (2, 'gym'),
       (3, 'jogging');

INSERT INTO price(id, item_id, amount, period)
VALUES (1, 1, 300, '0000-01-00'),
       (2, 1, 900, '0000-06-00'),
       (3, 1, 4500, '0000-12-00');

INSERT INTO subscription(id, user_id, item_id, duration, start_time, end_time)
VALUES (1, 1, 1, '0000-01-00', '1990-01-10', '1990-01-15'),
       (2, 1, 2, '0000-06-00', '2001-12-23', '2002-12-23'),
       (3, 2, 1, '0000-12-00', '1984-03-14', '1984-03-26'),
       (4, 3, 3, '0000-00-15', '1984-03-14', '1984-03-20');

INSERT INTO orders(id, subscription_id, price_id)
VALUES (1, 1, 1),
       (2, 1, 1),