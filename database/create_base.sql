DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS app_technic;
DROP TABLE IF EXISTS experiments;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_groups;


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

CREATE TABLE experiments (
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(50),
	description VARCHAR(300),
	creation_time TIMESTAMP,
	research_group BIGINT,
	state VARCHAR(50)
);

CREATE TABLE reports (
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(50),
	content VARCHAR(1000),
	creation_date TIMESTAMP,
	experiment_id BIGINT
);

CREATE TABLE applications (
	id BIGSERIAL PRIMARY KEY,
	type VARCHAR(50),
	description VARCHAR(200),
	creator BIGINT,
	creation_date TIMESTAMP,
	experiment_id BIGINT,
	last_status_transition_date TIMESTAMP,
	status VARCHAR(50),
	executionGroup BIGINT,
	report BIGINT
);

CREATE TABLE app_technic (
	id BIGINT,
	content VARCHAR(1000)
);


INSERT INTO users (username, password, first_name, role)
VALUES ('admin', 'admin', 'Admin', 'ADMIN');
