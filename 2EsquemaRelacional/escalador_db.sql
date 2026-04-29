CREATE DATABASE escalador;

USE escalador;

DROP TABLE IF EXISTS escoles;
CREATE TABLE escoles (
	id_escola INT NOT NULL AUTO_INCREMENT,
    nom VARCHAR(20) NOT NULL UNIQUE,
    lloc VARCHAR(20) ,
    popularitat ENUM("baixa", "mitjana", "alta"),
    aproximacio VARCHAR(45),
    
	CONSTRAINT pk_escola PRIMARY KEY (id_escola)
);

DROP TABLE IF EXISTS sectors;
CREATE TABLE  sectors (
	id_sector INT NOT NULL AUTO_INCREMENT,
    id_escola INT NOT NULL,
    nom_sector VARCHAR(20) NOT NULL,
    longitud DECIMAL(9,6),
    latitude DECIMAL(9,6),
    aproximacio VARCHAR(40),
    popularitat ENUM("baixa", "mitjana", "alta"),
    es_gel BOOLEAN,
    
	CONSTRAINT pk_sector PRIMARY KEY (id_sector),
	CONSTRAINT fk_escola FOREIGN KEY (id_escola) REFERENCES escoles (id_escola)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS escaladors;
CREATE TABLE escaladors(
	id_escalador INT NOT NULL AUTO_INCREMENT,
    dni CHAR(9) NOT NULL,
    nom VARCHAR(20) NOT NULL,
    cog1 VARCHAR(20) NOT NULL,
    cog2 VARCHAR(20) NOT NULL,
    alias VARCHAR(15),
    data_naix DATE,
    estil ENUM('esportiva', 'classica', 'gel'),
    
    CONSTRAINT pk_escalador PRIMARY KEY (id_escalador)
);

DROP TABLE IF EXISTS vies;
CREATE TABLE vies (
	id_via INT NOT NULL AUTO_INCREMENT,
    id_sector INT NOT NULL,
    id_escalador_creador INT NOT NULL,
    nom VARCHAR(20) NOT NULL,
    llargada SMALLINT NOT NULL,
    data_creacio DATE NOT NULL,
    orientacio ENUM("N", "NE", "NO", "SE", "SO", "E", "O", "S"),
    encoratge ENUM('friends', 'tascons', 'bagues', 'pitons', 'Tricams', 'BigBros', 'spits', 'parabolts', 'quimics'),
    tipus_roca ENUM('conglomerat', 'granit', 'calcaria', 'arenisca', 'altres'),
    tipus_via ENUM('esportiva', 'classica', 'gel') NOT NULL,
    estat ENUM('Apte', 'construcció', 'tancada'),
    restriccions VARCHAR(100),
    data_inici_no_apte DATE,
    data_fi_no_apte DATE,
    
    CONSTRAINT pk_via PRIMARY KEY (id_via),
    CONSTRAINT fk_sector FOREIGN KEY (id_sector) REFERENCES sectors (id_sector)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_escalador_creador FOREIGN KEY (id_escalador_creador) REFERENCES escaladors (id_escalador)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS llargs;
CREATE TABLE llargs (
	id_llarg INT NOT NULL AUTO_INCREMENT,
    id_via INT NOT NULL,
    num INT NOT NULL,
    llargada SMALLINT,
    grau_dificultat ENUM('4', '4+', '5', '5+', '6a', '6a+', '6b', '6b+', '6c', '6c+', '7a', '7a+', '7b', '7b+', '7c', '7c+', '8a', '8a+', '8b'),
    
    CONSTRAINT pk_llarg PRIMARY KEY (id_llarg),
    CONSTRAINT fk_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
);

DROP TABLE IF EXISTS assoliments;
CREATE TABLE assoliments (
	id_escalador INT NOT NULL,
    id_via INT NOT NULL,
    data DATE,
    
    CONSTRAINT pk_assoliment PRIMARY KEY (id_escalador, id_via, data),
    CONSTRAINT fk_assoliment_escalador FOREIGN KEY (id_escalador) REFERENCES escaladors(id_escalador)
		ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_assoliment_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
		ON DELETE CASCADE 
        ON UPDATE CASCADE
);



INSERT INTO escoles (id_escola, nom, lloc, popularitat) VALUES
(1, 'Siurana', 'Cornudella', 'alta'),
(2, 'Margalef', 'Priorat', 'alta'),
(3, 'Montserrat', 'Bages', 'alta'),
(4, 'Rodellar', 'Huesca', 'alta'),
(5, 'Chulilla', 'Valencia', 'mitjana'),
(6, 'Oliana', 'Alt Urgell', 'alta'),
(7, 'El Chorro', 'Málaga', 'mitjana'),
(8, 'Kalymnos', 'Grècia', 'alta'),
(9, 'Ceüse', 'França', 'alta'),
(10, 'Albarracín', 'Teruel', 'baixa');

INSERT INTO sectors (id_sector, id_escola, nom_sector, longitud, latitude, aproximacio, popularitat, es_gel) VALUES
(1, 1, 'Siuranella', 0.9324, 41.2581, '15 min', 'alta', 0),
(2, 1, 'Esperó Primavera', 0.9310, 41.2575, '10 min', 'mitjana', 0),
(3, 2, 'Laboratori', 0.7850, 41.2843, '5 min', 'alta', 0),
(4, 2, 'Finestra', 0.7860, 41.2850, '20 min', 'alta', 0),
(5, 3, 'Cavall Bernat', 1.8123, 41.6012, '45 min', 'alta', 0),
(6, 4, 'Las Ventanas', -0.0789, 42.2834, '30 min', 'alta', 0),
(7, 5, 'El Chorrillo', -0.8921, 39.6543, '10 min', 'baixa', 0),
(8, 6, 'Contrafort', 1.2945, 42.0678, '15 min', 'alta', 0),
(9, 8, 'Grande Grotta', 26.9234, 37.0012, '25 min', 'alta', 0),
(10, 9, 'Biographie', 5.9521, 44.5012, '60 min', 'alta', 0);

INSERT INTO escaladors (id_escalador, dni, nom, cog1, cog2, alias, data_naix, estil) VALUES
(1, '12345678A', 'Adam', 'Ondra', 'S', 'The Beast', '1993-02-05', 'esportiva'),
(2, '23456789B', 'Chris', 'Sharma', 'G', 'King', '1981-04-23', 'esportiva'),
(3, '34567890C', 'Alex', 'Honnold', 'P', 'Free Solo', '1985-08-17', 'classica'),
(4, '45678901D', 'Janja', 'Garnbret', 'M', 'Goat', '1999-03-12', 'esportiva'),
(5, '56789012E', 'Margo', 'Hayes', 'W', 'Margo', '1998-02-11', 'esportiva'),
(6, '67890123F', 'Sasha', 'DiGiulian', 'R', 'Sash', '1992-10-23', 'esportiva'),
(7, '78901234G', 'Edu', 'Marin', 'F', 'Edu', '1985-12-15', 'classica'),
(8, '89012345H', 'Oriane', 'Bertone', 'L', 'Ori', '2005-03-10', 'esportiva'),
(9, '90123456I', 'Will', 'Gadd', 'T', 'Ice Man', '1967-03-08', 'gel'),
(10, '01234567J', 'Laura', 'Rogora', 'B', 'Laura', '2001-04-28', 'esportiva');

INSERT INTO vies (id_via, id_sector, id_escalador_creador, nom, llargada, data_creacio, orientacio, encoratge, tipus_roca, tipus_via, estat) VALUES
(1, 1, 2, 'La Rambla', 40, '2003-03-08', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(2, 3, 1, 'First Round', 15, '2011-04-19', 'O', 'parabolts', 'conglomerat', 'esportiva', 'Apte'),
(3, 5, 3, 'Via de l’Aeri', 300, '1970-05-10', 'N', 'pitons', 'conglomerat', 'classica', 'Apte'),
(4, 6, 2, 'Dolphin', 25, '1995-06-12', 'SE', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(5, 8, 1, 'Papichulo', 45, '2008-05-31', 'SO', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(6, 10, 1, 'Realization', 35, '2001-07-18', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(7, 2, 7, 'Pati Noso', 20, '2015-10-20', 'E', 'spits', 'calcaria', 'esportiva', 'Apte'),
(8, 4, 4, 'Era Vella', 50, '2010-02-15', 'NO', 'parabolts', 'conglomerat', 'esportiva', 'Apte'),
(9, 7, 5, 'Chulilla Way', 30, '2020-01-01', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte'),
(10, 9, 8, 'Ixion', 20, '2018-08-05', 'SE', 'parabolts', 'calcaria', 'esportiva', 'Apte');

INSERT INTO llargs (id_via, num, llargada, grau_dificultat) VALUES
(1, 1, 40, '8b'), 
(2, 1, 15, '8b'),
(3, 1, 40, '5+'),
(3, 2, 35, '6a'), 
(3, 3, 45, '6b'),
(4, 1, 25, '7c'),
(5, 1, 45, '8b'),
(6, 1, 35, '8b'), 
(8, 1, 50, '8a+'), 
(10, 1, 20, '7b'); 

INSERT INTO assoliments (id_escalador, id_via, data) VALUES
(1, 1, '2008-02-10'), 
(4, 1, '2021-09-15'),
(2, 1, '2003-03-08'), 
(1, 2, '2017-02-15'), 
(10, 5, '2020-07-20'), 
(5, 6, '2017-09-24'),
(3, 3, '2014-06-12'),
(8, 10, '2022-05-18'),
(1, 5, '2016-12-01'),
(6, 8, '2019-11-30'); 