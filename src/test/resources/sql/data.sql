INSERT INTO users(id, name, email, phone)
VALUES (1, 'Artem', 'artem@gmail.com', '069-392-32-51'),
       (2, 'Dima', 'dima@gmail.com', '069-756-87-31'),
       (3, 'Vova', 'vova@gmail.com', '054-842-63-87');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO item(id, name)
VALUES (1, 'swimming'),
       (2, 'gym'),
       (3, 'jogging');
SELECT SETVAL('item_id_seq', (SELECT MAX(id) FROM item));

INSERT INTO duration(id, months_count)
VALUES (1, 1),
       (2, 3),
       (3, 6),
       (4, 12);
SELECT SETVAL('duration_id_seq', (SELECT MAX(id) FROM duration));

INSERT INTO price(id, item_id, amount, duration_id)
VALUES (1, 1, 300, 1),
       (2, 1, 900, 2),
       (3, 1, 3000, 3),
       (4, 1, 4500, 4);
SELECT SETVAL('price_id_seq', (SELECT MAX(id) FROM price));

INSERT INTO subscription(id, user_id, item_id, status, start_time, end_time)
VALUES (1, 1, 1, 'ACTIVE', '2022-04-10', '2022-05-10'),
       (2, 1, 2, 'ACTIVE', '2021-12-23', '2022-6-23'),
       (3, 2, 1, 'ACTIVE', '2022-03-14', '2023-03-14'),
       (4, 3, 3, 'ACTIVE', '2022-04-14', '2022-05-14');
SELECT SETVAL('subscription_id_seq', (SELECT MAX(id) FROM subscription));

INSERT INTO orders(id, subscription_id, price_id)
VALUES (1, 1, 1),
       (2, 2, 3),
       (3, 3, 4),
       (4, 4, 1);
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));