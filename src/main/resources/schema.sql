DROP TABLE if exists obtain_ingr_merchant;
DROP TABLE if exists trade_ingr_merchant;
DROP TABLE if exists update_ingr_merchant;

CREATE TABLE obtain_ingr_merchant
(
    id int primary key auto_increment,
);

CREATE TABLE ingredient
(
    id int primary key auto_increment,
    name varchar(255),
    value int,
    obtain_ingr_merchant_id int,
    foreign key (obtain_ingr_merchant_id) references obtain_ingr_merchant(id)
);

/*CREATE TABLE users
(
    id int primary key auto_increment,
    user_id varchar(255),
    first_name varchar(50),
    last_name varchar(50),
    email varchar(120),
    encrypted_password varchar(255),
    email_verification_token varchar(255),
    email_verification_status bit(1)
);*/
