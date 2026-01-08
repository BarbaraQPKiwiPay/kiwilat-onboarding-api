# QUOTE

## 1. Propósito del Bounded Context

El bounded context de **Quote** (Cotización) tiene como propósito gestionar las cotizaciones de préstamos que se generan durante el proceso de onboarding de clientes en el sistema Kiwipay.

### Qué problema de negocio resuelve:
- Almacena y gestiona la información financiera preliminar del solicitante (ingreso mensual, tipo y número de documento)
- Permite registrar múltiples cotizaciones para un mismo préstamo antes de la aprobación final
- Mantiene un historial de las cotizaciones realizadas con auditoría de fechas
- Vincula la información del solicitante con una sucursal específica del negocio

### Qué procesos cubre:
- Creación de cotizaciones asociadas a préstamos (loan applications)
- Consulta de cotizaciones por préstamo individual
- Actualización completa o parcial de los datos de una cotización
- Eliminación de cotizaciones
- Listado de todas las cotizaciones en el sistema

### Qué NO cubre (límites explícitos):
- **NO** calcula tasas de interés, MAF, cuotas o aprobaciones crediticias (responsabilidad del sistema SGL)
- **NO** gestiona documentos adjuntos (responsabilidad del bounded context Document)
- **NO** valida la capacidad crediticia del cliente (responsabilidad del módulo de Riesgo)
- **NO** maneja el flujo de aprobación del préstamo (responsabilidad del bounded context Loan)
- **NO** gestiona la información completa del cliente (responsabilidad del bounded context Client)

---

## 2. Stakeholders y Usuarios

### Usuarios del sistema:
- **Asesores de venta**: Crean y actualizan cotizaciones durante el proceso de onboarding de clientes
- **Backoffice/Administradores**: Consultan historial de cotizaciones para análisis y auditoría
- **Sistema SGL (externo)**: Puede consumir información de cotizaciones para cálculos de riesgo
- **Módulo de Loan**: Utiliza las cotizaciones como parte del expediente del préstamo

### Stakeholders de negocio:
- **Área Comercial**: Requiere visibilidad de las cotizaciones realizadas por sucursal
- **Área de Riesgo**: Necesita información de ingresos declarados para evaluación crediticia
- **Auditoría**: Requiere trazabilidad de cuándo se crearon y modificaron las cotizaciones

---

## 3. Lenguaje Ubicuo

| Término | Significado en el Dominio |
|---------|---------------------------|
| **Quote (Cotización)** | Registro de la información financiera preliminar del solicitante de un préstamo, incluyendo su documento de identidad y su ingreso mensual declarado |
| **Monthly Income (Ingreso Mensual)** | Monto en moneda local que el solicitante declara como ingreso mensual. Se utiliza para cálculos de capacidad de pago |
| **Document Type** | Tipo de documento de identidad del solicitante (DNI, CE, Pasaporte, etc.) |
| **Document Number** | Número único del documento de identidad del solicitante |
| **Branch (Sucursal)** | Punto de venta o clínica donde se origina la cotización del préstamo |
| **Loan** | Solicitud de préstamo a la cual pertenece la cotización |

---

## 4. Aggregate Roots

### Quote (Cotización)

**Responsabilidad principal:**
- Mantener la integridad de los datos de la cotización
- Asegurar que toda cotización esté asociada a un préstamo válido
- Garantizar que los datos financieros sean consistentes y válidos

**Invariantes que protege:**
1. Toda cotización DEBE tener un `loanId` válido y positivo
2. El `monthlyIncome` DEBE ser mayor que cero
3. El `documentType` y `documentNumber` NO pueden estar vacíos o ser nulos
4. Una cotización NO puede existir sin estar asociada a un préstamo

**Reglas de negocio clave:**
- El ingreso mensual debe tener como máximo 2 decimales (precisión financiera)
- Una vez creada, la cotización mantiene auditoría automática de fechas (created_at, updated_at)
- El `branchId` es opcional (puede haber cotizaciones sin sucursal específica)
- La validación se ejecuta siempre antes de persistir cambios (construcción y actualización)

---

## 5. Entidades

### Quote (Entidad Raíz del Agregado)

**Campos principales:**
| Campo | Tipo | Descripción | Obligatorio |
|-------|------|-------------|-------------|
| `id` | Long | Identificador único autogenerado | Sí (auto) |
| `loanId` | Long | Referencia al préstamo (FK primitiva) | Sí |
| `documentType` | String | Tipo de documento del solicitante | Sí |
| `documentNumber` | String | Número de documento del solicitante | Sí |
| `monthlyIncome` | BigDecimal | Ingreso mensual declarado (precisión 10,2) | Sí |
| `branchId` | String | Identificador de la sucursal | No |
| `createdAt` | OffsetDateTime | Fecha y hora de creación (auto) | Sí (auto) |
| `updatedAt` | OffsetDateTime | Fecha y hora de última actualización (auto) | Sí (auto) |

**Relación con otros objetos:**
- **Loan (Préstamo)**: Relación muchos-a-uno mediante `loanId` (FK primitiva, sin relación JPA)
  - Una cotización pertenece a UN préstamo
  - Un préstamo puede tener MÚLTIPLES cotizaciones
- **Catalog.Branch**: Relación opcional mediante `branchId` (FK primitiva, sin relación JPA)

**Identificador:**
- `id`: Long autogenerado con estrategia IDENTITY

---

## 6. Value Objects

El bounded context de Quote **NO** contiene Value Objects explícitos. Todos los atributos se modelan como tipos primitivos o wrappers de Java (`String`, `BigDecimal`, `Long`) por las siguientes razones:

**Decisión de diseño:**
- `documentType` y `documentNumber` son Strings simples porque no se validan en este contexto (la validación pertenece al bounded context Shared o Client)
- `monthlyIncome` es un `BigDecimal` pero no se encapsula como Value Object porque no tiene lógica de negocio adicional más allá de la validación numérica
- `branchId` es un String porque representa un identificador externo del Catalog

**Suposiciones:**
- Se asume que los enums de `DocumentType` están definidos en el módulo Shared
- La validación del formato del `documentNumber` se delega al contexto que lo requiera

---

## 7. Enums del Dominio

El bounded context de Quote **NO** define enums propios. Utiliza tipos primitivos para todos sus campos.

**Enums utilizados de otros contextos:**
- **DocumentType** (del módulo Shared): DNI, CE, PASSPORT
  - Representa los tipos de documento de identidad soportados
  - NO se importa directamente en Quote, se almacena como String

---

## 8. Eventos de Dominio

En la implementación actual, el bounded context de Quote **NO** emite eventos de dominio explícitos.

**Suposiciones:**
- Los cambios en las cotizaciones se comunican mediante polling o consultas síncronas desde otros módulos
- No existe comunicación asíncrona event-driven en este contexto
- Los timestamps de auditoría (`createdAt`, `updatedAt`) permiten trackear cambios, pero no se generan eventos

---

## 9. Casos de Uso (Application Layer)

### Casos de Uso de Comandos (Escritura)

| Caso de Uso | Descripción Funcional |
|-------------|----------------------|
| **Crear Cotización** | Permite registrar una nueva cotización asociada a un préstamo. Valida que el préstamo exista antes de crear la cotización |
| **Actualizar Cotización Completa** | Permite modificar todos los campos de una cotización existente (document type, document number, monthly income, branch) |
| **Actualizar Cotización Parcial (Patch)** | Permite modificar solo campos específicos de una cotización sin necesidad de enviar todos los datos |
| **Eliminar Cotización** | Permite eliminar una cotización del sistema. Valida que la cotización exista antes de eliminar |

### Casos de Uso de Consultas (Lectura)

| Caso de Uso | Descripción Funcional |
|-------------|----------------------|
| **Consultar Cotización por ID** | Recupera una cotización específica por su identificador único |
| **Consultar Cotizaciones por Préstamo** | Recupera todas las cotizaciones asociadas a un préstamo específico |
| **Listar Todas las Cotizaciones** | Recupera todas las cotizaciones del sistema sin filtros |

---

## 10. APIs Públicas (REST)

### Crear Cotización

**Endpoint:** `POST /api/v1/loans/{loanId}/quotes`

**Request:**
```json
{
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 3500.00,
  "branchId": "BRANCH001"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "loanId": 123,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 3500.00,
  "branchId": "BRANCH001",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T14:30:00-05:00"
}
```

**Códigos de error:**
- `404 NOT_FOUND`: Préstamo (loanId) no encontrado
- `422 UNPROCESSABLE_ENTITY`: Datos inválidos (monthly income <= 0, campos vacíos)
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Consultar Cotizaciones por Préstamo

**Endpoint:** `GET /api/v1/loans/{loanId}/quotes`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "loanId": 123,
    "documentType": "DNI",
    "documentNumber": "12345678",
    "monthlyIncome": 3500.00,
    "branchId": "BRANCH001",
    "createdAt": "2026-01-08T14:30:00-05:00",
    "updatedAt": "2026-01-08T14:30:00-05:00"
  },
  {
    "id": 2,
    "loanId": 123,
    "documentType": "DNI",
    "documentNumber": "12345678",
    "monthlyIncome": 4000.00,
    "branchId": "BRANCH002",
    "createdAt": "2026-01-08T15:00:00-05:00",
    "updatedAt": "2026-01-08T15:00:00-05:00"
  }
]
```

**Códigos de error:**
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Consultar Cotización por ID

**Endpoint:** `GET /api/v1/quotes/{quoteId}`

**Response (200 OK):**
```json
{
  "id": 1,
  "loanId": 123,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 3500.00,
  "branchId": "BRANCH001",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T14:30:00-05:00"
}
```

**Códigos de error:**
- `404 NOT_FOUND`: Cotización no encontrada
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Actualizar Cotización Completa

**Endpoint:** `PUT /api/v1/quotes/{quoteId}`

**Request:**
```json
{
  "documentType": "CE",
  "documentNumber": "87654321",
  "monthlyIncome": 4500.00,
  "branchId": "BRANCH003"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "loanId": 123,
  "documentType": "CE",
  "documentNumber": "87654321",
  "monthlyIncome": 4500.00,
  "branchId": "BRANCH003",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T16:45:00-05:00"
}
```

**Códigos de error:**
- `404 NOT_FOUND`: Cotización no encontrada
- `422 UNPROCESSABLE_ENTITY`: Datos inválidos
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Actualizar Cotización Parcialmente

**Endpoint:** `PATCH /api/v1/quotes/{quoteId}`

**Request:**
```json
{
  "monthlyIncome": 5000.00
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "loanId": 123,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 5000.00,
  "branchId": "BRANCH001",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T17:00:00-05:00"
}
```

**Campos actualizables:**
- `documentType`
- `documentNumber`
- `monthlyIncome`
- `branchId`

**Códigos de error:**
- `404 NOT_FOUND`: Cotización no encontrada
- `422 UNPROCESSABLE_ENTITY`: Campo inválido o valor incorrecto
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Eliminar Cotización

**Endpoint:** `DELETE /api/v1/quotes/{quoteId}`

**Response (204 No Content):**
Sin cuerpo de respuesta.

**Códigos de error:**
- `404 NOT_FOUND`: Cotización no encontrada
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

### Listar Todas las Cotizaciones

**Endpoint:** `GET /api/v1/quotes`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "loanId": 123,
    "documentType": "DNI",
    "documentNumber": "12345678",
    "monthlyIncome": 3500.00,
    "branchId": "BRANCH001",
    "createdAt": "2026-01-08T14:30:00-05:00",
    "updatedAt": "2026-01-08T14:30:00-05:00"
  },
  {
    "id": 2,
    "loanId": 456,
    "documentType": "CE",
    "documentNumber": "98765432",
    "monthlyIncome": 4200.00,
    "branchId": "BRANCH002",
    "createdAt": "2026-01-08T15:00:00-05:00",
    "updatedAt": "2026-01-08T15:00:00-05:00"
  }
]
```

**Códigos de error:**
- `500 INTERNAL_SERVER_ERROR`: Error interno del servidor

---

## 11. Modelo de Datos

### Tabla: `quotes`

| Columna | Tipo | Restricciones | Descripción |
|---------|------|---------------|-------------|
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Identificador único |
| `loan_id` | BIGINT | NOT NULL | FK al préstamo (sin constraint DB) |
| `document_type` | VARCHAR(50) | NOT NULL | Tipo de documento |
| `document_number` | VARCHAR(50) | NOT NULL | Número de documento |
| `monthly_income` | DECIMAL(10,2) | NOT NULL | Ingreso mensual declarado |
| `branch_id` | VARCHAR(50) | NULLABLE | Identificador de sucursal |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Fecha de creación |
| `updated_at` | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | Fecha de actualización |

**Índices:**
- PRIMARY KEY en `id`
- Índice recomendado en `loan_id` para queries por préstamo

**Decisiones de diseño:**
- **NO** se crean foreign key constraints en la base de datos (integridad referencial manejada en el servicio)
- Timestamps automáticos mediante Hibernate annotations
- Precisión decimal de 10 dígitos enteros y 2 decimales para `monthly_income`

---

## 12. Integraciones con Otros Bounded Contexts

### Contextos que CONSUME:

#### Loan (Préstamo)
- **Qué consume:** Validación de existencia de préstamos mediante `LoanRepository`
- **Tipo de comunicación:** Síncrona (llamada directa al repositorio)
- **Dependencia:** Fuerte acoplamiento mediante ID primitivo
- **Flujo:** Al crear una cotización, se valida que el `loanId` existe antes de persistir

#### Catalog (Catálogo)
- **Qué consume:** Referencia a sucursales mediante `branchId`
- **Tipo de comunicación:** Síncrona (solo almacenamiento de ID, sin validación)
- **Dependencia:** Débil acoplamiento (branchId puede ser null)
- **Flujo:** Se almacena el branchId sin validar su existencia

---

### Contextos que PRODUCEN (consumen Quote):

**Suposiciones:**
- El módulo **Loan** probablemente consume las cotizaciones para armar el expediente completo del préstamo
- El sistema **SGL externo** podría consultar cotizaciones para análisis de riesgo
- El módulo de **Reporting/Analytics** podría consumir cotizaciones para estadísticas

**Nota:** No se observa producción de eventos, por lo que la comunicación es mediante consultas pull (GET).

---

## 13. Diagrama Conceptual del Dominio (Descripción Textual)

```
┌─────────────────────────────────────────────────────────────┐
│                      LOAN (Préstamo)                        │
│                   [Agregado Externo]                        │
│  - Gestiona el ciclo de vida completo del préstamo         │
└───────────────────────┬─────────────────────────────────────┘
                        │
                        │ 1:N (un préstamo puede tener
                        │      múltiples cotizaciones)
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                   QUOTE (Cotización)                        │
│               [Aggregate Root de Quote]                     │
│  ┌───────────────────────────────────────────────────────┐  │
│  │ Atributos:                                            │  │
│  │ - id: Long (PK)                                       │  │
│  │ - loanId: Long (FK primitiva a Loan)                 │  │
│  │ - documentType: String                                │  │
│  │ - documentNumber: String                              │  │
│  │ - monthlyIncome: BigDecimal                           │  │
│  │ - branchId: String (FK primitiva opcional a Branch)  │  │
│  │ - createdAt: OffsetDateTime (auto)                    │  │
│  │ - updatedAt: OffsetDateTime (auto)                    │  │
│  └───────────────────────────────────────────────────────┘  │
│                                                             │
│  ┌───────────────────────────────────────────────────────┐  │
│  │ Métodos de negocio:                                   │  │
│  │ - validate(): Valida invariantes del agregado        │  │
│  └───────────────────────────────────────────────────────┘  │
└───────────────────────┬─────────────────────────────────────┘
                        │
                        │ 0..1:N (opcional)
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│              CATALOG.Branch (Sucursal)                      │
│                   [Agregado Externo]                        │
│  - Representa las sucursales/clínicas                      │
└─────────────────────────────────────────────────────────────┘
```

**Relaciones clave:**
1. **Quote → Loan**: Cada cotización DEBE pertenecer a un préstamo existente (relación obligatoria)
2. **Quote → Branch**: Una cotización PUEDE asociarse opcionalmente a una sucursal específica
3. **NO** hay relaciones bidireccionales JPA (solo IDs primitivos)

---

## 14. Reglas de Negocio Importantes

### Invariantes del Agregado Quote

1. **Regla de Préstamo Obligatorio**
   - Toda cotización DEBE estar asociada a un préstamo válido
   - El `loanId` debe ser un número positivo mayor que 0
   - Se valida la existencia del préstamo antes de crear la cotización

2. **Regla de Ingreso Mensual Positivo**
   - El `monthlyIncome` DEBE ser estrictamente mayor que 0
   - Se permite un máximo de 2 decimales (precisión financiera)
   - El valor máximo permitido es 10 dígitos enteros

3. **Regla de Documento de Identidad Completo**
   - El `documentType` NO puede ser nulo ni estar en blanco
   - El `documentNumber` NO puede ser nulo ni estar en blanco
   - NO se valida el formato del documentNumber en este contexto

4. **Regla de Sucursal Opcional**
   - El `branchId` es un campo opcional (puede ser null)
   - NO se valida la existencia de la sucursal al crear/actualizar

5. **Regla de Auditoría Automática**
   - Toda cotización tiene fecha de creación (`createdAt`) autogenerada
   - Toda modificación actualiza automáticamente `updatedAt`
   - Las fechas NO pueden ser modificadas manualmente

### Reglas de Actualización

6. **Regla de Actualización Parcial**
   - Al usar PATCH, solo se actualizan los campos enviados
   - Se ejecuta validación completa después de aplicar los cambios parciales
   - No se permiten campos no reconocidos (excepción `IllegalArgumentException`)

7. **Regla de Validación en Escritura**
   - Toda creación y actualización ejecuta `validate()` antes de persistir
   - Si la validación falla, se lanza `IllegalArgumentException`
   - Los errores de validación se propagan al cliente como HTTP 422

---

## 15. ADR Corto (Architectural Decision Record)

### ADR-001: Uso de IDs Primitivos en Lugar de Relaciones JPA

**Decisión:**
Utilizar IDs primitivos (`Long`, `String`) para referenciar entidades de otros bounded contexts en lugar de relaciones JPA (`@ManyToOne`, `@OneToMany`).

**Fecha:** 2026-01-08

**Contexto:**
- El sistema sigue principios de DDD con bounded contexts separados
- Se busca baja acoplamiento entre módulos
- Se necesita control explícito sobre la integridad referencial
- Se quiere evitar lazy loading accidental y problemas de N+1 queries

**Alternativas consideradas:**
1. **Relaciones JPA bidireccionales** (@ManyToOne con @JoinColumn)
   - Pros: Navegación automática, queries automáticas
   - Contras: Alto acoplamiento, lazy loading issues, boundaries poco claros
   
2. **IDs primitivos con validación en servicio** (ELEGIDA)
   - Pros: Bajo acoplamiento, boundaries explícitos, control total de queries
   - Contras: Se debe implementar validación manual
   
3. **Event Sourcing puro**
   - Pros: Desacoplamiento total
   - Contras: Complejidad innecesaria para el caso de uso actual

**Consecuencias:**
- ✅ Boundaries de bounded contexts claramente definidos
- ✅ No hay dependencies accidentales entre módulos
- ✅ Control explícito de cuándo y cómo se validan FKs
- ✅ Facilita migración a microservicios en el futuro
- ⚠️ Se debe validar manualmente la existencia de entidades referenciadas
- ⚠️ No hay constraints de FK en base de datos (integridad en aplicación)

---

### ADR-002: No Emitir Eventos de Dominio

**Decisión:**
No implementar eventos de dominio para cambios en cotizaciones en la versión actual.

**Fecha:** 2026-01-08

**Contexto:**
- El sistema actualmente opera de forma síncrona
- No hay requisitos de comunicación asíncrona entre módulos
- Los consumidores de Quote pueden hacer polling mediante las APIs REST

**Alternativas consideradas:**
1. **Eventos síncronos (Spring Events)**
   - Pros: Bajo acoplamiento dentro del mismo proceso
   - Contras: Aún requiere shared memory
   
2. **Sin eventos (ELEGIDA)**
   - Pros: Simplicidad, menos infraestructura
   - Contras: Comunicación mediante polling o consultas activas
   
3. **Message Broker (Kafka/RabbitMQ)**
   - Pros: Desacoplamiento total, escalabilidad
   - Contras: Complejidad operacional, overhead innecesario

**Consecuencias:**
- ✅ Arquitectura más simple y fácil de entender
- ✅ Menos infraestructura que mantener
- ✅ Timestamps de auditoría permiten detectar cambios
- ⚠️ Los consumidores deben hacer polling o consultas activas
- ⚠️ Dificulta eventual migración a arquitectura event-driven

---

## 16. Ejemplos Reales de Flujo

### Ejemplo 1: Creación de Cotización para Cliente Nuevo

**Escenario:**
Un asesor comercial recibe un cliente interesado en un préstamo para un procedimiento médico. El cliente tiene DNI 12345678 y declara un ingreso mensual de S/. 3,500. El asesor crea una cotización.

**Flujo paso a paso:**

1. **Input inicial (Frontend → Backend):**
```http
POST /api/v1/loans/456/quotes
Content-Type: application/json

{
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 3500.00,
  "branchId": "CLINIC_SAN_ISIDRO"
}
```

2. **Procesamiento en QuoteCommandServiceImpl:**
   - Se valida que el préstamo con `loanId = 456` existe consultando `LoanRepository`
   - Si no existe: lanza `QuoteBusinessException.loanNotFound()` → HTTP 404
   - Si existe: continúa

3. **Validación de datos en Quote.validate():**
   - `loanId = 456` (✅ es positivo)
   - `documentType = "DNI"` (✅ no está vacío)
   - `documentNumber = "12345678"` (✅ no está vacío)
   - `monthlyIncome = 3500.00` (✅ es mayor que 0)
   - Validación exitosa

4. **Persistencia:**
   - Se crea la entidad Quote con los datos validados
   - Se generan automáticamente: `id = 123`, `createdAt = 2026-01-08T14:30:00-05:00`, `updatedAt = 2026-01-08T14:30:00-05:00`
   - Se guarda en la tabla `quotes`

5. **Respuesta (Backend → Frontend):**
```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 123,
  "loanId": 456,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 3500.00,
  "branchId": "CLINIC_SAN_ISIDRO",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T14:30:00-05:00"
}
```

**Resultado final:**
- Se ha creado la cotización #123 asociada al préstamo #456
- La cotización queda registrada con auditoría de fecha de creación
- El asesor puede continuar el proceso de onboarding

---

### Ejemplo 2: Actualización Parcial de Ingreso Mensual

**Escenario:**
El cliente del ejemplo anterior presenta una boleta de pago que indica un ingreso mensual de S/. 4,200 en lugar de los S/. 3,500 inicialmente declarados. El asesor actualiza solo el campo de ingreso mensual.

**Flujo paso a paso:**

1. **Input inicial (Frontend → Backend):**
```http
PATCH /api/v1/quotes/123
Content-Type: application/json

{
  "monthlyIncome": 4200.00
}
```

2. **Procesamiento en QuoteCommandServiceImpl.patchQuote():**
   - Se busca la cotización con `id = 123` en `QuoteRepository`
   - Si no existe: lanza `QuoteBusinessException.quoteNotFound()` → HTTP 404
   - Si existe: continúa

3. **Aplicación de cambios parciales:**
   - Se itera sobre los campos enviados: `{"monthlyIncome": 4200.00}`
   - Se detecta el campo `"monthlyIncome"` en el switch-case
   - Se actualiza: `quote.setMonthlyIncome(4200.00)`
   - Los demás campos quedan sin cambios:
     - `documentType = "DNI"` (sin cambio)
     - `documentNumber = "12345678"` (sin cambio)
     - `branchId = "CLINIC_SAN_ISIDRO"` (sin cambio)

4. **Validación post-actualización:**
   - Se ejecuta `quote.validate()` con los nuevos valores
   - `monthlyIncome = 4200.00` (✅ es mayor que 0)
   - Todos los demás campos siguen siendo válidos
   - Validación exitosa

5. **Persistencia:**
   - Se actualiza la entidad en la tabla `quotes`
   - Se actualiza automáticamente: `updatedAt = 2026-01-08T15:45:00-05:00`
   - `createdAt` permanece sin cambios: `2026-01-08T14:30:00-05:00`

6. **Respuesta (Backend → Frontend):**
```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 123,
  "loanId": 456,
  "documentType": "DNI",
  "documentNumber": "12345678",
  "monthlyIncome": 4200.00,
  "branchId": "CLINIC_SAN_ISIDRO",
  "createdAt": "2026-01-08T14:30:00-05:00",
  "updatedAt": "2026-01-08T15:45:00-05:00"
}
```

**Resultado final:**
- Se ha actualizado solo el campo `monthlyIncome` de la cotización #123
- El resto de los campos permanecen intactos
- La auditoría refleja cuándo se modificó la cotización (`updatedAt` actualizado)
- El asesor puede continuar con el proceso de evaluación crediticia con el ingreso corregido

---

## Suposiciones

1. **Validación de documentType:** Se asume que la validación del formato y tipos válidos de documento se realiza en el módulo Shared o mediante validación de frontend. Quote solo almacena el valor como String.

2. **Validación de branchId:** Se asume que la validación de existencia de sucursales es responsabilidad del módulo Catalog o del frontend. Quote acepta cualquier String como branchId.

3. **Múltiples cotizaciones por préstamo:** Se asume que es válido tener múltiples cotizaciones para el mismo préstamo (por ejemplo, diferentes ingresos declarados en distintos momentos del proceso).

4. **Eliminación lógica vs física:** La implementación actual hace eliminación física (DELETE en BD). Se asume que no hay requisito de soft-delete o recuperación de cotizaciones eliminadas.

5. **Sincronización con sistema SGL:** Se asume que existe un sistema externo SGL que consume estos datos, pero Quote no tiene responsabilidad directa sobre esa integración.

6. **No se valida unicidad de documentNumber:** Se asume que puede haber múltiples cotizaciones con el mismo documentNumber (debido a que un cliente puede tener múltiples préstamos o cotizaciones en el tiempo).
