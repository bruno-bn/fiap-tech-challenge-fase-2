# Estágio 1: Build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom.xml e baixa as dependências (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline
# Copia o código fonte e compila o projeto ignorando os testes para ser mais rápido
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final de execução (apenas JRE para ficar mais leve)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia apenas o .jar gerado no Estágio 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando que roda quando o container inicia
ENTRYPOINT ["java", "-jar", "app.jar"]