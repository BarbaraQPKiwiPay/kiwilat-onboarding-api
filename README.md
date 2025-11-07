## 1. Requisitos previos

- **Java 17** o superior (JDK)
- **Maven 3.6+**
- **PostgreSQL 12+** 
- **Git**
- (Opcional) **Docker** y **Docker Compose**

---

## 2. Clonar el repositorio

```bash
git clone https://github.com/BarbaraQPKiwiPay/kiwilat-onboarding-api.git
cd kiwilat-onboarding-api/kiwipay-onboarding-api
```

---

## 3. Configurar la base de datos

1. **Crea una base de datos PostgreSQL** (local o en Render, ElephantSQL, etc.)
2. **Crea las tablas y pobla los catálogos** (ubigeo, clínicas, etc.) usando los scripts SQL proporcionados (por ejemplo, `data_ubigeo.sql`).
3. **Guarda las credenciales de conexión** (host, puerto, usuario, contraseña, nombre de la base de datos).

---

## 4. Configurar variables de entorno

Copia el archivo de ejemplo:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edita `application.properties` y coloca los datos de tu base de datos:

```
spring.datasource.url=jdbc:postgresql://<host>:<puerto>/<nombre_db>
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
```

Ajusta otros parámetros según tu entorno (puerto, perfil, etc.).

---

## 5. Compilar y levantar el backend

### Opción A: Usando Maven

```bash
mvn clean package
java -jar target/kiwipay-onboarding-api-*.jar
```

### Opción B: Usando Spring Boot (desarrollo)

```bash
mvn spring-boot:run
```

### Opción C: Usando Docker (si tienes Dockerfile)

```bash
docker build -t kiwipay-onboarding-api .
docker run -p 8080:8080 --env-file .env kiwipay-onboarding-api
```

---

## 6. Probar la API

- Accede a la documentación Swagger en: `http://localhost:8080/swagger-ui.html` o `/swagger-ui/index.html`
- Prueba los endpoints con Postman, Insomnia o curl.

---

## 7. Consideraciones adicionales

- Si usas Render u otro PaaS, configura las variables de entorno en el panel de la plataforma.
- Si cambias el modelo de datos, ejecuta los scripts de migración SQL correspondientes.
- Para poblar catálogos, ejecuta los scripts SQL antes de levantar el backend.
- El backend usa Clean Architecture y CQRS, revisa la estructura de carpetas para entender la separación de capas.

---

## 8. Comandos útiles

- **Compilar:** `mvn clean package`
- **Levantar en desarrollo:** `mvn spring-boot:run`
- **Levantar con Docker:** `docker build ...` y `docker run ...`
- **Ejecutar tests:** `mvn test`

---

## 9. Soporte

Para dudas o problemas, abre un issue en el repositorio o contacta al equipo de desarrollo.

---

## 10. Buenas prácticas de trabajo en equipo y control de versiones

### Ramas recomendadas (Git Flow simplificado)

- **main**: Solo código listo para producción (lo que está desplegado en Render).
- **develop**: Integración de nuevas features y pruebas internas (desarrollo).
- **feature/nombre**: Para cada nueva funcionalidad o cambio grande.
- **bugfix/nombre**: Para corrección de bugs.

### Flujo de trabajo sugerido

1. Crea una rama desde `develop` para tu feature o bugfix:
   ```bash
   git checkout develop
   git pull
   git checkout -b feature/nombre-de-tu-feature
   ```
2. Haz tus cambios, commits y push:
   ```bash
   git add .
   git commit -m "Descripción clara del cambio"
   git push origin feature/nombre-de-tu-feature
   ```
3. Abre un Pull Request (PR) a `develop`.
4. Revisa y prueba en `develop`.
5. Cuando esté listo, mergea a `main` para producción.
6. Despliega solo desde `main` a Render.

### Consejos para evitar conflictos

- Cada quien trabaja en su propia rama.
- Hacer PRs y revisiones de código.
- Actualizar frecuentemente la rama local con los últimos cambios de `develop`:
  ```bash
  git checkout develop
  git pull
  git checkout feature/nombre-de-tu-feature
  git merge develop
  ```
- Resolver conflictos antes de hacer merge.
- Usar mensajes de commit claros y descriptivos.

### Configuración de entornos (dev, qa, prod)

- Usa archivos de propiedades separados:
  - `application-dev.properties`
  - `application-qa.properties`
  - `application-prod.properties`
- Configura la variable de entorno `SPRING_PROFILES_ACTIVE` según el entorno:
  - Local: `dev`
  - QA: `qa`
  - Producción (Render): `prod`
- Ejemplo para correr en local:
  ```bash
  mvn spring-boot:run -Dspring-boot.run.profiles=dev
  ```
