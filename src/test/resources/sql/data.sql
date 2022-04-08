INSERT INTO users(id, name, email, phone)
VALUES (1, 'Artem', 'artem@gmail.com', '069-392-32-51'),
       (2, 'Dima', 'dima@gmail.com', '069-756-87-31'),
       (3, 'Vova', 'vova@gmail.com', '054-842-63-87');

INSERT INTO item(id, name)
VALUES (1, 'swimming'),
       (2, 'gym'),
       (3, 'jogging');

INSERT INTO period(id, years_count, months_count, days_count)
VALUES (1, 0, 1, 0),
       (2, 0, 3, 0),
       (3, 0, 6, 0),
       (4, 1, 0, 0);

INSERT INTO price(id, item_id, amount, period_id)
VALUES (1, 1, 300, 1),
       (2, 1, 900, 2),
       (3, 1, 4500, 4);

INSERT INTO subscription(id, user_id, item_id, period_id, start_time, end_time)
VALUES (1, 1, 1, 1, '1990-01-10', '1990-02-10'),
       (2, 1, 2, 3, '2001-12-23', '2002-6-23'),
       (3, 2, 1, 4, '1984-03-14', '1985-03-14'),
       (4, 3, 3, 1, '1984-03-14', '1984-04-14');

INSERT INTO orders(id, subscription_id, price_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (2, 3, 2),
       (3, 3, 3),
       (4, 4, 1);