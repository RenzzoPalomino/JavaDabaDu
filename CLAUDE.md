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
│   │   │   └── Usuario.java               (Entidad JPA)
│   │   ├── controller/                    (⬜ Por implementar)
│   │   ├── service/ no te                      (⬜ Por implementar)
│   │   ├── repository/                    (⬜ Por implementar)
│   │   └── config/                        (⬜ Por implementar)
│   └── test/
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

### 🔄 Fase 2: CRUD & Repositorio (SIGUIENTE)
**Objetivo**: Implementar operaciones básicas de base de datos

- [ ] **Repository Pattern**
  - [ ] Crear `UsuarioRepository` (extends JpaRepository)
  - [ ] Métodos custom queries (@Query)
  - [ ] Métodos finder (findByEmail, findById)

- [ ] **Service Layer**
  - [ ] Crear `UsuarioService` con lógica de negocio
  - [ ] Manejar excepciones (UsuarioNotFoundException, DuplicateEmailException)
  - [ ] Transacciones (@Transactional)

**Conceptos**: Repository, JpaRepository, Query derivation, Service pattern, @Transactional

---

### 🌐 Fase 3: API REST Controllers
**Objetivo**: Exponer operaciones mediante HTTP

- [ ] **REST Controller**
  - [ ] Crear `UsuarioController` con @RestController
  - [ ] GET /api/usuarios (listar todos)
  - [ ] GET /api/usuarios/{id} (obtener por ID)
  - [ ] POST /api/usuarios (crear nuevo)
  - [ ] PUT /api/usuarios/{id} (actualizar)
  - [ ] DELETE /api/usuarios/{id} (eliminar)

- [ ] **Request/Response DTOs**
  - [ ] CreateUsuarioRequest
  - [ ] UpdateUsuarioRequest
  - [ ] UsuarioResponse
  - [ ] ErrorResponse

- [ ] **Validación**
  - [ ] @Valid en parámetros de controlador
  - [ ] Bean Validation (@NotNull, @Email, @NotBlank)
  - [ ] Custom validators

**Conceptos**: @RestController, @RequestMapping, HTTP methods, DTOs, request validation, exception handling

---

### 🔐 Fase 4: Seguridad & Autenticación
**Objetivo**: Proteger endpoints

- [ ] Spring Security basic setup
- [ ] JWT tokens
- [ ] Roles y permisos (RBAC)
- [ ] Password encoding (BCrypt)

**Conceptos**: Security, Authentication, Authorization, JWT, Spring Security

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

**Última actualización**: 2026-08-12
**Estado**: En progreso (Fase 1 completada, inicia Fase 2)