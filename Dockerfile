FROM maven:3.6.3-openjdk-17-slim
COPY src /home/app/src
COPY pom.xml /home/app
VOLUME maven-repo:/root/.m2
WORKDIR /home/app/
RUN mvn package
ENTRYPOINT ["mvn","spring-boot:run"]