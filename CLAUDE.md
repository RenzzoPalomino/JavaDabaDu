# 📚 Mi API - Roadmap de Aprendizaje Java

## 📋 Descripción General

**Proyecto**: `mi-api` - API REST con Spring Boot
**Propósito**: Aplicación de aprendizaje progresivo de Java y arquitectura backend
**Stack**: Java 17 | Spring Boot 4.1.0 | PostgreSQL | Gradle

---

## 🏗️ Estructura Actual del Proyecto

```
mi-api/
├── src/
│   ├── main/java/com/company/mi_api/
│   │   ├── MiApiApplication.java          (Punto de entrada)
│   │   ├── model/
│   │   │   └── Usuario.java               (Entidad JPA completa)
│   │   ├── controller/
│   │   │   ├── UsuarioController.java     (REST endpoints CRUD)
│   │   │   └── AuthController.java        (register / login)
│   │   ├── service/
│   │   │   ├── UsuarioService.java        (Lógica de negocio)
│   │   │   └── AuthService.java           (Registro y login con JWT)
│   │   ├── repository/
│   │   │   └── UsuarioRepository.java     (JpaRepository + custom queries)
│   │   ├── dto/
│   │   │   ├── CrearUsuarioRequest.java   (Request validation)
│   │   │   ├── ActualizarUsuarioRequest.java
│   │   │   ├── UsuarioResponse.java       (Respuesta sin exponer la entidad)
│   │   │   ├── RegistroRequest.java / LoginRequest.java / AuthResponse.java
│   │   │   └── ErrorResponse.java         (Respuesta de error global)
│   │   ├── exception/
│   │   │   └── UsuarioNoEncontradoException.java
│   │   ├── security/
│   │   │   ├── SecurityConfig.java        (Filter chain, BCrypt, roles)
│   │   │   ├── JwtUtil.java               (Generar/validar tokens)
│   │   │   ├── JwtAuthFilter.java         (Filtro por request)
│   │   │   └── CustomUserDetailsService.java
│   │   └── config/
│   │       └── GlobalExceptionHandler.java (Manejo centralizado de excepciones)
│   └── test/
│       └── MiApiApplicationTests.java
├── build.gradle                           (Dependencias Gradle)
├── settings.gradle
└── gradle/
```

---

## 📦 Stack & Dependencias

### Versiones Clave
- **Java**: 17
- **Spring Boot**: 4.1.0
- **Gradle**: 8.x (wrapper)
- **PostgreSQL**: Latest (runtime)

### Dependencias Principales
```gradle
// Data & ORM
spring-boot-starter-data-jpa
org.postgresql:postgresql

// Web
spring-boot-starter-webmvc

// Validación
spring-boot-starter-validation

// Desarrollo
spring-boot-devtools
org.projectlombok:lombok

// Testing
JUnit 5 (via platform launcher)
```

---

## 🎯 Roadmap de Aprendizaje por Fases

### ✅ Fase 1: Fundamentos (COMPLETADA)
- [x] Configuración inicial Spring Boot
- [x] Modelo de datos (Usuario.java con JPA)
- [x] Anotaciones Lombok (@Data, @AllArgsConstructor, @NoArgsConstructor)
- [x] Entity mapping (Jakarta Persistence)

**Conceptos cubiertos**: Entity, @Table, @Column, @GeneratedValue, @Id, Lombok reducción de boilerplate

---

### ✅ Fase 2: CRUD & Repositorio (COMPLETADA)
**Objetivo**: Implementar operaciones básicas de base de datos

- [x] **Repository Pattern**
  - [x] Crear `UsuarioRepository` (extends JpaRepository)
  - [x] Métodos custom queries (@Query)
  - [x] Métodos finder (findByEmail, findById)

- [x] **Service Layer**
  - [x] Crear `UsuarioService` con lógica de negocio
  - [x] Manejar excepciones (UsuarioNoEncontradoException)
  - [x] Transacciones (@Transactional)

- [x] **Global Exception Handler**
  - [x] Crear `GlobalExceptionHandler` con @ControllerAdvice
  - [x] Mapeo de UsuarioNoEncontradoException → 404
  - [x] Mapeo de IllegalArgumentException → 400
  - [x] Mapeo de MethodArgumentNotValidException → 400
  - [x] Crear `ErrorResponse` DTO para respuestas consistentes

**Conceptos**: Repository, JpaRepository, Query derivation, Service pattern, @Transactional, @ControllerAdvice, @ExceptionHandler

---

### ✅ Fase 3: API REST Controllers (COMPLETADA)
**Objetivo**: Exponer operaciones mediante HTTP

- [x] **REST Controller**
  - [x] Crear `UsuarioController` con @RestController
  - [x] GET /api/usuarios (listar todos)
  - [x] GET /api/usuarios/{id} (obtener por ID)
  - [x] POST /api/usuarios (crear nuevo)
  - [x] PUT /api/usuarios/{id} (actualizar)
  - [x] DELETE /api/usuarios/{id} (eliminar)
  - [x] GET /api/usuarios/buscar (búsqueda por nombre)

- [x] **Request/Response DTOs**
  - [x] CrearUsuarioRequest
  - [x] ErrorResponse
  - [x] ActualizarUsuarioRequest
  - [x] UsuarioResponse

- [x] **Validación**
  - [x] @Valid en parámetros de controlador
  - [x] Bean Validation (@NotNull, @Email, @NotBlank)
  - [x] Exception handling centralizado

**Conceptos**: @RestController, @RequestMapping, HTTP methods, DTOs, request validation, exception handling, @ControllerAdvice

---

### ✅ Fase 4: Seguridad & Autenticación (COMPLETADA)
**Objetivo**: Proteger endpoints

- [x] Spring Security basic setup (`SecurityConfig`, filter chain stateless)
- [x] JWT tokens (`JwtUtil` genera/valida, `JwtAuthFilter` los procesa por request)
- [x] Roles y permisos (RBAC) — enum `Rol` (USER/ADMIN); DELETE de usuarios requiere ADMIN
- [x] Password encoding (BCrypt vía `PasswordEncoder` bean)
- [x] `POST /api/auth/register` y `POST /api/auth/login`
- [x] 401 para credenciales inválidas (`AuthenticationException` en GlobalExceptionHandler)

**Conceptos**: Security, Authentication, Authorization, JWT, Spring Security, BCrypt, RBAC, filtros servlet, SecurityContext

**Pendiente para después**: refresh tokens, logout/blacklist de tokens, endpoint para que un ADMIN cambie el rol de otro usuario.

---

### 🧪 Fase 5: Testing
**Objetivo**: Asegurar calidad del código

- [ ] Unit tests (Service layer)
  - [ ] JUnit 5
  - [ ] Mockito
  - [ ] AssertJ

- [ ] Integration tests
  - [ ] @SpringBootTest
  - [ ] TestContainers (PostgreSQL)
  - [ ] MockMvc

- [ ] Controller tests

**Conceptos**: Testing pyramid, Mocks, Assertions, Test fixtures

---

### 📊 Fase 6: Logging & Observabilidad
**Objetivo**: Monitorear y debuggear la app

- [ ] SLF4J + Logback setup
- [ ] Log levels (DEBUG, INFO, WARN, ERROR)
- [ ] Structured logging
- [ ] Actuator endpoints

**Conceptos**: Logging, Observability, Health checks

---

### 🚀 Fase 7: Deployment & DevOps
**Objetivo**: Llevar la app a producción

- [ ] Docker setup
- [ ] Docker Compose (PostgreSQL)
- [ ] CI/CD pipeline (GitHub Actions)
- [ ] Environment configuration

**Conceptos**: Containerization, CI/CD, Configuration management

---

## 📝 Convenciones de Código

### Estructura de Paquetes
```
com.company.mi_api
├── controller/      # REST endpoints
├── service/         # Lógica de negocio
├── repository/      # Data access
├── model/           # Entidades JPA
├── dto/             # Data Transfer Objects
├── exception/       # Custom exceptions
├── config/          # Configuración beans
└── util/            # Utilidades
```

### Nomenclatura
- **Entities**: `Usuario`, `Producto` (singular, PascalCase)
- **Repositories**: `UsuarioRepository` (Entity + Repository)
- **Services**: `UsuarioService` (Entity + Service)
- **Controllers**: `UsuarioController` (Entity + Controller)
- **DTOs**: `CreateUsuarioRequest`, `UsuarioResponse`
- **Exceptions**: `UsuarioNotFoundException`, `DuplicateEmailException`

### Patrones
- **DTO Pattern**: Separar representación HTTP de entidades JPA
- **Service Layer**: Toda lógica de negocio va aquí, nunca en controller
- **Repository Pattern**: Abstracción de acceso a datos
- **Exception Handling**: Custom exceptions + Global @ExceptionHandler

---

## 🔧 Configuración del Entorno

### application.properties (desarrollo)
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/mi_api_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server
server.port=8080

# Lombok
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
```

### Comandos Gradle Útiles
```bash
# Compilar
./gradlew build

# Ejecutar tests
./gradlew test

# Ejecutar la app
./gradlew bootRun

# Limpiar
./gradlew clean
```

---

## 🎓 Conceptos Java por Aprender

### OOP Fundamentals
- [ ] Clases y objetos
- [ ] Herencia
- [ ] Polimorfismo
- [ ] Encapsulación

### Collections & Streams
- [ ] List, Set, Map
- [ ] Stream API
- [ ] Lambda expressions
- [ ] Functional interfaces

### Excepciones
- [ ] try-catch-finally
- [ ] Custom exceptions
- [ ] Exception hierarchy
- [ ] Checked vs Unchecked

### Programación Funcional
- [ ] Lambdas
- [ ] Method references
- [ ] Streams
- [ ] Optional

---

## 🤝 Colaboración Claude Code

### Cómo usaré Claude en este proyecto

1. **Análisis de código**: Revisar estructuras y patrones
2. **Implementación de features**: Crearemos controladores, servicios, etc.
3. **Debugging**: Resolver errores
4. **Refactoring**: Mejorar código existente
5. **Testing**: Escribir y ejecutar tests
6. **Documentación**: Mantener esta guía actualizada

### Tracking de cambios
Cada commit/cambio será marcado aquí con la fase correspondiente y breve descripción.

---

## 📋 Changelog de Cambios

### [2026-08-12] Inicialización del proyecto
- ✅ Configuración inicial Spring Boot 4.1.0
- ✅ Modelo Usuario creado con JPA
- ✅ Gradle configurado con dependencias
- ✅ CLAUDE.md creado como roadmap

### [2026-08-14] Fase 2 & 3: CRUD, Controllers y Exception Handling
- ✅ UsuarioRepository completado con custom queries (@Query, findByEmail, etc.)
- ✅ UsuarioService implementado con CRUD completo (@Transactional)
- ✅ UsuarioController con todos los endpoints REST
- ✅ CrearUsuarioRequest DTO con validación Bean Validation
- ✅ GlobalExceptionHandler (@ControllerAdvice) implementado
  - Mapeo de UsuarioNoEncontradoException → 404
  - Mapeo de IllegalArgumentException → 400 (emails duplicados)
  - Mapeo de MethodArgumentNotValidException → 400 (validación)
  - ErrorResponse DTO con formato consistente
- ✅ Swagger/OpenAPI configurado y funcional
- ✅ Logging con SLF4J en Service y Controller

**Próximos pasos**: DTOs de respuesta (ActualizarUsuarioRequest, UsuarioResponse) y Testing

### [2026-10-23] Fase 3 completada: DTOs de salida y fix de Lombok
- ✅ Corregido `@BuilderCer` → `@Builder` en `ErrorResponse` (typo que impedía compilar, usado por `GlobalExceptionHandler`)
- ✅ `ActualizarUsuarioRequest` DTO creado (mismo patrón que `CrearUsuarioRequest`, con validación)
- ✅ `UsuarioResponse` DTO creado con `fromEntity(Usuario)` para no exponer la entidad JPA en las respuestas HTTP
- ✅ `UsuarioController` actualizado: todos los endpoints devuelven `UsuarioResponse`; PUT ahora valida con `@Valid ActualizarUsuarioRequest` en vez de recibir `Usuario` crudo

**Próximos pasos**: Fase 5 - Testing (JUnit 5, Mockito, MockMvc)

### [2026-10-23] Fase 4 completada: Spring Security + JWT + RBAC
- ✅ Dependencias: `spring-boot-starter-security`, `jjwt` 0.12.6
- ✅ `Usuario` ahora tiene `password` (hash BCrypt) y `rol` (enum `Rol`: USER/ADMIN)
- ✅ `SecurityConfig`: filter chain stateless, CSRF deshabilitado, reglas de autorización
  (`/api/auth/**` y Swagger públicos; DELETE de usuarios solo ADMIN; el resto autenticado)
- ✅ `JwtUtil` (generar/validar tokens), `JwtAuthFilter` (autentica cada request),
  `CustomUserDetailsService` (puente Usuario ↔ Spring Security)
- ✅ `AuthService` + `AuthController`: `POST /api/auth/register` y `POST /api/auth/login`,
  ambos devuelven un JWT
- ✅ `GlobalExceptionHandler`: `AuthenticationException` → 401
- ⚠️ Nota: si había usuarios previos en la BD de desarrollo, se recrearon (password/rol
  son NOT NULL y `ddl-auto=update` no hace backfill)

**Próximos pasos**: Fase 5 - Testing (JUnit 5, Mockito, MockMvc)

---

## 🔗 Recursos Útiles

### Documentación
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Jakarta Persistence](https://jakarta.ee/specifications/persistence/)
- [Lombok Project](https://projectlombok.org/)

### Videos/Tutoriales
- Spring Boot fundamentals
- REST API design best practices
- Testing en Java/Spring

---

## ❓ FAQ / Notas

**P: ¿Por qué separar DTOs de Entities?**
R: Los DTOs protegen tu modelo interno de cambios de API y permiten validación en la capa HTTP sin afectar la BD.

**P: ¿Por qué service layer?**
R: Centraliza la lógica de negocio, facilita testing y reutilización.

**P: ¿Spring Boot 4.1.0?**
R: Versión reciente, requiere Java 17+, includes Virtual Threads support.

---

**Última actualización**: 2026-08-14
**Estado**: En progreso (Fase 1-2 completadas, Fase 3 casi completada, próximo: Testing)