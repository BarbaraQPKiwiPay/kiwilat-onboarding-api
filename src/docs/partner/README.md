# PARTNER

## 1. Propósito del Bounded Context

### Qué problema de negocio resuelve
El bounded context **Partner** (Pareja/Cónyuge) resuelve la necesidad de **registrar y gestionar la información de los cónyuges** de las diferentes personas involucradas en un préstamo médico. En el contexto de KiwiPay, cuando se otorga un préstamo para procedimientos médicos, es necesario capturar información del cónyuge del titular, del aval o del paciente para fines de:

- **Evaluación crediticia completa**: Conocer el entorno familiar del solicitante
- **Cumplimiento normativo**: Registro de información de personas relacionadas al préstamo
- **Contacto alternativo**: Tener un punto de contacto adicional en caso necesario

### Qué procesos cubre
- Registro de información del cónyuge asociado a un cliente (titular del préstamo)
- Registro de información del cónyuge asociado a un aval (guarantor)
- Registro de información del cónyuge asociado a un paciente
- Actualización de datos personales del cónyuge
- Consulta de información del cónyuge por diferentes criterios
- Eliminación de registro del cónyuge

### Qué NO cubre (límites explícitos)
- **NO gestiona** la creación ni modificación de clientes, avales o pacientes (eso pertenece a otros bounded contexts)
- **NO valida** la situación crediticia del cónyuge
- **NO maneja** documentación adjunta del cónyuge (esto lo hace el bounded context de documentos)
- **NO gestiona** relaciones familiares más allá del cónyuge (hijos, padres, etc.)
- **NO almacena** direcciones del cónyuge (solo información personal básica)
- **NO realiza** verificaciones de identidad o validaciones ante sistemas externos

## 2. Stakeholders y Usuarios

| Stakeholder | Rol | Uso del Contexto |
|------------|-----|------------------|
| **Area Comercial** | Editan o Ingresan información del cónyuge | En caso de que el prestamo sea para un cliente, aval o paciente, se debe registrar la información del cónyuge en el sistema DE MANERA OPCIONAL|

## 3. Lenguaje Ubicuo

| Término | Significado en el Dominio |
|---------|---------------------------|
| **Partner** | Cónyuge o pareja del titular, aval o paciente involucrado en el préstamo |
| **PartnerType** | Tipo de relación del cónyuge: puede ser del CLIENT (titular), GUARANTOR (aval) o PATIENT (paciente) |
| **Owner Entity** | Entidad propietaria del registro del cónyuge (Cliente, Aval o Paciente) |
| **Loan Context** | Préstamo al cual está asociado el registro del cónyuge |
| **Uniqueness Constraint** | Regla que garantiza que cada entidad propietaria solo puede tener un cónyuge registrado |
| **Personal Information** | Datos identificativos del cónyuge: nombres, documento, teléfono, email |
| **FK Validation** | Validación de que la entidad propietaria existe antes de crear el registro del cónyuge |

## 4. Aggregate Roots

### Partner

**Responsabilidad principal:**
- Mantener la integridad de la información del cónyuge
- Garantizar la unicidad: una entidad propietaria solo puede tener un cónyuge
- Validar la consistencia de las claves foráneas según el tipo de cónyuge

**Invariantes que protege:**
1. **Unicidad de cónyuge**: Un cliente/aval/paciente solo puede tener UN cónyuge registrado
2. **Consistencia de FK**: Solo UNA llave foránea debe estar poblada según el `PartnerType`
   - Si es `CLIENT`: solo `clientId` debe tener valor
   - Si es `GUARANTOR`: solo `guarantorId` debe tener valor
   - Si es `PATIENT`: solo `patientId` debe tener valor
3. **Inmutabilidad de tipo**: Una vez creado, el `PartnerType` y las FK asociadas no pueden cambiar
4. **Unicidad de documento**: No pueden existir dos cónyuges diferentes con el mismo número de documento
5. **Auditoría temporal**: Fechas de creación y actualización se mantienen automáticamente

**Reglas de negocio clave:**
- El préstamo (`loanId`) debe existir antes de crear un cónyuge
- La entidad propietaria (cliente/aval/paciente) debe existir antes de asociar un cónyuge
- No se permite registrar un cónyuge si la entidad propietaria ya tiene uno
- Los cambios solo afectan información personal, no relaciones

## 5. Entidades

### Partner
**Campos principales:**
- `id` (Long): Identificador único del registro
- `loanId` (Long): Referencia al préstamo asociado
- `spouseType` (PartnerType): Tipo de relación (CLIENT, GUARANTOR, PATIENT)
- `clientId` (Long): FK condicional al cliente titular
- `guarantorId` (String): FK condicional al aval
- `patientId` (Long): FK condicional al paciente
- `documentType` (DocumentType): Tipo de documento de identidad
- `documentNumber` (String): Número deidentidad único
- `firstNames` (String): Nombres del cónyuge
- `lastNames` (String): Apellidos del cónyuge
- `email` (String): Correo electrónico
- `phone` (String): Teléfono de contacto
- `createdAt` (OffsetDateTime): Fecha de creación
- `updatedAt` (OffsetDateTime): Fecha de última actualización

**Identificador:**
- Clave primaria: `id` (autogenerado)
- Claves únicas: `clientId`, `guarantorId`, `patientId`, `documentNumber`

**Relación con otros objetos:**
- Relación N:1 con `Loan` (muchos cónyuges pueden estar asociados a un préstamo)
- Relación 0..1:1 con `Client` (un cliente puede tener a lo más un cónyuge)
- Relación 0..1:1 con `Guarantor` (un aval puede tener a lo más un cónyuge)
- Relación 0..1:1 con `Patient` (un paciente puede tener a lo más un cónyuge)

**Suposiciones:**
- Se asume que `Guarantor` usa String como tipo de ID
- Se asume que `Client` y `Patient` usan Long como tipo de ID

## 6. Value Objects

### PartnerType (Enum)
**Qué representa:**
- Representa el tipo de relación del cónyuge con la entidad propietaria
- Define qué clave foránea debe estar poblada

**Por qué es inmutable:**
- El tipo de cónyuge define la estructura de datos y las validaciones aplicables
- Cambiar el tipo requeriría cambiar las FK, lo cual rompería la integridad referencial
- La inmutabilidad garantiza trazabilidad: si cambia la relación, debe crearse un nuevo registro

**Validaciones que aplican:**
- Solo puede tener uno de tres valores: CLIENT, GUARANTOR, PATIENT
- No puede ser null al momento de la creación
- Determina qué validaciones de FK se ejecutan

### DocumentType (Compartido)
**Qué representa:**
- Tipo de documento de identidad (DNI, CE, RUC, etc.)

**Suposiciones:**
- Este Value Object se define en el módulo compartido (`shared`)
- Se reutiliza en múltiples bounded contexts

## 7. Enums del dominio

### PartnerType
```
CLIENT: Cónyuge del cliente/titular del préstamo
  - Representa al cónyuge de quien solicita el préstamo
  - Requiere que clientId esté poblado
  
GUARANTOR: Cónyuge del aval/garante
  - Representa al cónyuge de quien garantiza el préstamo
  - Requiere que guarantorId esté poblado
  
PATIENT: Cónyuge del paciente
  - Representa al cónyuge del beneficiario del procedimiento médico
  - Requiere que patientId esté poblado
```

**Significado de negocio:**
- Permite diferenciar quién es el propietario del registro del cónyuge
- Define el contexto en el que se usará la información
- Determina las validaciones y reglas aplicables

## 8. Eventos de Dominio

**Estado actual:** No se implementan eventos de dominio explícitos en este bounded context.

**Suposiciones:**
- Los eventos podrían agregarse en el futuro para:
  - `PartnerCreated`: Notificar cuando se registra un cónyuge
  - `PartnerUpdated`: Notificar cambios en información del cónyuge
  - `PartnerDeleted`: Notificar eliminación de registro

## 9. Casos de Uso (Application Layer)

### Comandos (PartnerCommandService)

#### createSpouse
**Descripción:** Registra un nuevo cónyuge asociado a un cliente, aval o paciente.

**Validaciones:**
- El préstamo debe existir
- La entidad propietaria debe existir
- La entidad propietaria no debe tener ya un cónyuge registrado
- El número de documento no debe estar duplicado
- Debe proporcionarse exactamente UNA FK según el `PartnerType`

**Efecto:** Crea un nuevo registro de Partner en la base de datos.

#### updateSpouse
**Descripción:** Actualiza la información personal de un cónyuge existente.

**Restricciones:**
- No permite cambiar `spouseType`
- No permite cambiar las FK (`clientId`, `guarantorId`, `patientId`)
- Solo actualiza información personal (nombres, documento, email, teléfono)

**Validaciones:**
- El cónyuge debe existir
- El nuevo número de documento no debe estar duplicado

**Efecto:** Actualiza los campos modificables del Partner.

#### deleteSpouse
**Descripción:** Elimina el registro de un cónyuge.

**Validaciones:**
- El cónyuge debe existir

**Efecto:** Elimina físicamente el registro de la base de datos.

**Suposiciones:**
- No se implementa eliminación lógica (soft delete)

### Consultas (PartnerQueryService)

#### getSpouseById
**Descripción:** Obtiene la información de un cónyuge por su ID.

**Retorna:** Optional\<PartnerResponse\> con los datos del cónyuge si existe.

#### getSpousesByLoan
**Descripción:** Obtiene todos los cónyuges asociados a un préstamo específico.

**Uso:** Cuando se necesita ver toda la información familiar relacionada a un préstamo.

**Retorna:** Lista de PartnerResponse (puede incluir cónyuge del cliente, aval y paciente).

#### getSpouseByClient
**Descripción:** Obtiene el cónyuge de un cliente específico.

**Retorna:** Optional\<PartnerResponse\> con el cónyuge si existe.

#### getSpouseByGuarantor
**Descripción:** Obtiene el cónyuge de un aval específico.

**Retorna:** Optional\<PartnerResponse\> con el cónyuge si existe.

#### getSpouseByPatient
**Descripción:** Obtiene el cónyuge de un paciente específico.

**Retorna:** Optional\<PartnerResponse\> con el cónyuge si existe.

## 10. APIs públicas (REST)

### Base URL
```
/api/v1/spouses
```

---

### POST /api/v1/spouses
**Descripción:** Crea un nuevo registro de cónyuge

**Request Body:**
```json
{
  "loanId": 123,
  "spouseType": "CLIENT",
  "clientId": 456,
  "guarantorId": null,
  "patientId": null,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "María Elena",
  "lastNames": "García Pérez",
  "email": "maria.garcia@email.com",
  "phone": "+51987654321"
}
```

**Validaciones Request:**
- `loanId`: requerido, numérico
- `spouseType`: requerido, debe ser CLIENT | GUARANTOR | PATIENT
- `documentType`: requerido
- `documentNumber`: requerido, no blanco
- `firstNames`: requerido, no blanco
- `lastNames`: requerido, no blanco
- `email`: opcional
- `phone`: opcional
- Una y solo una FK debe estar poblada según `spouseType`

**Response 201 Created:**
```json
{
  "id": 789,
  "loanId": 123,
  "spouseType": "CLIENT",
  "clientId": 456,
  "guarantorId": null,
  "patientId": null,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "María Elena",
  "lastNames": "García Pérez",
  "email": "maria.garcia@email.com",
  "phone": "+51987654321",
  "createdAt": "2026-01-06T15:30:00Z",
  "updatedAt": "2026-01-06T15:30:00Z"
}
```

**Códigos de error:**
- `400 BAD_REQUEST`: Validación fallida o regla de negocio violada
  - Préstamo no existe
  - Entidad propietaria no existe
  - Ya existe cónyuge para la entidad propietaria
  - Número de documento duplicado
  - FK inconsistente con `spouseType`

---

### GET /api/v1/spouses/{id}
**Descripción:** Obtiene un cónyuge por ID

**Path Parameters:**
- `id`: ID del cónyuge

**Response 200 OK:**
```json
{
  "id": 789,
  "loanId": 123,
  "spouseType": "CLIENT",
  "clientId": 456,
  "guarantorId": null,
  "patientId": null,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "firstNames": "María Elena",
  "lastNames": "García Pérez",
  "email": "maria.garcia@email.com",
  "phone": "+51987654321",
  "createdAt": "2026-01-06T15:30:00Z",
  "updatedAt": "2026-01-06T15:30:00Z"
}
```

**Códigos de error:**
- `404 NOT_FOUND`: Cónyuge no existe

---

### PUT /api/v1/spouses/{id}
**Descripción:** Actualiza información personal del cónyuge

**Path Parameters:**
- `id`: ID del cónyuge

**Request Body:**
```json
{
  "documentType": "CE",
  "documentNumber": "001234567",
  "firstNames": "María Elena Victoria",
  "lastNames": "García Pérez",
  "email": "maria.nuevoemail@email.com",
  "phone": "+51999888777"
}
```

**Response 200 OK:**
```json
{
  "id": 789,
  "loanId": 123,
  "spouseType": "CLIENT",
  "clientId": 456,
  "guarantorId": null,
  "patientId": null,
  "documentType": "CE",
  "documentNumber": "001234567",
  "firstNames": "María Elena Victoria",
  "lastNames": "García Pérez",
  "email": "maria.nuevoemail@email.com",
  "phone": "+51999888777",
  "createdAt": "2026-01-06T15:30:00Z",
  "updatedAt": "2026-01-06T16:45:00Z"
}
```

**Códigos de error:**
- `400 BAD_REQUEST`: Número de documento duplicado
- `404 NOT_FOUND`: Cónyuge no existe

---

### DELETE /api/v1/spouses/{id}
**Descripción:** Elimina el registro de un cónyuge

**Path Parameters:**
- `id`: ID del cónyuge

**Response 204 NO_CONTENT:** Sin contenido en el body

**Códigos de error:**
- `404 NOT_FOUND`: Cónyuge no existe

---

### GET /api/v1/spouses/loan/{loanId}
**Descripción:** Obtiene todos los cónyuges asociados a un préstamo

**Path Parameters:**
- `loanId`: ID del préstamo

**Response 200 OK:**
```json
[
  {
    "id": 789,
    "loanId": 123,
    "spouseType": "CLIENT",
    "clientId": 456,
    ...
  },
  {
    "id": 790,
    "loanId": 123,
    "spouseType": "GUARANTOR",
    "guarantorId": "G001",
    ...
  }
]
```

---

### GET /api/v1/spouses/client/{clientId}
**Descripción:** Obtiene el cónyuge de un cliente específico

**Path Parameters:**
- `clientId`: ID del cliente

**Response 200 OK:** Objeto PartnerResponse

**Códigos de error:**
- `404 NOT_FOUND`: Cliente no tiene cónyuge registrado

---

### GET /api/v1/spouses/guarantor/{guarantorId}
**Descripción:** Obtiene el cónyuge de un aval específico

**Path Parameters:**
- `guarantorId`: ID del aval

**Response 200 OK:** Objeto PartnerResponse

**Códigos de error:**
- `404 NOT_FOUND`: Aval no tiene cónyuge registrado

---

### GET /api/v1/spouses/patient/{patientId}
**Descripción:** Obtiene el cónyuge de un paciente específico

**Path Parameters:**
- `patientId`: ID del paciente

**Response 200 OK:** Objeto PartnerResponse

**Códigos de error:**
- `404 NOT_FOUND`: Paciente no tiene cónyuge registrado

## 11. Modelo de Datos

### Tabla: `partner`

**Descripción:** Almacena la información de los cónyuges asociados a clientes, avales o pacientes.

**Estructura:**

| Columna | Tipo | Restricciones | Descripción |
|---------|------|---------------|-------------|
| `id` | BIGSERIAL | PK | Identificador único |
| `loan_id` | BIGINT | NOT NULL | FK al préstamo |
| `spouse_type` | VARCHAR(20) | NOT NULL | Tipo de cónyuge: CLIENT, GUARANTOR, PATIENT |
| `client_id` | BIGINT | UNIQUE NULL | FK condicional al cliente |
| `guarantor_id` | VARCHAR(50) | UNIQUE, NULL | FK condicional al aval |
| `patient_id` | BIGINT | UNIQUE, NULL | FK condicional al paciente |
| `document_type` | VARCHAR(20) | NOT NULL | Tipo de documento |
| `document_number` | VARCHAR(20) | NOT NULL, UNIQUE | Número de documento |
| `first_names` | VARCHAR(100) | NOT NULL | Nombres |
| `last_names` | VARCHAR(100) | NOT NULL | Apellidos |
| `email` | VARCHAR(100) | NULL | Email |
| `phone` | VARCHAR(20) | NULL | Teléfono |
| `created_at` | TIMESTAMP WITH TIME ZONE | NOT NULL | Fecha creación |
| `updated_at` | TIMESTAMP WITH TIME ZONE | NULL | Fecha actualización |

**Índices:**
- PK en `id`
- UNIQUE en `client_id`
- UNIQUE en `guarantor_id`
- UNIQUE en `patient_id`
- UNIQUE en `document_number`

**Relaciones:**
- `loan_id` → `loans(id)`
- `client_id` →`clients(id)` (opcional)
- `guarantor_id` → `guarantors(id)` (opcional)
- `patient_id` → `patients(id)` (opcional)

**Reglas de consistencia:**
- Solo UNA de las FK (`client_id`, `guarantor_id`, `patient_id`) debe tener valor
- La FK poblada debe corresponder con el valor de `spouse_type`

## 12. Integraciones con otros bounded contexts

### Consume de:

#### Loan (Préstamo)
- **Qué consume:** Validación de existencia del préstamo
- **Tipo de comunicación:** Síncrona (consulta directa a repositorio)
- **Propósito:** Asegurar que el cónyuge se asocie a un préstamo válido
- **Dependencia:** LoanRepository.existsById()

#### Client (Cliente/Titular)
- **Qué consume:** Validación de existencia del cliente
- **Tipo de comunicación:** Síncrona (consulta directa a repositorio)
- **Propósito:** Validar que existe el cliente antes de asociar su cónyuge
- **Dependencia:** ClientRepository.existsById()

#### Guarantor (Aval)
- **Qué consume:** Validación de existencia del aval
- **Tipo de comunicación:** Síncrona (consulta directa a repositorio)
- **Propósito:** Validar que existe el aval antes de asociar su cónyuge
- **Dependencia:** GuarantorRepository.existsById()

#### Patient (Paciente)
- **Qué consume:** Validación de existencia del paciente
- **Tipo de comunicación:** Síncrona (consulta directa a repositorio)
- **Propósito:** Validar que existe el paciente antes de asociar su cónyuge
- **Dependencia:** PatientRepository.existsById()

### Produce para:

**Estado actual:** Este bounded context no produce eventos ni datos para otros contexts de forma activa.

**Potenciales consumidores:**
- **Risk Assessment** podría consumir información del cónyuge para análisis de riesgo familiar
- **Reporting** podría consultar estadísticas de cónyuges registrados
- **Audit** podría rastrear cambios en la información de cónyuges

**Suposiciones:**
- La comunicación es principalmente por consulta directa (pull) y no por eventos (push)

## 13. Diagrama conceptual del dominio (descripción textual)

```
                    ┌─────────┐
                    │  Loan   │
                    │ (Préstamo)│
                    └────┬────┘
                         │
                         │ 1
                         │
                         │
              ┌──────────┴──────────┬──────────────────┐
              │                     │                  │
              │ N                   │ N                │ N
              │                     │                  │
        ┌─────▼─────┐         ┌────▼─────┐      ┌─────▼──────┐
        │  Client   │         │Guarantor │      │  Patient   │
        │ (Titular) │         │  (Aval)  │      │ (Paciente) │
        └─────┬─────┘         └────┬─────┘      └─────┬──────┘
              │                    │                   │
              │ 0..1               │ 0..1              │ 0..1
              │                    │                   │
              └────────┬───────────┴──────┬────────────┘
                       │                  │
                       ▼                  ▼
                 ┌─────────────────────────────┐
                 │    Partner (Spouse)         │
                 │  [Aggregate Root]           │
                 ├─────────────────────────────┤
                 │ - id: Long                  │
                 │ - loanId: Long              │
                 │ - spouseType: PartnerType   │──┐
                 │ - clientId?: Long           │  │
                 │ - guarantorId?: String      │  │ Exactamente UNA FK
                 │ - patientId?: Long          │  │ debe tener valor
                 │ - documentType: DocumentType│──┘
                 │ - documentNumber: String    │
                 │ - firstNames: String        │
                 │ - lastNames: String         │
                 │ - email: String             │
                 │ - phone: String             │
                 │ - createdAt: DateTime       │
                 │ - updatedAt: DateTime       │
                 └─────────────────────────────┘
                           │
                           │ uses
                           ▼
                  ┌────────────────┐
                  │  PartnerType   │
                  │ [Value Object] │
                  ├────────────────┤
                  │ - CLIENT       │
                  │ - GUARANTOR    │
                  │ - PATIENT      │
                  └────────────────┘
```

**Descripción de relaciones:**

1. **Loan - Partner**: Un préstamo puede tener múltiples cónyuges (del cliente, aval y paciente)
2. **Client - Partner**: Un cliente puede tener 0 o 1 cónyuge (unicidad)
3. **Guarantor - Partner**: Un aval puede tener 0 o 1 cónyuge (unicidad)
4. **Patient - Partner**: Un paciente puede tener 0 o 1 cónyuge (unicidad)
5. **Partner - PartnerType**: Cada Partner tiene un tipo que determina qué FK usar
6. **Constraint de FK**: Solo una de las tres FK puede estar poblada

**Flujo de datos:**
1. Se crea un Préstamo
2. Se crean entidades relacionadas (Cliente, Aval, Paciente)
3. Para cada entidad que requiere cónyuge, se crea un Partner con el `PartnerType` correspondiente
4. El Partner valida que la entidad propietaria existe y no tiene ya un cónyuge
5. Se guarda el Partner con la FK apropiada poblada

## 14. Reglas de negocio importantes

### RN-01: Unicidad de cónyuge por entidad
**Regla:** Una entidad (Cliente, Aval o Paciente) solo puede tener UN cónyuge registrado.

- **Implementación:** Constraint UNIQUE en cada FK (`client_id`, `guarantor_id`, `patient_id`)
- **Validación:** Antes de crear, se verifica que no exista otro Partner con la misma FK
- **Excepción:** PartnerBusinessException si ya existe
- **Justificación:** Evita duplicados y mantiene consistencia en la información familiar

### RN-02: Consistencia de FK según PartnerType
**Regla:** Solo una FK debe estar poblada, correspondiente al `PartnerType`:
- `CLIENT` → solo `clientId` tiene valor
- `GUARANTOR` → solo `guarantorId` tiene valor
- `PATIENT` → solo `patientId` tiene valor

- **Implementación:** Método `validate()` en el aggregate que se ejecuta en `@PrePersist` y `@PreUpdate`
- **Validación:** Se verifica que las otras dos FK sean null
- **Excepción:** PartnerBusinessException si hay inconsistencia
- **Justificación:** Garantiza integridad referencial y evita ambigüedad

### RN-03: Inmutabilidad de tipo y relaciones
**Regla:** Una vez creado un Partner, su `spouseType` y las FK (`clientId`, `guarantorId`, `patientId`) no pueden cambiar.

- **Implementación:** El método `updateSpouse` solo permite cambiar información personal
- **Justificación:** Cambiar el tipo requeriría cambiar la FK, lo cual rompería la trazabilidad. Si cambia la relación, debe eliminarse y crearse un nuevo registro

### RN-04: Existencia de entidades relacionadas
**Regla:** El Loan y la entidad propietaria (Client/Guarantor/Patient) deben existir antes de crear un Partner.

- **Implementación:** Validación explícita con `existsById()` en los repositorios correspondientes
- **Excepción:** PartnerBusinessException si alguna entidad no existe
- **Justificación:** Mantiene integridad referencial a nivel de aplicación

### RN-05: Unicidad de documento
**Regla:** No pueden existir dos Partners con el mismo `documentNumber`.

- **Implementación:** Constraint UNIQUE en `document_number` + validación en servicio
- **Excepción:** PartnerBusinessException si hay duplicado
- **Justificación:** Evita registrar a la misma persona múltiples veces

### RN-06: Auditoría automática
**Regla:** Las fechas `createdAt` y `updatedAt` se gestionan automáticamente.

- **Implementación:** `@PrePersist` establece ambas fechas; `@PreUpdate` actualiza solo `updatedAt`
- **Justificación:** Garantiza trazabilidad sin intervención manual

## 15. ADR corto (Architectural Decision Record)

### ADR-001: Tabla unificada vs. Tablas separadas por tipo

**Decisión:** Usar una única tabla `partner` para todos los tipos de cónyuge

**Fecha:** 2026-01-06

**Contexto:** 
Se necesitaba decidir entre:
1. Una tabla `partner` con FK condicionales
2. Tres tablas: `client_spouses`, `guarantor_spouses`, `patient_spouses`

**Alternativas consideradas:**
- **Opción A (Elegida):** Tabla unificada con `spouse_type` discriminador
  - Ventajas: Menos tablas, queries unificadas, más fácil de consultar todos los cónyuges de un préstamo
  - Desventajas: Mayor complejidad en validaciones, FK condicionales
  
- **Opción B:** Tablas separadas por tipo
  - Ventajas: FK siempre pobladas, mayor claridad en el modelo
  - Desventajas: Duplicación de código, dificulta consultas globales, más tablas que mantener

**Consecuencia:**
- Se implementó validación robusta en el aggregate para garantizar consistencia de FK
- Se usan constraints UNIQUE en cada FK para forzar unicidad
- Simplifica operaciones de consulta global por préstamo

### ADR-002: Renombrado de Spouse a Partner

**Decisión:** Cambiar el nombre del bounded context de `spouse` a `partner`

**Fecha:** 2026-01-06

**Contexto:**
- Inicialmente se llamaba `spouse` (cónyuge)
- Se identificó conflicto de nombres con entidades legacy en `client` y `guarantor` modules
- Ambos módulos legacy tenían entidades `Spouse` mapeando a tablas `spouse` y `guarantor_spouses`

**Alternativas consideradas:**
- **Opción A:** Deprecar y modificar legacy entities
  - Riesgo: Impactar código en producción
- **Opción B (Elegida):** Renombrar nuevo bounded context a `partner`
  - Ventaja: No impacta código legacy, evita conflictos de mapeo Hibernate
  
**Consecuencia:**
- El bounded context se llama `partner` pero internamente aún usa nomenclatura "spouse" en algunos lugares (temporal)
- La tabla se mapea a `partner` para evitar conflictos con `spouse` legacy
- Se mantiene compatibilidad con módulos legacy que siguen usando sus propias entidades Spouse

### ADR-003: Validación de existencia síncrona

**Decisión:** Validar existencia de entidades relacionadas mediante consultas síncronas a repositorios

**Fecha:** 2026-01-06

**Contexto:**
En arquitecturas distribuidas podría usarse:
1. Consultas síncronas directas a repositorios (elegido)
2. Eventos/mensajería asíncrona
3. Servicios REST entre bounded contexts

**Alternativas consideradas:**
- **Opción A (Elegida):** Repositorios directos
  - Ventaja: Simple, rápido, transaccional
  - Desventaja: Acoplamiento a nivel de base de datos
  
- **Opción B:** API REST entre contexts
  - Ventaja: Desacoplamiento
  - Desventaja: Latencia, complejidad, manejo de fallos

**Consecuencia:**
- Fuerte acoplamiento con otros bounded contexts a nivel de persistencia
- Validaciones rápidas y dentro de la misma transacción
- Requiere que todos los contexts estén en la misma base de datos
- En futuro, podría refactorizarse a eventos o APIs si se necesita mayor desacoplamiento

## 16. Ejemplos reales de flujo

### Ejemplo 1: Registro de cónyuge del cliente titular

**Escenario:** Un ejecutivo registra el cónyuge del titular del préstamo durante el proceso de onboarding.

**Input (POST /api/v1/spouses):**
```json
{
  "loanId": 1001,
  "spouseType": "CLIENT",
  "clientId": 5023,
  "guarantorId": null,
  "patientId": null,
  "documentType": "DNI",
  "documentNumber": "45678912",
  "firstNames": "Ana Patricia",
  "lastNames": "Rodríguez Campos",
  "email": "ana.rodriguez@email.com",
  "phone": "+51912345678"
}
```

**Procesamiento:**
1. **Validación de Loan:** Se verifica que el préstamo 1001 existe → ✓
2. **Validación de Owner:** Se verifica que el cliente 5023 exists → ✓
3. **Validación de Unicidad:** Se consulta si cliente 5023 ya tiene cónyuge → No tiene
4. **Validación de Documento:** Se verifica que DNI 45678912 no esté duplicado → ✓
5. **Validación de FK:** Se verifica que solo `clientId` esté poblado → ✓
6. **Creación:** Se crea el aggregate Partner
7. **Invocación @PrePersist:** Se ejecuta `validate()` y se establecen `createdAt` y `updatedAt`
8. **Persistencia:** Se guarda en la tabla `partner`

**Output (201 Created):**
```json
{
  "id": 789,
  "loanId": 1001,
  "spouseType": "CLIENT",
  "clientId": 5023,
  "guarantorId": null,
  "patientId": null,
  "documentType": "DNI",
  "documentNumber": "45678912",
  "firstNames": "Ana Patricia",
  "lastNames": "Rodríguez Campos",
  "email": "ana.rodriguez@email.com",
  "phone": "+51912345678",
  "createdAt": "2026-01-06T10:15:30-05:00",
  "updatedAt": "2026-01-06T10:15:30-05:00"
}
```

**Resultado:** El cónyuge queda registrado y asociado al cliente 5023.

---

### Ejemplo 2: Intento de registrar segundo cónyuge para el mismo cliente (Caso de error)

**Escenario:** Se intenta registrar otro cónyuge para un cliente que ya tiene uno.

**Input (POST /api/v1/spouses):**
```json
{
  "loanId": 1001,
  "spouseType": "CLIENT",
  "clientId": 5023,
  "guarantorId": null,
  "patientId": null,
  "documentType": "CE",
  "documentNumber": "001234567",
  "firstNames": "María Jose",
  "lastNames": "González Torres",
  "email": "maria.gonzalez@email.com",
  "phone": "+51987654321"
}
```

**Procesamiento:**
1. **Validación de Loan:** Préstamo 1001 existe → ✓
2. **Validación de Owner:** Cliente 5023 existe → ✓
3. **Validación de Unicidad:** Se consulta si cliente 5023 ya tiene cónyuge → **SÍ TIENE (ID 789)**
4. **Excepción:** Se lanza `PartnerBusinessException`: "Client with ID 5023 already has a spouse"

**Output (400 Bad Request):**
```json
{
  "timestamp": "2026-01-06T10:20:15-05:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Client with ID 5023 already has a spouse",
  "path": "/api/v1/spouses"
}
```

**Resultado:** La operación es rechazada, protegiendo la invariante de unicidad.

---

## Suposiciones

1. **Tipo de ID de Guarantor:** Se asume que `Guarantor` usa `String` como tipo de ID (según lo observado en el código)

2. **Eliminación física:** No se implementa soft delete; la eliminación es física de la base de datos

3. **Sin eventos de dominio:** Actualmente no se publican eventos cuando se crea/modifica/elimina un Partner

4. **Comunicación síncrona:** Las validaciones de existencia de entidades se hacen mediante consultas directas a repositorios, no mediante APIs o eventos

5. **Base de datos compartida:** Se asume que todos los bounded contexts comparten la misma base de datos PostgreSQL

6. **Nomenclatura mixta:** Aunque el contexto se llama `partner`, internamente se mantiene terminología "spouse" en varios lugares (nombres de variables, métodos, endpoints) debido al renombrado reciente

7. **Sin validación de estado civil:** No se valida que la persona efectivamente esté casada; se confía en la información proporcionada

8. **Auditoría simple:** Solo se registran fechas de creación y actualización, no se registra quién hizo los cambios

9. **Sin historial:** No se mantiene historial de cambios en la información del cónyuge

10. **Documentación compartida:** El boundedcontext de documentos es responsable de gestionar archivos adjuntos del cónyuge (actas, etc.)
