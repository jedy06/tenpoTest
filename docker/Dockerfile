FROM maven:3.9.9-eclipse-temurin-21-noble AS build

WORKDIR /app

# Copia pom y dependencias primero para cacheo
COPY pom.xml .
COPY src ./src

# Instala Maven y compila
RUN mvn clean package -DskipTests

# Imagen final (runtime)
#FROM eclipse-temurin:21-jdk-alpine
FROM eclipse-temurin:21-noble

WORKDIR /app

# Copia el JAR generado desde la fase de build
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto del servicio
EXPOSE 8081

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
