# PATIENT

## 1. Propósito del Bounded Context

### ¿Qué problema de negocio resuelve?
El bounded context de **Patient** gestiona la información de los pacientes que recibirán el procedimiento médico financiado por el préstamo. En el modelo de negocio de Kiwipay, el préstamo se otorga para financiar tratamientos médicos, y el paciente es la persona que será sometida al procedimiento.

### ¿Qué procesos cubre?
- **Registro de pacientes**: Captura de datos personales del paciente (identidad, contacto, dirección)
- **Actualización de información**: Modificación de datos del paciente durante el proceso de onboarding
- **Consulta de pacientes**: Visualización de información de pacientes asociados a un préstamo
- **Gestión de múltiples pacientes**: Un préstamo puede tener uno o más pacientes (cirugías múltiples o diferentes pacientes)

### ¿Qué NO cubre?
- **NO gestiona el historial médico**: Solo datos de contacto e identificación
- **NO gestiona tratamientos**: La información del procedimiento médico está en Clinical Data
- **NO maneja aprobaciones crediticias**: Eso corresponde al bounded context de Loan
- **NO gestiona clínicas**: Los datos de clínicas están en el contexto de Catalog

---

## 2. Stakeholders y Usuarios

| Stakeholder | Uso | Necesidad |
|-------------|-----|-----------|
| **Área Comercial** | Registra pacientes durante captación | Capturar información del paciente para el proceso de onboarding |
| **Área de Riesgos** | Consulta información del paciente | Validar identidad y datos para análisis de riesgo |
| **Área de ADV** | Actualiza datos si hay cambios | Mantener información actualizada antes de la firma |
| **Frontend (Portal de Onboarding)** | CRUD completo de pacientes | Interfaz para gestión de pacientes por préstamo |

---

## 3. Lenguaje Ubicuo

| Término | Definición |
|---------|------------|
| **Patient (Paciente)** | Persona que recibirá el procedimiento médico financiado por el préstamo. Puede ser diferente al titular del crédito. |
| **Loan (Préstamo)** | Solicitud de financiamiento a la que pertenece el paciente. Un préstamo puede tener múltiples pacientes. |
| **Document Type (Tipo de Documento)** | Tipo de identificación del paciente: DNI, Carnet de Extranjería, Pasaporte, RUC. |
| **Address (Dirección)** | Ubicación geográfica del paciente, expresada mediante departamento, provincia, distrito y dirección específica. |
| **Gender (Género)** | Género del paciente: Femenino (F), Masculino (M), u Otro (OTHER). |

---

## 4. Aggregate Roots

### **Patient** (Aggregate Root)

**Responsabilidad principal:**
- Mantener la integridad de los datos del paciente
- Garantizar que toda la información personal esté completa y válida
- Asegurar la trazabilidad mediante timestamps de creación y actualización

**Invariantes que protege:**
- Un paciente DEBE estar asociado a un préstamo válido (`loanId` no nulo)
- Los datos de identificación son obligatorios (tipo y número de documento)
- Los nombres completos son requeridos (firstNames, lastNames)
- Los timestamps de auditoría se gestionan automáticamente

**Reglas de negocio clave:**
1. **Existencia de Loan**: No se puede crear un paciente sin un préstamo válido
2. **Auditoría automática**: Las fechas de creación y actualización se registran automáticamente
3. **Un paciente por procedimiento**: Cada paciente representa una persona individual que recibirá tratamiento
4. **Múltiples pacientes por préstamo**: Se permite registrar varios pacientes en un mismo préstamo

---

## 5. Entidades

### Patient

**Campos principales:**

| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | Long | Identificador único del paciente | Sí (auto-generado) |
| `loanId` | Long | Referencia al préstamo asociado | Sí |
| `documentType` | DocumentType | Tipo de documento de identidad | No |
| `documentNumber` | String | Número de documento | No |
| `firstNames` | String | Nombres del paciente | No |
| `lastNames` | String | Apellidos del paciente | No |
| `gender` | Gender | Género del paciente | No |
| `phone` | String | Teléfono de contacto | No |
| `email` | String | Correo electrónico | No |
| `address` | Address (embedded) | Dirección completa | No |
| `createdAt` | OffsetDateTime | Fecha de creación | Sí (auto) |
| `updatedAt` | OffsetDateTime | Fecha de última actualización | Sí (auto) |

**Relación con otros objetos:**
- **Loan (N:1)**: Un paciente pertenece a UN préstamo, pero un préstamo puede tener MÚLTIPLES pacientes
- **Address (Composition)**: La dirección está embebida dentro del paciente

**Identificador:**
- ID numérico auto-incremental generado por la base de datos

---

## 6. Value Objects

### Address

**Qué representa:**
Representa la dirección geográfica completa del paciente, estructurada en niveles administrativos (departamento, provincia, distrito) más una dirección específica.

**Por qué es inmutable:**
- La dirección es un valor que describe una ubicación en un momento dado
- Si cambia la dirección, se reemplaza todo el objeto, no se modifican partes
- Facilita la comparación y el tracking de cambios
- Evita inconsistencias parciales (ej: provincia que no pertenece al departamento)

**Estructura:**
```
Address {
  departmentId: String    // ID del departamento (ej: "15" para Lima)
  provinceId: String      // ID de la provincia
  districtId: String      // ID del distrito
  line1: String           // Dirección específica (calle, número, referencia)
}
```

**Validaciones que aplican:**
- Ninguna validación explícita a nivel de código (se confía en la UI)
- Los IDs deben corresponder a registros válidos en el Catalog
- La jerarquía geográfica debe ser consistente (distrito ∈ provincia ∈ departamento)

---

## 7. Enums del dominio

### DocumentType (Shared)

Tipos de documento de identidad aceptados:

| Valor | Significado |
|-------|-------------|
| `DNI` | Documento Nacional de Identidad (peruanos) |
| `CE` | Carnet de Extranjería (extranjeros residentes) |
| `PASS` | Pasaporte (extranjeros no residentes) |
| `RUC` | Registro Único de Contribuyentes (empresas - poco común para pacientes) |

### Gender (Shared)

Género del paciente:

| Valor | Significado |
|-------|-------------|
| `F` | Femenino |
| `M` | Masculino |
| `OTHER` | Otro / No especificado |

---

## 8. Eventos de Dominio

**Estado actual:** No se han implementado eventos de dominio para este contexto.

**Suposiciones:**
En una arquitectura event-driven, se podrían generar eventos como:
- `PatientRegistered`: Cuando se crea un nuevo paciente
- `PatientInformationUpdated`: Cuando se modifica información
- `PatientDeleted`: Cuando se elimina un paciente

Sin embargo, estos NO existen actualmente en el código.

---

## 9. Casos de Uso (Application Layer)

### Command Services (PatientCommandService)

| Caso de Uso | Descripción Funcional |
|-------------|------------------------|
| **createPatient** | Registra un nuevo paciente asociado a un préstamo. Valida que el préstamo exista antes de crear el paciente. |
| **updatePatient** | Actualiza la información de un paciente existente. Actualiza automáticamente el timestamp de modificación. |
| **deletePatient** | Elimina un paciente del sistema. Valida que el paciente exista y esté asociado al préstamo correcto. |

### Query Services (PatientQueryService)

| Caso de Uso | Descripción Funcional |
|-------------|------------------------|
| **getPatientById** | Obtiene información detallada de un paciente específico por su ID, validando que pertenezca al préstamo indicado. |
| **getPatientsByLoanId** | Lista todos los pacientes asociados a un préstamo (resumen). |
| **getAllPatientsByLoanId** | Lista todos los pacientes con información completa asociados a un préstamo. |

---

## 10. APIs públicas (REST)

### Base Path: `/api/v1/loans/{loanId}/patients`

---

#### **1. Crear Paciente**

**Endpoint:** `POST /api/v1/loans/{loanId}/patients`

**Request Body:**
```json
{
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "Juan Carlos",
  "lastNames": "Perez Lopez",
  "gender": "M",
  "phone": "987654321",
  "email": "juan.perez@example.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150101",
    "line1": "Av. Javier Prado 123, San Isidro"
  }
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "loanId": 1,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "Juan Carlos",
  "lastNames": "Perez Lopez",
  "gender": "M",
  "phone": "987654321",
  "email": "juan.perez@example.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150101",
    "line1": "Av. Javier Prado 123, San Isidro"
  },
  "createdAt": "2026-01-05T15:30:00-05:00"
}
```

**Códigos de error:**
- `400 Bad Request`: Datos inválidos en el request
- `404 Not Found`: El préstamo (loanId) no existe
- `409 Conflict`: Paciente con mismo documento ya existe

---

#### **2. Listar Pacientes de un Préstamo**

**Endpoint:** `GET /api/v1/loans/{loanId}/patients`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "loanId": 1,
    "documentType": "DNI",
    "documentNumber": "12345678",
    "firstNames": "Juan Carlos",
    "lastNames": "Perez Lopez",
    "gender": "M",
    "phone": "987654321",
    "email": "juan.perez@example.com",
    "address": {
      "departmentId": "15",
      "provinceId": "01",
      "districtId": "01",
      "line1": "Av. Javier Prado 123, San Isidro"
    },
    "createdAt": "2026-01-05T15:30:00-05:00"
  }
]
```

**Códigos de error:**
- `404 Not Found`: El préstamo no existe

---

#### **3. Obtener Paciente Específico**

**Endpoint:** `GET /api/v1/loans/{loanId}/patients/{patientId}`

**Response (200 OK):**
```json
{
  "id": 1,
  "loanId": 1,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "Juan Carlos",
  "lastNames": "Perez Lopez",
  "gender": "M",
  "phone": "987654321",
  "email": "juan.perez@example.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150101",
    "line1": "Av. Javier Prado 123, San Isidro"
  },
  "createdAt": "2026-01-05T15:30:00-05:00"
}
```

**Códigos de error:**
- `404 Not Found`: Paciente o préstamo no encontrado

---

#### **4. Actualizar Paciente**

**Endpoint:** `PUT /api/v1/loans/{loanId}/patients/{patientId}`

**Request Body:**
```json
{
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "Juan Updated",
  "lastNames": "Perez Lopez",
  "gender": "M",
  "phone": "999888777",
  "email": "juan.updated@example.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150101",
    "line1": "Av. Updated 456"
  }
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "loanId": 1,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "Juan Updated",
  "lastNames": "Perez Lopez",
  "gender": "M",
  "phone": "999888777",
  "email": "juan.updated@example.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150101",
    "line1": "Av. Updated 456"
  },
  "createdAt": "2026-01-05T15:30:00-05:00"
}
```

**Códigos de error:**
- `400 Bad Request`: Datos inválidos
- `404 Not Found`: Paciente o préstamo no encontrado

---

#### **5. Eliminar Paciente**

**Endpoint:** `DELETE /api/v1/loans/{loanId}/patients/{patientId}`

**Response (204 No Content)**

**Códigos de error:**
- `404 Not Found`: Paciente o préstamo no encontrado
- `409 Conflict`: No se puede eliminar (tiene registros asociados)

---

## 11. Modelo de Datos

### Tabla: `patient`

| Columna | Tipo | Constraints | Descripción |
|---------|------|-------------|-------------|
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Identificador único |
| `loan_id` | BIGINT | NOT NULL | FK hacia tabla `loan` |
| `document_type` | VARCHAR(20) | | Tipo de documento (DNI, CE, PASS, RUC) |
| `document_number` | VARCHAR(20) | | Número de documento |
| `first_names` | VARCHAR(100) | | Nombres del paciente |
| `last_names` | VARCHAR(100) | | Apellidos del paciente |
| `gender` | VARCHAR(10) | | Género (F, M, OTHER) |
| `phone` | VARCHAR(20) | | Teléfono de contacto |
| `email` | VARCHAR(100) | | Correo electrónico |
| `department_id` | VARCHAR(10) | | ID del departamento (embebido) |
| `province_id` | VARCHAR(10) | | ID de la provincia (embebido) |
| `district_id` | VARCHAR(10) | | ID del distrito (embebido) |
| `address_line1` | VARCHAR(255) | | Dirección específica (embebido) |
| `created_at` | TIMESTAMP | NOT NULL | Fecha de creación |
| `updated_at` | TIMESTAMP | NOT NULL | Fecha de actualización |

**Relaciones:**
- `loan_id` → `loan.id` (muchos a uno, sin constraint FK por diseño)

---

## 12. Integraciones con otros bounded contexts

### Qué contexto consume

| Contexto | Tipo | Propósito |
|----------|------|-----------|
| **Loan** | Síncrono (Repository) | Valida que el préstamo exista antes de crear/actualizar paciente |
| **Catalog** | Implícito | Los IDs de departamento/provincia/distrito deben existir en catálogo (validado explícitamente) |

### Qué contexto produce

**Ninguno actualmente**. Patient no expone servicios a otros bounded contexts directamente.

### Tipo de comunicación

- **Síncrona**: A través de invocaciones directas a repositorios (LoanRepository)
- **No hay eventos**: No se publican eventos de dominio actualmente

---

## 13. Diagrama conceptual del dominio (descripción textual)

```
┌─────────────────────────────────────────────────────────────┐
│                         LOAN                                │
│  (Aggregate Root del contexto Loan)                         │
│  - Contiene: id, clientId, loanStatus, etc.                 │
└───────────────────────┬─────────────────────────────────────┘
                        │
                        │ 1:N
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                       PATIENT                                │
│               (Aggregate Root)                               │
│                                                              │
│  Campos de Identidad:                                        │
│  - id (PK)                                                   │
│  - loanId (FK → Loan)                                        │
│  - documentType (DocumentType enum - SHARED)                 │
│  - documentNumber                                            │
│                                                              │
│  Datos Personales:                                           │
│  - firstNames, lastNames                                     │
│  - gender (Gender enum - SHARED)                             │
│  - phone, email                                              │
│                                                              │
│  Ubicación (Value Object embebido):                          │
│  - address (Address)                                         │
│    ├─ departmentId                                           │
│    ├─ provinceId                                             │
│    ├─ districtId                                             │
│    └─ line1                                                  │
│                                                              │
│  Auditoría:                                                  │
│  - createdAt (auto-gestionado)                               │
│  - updatedAt (auto-gestionado)                               │
└─────────────────────────────────────────────────────────────┘
                        │
                        │ Referencias (no modeladas como FK)
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                      CATALOG                                 │
│  - Department (por departmentId)                             │
│  - Province (por provinceId)                                 │
│  - District (por districtId)                                 │
└─────────────────────────────────────────────────────────────┘
```

**Relaciones clave:**
- **Loan → Patient**: Un préstamo puede tener N pacientes (1:N)
- **Patient → Address**: Composición (el address no existe sin patient)
- **Patient → Catalog**: Referencia débil (validados)

---

## 14. Reglas de negocio importantes

### RN-001: Validación de Loan
- **Regla**: No se puede crear un paciente sin un préstamo válido
- **Implementación**: PatientCommandServiceImpl valida `loanRepository.existsById(loanId)` antes de crear
- **Excepción**: RuntimeException con mensaje "Loan not found with id: {loanId}"

### RN-002: Gestión automática de timestamps
- **Regla**: Los timestamps se gestionan automáticamente, no manualmente
- **Implementación**: 
  - `@PrePersist`: Establece `createdAt` y `updatedAt` en la creación
  - `@PreUpdate`: Actualiza `updatedAt` en cada modificación
- **Consecuencia**: Los servicios NO deben manipular estos campos

### RN-003: Relación Loan-Patient
- **Regla**: Un paciente pertenece a UN solo préstamo
- **Invariante**: `loanId` es NOT NULL y no puede cambiar una vez asignado
- **Consecuencia**: Para "mover" un paciente a otro préstamo, se debe eliminar y recrear

### RN-004: Múltiples pacientes por préstamo
- **Regla**: Un préstamo puede tener múltiples pacientes
- **Caso de uso**: Cirugías múltiples para diferentes personas en el mismo préstamo familiar
- **No hay límite explícito**: No existe una regla de negocio que limite la cantidad

### RN-005: Value Objects compartidos
- **Regla**: Los enums `DocumentType` y `Gender` son compartidos entre múltiples contextos
- **Ubicación**: `shared.domain.valueobjects`
- **Consecuencia**: Cambios en estos enums afectan a Client, Patient, Guarantor, etc.

---

## 15. ADR (Architectural Decision Record)

### ADR-001: Migración de clientId a loanId

- **Decisión**: Cambiar la relación de Patient de `clientId` a `loanId`
- **Fecha**: 2026-01-05
- **Contexto**: 
  - Originalmente, Patient estaba ligado al Cliente
  - El análisis de dominio reveló que los pacientes son parte del proceso de préstamo, no del cliente
  - Un cliente puede tener múltiples préstamos, y cada préstamo puede tener diferentes pacientes
- **Alternativas consideradas**:
  1. Mantener `clientId` y agregar `loanId` como campo adicional (rechazado: redundancia)
  2. Crear una tabla intermedia Patient-Loan (rechazado: over-engineering)
  3. Cambiar completamente a `loanId` ( seleccionado)
- **Consecuencia**:
  - Breaking change en API: endpoints cambian de `/clients/{clientId}/patients` a `/loans/{loanId}/patients`
  - Migración de base de datos requerida: renombrar columna `client_id` → `loan_id`
  - Servicios usan ahora `LoanRepository` en vez de `ClientRepository`

### ADR-002: Value Objects compartidos

- **Decisión**: Crear package `shared.domain.valueobjects` para enums comunes
- **Fecha**: 2026-01-05
- **Contexto**:
  - `DocumentType`, `Gender` y `MaritalStatus` se usaban en múltiples bounded contexts
  - Existía duplicación de código entre Client y Patient
  - Necesidad de consistencia en tipos de datos compartidos
- **Alternativas consideradas**:
  1. Duplicar enums en cada contexto (rechazado: violación DRY)
  2. Referenciar desde Client (rechazado: acoplamiento innecesario)
  3. Crear package compartido (seleccionado)
- **Consecuencia**:
  - Mayor cohesión y menos duplicación
  - Un solo punto de mantenimiento para estos valores
  - Facilita futuras adiciones de bounded contexts

### ADR-003: No usar Foreign Keys en base de datos

- **Decisión**: No crear constraints de foreign key en la base de datos
- **Fecha**: Decisión de arquitectura inicial
- **Contexto**:
  - DDD sugiere bounded contexts independientes
  - Se prefiere integridad referencial a nivel de aplicación
  - Mayor flexibilidad para evolución independiente
- **Alternativas consideradas**:
  1. Usar FKs tradicionales (rechazado: acoplamiento en BD)
  2. Sin FKs, validación en aplicación (seleccionado)
- **Consecuencia**:
  - La validación de `loanId` se hace en el servicio
  - Mayor flexibilidad pero requiere más cuidado en la lógica
  - Posibilidad de datos huérfanos si no se gestiona bien

#### **ADR-004: Normalización del modelo de datos de ubigeo**

- **Contexto**:
  - En la versión inicial del script SQL, la tabla `district` contenía tanto `province_id` como `department_id`
  - Esto generaba redundancia ya que la relación jerárquica es: `Department` → `Province` → `District`
  - El `department_id` en `district` era información derivable a través de `province`
- **Decisión**:
  - Eliminar la columna `department_id` de la tabla `district`
  - Mantener solo `province_id` como clave foránea
  - Seguir estrictamente el modelo jerárquico normalizado
- **Razones**:
  1. **Normalización**: Evita duplicación de datos y mejora la integridad referencial
  2. **Consistencia**: Si se actualiza un departamento, no hay riesgo de inconsistencias en distritos
  3. **Jerarquía clara**: El modelo refleja la estructura administrativa real de Perú
  4. **Mantenibilidad**: Menos campos redundantes = menos posibilidad de errores
- **Alternativas consideradas**:
  1. Mantener `department_id` en `district` (rechazado: redundancia y riesgo de inconsistencias)
  2. Denormalizar completamente con todos los campos en una tabla (rechazado: anti-patrón)
  3. Modelo normalizado jerárquico (seleccionado)
- **Consecuencia**:
  - Para obtener el departamento de un distrito, se requiere hacer JOIN con `province`
  - Scripts de inserción más simples y sin redundancia
  - Validación de catálogos implementada en la capa de aplicación para verificar existencia
  - Estructura de IDs: `department` (2 dígitos), `province` (4 dígitos), `district` (6 dígitos)

---

## 16. Ejemplos reales de flujo

### Flujo 1: Registro de Paciente para Cirugía Estética

**Contexto:** Cliente solicita préstamo de S/. 15,000 para rinoplastia de su hija.

**Input:**
```json
POST /api/v1/loans/123/patients

{
  "documentType": "DNI",
  "documentNumber": "75849632",
  "firstNames": "María Elena",
  "lastNames": "Gonzales Ríos",
  "gender": "F",
  "phone": "987123456",
  "email": "maria.gonzales@gmail.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150122",
    "line1": "Calle Los Jazmines 456, Miraflores"
  }
}
```

**Proceso:**
1. API recibe request en `PatientController.createPatient()`
2. Controller delega a `PatientCommandService.createPatient(loanId=123, request)`
3. Servicio valida que loan 123 existe vía `loanRepository.existsById(123)` ✅
4. Crea objeto `Patient` con los datos proporcionados
5. Establece `loanId = 123`
6. Guarda en BD via `patientRepository.save(patient)`
7. `@PrePersist` hook establece `createdAt` y `updatedAt` automáticamente
8. Retorna `PatientResponse` con información completa

**Output (201 Created):**
```json
{
  "id": 45,
  "loanId": 123,
  "documentType": "DNI",
  "documentNumber": "75849632",
  "firstNames": "María Elena",
  "lastNames": "Gonzales Ríos",
  "gender": "F",
  "phone": "987123456",
  "email": "maria.gonzales@gmail.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150122",
    "line1": "Calle Los Jazmines 456, Miraflores"
  },
  "createdAt": "2026-01-05T10:30:25-05:00"
}
```

**Trazabilidad:**
- Base de datos `patient` tabla tiene nuevo registro con `id=45`
- Loan 123 ahora tiene 1 paciente asociado
- Frontend puede consultar pacientes del loan: `GET /api/v1/loans/123/patients`

---

### Flujo 2: Actualización de Datos por Error en Domicilio

**Contexto:** El área comercial se dio cuenta que la dirección del paciente está mal escrita.

**Situación inicial:**
- Paciente ID 45 tiene dirección: "Calle Los Jazmines 456"
- Debería ser: "Calle Los Jazmines 654"

**Input:**
```json
PUT /api/v1/loans/123/patients/45

{
  "documentType": "DNI",
  "documentNumber": "75849632",
  "firstNames": "María Elena",
  "lastNames": "Gonzales Ríos",
  "gender": "F",
  "phone": "987123456",
  "email": "maria.gonzales@gmail.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150122",
    "line1": "Calle Los Jazmines 654, Miraflores"
  }
}
```

**Proceso:**
1. API recibe request en `PatientController.updatePatient(loanId=123, patientId=45, request)`
2. Controller delega a `PatientCommandService.updatePatient(123, 45, request)`
3. Servicio busca paciente: `patientRepository.findByIdAndLoanId(45, 123)` ✅
4. Actualiza campos del paciente existente:
   - Reemplaza `address` completo con nuevo objeto Address
   - Otros campos se mantienen
5. Guarda cambios: `patientRepository.save(patient)`
6. `@PreUpdate` hook actualiza automáticamente `updatedAt`
7. Retorna `PatientResponse` con datos actualizados

**Output (200 OK):**
```json
{
  "id": 45,
  "loanId": 123,
  "documentType": "DNI",
  "documentNumber": "75849632",
  "firstNames": "María Elena",
  "lastNames": "Gonzales Ríos",
  "gender": "F",
  "phone": "987123456",
  "email": "maria.gonzales@gmail.com",
  "address": {
    "departmentId": "15",
    "provinceId": "1501",
    "districtId": "150122",
    "line1": "Calle Los Jazmines 654, Miraflores"
  },
  "createdAt": "2026-01-05T10:30:25-05:00"
}
```

**Resultado:**
- Dirección corregida en base de datos
- `updatedAt` refleja timestamp de la última modificación
- Histórico de cambios se puede auditar por timestamps

---

## Suposiciones

1. **No existe validación de unicidad de documento**: No se valida que un `documentNumber` sea único. En teoría, podría haber duplicados.

2. **Sin límite de pacientes por loan**: No hay una regla de negocio explícita que limite cuántos pacientes puede tener un préstamo.

3. **Sin eventos de dominio**: No se generan eventos cuando se crea, actualiza o elimina un paciente. Esto podría ser una mejora futura.

4. **Sin validaciones de formato**: No hay validaciones explícitas de formato para email, teléfono, etc. Se asume datos válidos del frontend.

6. **Delete no valida dependencias**: El delete no verifica si existen registros dependientes (ej: cónyuge del paciente). Esto podría causar inconsistencias.

7. **Sin paginación**: Los endpoints que listan pacientes no implementan paginación. Esto podría ser un problema si un loan tiene muchos pacientes (caso poco probable).

---

**Fecha de documentación:** 2026-01-05  
**Versión del sistema:** 1.0  
**Última actualización:** Migración de clientId a loanId completada
