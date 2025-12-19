# Sistema de Autenticación y Autorización JWT

## Resumen

Sistema completo de autenticación y autorización implementado con:
- **JWT (JSON Web Tokens)** para autenticación
- **Roles y permisos** granulares
- **Spring Security** para protección de endpoints
- **Clean Architecture + DDD** siguiendo las mejores prácticas

## Roles Disponibles

- `SUPERADMIN`: Acceso completo al sistema, puede gestionar usuarios
- `COMERCIAL`: Acceso a funciones comerciales
- `ADV`: Acceso a reportes y funciones ADV
- `RIESGOS`: Acceso a análisis de riesgos

## Endpoints Principales

### Autenticación Pública

```
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "superadmin",
  "password": "admin123"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "username": "superadmin",
  "firstName": "Super",
  "lastName": "Administrator",
  "email": "admin@kiwipay.com",
  "roles": ["SUPERADMIN"]
}
```

### Gestión de Usuarios (Solo SuperAdmin)

```
# Registrar nuevo usuario
POST /api/v1/admin/users/register
Authorization: Bearer <token>
Content-Type: application/json

{
  "username": "comercial01",
  "password": "password123",
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@kiwipay.com",
  "roles": ["COMERCIAL"]
}

# Listar usuarios
GET /api/v1/admin/users
Authorization: Bearer <token>

# Obtener usuario por ID
GET /api/v1/admin/users/1
Authorization: Bearer <token>

# Eliminar usuario
DELETE /api/v1/admin/users/1
Authorization: Bearer <token>
```

### Endpoints Protegidos por Rol

```
# Dashboard Comercial (COMERCIAL o SUPERADMIN)
GET /api/v1/examples/comercial/dashboard
Authorization: Bearer <token>

# Reportes ADV (ADV o SUPERADMIN)
GET /api/v1/examples/adv/reports
Authorization: Bearer <token>

# Análisis de Riesgos (RIESGOS o SUPERADMIN)
GET /api/v1/examples/riesgos/analysis
Authorization: Bearer <token>

# Información del usuario actual
GET /api/v1/examples/user-info
Authorization: Bearer <token>
```

## Configuración de Seguridad

La clase `SecurityConfig.java` define las reglas de acceso:

```java
// Endpoints públicos
.requestMatchers("/api/v1/auth/login").permitAll()

// Solo SuperAdmin
.requestMatchers("/api/v1/admin/**").hasRole("SUPERADMIN")

// Por roles específicos
.requestMatchers("/api/v1/comercial/**").hasRole("COMERCIAL")
.requestMatchers("/api/v1/adv/**").hasRole("ADV")
.requestMatchers("/api/v1/riesgos/**").hasRole("RIESGOS")

// Resto requiere autenticación
.anyRequest().authenticated()
```

## Usuario por Defecto

Al iniciar la aplicación se crea automáticamente:
- **Username:** `superadmin`
- **Password:** `admin123`
- **Roles:** `SUPERADMIN`

## Uso en Frontend

### 1. Login
```javascript
const response = await fetch('/api/v1/auth/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ username, password })
});

const data = await response.json();
// Guardar token y roles
localStorage.setItem('token', data.token);
localStorage.setItem('roles', JSON.stringify(data.roles));
```

### 2. Proteger Rutas Frontend
```javascript
const userRoles = JSON.parse(localStorage.getItem('roles') || '[]');

// Mostrar sección comercial solo si tiene rol COMERCIAL o SUPERADMIN
const showComercialSection = userRoles.includes('COMERCIAL') || userRoles.includes('SUPERADMIN');

// Mostrar sección ADV solo si tiene rol ADV o SUPERADMIN
const showAdvSection = userRoles.includes('ADV') || userRoles.includes('SUPERADMIN');

// Mostrar sección de riesgos solo si tiene rol RIESGOS o SUPERADMIN
const showRiesgosSection = userRoles.includes('RIESGOS') || userRoles.includes('SUPERADMIN');

// Mostrar administración solo si es SUPERADMIN
const showAdminSection = userRoles.includes('SUPERADMIN');
```

### 3. Hacer Peticiones Autenticadas
```javascript
const token = localStorage.getItem('token');

const response = await fetch('/api/v1/examples/comercial/dashboard', {
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
});
```

## Swagger UI

Accede a la documentación interactiva en: `http://localhost:8081/swagger-ui.html`

1. Haz login en `/api/v1/auth/login` para obtener el token
2. Copia el token (sin "Bearer")
3. Clic en "Authorize" en Swagger UI
4. Pega el token y presiona "Authorize"
5. Ahora puedes probar todos los endpoints protegidos

## Estructura del Proyecto

```
authentication/
├── domain/
│   ├── model/
│   │   ├── aggregates/User.java
│   │   └── entities/Role.java
│   └── services/
├── application/
│   └── internal/
│       ├── dto/
│       └── commandservices/
├── infrastructure/
│   └── persistence/jpa/repositories/
└── interfaces/rest/
```

## Extensión del Sistema

Para agregar nuevos roles:

1. **Crear el rol en DataInitializer.java:**
```java
createRoleIfNotExists("NUEVO_ROL", "Descripción del rol");
```

2. **Actualizar SecurityConfig.java:**
```java
.requestMatchers("/api/v1/nuevo-modulo/**").hasRole("NUEVO_ROL")
```

3. **Crear endpoints protegidos:**
```java
@PreAuthorize("hasRole('NUEVO_ROL') or hasRole('SUPERADMIN')")
```

4. **Actualizar frontend** para mostrar/ocultar secciones según el nuevo rol.