INSERT INTO Account(id, iban, type, fee, interest, user) VALUES
(10,'FR562039' , 'Counrat', 23.4,0, 1),
(20,'FR4539' , 'Livret',12.5, 0.08,1),
(30,'FR89039' , 'LDD',89.4, 0.3, 3);

INSERT INTO user(id, email, password) VALUES
(1, 'fdis@fkds.fr', 'truc');

INSERT INTO Operation(id, ibanSrc, ibanDest, value, date, label, type) VALUES
(101,'FR562039' , 'FR4539',20,'13/01/20180', 'pizza', 'VIREMENT');