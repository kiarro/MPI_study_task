CREATE TABLE user_groups (
	id BIGSERIAL PRIMARY KEY
);

CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	birth_date TIMESTAMP,
	job_agreement_number VARCHAR(10),
	role VARCHAR(30) NOT NULL,
	phone_number VARCHAR(20),
	email VARCHAR(50),
	about_yourself VARCHAR(300),
	user_group BIGINT
	-- CONSTRAINT FK_UserGroup FOREIGN KEY (user_group) REFERENCES user_groups(id)
);

INSERT INTO users (username, password, first_name, role)
VALUES ('admin', 'admin', 'Admin', 'ADMIN');
