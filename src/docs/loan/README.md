# Loan

## 1. Propósito del Bounded Context

**Qué problema resuelve:**
Gestiona el ciclo completo de vida de un préstamo (loan) desde su creación hasta su desembolso, controlando las etapas de aprobación por diferentes áreas (Comercial, ADV y Riesgos) y manteniendo el estado actual del préstamo con trazabilidad de fechas clave.

**Procesos que cubre:**
- Creación de solicitudes de préstamo para clientes
- Flujo de aprobación multinivel (Comercial → ADV → Riesgos)
- Gestión de observaciones y correcciones en cada etapa
- Transición de estados basada en reglas de negocio
- Registro de fechas críticas (aprobación por riesgos, firma, desembolso)
- Consulta y actualización de información del préstamo

**Límites explícitos (NO cubre):**
- NO gestiona la información del cliente (bounded context `Client`)
- NO gestiona datos clínicos (bounded context `ClinicalData`)
- NO genera ni almacena contratos digitales (delegado a `Keynua`)
- NO ejecuta el desembolso bancario real (solo registra la fecha)
- NO calcula scoring crediticio (solo almacena el resultado)

---

## 2. Stakeholders y Usuarios

| Rol | Uso |
|-----|-----|
| **Área Comercial** | Cuelga documentos y pide a ADV que los revise |
| **Área de Ventas (ADV)** | Revisa documentos y pide a Riesgos que los revise |
| **Área de Riesgos** | Aprueba o rechaza préstamos basándose en análisis crediticio |
| **Cliente** | Proporciona datos y firma el contrato (indirectamente) |
| **Backoffice** | Consulta estado de préstamos, actualiza datos |

---

## 3. Lenguaje Ubicuo

| Término | Definición |
|---------|-----------|
| **Loan (Préstamo)** | Solicitud de crédito asociada a un cliente que pasa por un flujo de aprobación |
| **Status (Estado)** | Etapa actual del préstamo en el flujo de aprobación |
| **Comercial** | Área que cuelga documentos y pide a ADV que los revise |
| **ADV (Área de Ventas)** | Primera etapa de evaluación comercial del préstamo |
| **Riesgos** | Área que realiza análisis crediticio y aprueba/rechaza definitivamente |
| **Observado** | Estado temporal donde se requieren correcciones antes de continuar |
| **MAF** | Monto Aprobado Final del préstamo |
| **Quota Number** | Número de cuotas para el pago del préstamo |
| **Income** | Ingreso declarado del cliente |
| **Signature** | Firma digital del contrato de préstamo |
| **Disbursement** | Desembolso del dinero al cliente |

---

## 4. Aggregate Roots

### Loan
**Responsabilidad principal:**
Mantener la integridad y consistencia del préstamo a través de su ciclo de vida, asegurando que las transiciones de estado cumplan las reglas de negocio.

**Invariantes que protege:**
- Un préstamo debe tener siempre un cliente válido asociado
- Solo se pueden realizar transiciones de estado permitidas por la máquina de estados
- Los estados finales (DISBURSED, REJECTED_BY_RISK) no permiten más cambios
- Las fechas críticas se registran automáticamente al cambiar a ciertos estados

**Reglas de negocio clave:**
1. Estado inicial siempre es `PRE-APROBADO`
2. Flujo obligatorio: PRE-APROBADO → DOCUMENTS_COMPLETED → APPROVED_BY_ADV → APPROVED_BY_RISK → SIGNED → DISBURSED
3. Estados "observado" permiten retroceder para correcciones
4. Al aprobar por riesgos, se registra `approvedByRiskAt`
5. Al firmar contrato, se registra `signatureAt`
6. Al desembolsar, se registra `disbursementAt` y el préstamo queda en estado final

---

## 5. Entidades

### Loan (Aggregate Root)

**Campos principales:**
- `id`: Identificador único del préstamo
- `clientId`: Referencia al cliente (Long, no relación JPA)
- `clinicalDataId`: Referencia a datos clínicos (opcional)
- `income`: Ingreso del cliente
- `quotaNumber`: Número de cuotas
- `maf`: Monto Aprobado Final
- `loanGroup`: Grupo de riesgo
- `segment`: Segmento comercial
- `employmentStatus`: Estado laboral
- `classification`: Clasificación crediticia
- `finalRate`: Tasa final aplicada
- `experianRate`: Tasa obtenida de Experian
- `additionalRate`: Tasa adicional
- `initial`: Pago inicial
- `loanStatus`: Estado actual del préstamo
- `createdAt`, `updatedAt`: Timestamps de auditoría general
- `signatureAt`, `approvedByRiskAt`, `disbursementAt`: Timestamps de eventos críticos

**Relación con otros objetos:**
- Referencia a `Client` mediante `clientId` (sin relación JPA, validación en capa de servicio)
- Referencia opcional a `ClinicalData` mediante `clinicalDataId`

**Identificador:**
`id` (Long) - Autogenerado por base de datos

---

## 6. Value Objects

### LoanStatus (Enum - Value Object inmutable)

**Qué representa:**
El estado actual del préstamo en el flujo de evaluación y aprobación.

**Por qué es inmutable:**
- Los estados son valores predefinidos que no pueden modificarse
- Garantiza que solo existan estados válidos en el sistema
- Encapsula la lógica de transiciones permitidas

**Validaciones que aplica:**
- `canTransitionTo(newStatus)`: Verifica si la transición es válida
- `isFinalState()`: Identifica estados que no permiten más cambios
- `allowsDocumentUpload()`: Define en qué estados se pueden subir documentos
- `getAllowedTransitions()`: Retorna lista de estados permitidos desde el actual

---

## 7. Enums del dominio

### LoanStatus

| Valor | Significado |
|-------|-------------|
| `PRE_APPROVED` | Préstamo creado, pendiente de completar documentos |
| `DOCUMENTS_COMPLETED` | Documentos completos, listo para revisión ADV |
| `APPROVED_BY_ADV` | Aprobado por Área de Ventas, pasa a Riesgos |
| `OBSERVED_BY_ADV` | Requiere correcciones según ADV |
| `APPROVED_BY_RISK` | Aprobado por Riesgos, listo para firma (ESTADO FINAL) |
| `REJECTED_BY_RISK` | Rechazado por Riesgos (ESTADO FINAL) |
| `OBSERVED_BY_RISK` | Requiere correcciones según Riesgos |
| `SIGNED` | Contrato firmado digitalmente |
| `DISBURSED` | Dinero desembolsado |

---

## 8. Eventos de Dominio

**Suposiciones:**
Actualmente no se han implementado eventos de dominio explícitos. El cambio de estado es sincrónico mediante el método `changeStatus()`.

**Eventos potenciales (no implementados):**
- `LoanApprovedByRisk`: Se dispararía al aprobar por riesgos
- `LoanSigned`: Se dispararía al firmar contrato
- `LoanDisbursed`: Se dispararía al desembolsar

---

## 9. Casos de Uso (Application Layer)

### Comandos (LoanCommandService)

| Caso de Uso | Descripción |
|-------------|-------------|
| **createLoan** | Crea un nuevo préstamo asociado a un cliente existente |
| **updateLoan** | Actualiza cualquier campo editable del préstamo |
| **changeStatus** | Cambia el estado del préstamo según flujo permitido |
| **deleteLoan** | Elimina un préstamo (solo si no está en estado final) |

### Consultas (LoanQueryService)

| Caso de Uso | Descripción |
|-------------|-------------|
| **getLoanById** | Obtiene un préstamo específico por ID |
| **getAllLoans** | Lista todos los préstamos con paginación (50 por defecto) |
| **getLoansByClientId** | Obtiene todos los préstamos de un cliente |
| **getLoansByStatus** | Filtra préstamos por estado específico |

---

## 10. APIs públicas (REST)

### POST /api/v1/loans
**Descripción:** Crear un nuevo préstamo

**Request:**
```json
{
  "clientId": 1,
  "income": 5000.00,
  "quotaNumber": 12,
  "maf": 150.00,
  "loanGroup": "A",
  "segment": "Premium",
  "employmentStatus": "EMPLOYED",
  "classification": "Low Risk",
  "finalRate": 12.5,
  "createdByUserId": 1
}
```

**Response 201:**
```json
{
  "id": 1,
  "clientId": 1,
  "loanStatus": "PRE-APROBADO",
  "income": 5000.0,
  "createdAt": "2026-01-03T18:00:00Z"
}
```

**Errores:**
- `404 CLIENT_NOT_FOUND`: Cliente no existe
- `400`: Datos inválidos

---

### GET /api/v1/loans/{loanId}
**Descripción:** Obtener préstamo por ID

**Response 200:**
```json
{
  "id": 1,
  "clientId": 1,
  "loanStatus": "PRE-APROBADO",
  "income": 5000.0,
  "approvedByRiskAt": "2026-01-03T18:30:00Z",
  "createdAt": "2026-01-03T18:00:00Z",
  "updatedAt": "2026-01-03T18:30:00Z"
}
```

**Errores:**
- `404 LOAN_NOT_FOUND`: Préstamo no existe

---

### GET /api/v1/loans
**Descripción:** Listar préstamos con paginación

**Query Params:**
- `page` (default: 0)
- `size` (default: 50)
- `sortBy` (default: createdAt)
- `sortDirection` (default: DESC)

**Response 200:**
```json
{
  "content": [...],
  "totalElements": 150,
  "totalPages": 3,
  "number": 0,
  "size": 50
}
```

---

### PUT /api/v1/loans/{loanId}
**Descripción:** Actualizar datos del préstamo

**Request:**
```json
{
  "income": 6000.00,
  "finalRate": 13.5,
  "updatedByUserId": 1
}
```

**Response 200:** Préstamo actualizado

**Errores:**
- `404 LOAN_NOT_FOUND`

---

### PUT /api/v1/loans/{loanId}/status
**Descripción:** Cambiar estado del préstamo

**Request:**
```json
{
  "newStatus": "APPROVED_BY_RISK",
  "userId": 1,
  "reason": "Análisis crediticio aprobado"
}
```

**Response 200:** Préstamo con nuevo estado

**Errores:**
- `404 LOAN_NOT_FOUND`
- `409 INVALID_STATUS_TRANSITION`: Transición no permitida

---

### DELETE /api/v1/loans/{loanId}
**Descripción:** Eliminar préstamo

**Response 204:** Sin contenido (éxito)

**Errores:**
- `404 LOAN_NOT_FOUND`
- `409 CANNOT_DELETE_LOAN`: Préstamo en estado final

---

### GET /api/v1/loans/client/{clientId}
**Descripción:** Obtener préstamos de un cliente

**Response 200:** Lista de préstamos

---

### GET /api/v1/loans/status/{status}
**Descripción:** Filtrar por estado

**Response 200:** Lista de préstamos

**Errores:**
- `400 INVALID_STATUS`: Estado no existe

---

## 11. Modelo de Datos

### Tabla: loan

| Columna | Tipo | Descripción |
|---------|------|-------------|
| id | BIGSERIAL | PK autoincremental |
| client_id | BIGINT NOT NULL | FK a client (validado en servicio) |
| clinical_data_id | BIGINT | FK opcional a clinical_data |
| income | DOUBLE PRECISION | Ingreso del cliente |
| quota_number | DOUBLE PRECISION | Número de cuotas |
| maf | DOUBLE PRECISION | Monto Aprobado Final |
| loan_group | VARCHAR(50) | Grupo de riesgo |
| segment | VARCHAR(50) | Segmento comercial |
| employment_status | VARCHAR(50) | Estado laboral |
| classification | VARCHAR(50) | Clasificación crediticia |
| final_rate | DOUBLE PRECISION | Tasa final |
| experian_rate | DOUBLE PRECISION | Tasa Experian |
| additional_rate | DOUBLE PRECISION | Tasa adicional |
| initial | DOUBLE PRECISION | Pago inicial |
| loan_status | VARCHAR(50) NOT NULL | Estado actual |
| created_at | TIMESTAMP NOT NULL | Fecha de creación |
| updated_at | TIMESTAMP | Última actualización |
| signature_at | TIMESTAMP | Fecha de firma |
| approved_by_risk_at | TIMESTAMP | Fecha aprobación por riesgos |
| disbursement_at | TIMESTAMP | Fecha de desembolso |

**Índices:**
- `idx_loan_client_id` en client_id
- `idx_loan_status` en loan_status
- `idx_loan_created_at` en created_at DESC
- `idx_loan_clinical_data_id` en clinical_data_id

**Restricciones:**
- `chk_loan_status`: Solo permite valores del enum LoanStatus
- NO hay foreign keys en base de datos (validación en capa de servicio)

---

## 12. Integraciones con otros bounded contexts

### Consume de:
| Contexto | Qué consume | Tipo |
|----------|-------------|------|
| **Client** | Valida existencia del cliente antes de crear loan | Sync (validación en servicio) |
| **ClinicalData** | Valida existencia de datos clínicos (opcional) | Sync (validación en servicio) |

### Produce para:
| Contexto | Qué produce | Tipo |
|----------|-------------|------|
| **Keynua** | Estado SIGNED activa generación de contrato digital | Async (potencial evento futuro) |
| **Disbursement** | Estado APPROVED_BY_RISK habilita desembolso | Sync (consulta de préstamos aprobados) |

**Tipo de comunicación:**
Actualmente **síncrona** mediante validación de IDs en servicios. No hay eventos asíncronos implementados.

---

## 13. Diagrama conceptual del dominio

```
┌─────────────────────────────────────────────────────────┐
│                     LOAN (Aggregate Root)                │
│  - Mantiene integridad del préstamo                     │
│  - Controla transiciones de estado                       │
│  - Registra fechas críticas automáticamente             │
└─────────────────────────────────────────────────────────┘
                          │
                          │ contiene
                          ▼
              ┌─────────────────────┐
              │    LoanStatus       │
              │   (Value Object)    │
              │  - Máquina estados  │
              │  - Validaciones     │
              └─────────────────────┘
                          
      ┌───────────────────┼───────────────────┐
      │                   │                   │
      ▼                   ▼                   ▼
┌──────────┐      ┌──────────────┐    ┌──────────────┐
│  Client  │      │ ClinicalData │    │   Timestamps │
│ (ref ID) │      │  (ref ID)    │    │  - createdAt │
└──────────┘      └──────────────┘    │  - signatureAt
                                      │  - approvedByRiskAt
                                      │  - disbursementAt
                                      └──────────────┘

Flujo: PRE_APPROVED → DOCUMENTS_COMPLETED → APPROVED_BY_ADV 
       → APPROVED_BY_RISK → SIGNED → DISBURSED
       
Estados observación permiten retroceso para correcciones
```

---

## 14. Reglas de negocio importantes

1. **Transición de estados obligatoria:** No se puede saltar etapas del flujo de aprobación

2. **Estados finales inmutables:** DISBURSED, REJECTED_BY_RISK, PRE_APPROVED, AUTO_REJECTED no permiten cambios

3. **Validación de cliente:** Solo se puede crear un loan si el cliente existe en el sistema

4. **Registro automático de fechas:** 
   - `approvedByRiskAt` se registra al pasar a APPROVED_BY_RISK
   - `signatureAt` se registra al pasar a SIGNED
   - `disbursementAt` se registra al pasar a DISBURSED

5. **Eliminación restringida:** Solo se pueden eliminar préstamos que NO estén en estado final

6. **Actualización flexible:** Se puede actualizar cualquier campo del préstamo excepto el ID

7. **Sin relaciones JPA:** Todas las referencias a otras entidades son mediante Long IDs (patrón del proyecto)

---

## 15. ADR corto (Architectural Decision Record)

### ADR-001: Uso de IDs primitivos en lugar de relaciones JPA

- **Decisión:** Usar `Long clientId` en lugar de `@ManyToOne Client client`
- **Fecha:** 2026-01-03
- **Contexto:** El proyecto tiene una convención establecida de NO usar relaciones JPA para evitar problemas de lazy loading, N+1 queries y complejidad en DTOs
- **Alternativas consideradas:**
  - Usar `@ManyToOne` con fetch LAZY (descartado por convención del proyecto)
  - Usar `@OneToOne` para clinical data (descartado por misma razón)
- **Consecuencia:** 
  - ✅ Mayor control sobre queries
  - ✅ DTOs más simples
  - ✅ No hay problemas de lazy loading
  - ⚠️ Validación de integridad referencial en capa de servicio
  - ⚠️ No hay cascada automática de base de datos

---

### ADR-002: Renombrar columna "group" a "loan_group"

- **Decisión:** Usar `loan_group` en lugar de `"group"` escapado
- **Fecha:** 2026-01-03
- **Contexto:** `group` es palabra reservada en PostgreSQL (usado en GROUP BY), causaba errores de sintaxis SQL
- **Alternativas consideradas:**
  - Escapar con comillas: `"group"` (funciona pero es propenso a errores)
  - Renombrar a `loan_group` (elegido)
- **Consecuencia:**
  - ✅ No hay conflictos con palabras reservadas
  - ✅ Código más portable entre BD
  - ⚠️ Nombre de columna diferente al campo Java (`group` en entity, `loan_group` en BD)

---

### ADR-003: Simplificación de campos de auditoría

- **Decisión:** Mantener solo 5 timestamps esenciales (createdAt, updatedAt, signatureAt, approvedByRiskAt, disbursementAt)
- **Fecha:** 2026-01-03
- **Contexto:** Inicialmente se diseñó con 10 timestamps y 9 user IDs. El usuario solicitó simplificar a solo fechas críticas
- **Alternativas consideradas:**
  - Mantener todos los campos de auditoría (descartado por complejidad innecesaria)
  - Tabla separada de auditoría (sobrecarga para MVPç)
  - Solo timestamps esenciales (elegido)
- **Consecuencia:**
  - ✅ Modelo más simple y mantenible
  - ✅ Respuestas JSON más limpias
  - ⚠️ No se rastrea quién hizo cada cambio de estado (solo cuándo)
  - ⚠️ Se perdió trazabilidad detallada por usuario

---

### ADR-004: CQRS con Command y Query Services separados

- **Decisión:** Separar LoanCommandService (escritura) y LoanQueryService (lectura)
- **Fecha:** 2026-01-03
- **Contexto:** Aplicar patrón CQRS para separar responsabilidades
- **Alternativas consideradas:**
  - Servicio único (LoanService) con todos los métodos
  - CQRS completo con bases de datos separadas
  - CQRS simplificado (elegido)
- **Consecuencia:**
  - ✅ Separación clara de responsabilidades
  - ✅ Queries optimizadas con `@Transactional(readOnly = true)`
  - ✅ Escalabilidad futura (se pueden optimizar reads independientemente)
  - ⚠️ Más clases en el proyecto

---

## 16. Ejemplos reales de flujo

### Flujo 1: Creación y aprobación completa de un préstamo

**Input inicial - Crear préstamo:**
```http
POST /api/v1/loans
{
  "clientId": 123,
  "income": 8000.00,
  "quotaNumber": 24,
  "maf": 200.00,
  "createdByUserId": 1
}
```

**Estado:** PRE_APPROVED

---

**Paso 1 - Marcar documentos completos:**
```http
PUT /api/v1/loans/1/status
{
  "newStatus": "DOCUMENTS_COMPLETED",
  "userId": 1,
  "reason": "Documentos subidos y validados"
}
```

**Estado:** DOCUMENTS_COMPLETED

---

**Paso 2 - Aprobación por ADV:**
```http
PUT /api/v1/loans/1/status
{
  "newStatus": "APPROVED_BY_ADV",
  "userId": 2,
  "reason": "Cliente cumple perfil comercial"
}
```

**Estado:** APPROVED_BY_ADV

---

**Paso 3 - Aprobación por Riesgos:**
```http
PUT /api/v1/loans/1/status
{
  "newStatus": "APPROVED_BY_RISK",
  "userId": 3,
  "reason": "Score crediticio aprobado"
}
```

**Estado:** APPROVED_BY_RISK
**Registro automático:** `approvedByRiskAt` = timestamp actual

---

**Paso 4 - Firma de contrato:**
```http
PUT /api/v1/loans/1/status
{
  "newStatus": "SIGNED",
  "userId": 123,
  "reason": "Contrato firmado digitalmente"
}
```

**Estado:** SIGNED
**Registro automático:** `signatureAt` = timestamp actual

---

**Paso 5 - Desembolso:**
```http
PUT /api/v1/loans/1/status
{
  "newStatus": "DISBURSED",
  "userId": 4,
  "reason": "Transferencia ejecutada"
}
```

**Estado final:** DISBURSED
**Registro automático:** `disbursementAt` = timestamp actual

**Resultado:** Préstamo completado exitosamente. Ya no se permiten más cambios.

---

### Flujo 2: Préstamo observado y corregido

**Input inicial:**
```http
POST /api/v1/loans
{
  "clientId": 456,
  "income": 3000.00,
  "createdByUserId": 1
}
```

**Estado:** PENDING

---

**Paso 1:**
```http
PUT /api/v1/loans/2/status
{"newStatus": "DOCUMENTS_COMPLETED", "userId": 1}
```

**Estado:** DOCUMENTS_COMPLETED

---

**Paso 2 - ADV encuentra problemas:**
```http
PUT /api/v1/loans/2/status
{
  "newStatus": "OBSERVED_BY_ADV",
  "userId": 2,
  "reason": "Falta comprobante de ingresos adicional"
}
```

**Estado:** OBSERVED_BY_ADV (permite retroceder)

---

**Paso 3 - Cliente corrige y reenvía:**
```http
PUT /api/v1/loans/2/status
{
  "newStatus": "DOCUMENTS_COMPLETED",
  "userId": 1,
  "reason": "Documentos adicionales subidos"
}
```

**Estado:** DOCUMENTS_COMPLETED (retroceso permitido)

---

**Paso 4 - Segunda revisión ADV:**
```http
PUT /api/v1/loans/2/status
{
  "newStatus": "APPROVED_BY_ADV",
  "userId": 2,
  "reason": "Documentos ahora completos"
}
```

**Estado:** APPROVED_BY_ADV

---

**Paso 5 - Riesgos lo rechaza:**
```http
PUT /api/v1/loans/2/status
{
  "newStatus": "REJECTED_BY_RISK",
  "userId": 3,
  "reason": "Score crediticio insuficiente"
}
```

**Estado final:** REJECTED_BY_RISK

**Resultado:** Préstamo rechazado. No se permiten más cambios. Cliente no puede solicitar cambios sobre este préstamo.

---

## Suposiciones

1. **Usuarios externos:** Asumimos que existe un módulo de usuarios/autenticación que provee los `userId` usados en las operaciones

2. **Integración con Keynua:** Asumimos que la firma del contrato se gestiona externamente y solo actualizamos el estado cuando se confirma

3. **Desembolso:** Asumimos que otro sistema ejecuta la transferencia bancaria y notifica para actualizar el estado

4. **Validación de montos:** No hay validaciones de negocio sobre límites de `maf`, `income`, etc. (podrían agregarse)

5. **Rol de usuario:** No se valida que el usuario tenga permiso para aprobar en ADV o Riesgos (se asume gestión externa de permisos)
