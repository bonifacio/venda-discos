INSERT INTO GENERO 
	(NOME)
VALUES
	('POP'),
	('MPB'),
	('CLASSIC'),
	('ROCK');

INSERT INTO CASHBACK
	(DIA, PERCENTUAL, GENERO_ID)
VALUES
	('SUNDAY', 0.25, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('MONDAY', 0.07, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('TUESDAY', 0.06, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('WEDNESDAY', 0.02, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('THURSDAY', 0.10, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('FRIDAY', 0.15, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	('SATURDAY', 0.20, (SELECT ID GENERO FROM GENERO WHERE NOME = 'POP')),
	
	('SUNDAY', 0.30, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('MONDAY', 0.05, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('TUESDAY', 0.10, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('WEDNESDAY', 0.15, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('THURSDAY', 0.20, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('FRIDAY', 0.25, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	('SATURDAY', 0.30, (SELECT ID GENERO FROM GENERO WHERE NOME = 'MPB')),
	
	('SUNDAY', 0.35, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('MONDAY', 0.03, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('TUESDAY', 0.05, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('WEDNESDAY', 0.08, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('THURSDAY', 0.13, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('FRIDAY', 0.18, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	('SATURDAY', 0.25, (SELECT ID GENERO FROM GENERO WHERE NOME = 'CLASSIC')),
	
	('SUNDAY', 0.40, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('MONDAY', 0.10, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('TUESDAY', 0.15, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('WEDNESDAY', 0.15, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('THURSDAY', 0.15, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('FRIDAY', 0.20, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK')),
	('SATURDAY', 0.40, (SELECT ID GENERO FROM GENERO WHERE NOME = 'ROCK'));
	
--INSERT INTO DISCO (NOME, PRECO, GENERO_ID) VALUES ('Banda di2', 10, 3);
