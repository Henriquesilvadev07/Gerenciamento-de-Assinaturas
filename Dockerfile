# Estágio de Build (Compilação com Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
ENV LANG=C.UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução (Imagem leve para rodar o JAR)
FROM eclipse-temurin:21-jre
ENV LANG=C.UTF-8
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]