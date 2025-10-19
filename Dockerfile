FROM openjdk:21-slim
WORKDIR /app
COPY target/*.jar dama_shop.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dama_shop.jar"]