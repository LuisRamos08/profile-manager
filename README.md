# Proyecto de Microservicios con Spring Boot y Kubernetes

Este proyecto implementa un sistema de gestión de perfiles utilizando microservicios con Spring Boot, MongoDB, Docker y Kubernetes.

## Arquitectura
El sistema consta de los siguientes microservicios:
- **person-crud-ms**: Proporciona operaciones CRUD sobre perfiles de personas.
- **person-profile-ms**: Recupera información detallada de los perfiles.
- **MongoDB**: Base de datos NoSQL para almacenamiento de perfiles.

Cada microservicio está protegido mediante autenticación JWT y se comunica con MongoDB de manera segura.

## Tecnologías Utilizadas
- **Spring Boot**
- **MongoDB**
- **Docker**
- **Kubernetes**
- **JWT (JSON Web Token) para autenticación**

## Configuración y Despliegue

### 1. Clonar el Repositorio
```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```

### 2. Configurar Kubernetes
#### Desplegar MongoDB
```bash
kubectl apply -f kubernetes/mongodb-deployment.yaml
```
#### Desplegar los Microservicios
```bash
kubectl apply -f kubernetes/person-crud-ms-deployment.yaml
kubectl apply -f kubernetes/person-profile-ms-deployment.yaml
```

### 3. Verificar los Pods y Servicios
```bash
kubectl get pods
kubectl get services
```
## 4. Probar los Microservicios

Los microservicios estarán disponibles en los siguientes endpoints:

### Crear Perfiles
**Endpoint:** `POST http://localhost:8080/profile/create-profile`

**Body (JSON):**
```json
{
    "name": "Test",
    "lastName": "Test2",
    "cellphone": "8888888888",
    "email": "test@hotmail.com",
    "address": "Calle Cuarta 123"
}
```

**Descripción:** Al crear un perfil, se genera un token que puede ser utilizado en el endpoint de eliminación.

---

### Eliminar Perfiles
**Endpoint:** `DELETE http://localhost:8080/profile/delete-profile/{id}`

**Encabezado requerido:**
```
Authorization: Bearer <token>
```

**Descripción:** Se debe reemplazar `{id}` con el identificador del perfil a eliminar y enviar el token generado en la creación.

---

### Obtener Perfil de Personas
**Endpoint:** `GET http://localhost:7070/profile/get-profile`

**Descripción:** Permite recuperar la información de un perfil almacenado en el sistema.



