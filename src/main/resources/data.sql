-- member
INSERT INTO member (user_id, password, name, created_date, modified_date) VALUES ('test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', '관리자', now(), now());

-- product
INSERT INTO product (product_name, product_price, created_date, modified_date) VALUES ('사무실 의자', 35000, now(), now());
INSERT INTO product (product_name, product_price, created_date, modified_date) VALUES ('컴퓨터 의자', 50000, now(), now());