Accessing Intellij
- cd Documents/idea-IC-252.26830.84/bin
- ./idea.sh

Running the mysql docker
- docker run test_mysql
- docker exec -it test_mysql bash 
- mysql -u root -p

Access Swagger for API testing:
- http://localhost:8080/swagger-ui.html


Look into User/API Get List of Users

Look into Group/API Get Members by group ID

For more sensor add java classes like this:
- entity
- repository
- service
- serviceimpl
- mapper
- dto
- api
- apiimpl

Sensors to do:
- co2
- humidity
- moisture

To build backend into Jar run:
-  mvn clean install -DskipTests

git lfs commit:
- git lfs track "backend/mylinks-spring-boot-main/target/mylinks-spring-boot-1.0-SNAPSHOT.jar"
- git add .gitattributes
- git add backend/mylinks-spring-boot-main/target/mylinks-spring-boot-1.0-SNAPSHOT.jar
- git commit -m "Add JAR via LFS"
- git push origin backend