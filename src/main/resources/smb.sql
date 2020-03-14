
DELETE from User;
CREATE TABLE User (
    id int(100) NOT NULL AUTO_INCREMENT,
    username varchar(50) not null,
    password varchar(50) not null,
	status varchar(10) not null,
	PRIMARY KEY (`id`)
);