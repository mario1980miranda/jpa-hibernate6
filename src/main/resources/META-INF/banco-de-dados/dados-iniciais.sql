INSERT INTO tb_product (name, price, description) VALUES ('Kindle', 499.90, 'New Kindle is here');
INSERT INTO tb_product (name, price, description) VALUES ('Nintendo Switch', 499.90, 'Best Nintendo console of 2017.');
INSERT INTO tb_product (name, price, description) VALUES ('DVD Player', 199, 'Physical media player');
INSERT INTO tb_product (name, price, description) VALUES ('IPad', 999, 'New IPad');
INSERT INTO tb_category (id, parent_category_id, name) VALUES (1, null, 'First Category');
INSERT INTO tb_client (id, name) VALUES (1,'Mario Miranda');
INSERT INTO tb_client (id, name) VALUES (2,'Christina Chan');
INSERT INTO tb_client (id, name) VALUES (3, 'Gatusca & Nino');
INSERT INTO tb_order (client_id,id,total,conclusion_date,create_date,city,complement,postal_code,province,rue,status) VALUES (1,1,10000,null,sysdate(),'Curitba','Apto 101','840-9204','Parana','Dr Goulin',null);