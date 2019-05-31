FROM maven:3.6.1-jdk-11 AS build
WORKDIR /usr/src/app
COPY src src
COPY pom.xml .
RUN mvn -e package

FROM openjdk:11
ARG DB_USER
ARG DB_PASSWORD
ENV ENV_DB_USER=$DB_USER
ENV ENV_DB_PASSWORD=$DB_PASSWORD
WORKDIR /opt/venda-discos
COPY --from=build /usr/src/app/target/venda-discos*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]