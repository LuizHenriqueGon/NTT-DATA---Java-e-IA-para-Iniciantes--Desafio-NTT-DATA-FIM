# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem oficial do Maven com JDK 21 para compilar o nosso projeto.
FROM maven:3.9-eclipse-temurin-21-alpine AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o ficheiro pom.xml para o container para descarregar as dependências
COPY pom.xml .

# Descarrega todas as dependências do projeto. O Docker irá guardar esta camada em cache.
RUN mvn dependency:go-offline

# Copia o resto do código-fonte do projeto
COPY src ./src

# Compila a aplicação e gera o ficheiro .jar
RUN mvn package -DskipTests

# Estágio 2: Criação da Imagem Final
# Usamos uma imagem base do Java 21, que é muito mais leve que a imagem do Maven.
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o ficheiro .jar que foi gerado no estágio de build
# Altere 'product-catalog-0.0.1-SNAPSHOT.jar' se o nome do seu artefacto for diferente
COPY --from=build /app/target/product-catalog-0.0.1-SNAPSHOT.jar .

# Expõe a porta que a aplicação usa dentro do container
EXPOSE 8100

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "product-catalog-0.0.1-SNAPSHOT.jar"]