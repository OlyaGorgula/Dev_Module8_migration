CREATE TABLE IF NOT EXISTS worker(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000) NOT NULL,
    birthday DATE,
    level VARCHAR(10) NOT NULL,
    salary INTEGER
);
ALTER TABLE worker ADD CONSTRAINT level_enum_values CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior'));
ALTER TABLE worker ADD CONSTRAINT salary_range CHECK (salary > 100 AND salary < 100000 );
ALTER TABLE worker ADD CONSTRAINT birthday_range CHECK (birthday >= '1900-01-01' );
ALTER TABLE worker ADD CONSTRAINT worker_name_length CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000 );

CREATE TABLE IF NOT EXISTS client(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000) NOT NULL
);
ALTER TABLE client ADD CONSTRAINT client_name_length CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000 );

CREATE TABLE IF NOT EXISTS project(
    id IDENTITY PRIMARY KEY,
    client_id BIGINT NOT NULL,
    name VARCHAR(1000) NOT NULL,
    start_date DATE,
    finish_date DATE
);
ALTER TABLE project ADD CONSTRAINT client_id_fk FOREIGN KEY(client_id) REFERENCES client(id);
ALTER TABLE project ADD CONSTRAINT project_name_length CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000 );

CREATE TABLE IF NOT EXISTS project_worker(
    project_id BIGINT,
    worker_id BIGINT,
    PRIMARY KEY (project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);