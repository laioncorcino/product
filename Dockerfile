# Baixando a imagem do OpenJDK 18
FROM openjdk:18

#Definindo a pasta de trabalho do container
WORKDIR /app

#Copiando todos os arquivos para esta pasta de trabalho
COPY . /app

#Executando o comando para gerar o deploy (pacote de execução) do projeto
RUN ./mvnw clean package -B -DskipTests

#Define a porta em que o projeto irá rodar dentro do container
EXPOSE 8082

#Executando o projeto quando o container for inicializado
CMD ["java", "-jar", "target/product-0.0.1-SNAPSHOT.jar"]
