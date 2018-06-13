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
insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('Manuel', 'Härtle', '123456', 'jadecoma', 0);
insert into `customer` (`name`, `lastname`, `password`, `username`, `role`) values('Fritz', 'Müller', '123456', 'SuperFritz', 0);