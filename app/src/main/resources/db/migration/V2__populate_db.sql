INSERT INTO worker (name, birthday, level, salary) VALUES
('Olya', '2000-01-07', 'Trainee','900'),
('Vanya', '1980-01-04', 'Trainee','800'),
('Valya', '2000-01-07', 'Junior', '7000'),
('Kolya', '1983-02-09', 'Junior', '6000'),
('Anya', '1990-03-05', 'Middle', '7000'),
('Vova', '1980-01-04', 'Middle', '8000'),
('Toya', '1988-08-31', 'Senior', '9000'),
('Ira', '1989-09-25', 'Senior', '10000'),
('Igor', '1990-03-05', 'Senior', '11000'),
('Vita', '2000-01-07', 'Senior', '12000');


INSERT INTO client (name) VALUES
('Ivan Vasiliy'),
('Olga Gorgula'),
('Taras Sergiev'),
('Anyuta Stepan'),
('Bodya Toshka');

INSERT INTO project (client_id, name, start_date, finish_date) VALUES
('1', 'Project1', '2000-01-07', '2000-08-07'),
('1', 'Project2', '2000-02-07', '2001-03-07'),
('2', 'Project3', '2000-03-07', '2002-04-07'),
('2', 'Project4', '2000-04-07', '2003-05-07'),
('4', 'Project5', '2000-05-07', '2000-06-07'),
('4', 'Project6', '2000-06-07', '2001-07-07'),
('4', 'Project7', '2000-07-07', '2004-08-07'),
('5', 'Project8', '2000-08-07', '2001-09-07'),
('5', 'Project9', '2000-09-07', '2001-10-07'),
('5', 'Project10', '2000-10-07', '2002-11-07');

INSERT INTO project_worker (project_id, worker_id) VALUES
('1', '1'),
('1', '2'),
('1', '3'),
('2', '4'),
('2', '5'),
('3', '6'),
('3', '7'),
('4', '8'),
('4', '9'),
('5', '10'),
('6', '1'),
('6', '2'),
('7', '3'),
('8', '4'),
('9', '5');