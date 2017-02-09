

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

INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai', 'cydonia oblonga', 'Rosacces', 5, '12/05/2014', '12/05/2014', '{"marc","marc2"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai1', 'cydonia oblonga', 'Rosacces', 47, '12/05/2014', '12/05/2014', '{"marc2","marc3"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai2', 'cydonia oblonga', 'Rosacces', 9, '12/05/2014', '12/05/2014', '{"marc3","marc4"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai3', 'cydonia oblonga', 'Rosacces', 1, '12/05/2014', '12/05/2014', '{"marc4","marc5"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai4', 'cydonia oblonga', 'Rosacces', 4, '12/05/2014', '12/05/2014', '{"marc5","marc6"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai5', 'cydonia oblonga', 'Rosacces', 12, '12/05/2014', '12/05/2014', '{"marc6","marc7"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai6', 'cydonia oblonga', 'Rosacces', 51, '12/05/2014', '12/05/2014', '{"marc7","marc8"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai7', 'cydonia oblonga', 'Rosacces', 25, '12/05/2014', '12/05/2014', '{"marc8","marc9"}');
INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris ) VALUES('saisho no bonsai8', 'cydonia oblonga', 'Rosacces', 7, '12/05/2014', '12/05/2014', '{"marc9","marc10"}');
