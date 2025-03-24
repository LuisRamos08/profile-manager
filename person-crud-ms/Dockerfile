# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR del microservicio al contenedor
COPY target/person-crud-ms.jar person-crud-ms.jar

# Exponer el puerto en el que corre el microservicio
EXPOSE 9000

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "person-crud-ms.jar"]