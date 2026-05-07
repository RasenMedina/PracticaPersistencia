CREATE DATABASE escalada;

USE escalada;

DROP TABLE IF EXISTS escoles;
CREATE TABLE escoles (
	id_escola INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nom VARCHAR(20) NOT NULL UNIQUE,
    lloc VARCHAR(20) ,
    popularitat ENUM("baixa", "mitjana", "alta"),
    aproximacio VARCHAR(45),
    
	CONSTRAINT pk_escola PRIMARY KEY (id_escola)
);

DROP TABLE IF EXISTS sectors;
CREATE TABLE  sectors (
	id_sector INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_escola INT UNSIGNED NOT NULL,
    nom_sector VARCHAR(20) NOT NULL,
    longitud DECIMAL(11,8),
    latitude DECIMAL(11,8),
    aproximacio VARCHAR(40),
    popularitat ENUM("baixa", "mitjana", "alta"),
    es_gel BOOLEAN NOT NULL,
    
	CONSTRAINT pk_sector PRIMARY KEY (id_sector),
	CONSTRAINT fk_escola FOREIGN KEY (id_escola) REFERENCES escoles (id_escola)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS escaladors;
CREATE TABLE escaladors(
	id_escalador INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dni CHAR(9) NOT NULL,
    nom VARCHAR(20) NOT NULL,
    cog1 VARCHAR(20) NOT NULL,
    cog2 VARCHAR(20),
    alias VARCHAR(15),
    data_naix DATE,
    estil ENUM('esportiva', 'classica', 'gel'),
    
    CONSTRAINT pk_escalador PRIMARY KEY (id_escalador)
);

DROP TABLE IF EXISTS vies;
CREATE TABLE vies (
	id_via INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_sector INT UNSIGNED NOT NULL,
    id_escalador_creador INT UNSIGNED NOT NULL,
    nom VARCHAR(20) NOT NULL,
    data_creacio DATE,
    orientacio ENUM("N", "NE", "NO", "SE", "SO", "E", "O", "S"),
    ancoratge ENUM('friends', 'tascons', 'bagues', 'pitons', 'Tricams', 'BigBros', 'spits', 'parabolts', 'quimics'),
    tipus_roca ENUM('conglomerat', 'granit', 'calcaria', 'arenisca', 'altres'),
    tipus_via ENUM('esportiva', 'classica', 'gel') NOT NULL,
    estat ENUM('Apte', 'construcció', 'tancada') NOT NULL,
    restriccions VARCHAR(100),
    data_inici_no_apte DATE,
    data_fi_no_apte DATE,
    
    CONSTRAINT pk_via PRIMARY KEY (id_via),
    CONSTRAINT fk_sector FOREIGN KEY (id_sector) REFERENCES sectors (id_sector)
		ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_escalador_creador FOREIGN KEY (id_escalador_creador) REFERENCES escaladors (id_escalador)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS llargs;
CREATE TABLE llargs (
	id_llarg INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_via INT UNSIGNED NOT NULL,
    ordre INT UNSIGNED NOT NULL COMMENT "Començant per sota",
    llargada SMALLINT NOT NULL,
    grau_dificultat ENUM('4', '4+', '5', '5+', '6a', '6a+', '6b', '6b+', '6c', '6c+', '7a', '7a+', '7b', '7b+', '7c', '7c+', '8a', '8a+', '8b', '8c', '8c+' , '9a' , '9a+' , '9b' , '9b+' , '9c' ,'9c+'),
    
    CONSTRAINT pk_llarg PRIMARY KEY (id_llarg),
    CONSTRAINT fk_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS assoliments;
CREATE TABLE assoliments (
	id_assoliment INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_escalador INT UNSIGNED NOT NULL,
    id_via INT UNSIGNED NOT NULL,
    data DATE,
    
    CONSTRAINT pk_assoliment PRIMARY KEY (id_assoliment),
    CONSTRAINT fk_assoliment_escalador FOREIGN KEY (id_escalador) REFERENCES escaladors(id_escalador)
		ON DELETE RESTRICT 
        ON UPDATE CASCADE,
    CONSTRAINT fk_assoliment_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
		ON DELETE RESTRICT 
        ON UPDATE CASCADE
);

INSERT INTO escoles (nom, lloc, popularitat, aproximacio) VALUES
('Siurana', 'Tarragona', 'alta', '15 min caminant'),
('Montserrat', 'Barcelona', 'alta', '20 min caminant'),
('Rodellar', 'Huesca', 'alta', '25 min caminant'),
('Margalef', 'Tarragona', 'alta', '10 min caminant'),
('Terradets', 'Lleida', 'mitjana', '30 min caminant'),
('Albarracin', 'Teruel', 'alta', '15 min caminant'),
('Riglos', 'Huesca', 'mitjana', '20 min caminant'),
('Cavallers', 'Lleida', 'baixa', '40 min caminant'),
('Pedraforca', 'Barcelona', 'mitjana', '50 min caminant'),
('Gelida', 'Barcelona', 'baixa', '10 min caminant');

INSERT INTO sectors (id_escola, nom_sector, longitud, latitude, aproximacio, popularitat, es_gel) VALUES
(1, 'El Pati', 0.876543, 41.123456, '10 min', 'alta', FALSE),
(1, 'Can Marges', 0.876500, 41.120000, '15 min', 'alta', FALSE),
(2, 'Sant Benet', 1.123456, 41.600000, '20 min', 'alta', FALSE),
(2, 'Gorros', 1.120000, 41.610000, '25 min', 'alta', FALSE),
(3, 'Mascun', 0.500000, 42.300000, '30 min', 'alta', FALSE),
(4, 'Raco de la Finestra', 0.400000, 41.400000, '10 min', 'alta', FALSE),
(5, 'Paret de Catalunya', 0.300000, 42.000000, '30 min', 'mitjana', FALSE),
(6, 'Sector Arrastradero', 0.200000, 40.400000, '20 min', 'alta', FALSE),
(7, 'Mallos', 0.100000, 42.200000, '15 min', 'mitjana', FALSE),
(8, 'Gel Sector', 0.900000, 42.500000, '40 min', 'baixa', TRUE);

INSERT INTO escaladors (dni, nom, cog1, cog2, alias, data_naix, estil) VALUES
('12345678A', 'Marc', 'Garcia', 'Lopez', 'Maki', '1990-05-10', 'esportiva'),
('23456789B', 'Anna', 'Perez', 'Soler', 'Aneta', '1992-07-21', 'classica'),
('34567890C', 'Joan', 'Martinez', NULL, 'Jojo', '1985-03-15', 'gel'),
('45678901D', 'Laura', 'Gomez', 'Ruiz', 'Lau', '1998-11-02', 'esportiva'),
('56789012E', 'David', 'Torres', NULL, 'Davo', '1988-01-25', 'classica'),
('67890123F', 'Clara', 'Navarro', 'Diaz', 'Clari', '1995-06-18', 'gel'),
('78901234G', 'Pol', 'Sanchez', NULL, 'Poli', '2000-09-09', 'esportiva'),
('89012345H', 'Marta', 'Romero', 'Vega', 'Marti', '1993-12-30', 'classica'),
('90123456I', 'Nil', 'Ortega', NULL, 'Nilo', '1999-04-14', 'gel'),
('01234567J', 'Sara', 'Castro', 'Mora', 'Sarita', '1997-08-08', 'esportiva');

INSERT INTO vies (id_sector, id_escalador_creador, nom, data_creacio, orientacio, ancoratge, tipus_roca, tipus_via, estat) VALUES
(1, 1, 'La Classica', '2010-01-01', 'N', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(2, 2, 'El Repte', '2012-05-10', 'S', 'friends', 'granit', 'classica', 'Apte'),
(3, 3, 'Gel Etern', '2015-03-20', 'N', 'pitons', 'altres', 'gel', 'Apte'),
(4, 4, 'Via Nova', '2018-07-07', 'E', 'spits', 'calcaria', 'esportiva', 'Apte'),
(5, 5, 'Desafiament', '2011-11-11', 'O', 'tascons', 'granit', 'classica', 'Apte'),
(6, 6, 'Fred Extrem', '2019-12-01', 'N', 'pitons', 'altres', 'gel', 'Apte'),
(7, 7, 'Paret Alta', '2020-06-06', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(8, 8, 'Roca Dura', '2013-03-03', 'E', 'friends', 'granit', 'classica', 'Apte'),
(9, 9, 'Gel Blau', '2016-09-09', 'N', 'pitons', 'altres', 'gel', 'Apte'),
(10, 10, 'Final Boss', '2021-10-10', 'O', 'quimics', 'calcaria', 'esportiva', 'Apte');

INSERT INTO llargs (id_via, ordre, llargada, grau_dificultat) VALUES
(1, 1, 30, '6a'),
(1, 2, 25, '6b'),
(2, 1, 40, '5+'),
(3, 1, 50, '6c'),
(4, 1, 20, '5'),
(5, 1, 35, '6a+'),
(6, 1, 45, '7a'),
(7, 1, 25, '6b+'),
(8, 1, 30, '5+'),
(9, 1, 50, '7b');

INSERT INTO assoliments (id_escalador, id_via, data) VALUES
(1, 1, '2022-01-01'),
(2, 2, '2022-02-02'),
(3, 3, '2022-03-03'),
(4, 4, '2022-04-04'),
(5, 5, '2022-05-05'),
(6, 6, '2022-06-06'),
(7, 7, '2022-07-07'),
(8, 8, '2022-08-08'),
(9, 9, '2022-09-09'),
(10, 10, '2022-10-10');

