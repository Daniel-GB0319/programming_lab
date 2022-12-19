use empresa;
create table Employees
(
id int not null,
age int not null,
first varchar(255),
last varchar(255)
);

INSERT INTO Employees VALUES (100, 18, 'San', 'Bau');
INSERT INTO Employees VALUES (101, 25, 'Luis', 'Her');
INSERT INTO Employees VALUES (102, 30, 'Enri', 'Olv');
INSERT INTO Employees VALUES (103, 28, 'Ive', 'Ro');