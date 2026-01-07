# CATALOG

## 1. Propósito del Bounded Context

### ¿Qué problema de negocio resuelve?
El bounded context de **Catalog** gestiona todos los datos maestros de referencia que son utilizados por otros bounded contexts del sistema. Proporciona información geográfica de Perú (ubigeo) y catálogos médicos para las operaciones de onboarding.

### ¿Qué procesos cubre?
- **Catálogo geográfico**: Gestión de departamentos, provincias y distritos de Perú (ubigeo)
- **Catálogo médico**: Gestión de categorías médicas, clínicas y sucursales clínicas
- **Consultas de catálogo**: Endpoints de solo lectura para obtener datos de referencia
- **Filtrado jerárquico**: Consulta de provincias por departamento, distritos por provincia, etc.

### ¿Qué NO cubre?
- **NO gestiona creación/eliminación**: Los catálogos son datos maestros, no se crean desde la aplicación
- **NO maneja lógica de negocio compleja**: Solo almacena y sirve datos de referencia
- **NO valida entidades de otros contextos**: Solo proporciona datos, la validación se hace en otros módulos

---

## 2. Stakeholders y Usuarios

| Stakeholder | Uso | Necesidad |
|-------------|-----|-----------|
| **Todos los módulos** | Consultan catálogos para validación y display | Obtener información de referencia actualizada |
| **Frontend (Portal de Onboarding)** | Poblar dropdowns y formularios | Mostrar opciones válidas a los usuarios |
| **Patient Module** | Validar districtId en direcciones | Garantizar distrito válido |
| **Client Module** | Validar districtId en direcciones | Garantizar distrito válido |
| **Guarantor Module** | Validar districtId en direcciones | Garantizar distrito válido |
| **Loan Module** | Validar clinicBranchId | Garantizar clínica válida |

---

## 3. Lenguaje Ubicuo

| Término | Definición |
|---------|------------|
| **Department (Departamento)** | Primera división administrativa de Perú. Ejemplo: Lima, Cusco, Arequipa. ID de 2 dígitos. |
| **Province (Provincia)** | Segunda división administrativa de Perú. Pertenece a un departamento. ID de 4 dígitos. |
| **District (Distrito)** | Tercera división administrativa de Perú. Pertenece a una provincia. ID de 6 dígitos. |
| **Ubigeo** | Sistema de codificación geográfica de Perú. Jerarquía: Department → Province → District. |
| **MedicalCategory (Categoría Médica)** | Tipo de procedimiento médico ofrecido (cirugía estética, odontología, oftalmología, etc.). |
| **Clinic (Clínica)** | Institución médica que ofrece procedimientos. Pertenece a una categoría médica. |
| **ClinicBranch (Sucursal de Clínica)** | Ubicación física específica de una clínica. Una clínica puede tener múltiples sucursales. |

---

## 4. Aggregate Roots

### **Department** (Aggregate Root)

**Responsabilidad principal:**
- Representar el nivel más alto de la jerarquía geográfica de Perú
- Servir como punto de entrada para consultas geográficas jerárquicas

**Invariantes que protege:**
- ID único de 2 dígitos (ej: "15" para Lima)
- Nombre debe ser no nulo

### **Clinic** (Aggregate Root)

**Responsabilidad principal:**
- Representar una institución médica en el sistema
- Mantener la relación con su categoría médica

**Invariantes que protege:**
- ID único
- Debe pertenecer a una categoría médica válida (medicalCategoryId)
- Nombre debe ser no nulo

---

## 5. Entidades

### Department (Aggregate Root)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único del departamento (2 dígitos) | Sí |
| `name` | String | Nombre del departamento | Sí |

**Relación con otros objetos:**
- **Province (1:N)**: Un departamento tiene múltiples provincias

**Identificador:**
- ID de 2 dígitos (ej: "15")

---

### Province (Entity)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único de la provincia (4 dígitos) | Sí |
| `name` | String | Nombre de la provincia | Sí |
| `departmentId` | String | ID del departamento al que pertenece | Sí |

**Relación con otros objetos:**
- **Department (N:1)**: Pertenece a un departamento
- **District (1:N)**: Una provincia tiene múltiples distritos

**Identificador:**
- ID de 4 dígitos (ej: "1501")

---

### District (Entity)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único del distrito (6 dígitos) | Sí |
| `name` | String | Nombre del distrito | Sí |
| `provinceId` | String | ID de la provincia a la que pertenece | Sí |

**Relación con otros objetos:**
- **Province (N:1)**: Pertenece a una provincia
- **Jerarquía completa**: District → Province → Department

**Identificador:**
- ID de 6 dígitos (ej: "150101")

---

### MedicalCategory (Entity)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único de la categoría | Sí |
| `name` | String | Nombre de la categoría | Sí |

**Relación con otros objetos:**
- **Clinic (1:N)**: Una categoría tiene múltiples clínicas

---

### Clinic (Aggregate Root)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único de la clínica | Sí |
| `name` | String | Nombre de la clínica | Sí |
| `medicalCategoryId` | String | ID de la categoría médica | Sí |

**Relación con otros objetos:**
- **MedicalCategory (N:1)**: Pertenece a una categoría médica
- **ClinicBranch (1:N)**: Una clínica tiene múltiples sucursales

---

### ClinicBranch (Entity)

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | String | ID único de la sucursal | Sí |
| `name` | String | Nombre/ubicación de la sucursal | Sí |
| `clinicId` | String | ID de la clínica a la que pertenece | Sí |

**Relación con otros objetos:**
- **Clinic (N:1)**: Pertenece a una clínica

---

## 6. Value Objects

No se usan Value Objects en este contexto. Todas las entidades son simples con campos primitivos.

---

## 7. Enums del dominio

No hay enums específicos de este contexto. Catalog es un contexto de solo lectura que almacena datos maestros.

---

## 8. Eventos de Dominio

**Estado actual:** No se han implementado eventos de dominio para este contexto.

**Suposiciones:**
Dado que Catalog es solo lectura, no se generan eventos. Los cambios en catálogos (si los hubiera) se hacen directamente en la base de datos.

---

## 9. Casos de Uso (Application Layer)

### Query Services

#### LocationQueryService

| Caso de Uso | Descripción Funcional |
|-------------|--------------------------|
| **getAllDepartments** | Obtiene la lista completa de departamentos de Perú |
| **getDepartmentById** | Busca un departamento específico por su ID |
| **getAllProvinces** | Obtiene la lista completa de provincias |
| **getProvincesByDepartmentId** | Filtra provincias que pertenecen a un departamento específico |
| **getAllDistricts** | Obtiene la lista completa de distritos |
| **getDistrictsByProvinceId** | Filtra distritos que pertenecen a una provincia específica |

#### CatalogQueryService

| Caso de Uso | Descripción Funcional |
|-------------|--------------------------|
| **getAllMedicalCategories** | Obtiene la lista completa de categorías médicas |
| **getClinicsByCategory** | Filtra clínicas por categoría médica |
| **getBranchesByClinic** | Obtiene todas las sucursales de una clínica específica |
| **searchClinics** | Busca clínicas por nombre y opcionalmente por categoría |

---

## 10. APIs públicas (REST)

### Geographic Catalog Endpoints

#### **1. Listar Departamentos**

**Endpoint:** `GET /api/v1/departments`

**Response (200 OK):**
```json
[
  {
    "id": "15",
    "name": "Lima"
  },
  {
    "id": "08",
    "name": "Cusco"
  }
]
```

---

#### **2. Listar Provincias**

**Endpoint:** `GET /api/v1/provinces`

**Query Parameters:**
- `departmentId` (optional): Filtra provincias por departamento

**Response (200 OK):**
```json
[
  {
    "id": "1501",
    "name": "Lima",
    "departmentId": "15"
  },
  {
    "id": "1502",
    "name": "Barranca",
    "departmentId": "15"
  }
]
```

---

#### **3. Listar Distritos**

**Endpoint:** `GET /api/v1/districts`

**Query Parameters:**
- `provinceId` (optional): Filtra distritos por provincia

**Response (200 OK):**
```json
[
  {
    "id": "150101",
    "name": "Lima",
    "provinceId": "1501"
  },
  {
    "id": "150122",
    "name": "Miraflores",
    "provinceId": "1501"
  }
]
```

---

### Medical Catalog Endpoints

#### **4. Listar Categorías Médicas**

**Endpoint:** `GET /api/v1/medical-categories`

**Response (200 OK):**
```json
[
  {
    "id": "EST",
    "name": "Cirugía Estética"
  },
  {
    "id": "OFT",
    "name": "Oftalmología"
  }
]
```

---

#### **5. Listar Clínicas**

**Endpoint:** `GET /api/v1/clinics`

**Query Parameters:**
- `categoryId` (optional): Filtra clínicas por categoría médica
- `query` (optional): Busca por nombre de clínica

**Response (200 OK):**
```json
[
  {
    "id": "CLI001",
    "name": "Clínica San Juan de Dios"
  },
  {
    "id": "CLI002",
    "name": "Clínica Ricardo Palma"
  }
]
```

---

#### **6. Listar Sucursales de Clínica**

**Endpoint:** `GET /api/v1/clinic-branches?clinicId={clinicId}`

**Response (200 OK):**
```json
[
  {
    "id": "BRA001",
    "name": "San Isidro - Av. Javier Prado"
  },
  {
    "id": "BRA002",
    "name": "Miraflores - Av. Larco"
  }
]
```

---

## 11. Modelo de Datos

### Jerarquía Geográfica

```
department (id: 2 dígitos)
  ↓
province (id: 4 dígitos, department_id)
  ↓
district (id: 6 dígitos, province_id)
```

### Jerarquía Médica

```
medical_category (id: string)
  ↓
clinic (id: string, medical_category_id)
  ↓
clinic_branch (id: string, clinic_id)
```

### Tabla: `department`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(2) | PRIMARY KEY | ID de 2 dígitos |
| `name` | VARCHAR(100) | NOT NULL | Nombre del departamento |

### Tabla: `province`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(4) | PRIMARY KEY | ID de 4 dígitos |
| `name` | VARCHAR(100) | NOT NULL | Nombre de la provincia |
| `department_id` | VARCHAR(2) | NOT NULL | FK hacia department (sin constraint) |

### Tabla: `district`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(6) | PRIMARY KEY | ID de 6 dígitos |
| `name` | VARCHAR(100) | NOT NULL | Nombre del distrito |
| `province_id` | VARCHAR(4) | NOT NULL | FK hacia province (sin constraint) |

### Tabla: `medical_category`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(10) | PRIMARY KEY | ID único |
| `name` | VARCHAR(100) | NOT NULL | Nombre de la categoría |

### Tabla: `clinic`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(10) | PRIMARY KEY | ID único |
| `name` | VARCHAR(200) | NOT NULL | Nombre de la clínica |
| `medical_category_id` | VARCHAR(10) | NOT NULL | FK hacia medical_category (sin constraint) |

### Tabla: `clinic_branch`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | VARCHAR(10) | PRIMARY KEY | ID único |
| `name` | VARCHAR(200) | NOT NULL | Nombre/ubicación de la sucursal |
| `clinic_id` | VARCHAR(10) | NOT NULL | FK hacia clinic (sin constraint) |

---

## 12. Integraciones con otros bounded contexts

### Qué contextos consumen Catalog

| Contexto | Tipo | Propósito |
|----------|------|-----------|
| **Patient** | Síncrono (Repository) | Valida districtId en direcciones de pacientes |
| **Client** | Síncrono (Repository) | Valida districtId en direcciones de clientes |
| **Guarantor** | Síncrono (Repository) | Valida districtId en direcciones de garantes |
| **Loan** | Síncrono (Repository) | Valida clinicBranchId y districtId en préstamos |

### Qué contexto produce

**Ninguno**. Catalog no consume otros bounded contexts, solo proporciona datos de referencia.

### Tipo de comunicación

- **Síncrona**: A través de invocaciones directas a repositorios
- **Read-only**: Catalog no escribe en otros contextos

---

## 13. Diagrama conceptual del dominio

```
┌─────────────────────────────────────────────────────────────┐
│                       DEPARTMENT                             │
│                  (Aggregate Root)                            │
│  - id (2 digits)                                            │
│  - name                                                      │
└───────────────────────┬─────────────────────────────────────┘
                        │ 1:N
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                        PROVINCE                              │
│                       (Entity)                               │
│  - id (4 digits)                                            │
│  - name                                                      │
│  - departmentId → Department                                │
└───────────────────────┬─────────────────────────────────────┘
                        │ 1:N
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                        DISTRICT                              │
│                       (Entity)                               │
│  - id (6 digits)                                            │
│  - name                                                      │
│  - provinceId → Province                                    │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                   MEDICAL_CATEGORY                           │
│                       (Entity)                               │
│  - id                                                        │
│  - name                                                      │
└───────────────────────┬─────────────────────────────────────┘
                        │ 1:N
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                        CLINIC                                │
│                  (Aggregate Root)                            │
│  - id                                                        │
│  - name                                                      │
│  - medicalCategoryId → MedicalCategory                      │
└───────────────────────┬─────────────────────────────────────┘
                        │ 1:N
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                    CLINIC_BRANCH                             │
│                       (Entity)                               │
│  - id                                                        │
│  - name                                                      │
│  - clinicId → Clinic                                        │
└─────────────────────────────────────────────────────────────┘
```

**Relaciones clave:**
- **Department → Province → District**: Jerarquía geográfica normalizada
- **MedicalCategory → Clinic → ClinicBranch**: Jerarquía de catálogos médicos
- **Todas las relaciones usan IDs primitivos**: No hay navegación de objetos JPA

---

## 14. Reglas de negocio importantes

### RN-001: Solo lectura
- **Regla**: Catalog es un contexto de solo lectura desde la aplicación
- **Implementación**: No existen Command Services, solo Query Services
- **Consecuencia**: Los datos maestros se gestionan directamente en la base de datos

### RN-002: Jerarquía geográfica normalizada
- **Regla**: La jerarquía District → Province → Department debe ser consistente
- **Implementación**: 
  - District almacena solo provinceId
  - Province almacena solo departmentId
  - No se duplican niveles superiores
- **Consecuencia**: Para obtener el departamento de un distrito, se hace: District.provinceId → Province.departmentId

### RN-003: IDs son inmutables
- **Regla**: Los IDs de catálogos no cambian una vez asignados
- **Razón**: Son referenciados por múltiples bounded contexts
- **Consecuencia**: Si se necesita "cambiar" un catálogo, se crea uno nuevo y se migran las referencias

### RN-004: Validación en otros contextos
- **Regla**: Catalog NO valida la existencia de IDs
- **Implementación**: Cada contexto (Patient, Loan, etc.) debe validar usando repositorios
- **Consecuencia**: Patient valida districtId contra DistrictRepository

---

## 15. ADR (Architectural Decision Record)

### ADR-001: Uso de IDs primitivos en lugar de relaciones JPA

- **Decisión**: Usar `String` para IDs y `@Column(name = "entity_id")` en lugar de `@ManyToOne`
- **Fecha**: 2026-01-07
- **Contexto**:
  - Originalmente las entidades usaban `@ManyToOne`, `@OneToMany`, `@JoinColumn`
  - Esto creaba acoplamiento fuerte y navegación de objetos
  - Violaba los lineamientos arquitectónicos del proyecto
- **Alternativas consideradas**:
  1. Mantener relaciones JPA (rechazado: viola lineamientos)
  2. Usar DTOs anidados (rechazado: complejidad innecesaria)
  3. IDs primitivos con validación en servicios ( seleccionado)
- **Consecuencia**:
  - Mayor control sobre queries
  - Sin lazy loading issues
  - Validación explícita en servicios
  - Más flexible para evolución independiente

### ADR-002: Catalog como contexto de solo lectura

- **Decisión**: No implementar Command Services para Catalog
- **Fecha**: 2026-01-07
- **Contexto**:
  - Los catálogos son datos maestros
  - Cambios son infrecuentes y controlados
  - No se requiere auditoría de cambios desde la aplicación
- **Alternativas consideradas**:
  1. CRUD completo desde aplicación (rechazado: complejidad innecesaria)
  2. Solo lectura, gestión manual en BD (seleccionado)
- **Consecuencia**:
  - Simplicidad en el código
  - Datos maestros gestionados por DBAs o scripts SQL
  - No hay endpoints POST/PUT/DELETE

### ADR-003: No usar Foreign Key constraints

- **Decisión**: No crear constraints de foreign key en la base de datos
- **Fecha**: 2026-01-07 (Decisión de arquitectura del proyecto)
- **Contexto**:
  - DDD sugiere bounded contexts independientes
  - Se prefiere integridad referencial a nivel de aplicación
  - Mayor flexibilidad para evolución independiente
- **Alternativas consideradas**:
  1. Usar FKs tradicionales (rechazado: acoplamiento en BD)
  2. Sin FKs, validación en aplicación ( seleccionado)
- **Consecuencia**:
  - La validación de IDs se hace en los servicios de cada contexto
  - Mayor flexibilidad pero requiere más cuidado en la lógica
  - Posibilidad de datos huérfanos si no se gestiona bien

---

## Suposiciones

1. **Catálogos son estables**: No se espera que cambien frecuentemente durante la operación normal.

2. **Todos los IDs son String**: Para mantener consistencia, incluso números se almacenan como String.

3. **Sin paginación**: Se asume que la cantidad de datos en catálogos es manejable sin paginación (cientos, no miles).

4. **Sin soft delete**: Los registros de catálogo no se eliminan, se mantienen históricos.

5. **Frontend cachea catálogos**: Se espera que el frontend haga cache de catálogos para reducir llamadas al backend.

---

**Fecha de documentación:** 2026-01-07  
**Versión del sistema:** 1.0  
**Última actualización:** Refactorización a IDs primitivos completada
