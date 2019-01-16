INSERT INTO Account(id, iban, type, fee, interest, user) VALUES
(10,'FR562039' , 'Counrat', 23.4,0, 10),
(20,'FR4539' , 'Livret',12.5, 0.08,10),
(30,'FR89039' , 'LDD',89.4, 0.3, 3);

INSERT INTO user(id, first_name, last_name, email, password) VALUES
(10, 'Nicolas', 'Sarkozy', 'fdis@fkds.fr', 'truc');

INSERT INTO Operation(id, ibanSrc, ibanDest, value, date, label, type) VALUES
(101,'FR562039' , 'FR4539',20,'13/01/20180', 'pizza', 'VIREMENT');