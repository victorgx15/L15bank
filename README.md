## Execution du programme:

Ouvrir 3 terminaux:
- User (port 8080)
- Account (port 9001)
- Operation (port 9020)

Pour chacun de ces terminaux (Par ordre: Operation, Account et User):
- mvn clean package
- mvn spring-boot:run

## Execution du programme avec docker:
Have docker installed and run:
sudo docker run -p 9001:9001 togira/l15bank:account
sudo docker run -p 9020:9020 togira/l15bank:operation
sudo docker run -p 8080:8080 togira/l15bank:latest
This will download the images and run them

#build a docker image:
-build with maven
-sudo docker build -t togira/l15bank:user  .
-sudo docker build -t togira/l15bank:account  .
-sudo docker build -t togira/l15bank:operation  .

