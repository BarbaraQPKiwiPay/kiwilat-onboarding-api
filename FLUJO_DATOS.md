
## **Flujo de creación de datos (orden recomendado)**

### 1. **Catálogos y Ubigeo**
- **Departamentos, Provincias, Distritos**  
  Ya están precargados con el script data_ubigeo.sql.  
  **No necesitas crearlos manualmente.**

- **MedicalCategory, Clinic, ClinicBranch**  
  Si tu flujo requiere categorías médicas o clínicas, asegúrate de tenerlas creadas (pueden estar precargadas o tener endpoints para crearlas).

---

### 2. **Client (Cliente)**
- **Debes crear primero un Cliente**  
  - Endpoint: `/clients`
  - Requiere: datos personales y referencias a `departmentId`, `provinceId`, `districtId` (ubigeo).
  - **Guarda el `clientId`** que retorna la API.

---

### 3. **Patient (Paciente)**
- **Un Paciente está asociado a un Cliente**
  - Endpoint: `/patients`
  - Requiere: `clientId` (del cliente creado antes).
  - Puedes tener varios pacientes por cliente.

---

### 4. **Spouse (Cónyuge)**
- **El Cónyuge también está asociado a un Cliente**
  - Endpoint: `/spouses`
  - Requiere: `clientId` (del cliente creado antes).
  - Solo puedes tener un cónyuge por cliente.

---

### 5. **ClinicalData (Datos clínicos)**
- **ClinicalData asocia Cliente, Categoría Médica, Clínica y Sucursal**
  - Endpoint: `/clinical-data`
  - Requiere:  
    - `clientId` (del cliente creado antes)
    - `medicalCategoryId` (de catálogo)
    - `clinicId` y `branchId` (de catálogo)
  - **Debes tener creados los catálogos antes de asociar ClinicalData.**

---

## **Resumen visual del flujo**

```
[Ubigeo/Catálogos] → [Client] → [Patient]
                                 ↓
                             [Spouse]
                                 ↓
                          [ClinicalData]
```

---

## 🔵 **¿Qué debes tomar en cuenta?**

- **Siempre crea primero el Cliente** (es la entidad raíz).
- **Guarda el `clientId`** para asociar pacientes, cónyuge y clinical data.
- **Verifica que los catálogos (categorías, clínicas, sucursales) existan** antes de crear ClinicalData.
- **No puedes crear Patient, Spouse o ClinicalData sin un Client existente.**
