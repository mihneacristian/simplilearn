CREATE SCHEMA IF NOT EXISTS `simplilearn`;

CREATE TABLE IF NOT EXISTS `simplilearn`.`user`
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    role       VARCHAR(255) NOT NULL,
    active     BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS `simplilearn`.`product`
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category    VARCHAR(255) NOT NULL,
    price       DOUBLE       NOT NULL,
    stock       INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS `simplilearn`.`order`
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    user_id    INT NOT NULL,
    order_date DATETIME NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO `simplilearn`.`user` (username, password, first_name, last_name, email, role, active) VALUES ('admin', '$2y$10$rQIY.7NQ7vQ0l3Lftmk79.JWrorfeOAwrK2p2EZ0NRX6BSC5ppnqK',
                                                                                                     'admin', 'admin',
                                                                                                     'admin@admin.com', 'ADMIN',
                                                                                                     TRUE);

INSERT INTO `simplilearn`.`product` (name, description, category, price, stock) VALUES ('TV', 'Television set', 'Home media', 2500.5, 50);