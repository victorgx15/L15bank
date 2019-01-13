INSERT INTO Account(id,iban,type) VALUES	(10,'FR562039' , 'Counrat'),
										(20,'FR4539' , 'Livret'),
										(30,'FR89039' , 'LDD');

INSERT INTO user(id,name,first_name, email, username, password, passwordConfirm) VALUES	(1,'dupond' , 'toto', 'toto@gmail.com', 'toto', 'password', 'password');

INSERT INTO role(id,name, users) VALUES	(1,'USER', 1);
