CREATE TABLE customer (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	role INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('admin', 'admin', 'admin', 'admin', 1);
insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('Manuel', 'Härtle', '123456', 'jadecoma', 2);
insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('Fritz', 'Müller', '123456', 'SuperFritz', 2);

CREATE TABLE role (
	id INT NOT NULL AUTO_INCREMENT,
	level1 INT,
	type VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into `role` (`level1`, `type`) values(0, 'admin');
insert into `role` (`level1`, `type`) values(1, 'user');