# Proyecto de Microservicios con Spring Boot y Kubernetes

Este proyecto implementa un sistema de gestión de perfiles utilizando una arquitectura basada en microservicios con Spring Boot, MongoDB, Docker y Kubernetes. La solución está diseñada para ser escalable, segura y eficiente en el manejo de información de perfiles de usuarios.

## 👑 Índice
1. 📌 [Arquitectura](#-arquitectura)
2. 🚀 [Tecnologías Utilizadas](#-tecnologías-utilizadas)
3. ⚙️ [Configuración y Despliegue](#%ef%b8%8f-configuración-y-despliegue)
    - 1️⃣ [Clonar el Repositorio](#1%ef%b8%8f⃣-clonar-el-repositorio)
    - 2️⃣ [Construir Imágenes Docker](#2%ef%b8%8f⃣-construir-imágenes-docker)
    - 3️⃣ [Verificar Imágenes Docker](#3%ef%b8%8f⃣-verificar-imágenes-docker)
    - 4️⃣ [Configurar Kubernetes](#4%ef%b8%8f⃣-configurar-kubernetes)
    - 5️⃣ [Verificar el Estado de los Pods y Servicios](#5%ef%b8%8f⃣-verificar-el-estado-de-los-pods-y-servicios)
    - 6️⃣ [Acceder a los Servicios](#6%ef%b8%8f⃣-acceder-a-los-servicios)
4. 🛠️ [Probar los Microservicios](#-probar-los-microservicios)
5. 🖥️ [Script Automático para Despliegue](#%ef%b8%8f-script-automático-para-despliegue)
    - 💻 Windows (`deploy-all.bat`)
    - 💻 Linux & Mac (`deploy-all.sh`)
6. 🔄 [Comandos Útile para Mantenimiento](#-comandos-útiles-para-mantenimiento)

## 📌 Arquitectura
El sistema consta de los siguientes componentes clave:
- **person-crud-ms**: Microservicio que proporciona operaciones CRUD sobre perfiles de personas.
- **person-profile-ms**: Microservicio que recupera información detallada de los perfiles.
- **MongoDB**: Base de datos NoSQL utilizada para el almacenamiento de perfiles.

Cada microservicio está protegido mediante autenticación JWT y se comunica con MongoDB de manera segura.

## 🚀 Tecnologías Utilizadas
- **Spring Boot** (para la creación de los microservicios)
- **MongoDB** (base de datos NoSQL)
- **Docker** (para la contenedorización)
- **Kubernetes** (orquestación y despliegue)
- **JWT (JSON Web Token)** (mecanismo de autenticación y seguridad)

## ⚙️ Configuración y Despliegue

### 1️⃣ Clonar el Repositorio
```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```

### 2️⃣ Construir Imágenes Docker
```bash
# Para person-crud-ms
cd person-crud-ms
mvn clean package
docker build -t person-crud-ms:1.0 .
cd ..

# Para person-profile-ms
cd person-profile-ms
mvn clean package
docker build -t person-profile-ms:1.0 .
cd ..
```

### 3️⃣ Verificar Imágenes Docker
```bash
docker images | grep "person-"
```

### 4️⃣ Configurar Kubernetes
```bash
kubectl apply -f kubernetes/mongodb-deployment.yaml
kubectl apply -f kubernetes/person-crud-ms-deployment.yaml
kubectl apply -f kubernetes/person-profile-ms-deployment.yaml
```

### 5️⃣ Verificar el Estado de los Pods y Servicios
```bash
kubectl get pods -w
kubectl get services
```

### 6️⃣ Acceder a los Servicios
```bash
kubectl port-forward svc/person-crud-ms 8080:8080
kubectl port-forward svc/person-profile-ms 7070:7070
```

## 🖥️ Script Automático para Despliegue

### 💻 Windows (`deploy-all.bat`)
```batch
@echo off
echo Construyendo imágenes Docker...
cd person-crud-ms
mvn clean package
docker build -t person-crud-ms:1.0 .
cd ..

cd person-profile-ms
mvn clean package
docker build -t person-profile-ms:1.0 .
cd ..

kubectl apply -f kubernetes/
start cmd /k kubectl port-forward svc/person-crud-ms 8080:8080
start cmd /k kubectl port-forward svc/person-profile-ms 7070:7070
```

### 💻 Linux & Mac (`deploy-all.sh`)
```bash
#!/bin/bash
echo "Construyendo imágenes Docker..."
cd person-crud-ms
mvn clean package
docker build -t person-crud-ms:1.0 .
cd ..

cd person-profile-ms
mvn clean package
docker build -t person-profile-ms:1.0 .
cd ..

echo "Desplegando en Kubernetes..."
kubectl apply -f kubernetes/

echo "Iniciando port-forward..."
kubectl port-forward svc/person-crud-ms 8080:8080 &
kubectl port-forward svc/person-profile-ms 7070:7070 &
```

## 🔄 Comandos Útiles para Mantenimiento

### 📜 Ver logs de un pod
bash
kubectl logs -f <pod-name>


### 🗑️ Eliminar todas las implementaciones y servicios
```bash
kubectl delete deployment,svc mongodb person-crud-ms person-profile-ms
```

### 🔄 Reiniciar un deployment
```bash
kubectl rollout restart deployment/person-crud-ms
```

📌 **Notas finales:** 
Este proyecto está diseñado para facilitar la implementación de microservicios con autenticación segura, almacenamiento en base de datos NoSQL y despliegue en contenedores. La modularidad del sistema permite escalarlo y adaptarlo a diferentes necesidades empresariales. 

