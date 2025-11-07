# Kiwipay Onboarding API

Backend para el proceso de onboarding de Kiwipay. Proyecto Java 17+ con Spring Boot, PostgreSQL y Maven.

## 1. Requisitos

- Java 17 o superior
- Maven 3.6+
- PostgreSQL 12+
- Git

## 2. Instalación y configuración

### a) Clonar el repositorio

```bash
git clone https://github.com/BarbaraQPKiwiPay/kiwilat-onboarding-api.git
cd kiwilat-onboarding-api/kiwipay-onboarding-api
```

### b) Configurar la base de datos

1. Crea una base de datos PostgreSQL local (ejemplo: `kiwi_dev`).
2. Ejecuta los scripts SQL de catálogos y tablas (ejemplo: `data_ubigeo.sql`).

### c) Configurar el entorno

- Copia y edita el archivo de configuración para desarrollo:

```bash
cp src/main/resources/application-dev.properties
```

- Modifica los datos de conexión a tu base local en `application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/kiwi_dev
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```


## 3. Levantar el backend

### En desarrollo (base local):

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### En producción (Render):

- Render usará automáticamente el perfil `prod`.

## 4. Probar la API

- Accede a la documentación Swagger en:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Prueba los endpoints con Postman, Insomnia o curl.


## 5. Soporte

Para dudas o problemas, abre un issue en el repositorio o contacta al equipo de desarrollo.

## 6. Reglas y buenas prácticas de trabajo en equipo

1. Crea una rama desde `develop` con el prefijo adecuado (`feature/`, `bugfix/`, etc.).
2. Realiza tus cambios y haz commits siguiendo la convención (ver abajo).
3. Haz push de tu rama al repositorio remoto.
4. Abre un Pull Request hacia `develop`.
5. Espera la revisión y aprobación de al menos un miembro del equipo.
6. Realiza el merge solo después de la aprobación.
7. Si es necesario, actualiza tu rama con los últimos cambios de `develop` antes de hacer merge.

#### Convención de commits

- `feat: descripción` para nuevas funcionalidades.
- `fix: descripción` para corrección de errores.
- `docs: descripción` para cambios en la documentación.
- `refactor: descripción` para mejoras internas del código.
- `test: descripción` para agregar o mejorar tests.
- `chore: descripción` para tareas de mantenimiento.