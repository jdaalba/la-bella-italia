FROM openjdk:17-jdk-alpine
#MAINTAINER jdaalba.com
COPY target/app.jar /app.jar
COPY src/main/resources/application.yml /application.yml
COPY src/main/resources/application-mail.yml /application-mail.yml
#VOLUME src/main/resources /
ENTRYPOINT ["java","-jar","/app.jar","-Dspring-boot.run.profiles=mail"]