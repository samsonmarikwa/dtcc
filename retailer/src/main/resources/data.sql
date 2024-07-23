INSERT INTO customer (customer_id, customer_name) VALUES (100, 'Jane Doe');
INSERT INTO customer (customer_id, customer_name) VALUES (200, 'Joe Bloggs');
INSERT INTO customer (customer_id, customer_name) VALUES (300, 'Mary Jane');
INSERT INTO customer (customer_id, customer_name) VALUES (400, 'Peter Parker');

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (1, '2024-07-22', 'iPhone', 120.00, 100);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (2, '2024-08-23', 'MacBook', 160.00, 100);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (3, '2024-06-24', 'iPad', 130.00, 200);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (4, '2024-09-25', 'iPod', 60.00, 200);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (5, '2024-10-26', 'iWatch', 40.00, 200);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (6, '2024-11-27', 'iMac', 140.00, 300);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (7, '2024-12-28', 'MacBook', 160.00, 300);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (8, '2024-09-29', 'Samsung Galaxy', 80.00, 300);

INSERT INTO transactions (id, tdate, description, amount, customer_id)
VALUES (9, '2024-10-26', 'iWatch', 40.00, 400);