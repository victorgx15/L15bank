INSERT INTO Account(id,iban,type,user) VALUES	(10,'FR562039' , 'Counrat',1), (20,'FR4539' , 'Livret',1), (30,'FR89039' , 'LDD',3);
INSERT INTO user(id, email, username, password) VALUES (1, 'fdis@fkds.fr', 'chose', 'truc');
insert into Role (User_Id, Role_Name) values (1, 'ROLE_USER');
INSERT INTO Operation(id,ibanSrc,ibanDest,value, date, Label) VALUES	(101,'FR562039' , 'FR4539',20,'13/01/20180', 'virement');