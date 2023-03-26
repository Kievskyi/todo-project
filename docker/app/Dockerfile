# Dockerfile для билда проекта Maven и Tomcat
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /usr/src/app
COPY /pom.xml .
RUN mvn dependency:go-offline

COPY /src/ /usr/src/app/src/
RUN mvn package -DskipTests

FROM tomcat:latest
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /usr/src/app/target/todo-project.war /usr/local/tomcat/webapps/ROOT.war
