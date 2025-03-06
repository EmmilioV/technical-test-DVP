# **CRUD de Personas y Tickets con Spring Boot**

Este proyecto fue desarrollado como parte de una prueba técnica. Implementa un CRUD para gestionar usuarios y tickets, utilizando Java con Spring Boot. Además, cuenta con autenticación mediante JWT, almacenamiento en caché con Caffeine y documentación con Swagger.

## 🚀 **Tecnologías utilizadas**
- **Java 17**
- **Spring Boot 3.4.3**
- **JWT 0.12.3** (Autenticación y autorización)
- **Spring Data JPA** (Persistencia de datos)
- **Caffeine 3.0.4** (Caché)
- **Swagger 2.7.0** (Documentación de API)

---

## ⚠ **Consideraciones**
- El archivo `application.properties` contiene credenciales predeterminadas **(solo para pruebas locales)**.  
- `{noop}` en la contraseña indica que **no está encriptada**. **¡No utilizar en producción!**

---

## 🔧 **Instalación y ejecución**
1. Clona el repositorio:
   ```sh
   git clone https://github.com/EmmilioV/technical-test-DVP.git
   cd technical-test-DVP
   ```
2. Ejecuta el proyecto con Maven:
   ```sh
   ./mvnw spring-boot:run
   ```
3. Accede a Swagger para revisar la documentación de los endpoints:
   - [Swagger UI](http://localhost:8082/swagger-ui/index.html)

---

## 🛠 **Autenticación y uso de la API**
Antes de realizar peticiones, **obtén un token JWT**.

### **1️⃣ Obtener token de autenticación**
```sh
curl --location --request POST 'http://localhost:8082/auth/login?username=admin&password=admin'
```
📌 **Respuesta esperada:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

---

### **2️⃣ Crear una persona**
```sh
curl --location --request POST 'http://localhost:8082/persons/create-one' \
--header 'Authorization: Bearer <TOKEN>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Pablo",
    "lastName": "Villa"
}'
```

---

### **3️⃣ Crear un ticket**
```sh
curl --location --request POST 'http://localhost:8082/tickets/create-one' \
--header 'Authorization: Bearer <TOKEN>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "personId": "<ID DE LA PERSONA CREADA>",
    "description": "ticket de prueba 1",
    "status": "ABIERTO"
}'
```

## ❤️ **Autor**
Hecho con ❤️ por **Pablo Emilio Villa**.
