INSERT INTO tb_product (name, price, create_date, description) VALUES ('Kindle', 500, date_sub(sysdate(), interval 1 day), 'New Kindle is here');
INSERT INTO tb_product (name, price, create_date, description) VALUES ('Nintendo Switch', 499.90, date_sub(sysdate(), interval 1 day), 'Best Nintendo console of 2017.');
INSERT INTO tb_product (name, price, create_date, description) VALUES ('DVD Player', 199, date_sub(sysdate(), interval 1 day), 'Physical media player');
INSERT INTO tb_product (name, price, create_date, description) VALUES ('IPad', 999, date_sub(sysdate(), interval 1 day), 'New IPad');
INSERT INTO tb_category (id, parent_category_id, name) VALUES (1, null, 'First Category');
INSERT INTO tb_client (id, name) VALUES (1,'Mario Miranda');
INSERT INTO tb_client (id, name) VALUES (2,'Christina Chan');
INSERT INTO tb_client (id, name) VALUES (3, 'Gatusca & Nino');

INSERT INTO tb_order(`id`,`client_id`,`total`,`create_date`,`city`,`postal_code`,`province`,`rue`,`status`)VALUES(1,1,1000,sysdate(),'Quebec','G1H2J3','Quebec','2880, Chemin St-Foy','WAITING');
INSERT INTO tb_order(`id`,`client_id`,`total`,`create_date`,`city`,`postal_code`,`province`,`rue`,`status`)VALUES(2,1,500,sysdate(),'Quebec','G1H2J3','Quebec','2880, Chemin St-Foy','WAITING');

INSERT INTO tb_order_item (order_id, product_id, product_price, quantity) VALUES (1, 1, 500, 2);
INSERT INTO tb_order_item (order_id, product_id, product_price, quantity) VALUES (2, 1, 500, 1);
--INSERT INTO tb_payment (order_id, status, card_number) VALUES (1, 'PROCESSING', '0123 4567 7890 1234');
INSERT INTO tb_payment (order_id, discriminator, status, card_number) VALUES (1, 'C', 'PROCESSING', '0123 4567 7890 1234');
