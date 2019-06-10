FROM openjdk:8-jre-alpine
ARG DB_USER
ARG DB_PASSWORD
ENV ENV_DB_USER=$DB_USER
ENV ENV_DB_PASSWORD=$DB_PASSWORD
WORKDIR /opt/venda-discos
COPY target/venda-discos*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]