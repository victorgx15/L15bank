# L15bank
L15 is a Java microservice application... to manage bank accounts

#### Execution du programme:
Ouvrir 3 terminaux:
-User (port 8080)
-Account (port 9001)
-Operation (port 9020)

Pour chacun de ces terminaux (User en dernier):
- mvn clean package
- ./mvnw spring-boot:run

#### Tuto Spring en général
https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot
https://spring.io/guides/gs/spring-boot/

#### Tuto Spring sur le web
https://o7planning.org/fr/11545/tutoriel-spring-boot-et-thymeleaf
https://spring.io/guides/gs/serving-web-content/
https://g00glen00b.be/spring-form-validation/

#### Tuto Spring database
http://www.springboottutorial.com/spring-boot-and-h2-in-memory-database
https://spring.io/guides/gs/accessing-data-jpa/
http://localhost:8080/h2-console

#### Découpage en microservices
https://openclassrooms.com/fr/courses/4668216-optimisez-votre-architecture-microservices/5176135-creez-les-microservices-e-commerce-et-leur-client