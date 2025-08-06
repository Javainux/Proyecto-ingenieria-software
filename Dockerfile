# Etapa de build con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de producción con JDK ligero
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Midoc-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto que Railway necesita
EXPOSE 8080


RUN ls -l /app

ENTRYPOINT ["java", "-jar", "app.jar"]
