

CREATE DATABASE bonsai;
\c bonsai

CREATE TABLE bonsai(
	id SERIAL PRIMARY KEY,
	nom text NOT NULL, 
	nomBotanic text, 
	familia text, 
	edat numeric CONSTRAINT edat_positiva CHECK (edat > 0),
	dataAlta date,
	dataBaixa date,
	propietaris text[]
);

CREATE TABLE tractaments(
	idTractament SERIAL PRIMARY KEY,
	idBonsai int,
	dataTractament date,
	motiuTractament text,	
	FOREIGN KEY (idBonsai) REFERENCES bonsai (id)
);



	
ALTER SEQUENCE bonsai_id_seq RESTART WITH 1;

INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai5', 'cydonia oblonga', '1', 5, '12/05/2014', '12/05/2014', '{"marc","marc2"}');
