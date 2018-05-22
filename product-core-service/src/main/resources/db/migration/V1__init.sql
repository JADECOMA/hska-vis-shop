CREATE TABLE product (
	id INT NOT NULL AUTO_INCREMENT,
	details VARCHAR(255),
	name VARCHAR(255),
	price DOUBLE,
	category_id INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

INSERT INTO product (details, name, price, category_id) VALUES
("Detail 1", "Name 1", 10.20, 1),
("Detail 2", "Name 2", 20.40, 2),
("Detail 3", "Name 3", 30.60, 1);

--CREATE UNIQUE INDEX UK_mufchskagt7e1w4ksmt9lum5l ON customer (username ASC);
--
--CREATE INDEX FK74aoh99stptslhotgf41fitt0 ON customer (role ASC);
--
--CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id ASC);

