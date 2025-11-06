-- Seed Departments (UBIGEO department codes)
INSERT INTO department (id, name) VALUES ('01', 'Amazonas');
INSERT INTO department (id, name) VALUES ('02', 'Áncash');
INSERT INTO department (id, name) VALUES ('03', 'Apurímac');
INSERT INTO department (id, name) VALUES ('04', 'Arequipa');
INSERT INTO department (id, name) VALUES ('05', 'Ayacucho');
INSERT INTO department (id, name) VALUES ('06', 'Cajamarca');
INSERT INTO department (id, name) VALUES ('07', 'Callao');
INSERT INTO department (id, name) VALUES ('08', 'Cusco');
INSERT INTO department (id, name) VALUES ('09', 'Huancavelica');
INSERT INTO department (id, name) VALUES ('10', 'Huánuco');
INSERT INTO department (id, name) VALUES ('11', 'Ica');
INSERT INTO department (id, name) VALUES ('12', 'Junín');
INSERT INTO department (id, name) VALUES ('13', 'La Libertad');
INSERT INTO department (id, name) VALUES ('14', 'Lambayeque');
INSERT INTO department (id, name) VALUES ('15', 'Lima');
INSERT INTO department (id, name) VALUES ('16', 'Loreto');
INSERT INTO department (id, name) VALUES ('17', 'Madre de Dios');
INSERT INTO department (id, name) VALUES ('18', 'Moquegua');
INSERT INTO department (id, name) VALUES ('19', 'Pasco');
INSERT INTO department (id, name) VALUES ('20', 'Piura');
INSERT INTO department (id, name) VALUES ('21', 'Puno');
INSERT INTO department (id, name) VALUES ('22', 'San Martín');
INSERT INTO department (id, name) VALUES ('23', 'Tacna');
INSERT INTO department (id, name) VALUES ('24', 'Tumbes');
INSERT INTO department (id, name) VALUES ('25', 'Ucayali');

-- Sample Provinces (a small, representative set)
-- Province IDs use a 4-digit style: DDPP (department + province)
INSERT INTO province (id, name, department_id) VALUES ('1501', 'Lima', '15');
INSERT INTO province (id, name, department_id) VALUES ('1502', 'Barranca', '15');
INSERT INTO province (id, name, department_id) VALUES ('0801', 'Cusco', '08');
INSERT INTO province (id, name, department_id) VALUES ('0401', 'Arequipa', '04');
INSERT INTO province (id, name, department_id) VALUES ('2001', 'Piura', '20');

-- Sample Districts (a small, representative set)
-- District IDs use a 6-digit style: DDPPDD (department+province+district)
INSERT INTO district (id, name, province_id) VALUES ('150101', 'Lima (Cercado)', '1501');
INSERT INTO district (id, name, province_id) VALUES ('150102', 'Ancón', '1501');
INSERT INTO district (id, name, province_id) VALUES ('150103', 'Ate', '1501');
INSERT INTO district (id, name, province_id) VALUES ('080101', 'Cusco', '0801');
INSERT INTO district (id, name, province_id) VALUES ('040101', 'Arequipa', '0401');
INSERT INTO district (id, name, province_id) VALUES ('200101', 'Piura', '2001');

-- Medical Categories (Especialidades médicas)
INSERT INTO medical_category (id, name) VALUES ('CARDIO', 'Cardiología');
INSERT INTO medical_category (id, name) VALUES ('DERMATO', 'Dermatología');
INSERT INTO medical_category (id, name) VALUES ('GINECO', 'Ginecología');
INSERT INTO medical_category (id, name) VALUES ('NEUROL', 'Neurología');
INSERT INTO medical_category (id, name) VALUES ('ORTOPED', 'Ortopedia');
INSERT INTO medical_category (id, name) VALUES ('PEDIATR', 'Pediatría');
INSERT INTO medical_category (id, name) VALUES ('PSIQUIAT', 'Psiquiatría');
INSERT INTO medical_category (id, name) VALUES ('OFTALMOL', 'Oftalmología');

-- Clinics by category
INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-001', 'Clínica San Pablo', 'CARDIO');
INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-002', 'Clínica Anglo Americana', 'CARDIO');
INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-003', 'Instituto Nacional Cardiovascular', 'CARDIO');

INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-004', 'Clínica Dermatológica', 'DERMATO');
INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-005', 'Centro Dermatológico Especializado', 'DERMATO');

INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-006', 'Clínica Maison de Santé', 'GINECO');
INSERT INTO clinic (id, name, medical_category_id) VALUES ('CLN-007', 'Instituto Nacional Materno Perinatal', 'GINECO');

-- Clinic Branches
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-001', 'Sede San Borja', 'CLN-001');
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-002', 'Sede Surco', 'CLN-001');
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-003', 'Sede Chorrillos', 'CLN-001');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-004', 'Sede Miraflores', 'CLN-002');
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-005', 'Sede San Isidro', 'CLN-002');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-006', 'Sede Principal', 'CLN-003');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-007', 'Sede Lima Centro', 'CLN-004');
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-008', 'Sede Miraflores', 'CLN-004');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-009', 'Sede Surquillo', 'CLN-005');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-010', 'Sede Surco', 'CLN-006');
INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-011', 'Sede San Miguel', 'CLN-006');

INSERT INTO clinic_branch (id, name, clinic_id) VALUES ('BR-012', 'Sede Lima', 'CLN-007');

-- Note:
-- 1) This file seeds all 25 departments with correct UBIGEO department codes.
-- 2) Provinces and districts included are a small representative sample (Lima, Cusco, Arequipa, Piura).
-- 3) Medical categories, clinics and branches added for catalog functionality.
-- 4) If you want the complete UBIGEO dataset (all provinces & districts), I can add it —
--    it's large (~1800+ districts). We can either:
--      a) add a full `data.sql` here (large file), or
--      b) load from a CSV/JSON import routine, or
--      c) accept a provided UBIGEO file and I'll create an importer.
