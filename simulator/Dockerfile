# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado para o contêiner
COPY target/simulator-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]