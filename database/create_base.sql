CREATE TABLE user_groups (
	id BIGINT PRIMARY KEY
);

CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	birth_date TIMESTAMP,
	job_agreement_number VARCHAR(100),
	role VARCHAR(30) NOT NULL,
	phone_number VARCHAR(100),
	email VARCHAR(100),
	about_yourself VARCHAR(300),
	user_group_id BIGINT,
	FOREIGN KEY (user_group_id) REFERENCES user_groups(id)
);

CREATE TABLE credentials (
	id BIGINT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE experiments (
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(50),
	description VARCHAR(300),
	creation_time TIMESTAMP,
	research_group_id BIGINT,
	status VARCHAR(50),
	FOREIGN KEY (research_group_id) REFERENCES user_groups(id)
);

CREATE TABLE applications (
	id BIGSERIAL PRIMARY KEY,
	type VARCHAR(50),
	description VARCHAR(200),
	creator_id BIGINT,
	creation_date TIMESTAMP,
	experiment_id BIGINT,
	last_status_transition_date TIMESTAMP,
	status VARCHAR(50),
	execution_group_id BIGINT,
	FOREIGN KEY (execution_group_id) REFERENCES user_groups(id),
	FOREIGN KEY (experiment_id) REFERENCES experiments(id),
	FOREIGN KEY (creator_id) REFERENCES users(id)
);

CREATE TABLE apps_technic (
	id BIGINT PRIMARY KEY,
	content VARCHAR(1000),
	FOREIGN KEY (id) REFERENCES applications(id)
);

CREATE TABLE reports (
	id BIGSERIAL PRIMARY KEY,
	title VARCHAR(50),
	content VARCHAR(1000),
	creation_date TIMESTAMP,
	experiment_id BIGINT,
	application_id BIGINT,
	FOREIGN KEY (experiment_id) REFERENCES experiments(id),
	FOREIGN KEY (application_id) REFERENCES applications(id)
);

CREATE TABLE subjects (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(300),
	hair_color VARCHAR(100),
	eyes_color VARCHAR(100),
	skin_color VARCHAR(100),
	specials VARCHAR(400),
	weight REAL,
	height REAL,
	birth_date DATE
);

CREATE TABLE artifacts (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(300),
	description VARCHAR(1000)
);

CREATE TABLE apps_analysis (
	id BIGINT PRIMARY KEY,
	subject_id BIGSERIAL,
	analysis_description VARCHAR(1000),
	FOREIGN KEY (subject_id) REFERENCES subjects(id),
	FOREIGN KEY (id) REFERENCES applications(id)
);

CREATE TABLE landing_points (
	id BIGSERIAL PRIMARY KEY,
	coord_x NUMERIC(12, 8),
	coord_y NUMERIC(12, 8),
	artifact_id BIGINT,
	application_id BIGINT,
	amount BIGINT,
	FOREIGN KEY (artifact_id) REFERENCES artifacts(id),
	FOREIGN KEY (application_id) REFERENCES applications(id)
);

CREATE TABLE apps_landing (
	id BIGINT PRIMARY KEY,
	FOREIGN KEY (id) REFERENCES applications(id)
);

WITH ins1 AS (
    INSERT INTO users (first_name, role)
    VALUES ('Admin', 'ADMIN')
    RETURNING id AS id
)
INSERT INTO credentials (id, username, password)
SELECT id, 'admin', 'admin' FROM ins1;
