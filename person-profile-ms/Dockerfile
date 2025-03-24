# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR del microservicio al contenedor
COPY target/person-profile-ms.jar person-profile-ms.jar

# Exponer el puerto en el que corre el microservicio
EXPOSE 9001

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "person-profile-ms.jar"]