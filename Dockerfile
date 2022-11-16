FROM openjdk:17-jdk-alpine
LABEL maintainer="jdaalba@gmail.com"
COPY target/app.jar /app.jar
COPY src/main/resources/application.yml /application.yml
COPY src/main/resources/application-mail.yml /application-mail.yml
COPY src/main/resources/static/images/ src/main/resources/static/images/
ENTRYPOINT ["java","-jar","/app.jar"]