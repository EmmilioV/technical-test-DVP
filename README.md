# CRUD Básico para Personas/Usuarios y Tickets

Este proyecto fue desarrollado como parte de una prueba técnica. Implementa un CRUD para la gestión de usuarios y tickets, desarrollado en **Java 17** con **Spring Boot 3.4.3**. Además, incluye autenticación con **JWT**, almacenamiento en caché con **Caffeine**, y documentación mediante **Swagger**.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Security con JWT (0.12.3)**
- **Spring Data JPA**
- **H2 Database (Base de datos en memoria)**
- **Caffeine Cache (3.0.4)**
- **Swagger para documentación (2.7.0)**
- **Maven**

## ⚠️ Precaución

El archivo `application.properties` contiene el **secret key**, **username** y **password** necesarios para realizar pruebas en local. **Esto no debe utilizarse en entornos de producción**.

📌 **Nota:** `{noop}` no forma parte de la contraseña, solo indica que no está encriptada.

## 📌 Funcionalidades

1. **Gestión de Personas**
   - Crear y actualizar una persona.
   - Consultar todas las personas o buscar una por su ID.

2. **Gestión de Tickets**
   - Crear, actualizar y eliminar un ticket.
   - Consultar tickets por ID o paginados.
   - Filtrar tickets por estado (**ABIERTO**, **CERRADO**) y por ID de persona.

## 🛠️ Instalación y Ejecución

### 1️⃣ Clonar el Repositorio
```sh
 git clone <URL_DEL_REPOSITORIO>
 cd <NOMBRE_DEL_PROYECTO>
```

### 2️⃣ Ejecutar el Proyecto con Maven
```sh
 ./mvnw spring-boot:run
```

### 3️⃣ Acceder a la Documentación de la API
La documentación de los endpoints está disponible en Swagger:
```sh
 https://localhost:8082/swagger-ui/index.html
```

### 4️⃣ Autenticarse y Autorizar en Swagger
1. Ejecuta el endpoint de autenticación con el `username` y `password` definidos en `application.properties`.
2. Copia el token generado.
3. En la interfaz de Swagger, haz clic en el botón **"Authorize"** (esquina superior derecha) e ingresa el token.
4. Ahora puedes probar los endpoints protegidos. 🚀

---

## 👨‍💻 Autor
Hecho con ❤️ por **Pablo Emilio Villa**.


