INSERT INTO tb_product (name, price, create_date, description, version) VALUES ('Kindle', 500, date_sub(sysdate(), interval 1 day), 'New Kindle is here', 0);
INSERT INTO tb_product (name, price, create_date, description, version) VALUES ('Nintendo Switch', 499.90, date_sub(sysdate(), interval 1 day), 'Best Nintendo console of 2017.', 0);
INSERT INTO tb_product (name, price, create_date, description, version) VALUES ('DVD Player', 199, date_sub(sysdate(), interval 1 day), 'Physical media player', 0);
INSERT INTO tb_product (name, price, create_date, description, version) VALUES ('IPad', 999, date_sub(sysdate(), interval 1 day), 'New IPad', 0);
INSERT INTO tb_category (id, parent_category_id, name, version) VALUES (1, null, 'First Category', 0);
INSERT INTO tb_client (id, name, cpf, version) VALUES (1,'Mario Miranda', '12345678901', 0);
INSERT INTO tb_client (id, name, cpf, version) VALUES (2,'Christina Chan', '11111111111', 0);
INSERT INTO tb_client (id, name, cpf, version) VALUES (3, 'Gatusca & Nino', '22222222222', 0);

INSERT INTO tb_order(`id`,`client_id`,`total`,`create_date`,`city`,`postal_code`,`province`,`rue`,`status`, version)VALUES(1,1,1000,sysdate(),'Quebec','G1H2J3','QC','2880, Chemin St-Foy','WAITING', 0);
INSERT INTO tb_order(`id`,`client_id`,`total`,`create_date`,`city`,`postal_code`,`province`,`rue`,`status`, version)VALUES(2,1,500,sysdate(),'Quebec','G1H2J3','QC','2880, Chemin St-Foy','WAITING', 0);

INSERT INTO tb_order_item (order_id, product_id, product_price, quantity, version) VALUES (1, 1, 500, 2, 0);
INSERT INTO tb_order_item (order_id, product_id, product_price, quantity, version) VALUES (2, 1, 500, 1, 0);
--INSERT INTO tb_payment (order_id, status, card_number) VALUES (1, 'PROCESSING', '0123 4567 7890 1234');
INSERT INTO tb_payment (order_id, payment_type, status, card_number, version) VALUES (1, 'card', 'PROCESSING', '0123 4567 7890 1234', 0);
