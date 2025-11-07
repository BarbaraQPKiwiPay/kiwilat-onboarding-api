
CREATE TABLE department(
	id VARCHAR(2) NOT NULL PRIMARY KEY ,
	name VARCHAR(45) NOT NULL
);

CREATE TABLE province(
	id VARCHAR(4) NOT NULL PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	department_id VARCHAR(2) NOT NULL
);

CREATE TABLE district(
    id VARCHAR(6) NOT NULL PRIMARY KEY,
	name VARCHAR(45) NULL,
	province_id VARCHAR(4) NULL,
	department_id VARCHAR(2) NULL
);


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

INSERT INTO department (id, name) VALUES ('12', 'Juní');

INSERT INTO department (id, name) VALUES ('13', 'La Libertad');

INSERT INTO department (id, name) VALUES ('14', 'Lambayeque');

INSERT INTO department (id, name) VALUES ('15', 'Lima');

INSERT INTO department (id, name) VALUES ('16', 'Loreto');

INSERT INTO department (id, name) VALUES ('17', 'Madre de Dios');

INSERT INTO department (id, name) VALUES ('18', 'Moquegua');

INSERT INTO department (id, name) VALUES ('19', 'Pasco');

INSERT INTO department (id, name) VALUES ('20', 'Piura');

INSERT INTO department (id, name) VALUES ('21', 'Puno');

INSERT INTO department (id, name) VALUES ('22', 'San Martí');

INSERT INTO department (id, name) VALUES ('23', 'Tacna');

INSERT INTO department (id, name) VALUES ('24', 'Tumbes');

INSERT INTO department (id, name) VALUES ('25', 'Ucayali');

INSERT INTO province (id, name, department_id) VALUES ('0101', 'Chachapoyas', '01');

INSERT INTO province (id, name, department_id) VALUES ('0102', 'Bagua', '01');

INSERT INTO province (id, name, department_id) VALUES ('0103', 'Bongará', '01');

INSERT INTO province (id, name, department_id) VALUES ('0104', 'Condorcanqui', '01');

INSERT INTO province (id, name, department_id) VALUES ('0105', 'Luya', '01');

INSERT INTO province (id, name, department_id) VALUES ('0106', 'Rodríguez de Mendoza', '01');

INSERT INTO province (id, name, department_id) VALUES ('0107', 'Utcubamba', '01');

INSERT INTO province (id, name, department_id) VALUES ('0201', 'Huaraz', '02');

INSERT INTO province (id, name, department_id) VALUES ('0202', 'Aija', '02');

INSERT INTO province (id, name, department_id) VALUES ('0203', 'Antonio Raymondi', '02');

INSERT INTO province (id, name, department_id) VALUES ('0204', 'Asunció', '02');

INSERT INTO province (id, name, department_id) VALUES ('0205', 'Bolognesi', '02');

INSERT INTO province (id, name, department_id) VALUES ('0206', 'Carhuaz', '02');

INSERT INTO province (id, name, department_id) VALUES ('0207', 'Carlos Fermín Fitzcarrald', '02');

INSERT INTO province (id, name, department_id) VALUES ('0208', 'Casma', '02');

INSERT INTO province (id, name, department_id) VALUES ('0209', 'Coro', '02');

INSERT INTO province (id, name, department_id) VALUES ('0210', 'Huari', '02');

INSERT INTO province (id, name, department_id) VALUES ('0211', 'Huarmey', '02');

INSERT INTO province (id, name, department_id) VALUES ('0212', 'Huaylas', '02');

INSERT INTO province (id, name, department_id) VALUES ('0213', 'Mariscal Luzuriaga', '02');

INSERT INTO province (id, name, department_id) VALUES ('0214', 'Ocros', '02');

INSERT INTO province (id, name, department_id) VALUES ('0215', 'Pallasca', '02');

INSERT INTO province (id, name, department_id) VALUES ('0216', 'Pomabamba', '02');

INSERT INTO province (id, name, department_id) VALUES ('0217', 'Recuay', '02');

INSERT INTO province (id, name, department_id) VALUES ('0218', 'Santa', '02');

INSERT INTO province (id, name, department_id) VALUES ('0219', 'Sihuas', '02');

INSERT INTO province (id, name, department_id) VALUES ('0220', 'Yungay', '02');

INSERT INTO province (id, name, department_id) VALUES ('0301', 'Abancay', '03');

INSERT INTO province (id, name, department_id) VALUES ('0302', 'Andahuaylas', '03');

INSERT INTO province (id, name, department_id) VALUES ('0303', 'Antabamba', '03');

INSERT INTO province (id, name, department_id) VALUES ('0304', 'Aymaraes', '03');

INSERT INTO province (id, name, department_id) VALUES ('0305', 'Cotabambas', '03');

INSERT INTO province (id, name, department_id) VALUES ('0306', 'Chincheros', '03');

INSERT INTO province (id, name, department_id) VALUES ('0307', 'Grau', '03');

INSERT INTO province (id, name, department_id) VALUES ('0401', 'Arequipa', '04');

INSERT INTO province (id, name, department_id) VALUES ('0402', 'Camaná', '04');

INSERT INTO province (id, name, department_id) VALUES ('0403', 'Caravelí', '04');

INSERT INTO province (id, name, department_id) VALUES ('0404', 'Castilla', '04');

INSERT INTO province (id, name, department_id) VALUES ('0405', 'Caylloma', '04');

INSERT INTO province (id, name, department_id) VALUES ('0406', 'Condesuyos', '04');

INSERT INTO province (id, name, department_id) VALUES ('0407', 'Islay', '04');

INSERT INTO province (id, name, department_id) VALUES ('0408', 'La Uniò', '04');

INSERT INTO province (id, name, department_id) VALUES ('0501', 'Huamanga', '05');

INSERT INTO province (id, name, department_id) VALUES ('0502', 'Cangallo', '05');

INSERT INTO province (id, name, department_id) VALUES ('0503', 'Huanca Sancos', '05');

INSERT INTO province (id, name, department_id) VALUES ('0504', 'Huanta', '05');

INSERT INTO province (id, name, department_id) VALUES ('0505', 'La Mar', '05');

INSERT INTO province (id, name, department_id) VALUES ('0506', 'Lucanas', '05');

INSERT INTO province (id, name, department_id) VALUES ('0507', 'Parinacochas', '05');

INSERT INTO province (id, name, department_id) VALUES ('0508', 'Pàucar del Sara Sara', '05');

INSERT INTO province (id, name, department_id) VALUES ('0509', 'Sucre', '05');

INSERT INTO province (id, name, department_id) VALUES ('0510', 'Víctor Fajardo', '05');

INSERT INTO province (id, name, department_id) VALUES ('0511', 'Vilcas Huamá', '05');

INSERT INTO province (id, name, department_id) VALUES ('0601', 'Cajamarca', '06');

INSERT INTO province (id, name, department_id) VALUES ('0602', 'Cajabamba', '06');

INSERT INTO province (id, name, department_id) VALUES ('0603', 'Celendí', '06');

INSERT INTO province (id, name, department_id) VALUES ('0604', 'Chota', '06');

INSERT INTO province (id, name, department_id) VALUES ('0605', 'Contumazá', '06');

INSERT INTO province (id, name, department_id) VALUES ('0606', 'Cutervo', '06');

INSERT INTO province (id, name, department_id) VALUES ('0607', 'Hualgayoc', '06');

INSERT INTO province (id, name, department_id) VALUES ('0608', 'Jaé', '06');

INSERT INTO province (id, name, department_id) VALUES ('0609', 'San Ignacio', '06');

INSERT INTO province (id, name, department_id) VALUES ('0610', 'San Marcos', '06');

INSERT INTO province (id, name, department_id) VALUES ('0611', 'San Miguel', '06');

INSERT INTO province (id, name, department_id) VALUES ('0612', 'San Pablo', '06');

INSERT INTO province (id, name, department_id) VALUES ('0613', 'Santa Cruz', '06');

INSERT INTO province (id, name, department_id) VALUES ('0701', 'Prov. Const. del Callao', '07');

INSERT INTO province (id, name, department_id) VALUES ('0801', 'Cusco', '08');

INSERT INTO province (id, name, department_id) VALUES ('0802', 'Acomayo', '08');

INSERT INTO province (id, name, department_id) VALUES ('0803', 'Anta', '08');

INSERT INTO province (id, name, department_id) VALUES ('0804', 'Calca', '08');

INSERT INTO province (id, name, department_id) VALUES ('0805', 'Canas', '08');

INSERT INTO province (id, name, department_id) VALUES ('0806', 'Canchis', '08');

INSERT INTO province (id, name, department_id) VALUES ('0807', 'Chumbivilcas', '08');

INSERT INTO province (id, name, department_id) VALUES ('0808', 'Espinar', '08');

INSERT INTO province (id, name, department_id) VALUES ('0809', 'La Convenció', '08');

INSERT INTO province (id, name, department_id) VALUES ('0810', 'Paruro', '08');

INSERT INTO province (id, name, department_id) VALUES ('0811', 'Paucartambo', '08');

INSERT INTO province (id, name, department_id) VALUES ('0812', 'Quispicanchi', '08');

INSERT INTO province (id, name, department_id) VALUES ('0813', 'Urubamba', '08');

INSERT INTO province (id, name, department_id) VALUES ('0901', 'Huancavelica', '09');

INSERT INTO province (id, name, department_id) VALUES ('0902', 'Acobamba', '09');

INSERT INTO province (id, name, department_id) VALUES ('0903', 'Angaraes', '09');

INSERT INTO province (id, name, department_id) VALUES ('0904', 'Castrovirreyna', '09');

INSERT INTO province (id, name, department_id) VALUES ('0905', 'Churcampa', '09');

INSERT INTO province (id, name, department_id) VALUES ('0906', 'Huaytará', '09');

INSERT INTO province (id, name, department_id) VALUES ('0907', 'Tayacaja', '09');

INSERT INTO province (id, name, department_id) VALUES ('1001', 'Huánuco', '10');

INSERT INTO province (id, name, department_id) VALUES ('1002', 'Ambo', '10');

INSERT INTO province (id, name, department_id) VALUES ('1003', 'Dos de Mayo', '10');

INSERT INTO province (id, name, department_id) VALUES ('1004', 'Huacaybamba', '10');

INSERT INTO province (id, name, department_id) VALUES ('1005', 'Huamalíes', '10');

INSERT INTO province (id, name, department_id) VALUES ('1006', 'Leoncio Prado', '10');

INSERT INTO province (id, name, department_id) VALUES ('1007', 'Marañó', '10');

INSERT INTO province (id, name, department_id) VALUES ('1008', 'Pachitea', '10');

INSERT INTO province (id, name, department_id) VALUES ('1009', 'Puerto Inca', '10');

INSERT INTO province (id, name, department_id) VALUES ('1010', 'Lauricocha ', '10');

INSERT INTO province (id, name, department_id) VALUES ('1011', 'Yarowilca ', '10');

INSERT INTO province (id, name, department_id) VALUES ('1101', 'Ica ', '11');

INSERT INTO province (id, name, department_id) VALUES ('1102', 'Chincha ', '11');

INSERT INTO province (id, name, department_id) VALUES ('1103', 'Nasca ', '11');

INSERT INTO province (id, name, department_id) VALUES ('1104', 'Palpa ', '11');

INSERT INTO province (id, name, department_id) VALUES ('1105', 'Pisco ', '11');

INSERT INTO province (id, name, department_id) VALUES ('1201', 'Huancayo ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1202', 'Concepción ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1203', 'Chanchamayo ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1204', 'Jauja ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1205', 'Junín ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1206', 'Satipo ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1207', 'Tarma ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1208', 'Yauli ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1209', 'Chupaca ', '12');

INSERT INTO province (id, name, department_id) VALUES ('1301', 'Trujillo ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1302', 'Ascope ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1303', 'Bolívar ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1304', 'Chepén ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1305', 'Julcán ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1306', 'Otuzco ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1307', 'Pacasmayo ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1308', 'Pataz ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1309', 'Sánchez Carrión ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1310', 'Santia de Chuco ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1311', 'Gran Chimú ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1312', 'Virú ', '13');

INSERT INTO province (id, name, department_id) VALUES ('1401', 'Chiclayo ', '14');

INSERT INTO province (id, name, department_id) VALUES ('1402', 'Ferreñafe ', '14');

INSERT INTO province (id, name, department_id) VALUES ('1403', 'Lambayeque ', '14');

INSERT INTO province (id, name, department_id) VALUES ('1501', 'Lima ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1502', 'Barranca ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1503', 'Cajatambo ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1504', 'Canta ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1505', 'Cañete ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1506', 'Huaral ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1507', 'Huarochirí ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1508', 'Huaura ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1509', 'Oyón ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1510', 'Yauyos ', '15');

INSERT INTO province (id, name, department_id) VALUES ('1601', 'Maynas ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1602', 'Alto Amazonas ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1603', 'Loreto ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1604', 'Mariscal Ramón Castilla ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1605', 'Requena ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1606', 'Ucayali ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1607', 'Datem del Marañón ', '16');

INSERT INTO province (id, name, department_id) VALUES ('1608', 'Putumayo', '16');

INSERT INTO province (id, name, department_id) VALUES ('1701', 'Tambopata ', '17');

INSERT INTO province (id, name, department_id) VALUES ('1702', 'Manu ', '17');

INSERT INTO province (id, name, department_id) VALUES ('1703', 'Tahuamanu ', '17');

INSERT INTO province (id, name, department_id) VALUES ('1801', 'Mariscal Nieto ', '18');

INSERT INTO province (id, name, department_id) VALUES ('1802', 'General Sánchez Cerro ', '18');

INSERT INTO province (id, name, department_id) VALUES ('1803', 'Ilo ', '18');

INSERT INTO province (id, name, department_id) VALUES ('1901', 'Pasco ', '19');

INSERT INTO province (id, name, department_id) VALUES ('1902', 'Daniel Alcides Carrión ', '19');

INSERT INTO province (id, name, department_id) VALUES ('1903', 'Oxapampa ', '19');

INSERT INTO province (id, name, department_id) VALUES ('2001', 'Piura ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2002', 'Ayabaca ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2003', 'Huancabamba ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2004', 'Morropón ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2005', 'Paita ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2006', 'Sullana ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2007', 'Talara ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2008', 'Sechura ', '20');

INSERT INTO province (id, name, department_id) VALUES ('2101', 'Puno ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2102', 'Azángaro ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2103', 'Carabaya ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2104', 'Chucuito ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2105', 'El Collao ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2106', 'Huancané ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2107', 'Lampa ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2108', 'Melgar ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2109', 'Moho ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2110', 'San Antonio de Putina ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2111', 'San Román ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2112', 'Sandia ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2113', 'Yunguyo ', '21');

INSERT INTO province (id, name, department_id) VALUES ('2201', 'Moyobamba ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2202', 'Bellavista ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2203', 'El Dorado ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2204', 'Huallaga ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2205', 'Lamas ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2206', 'Mariscal Cáceres ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2207', 'Picota ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2208', 'Rioja ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2209', 'San Martín ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2210', 'Tocache ', '22');

INSERT INTO province (id, name, department_id) VALUES ('2301', 'Tacna ', '23');

INSERT INTO province (id, name, department_id) VALUES ('2302', 'Candarave ', '23');

INSERT INTO province (id, name, department_id) VALUES ('2303', 'Jorge Basadre ', '23');

INSERT INTO province (id, name, department_id) VALUES ('2304', 'Tarata ', '23');

INSERT INTO province (id, name, department_id) VALUES ('2401', 'Tumbes ', '24');

INSERT INTO province (id, name, department_id) VALUES ('2402', 'Contralmirante Villar ', '24');

INSERT INTO province (id, name, department_id) VALUES ('2403', 'Zarumilla ', '24');

INSERT INTO province (id, name, department_id) VALUES ('2501', 'Coronel Portillo ', '25');

INSERT INTO province (id, name, department_id) VALUES ('2502', 'Atalaya ', '25');

INSERT INTO province (id, name, department_id) VALUES ('2503', 'Padre Abad ', '25');

INSERT INTO province (id, name, department_id) VALUES ('2504', 'Purús', '25');


INSERT INTO district (id, name, province_id, department_id) VALUES ('010101', 'Chachapoyas', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010102', 'Asunció', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010103', 'Balsas', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010104', 'Cheto', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010105', 'Chiliqui', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010106', 'Chuquibamba', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010107', 'Granada', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010108', 'Huancas', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010109', 'La Jalca', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010110', 'Leimebamba', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010111', 'Levanto', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010112', 'Magdalena', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010113', 'Mariscal Castilla', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010114', 'Molinopampa', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010115', 'Montevideo', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010116', 'Olleros', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010117', 'Quinjalca', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010118', 'San Francisco de Daguas', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010119', 'San Isidro de Maino', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010120', 'Soloco', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010121', 'Sonche', '0101', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010201', 'Bagua', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010202', 'Arama', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010203', 'Copalli', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010204', 'El Parco', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010205', 'Imaza', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010206', 'La Peca', '0102', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010301', 'Jumbilla', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010302', 'Chisquilla', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010303', 'Churuja', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010304', 'Corosha', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010305', 'Cuispes', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010306', 'Florida', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010307', 'Jaza', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010308', 'Recta', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010309', 'San Carlos', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010310', 'Shipasbamba', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010311', 'Valera', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010312', 'Yambrasbamba', '0103', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010401', 'Nieva', '0104', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010402', 'El Cenepa', '0104', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010403', 'Río Santia', '0104', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010501', 'Lamud', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010502', 'Camporredondo', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010503', 'Cocabamba', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010504', 'Colcamar', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010505', 'Conila', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010506', 'Inguilpata', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010507', 'Longuita', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010508', 'Lonya Chico', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010509', 'Luya', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010510', 'Luya Viejo', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010511', 'María', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010512', 'Ocalli', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010513', 'Ocumal', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010514', 'Pisuquia', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010515', 'Providencia', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010516', 'San Cristóbal', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010517', 'San Francisco de Yeso', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010518', 'San Jerónimo', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010519', 'San Juan de Lopecancha', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010520', 'Santa Catalina', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010521', 'Santo Tomas', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010522', 'Ti', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010523', 'Trita', '0105', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010601', 'San Nicolás', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010602', 'Chirimoto', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010603', 'Cochamal', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010604', 'Huambo', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010605', 'Limabamba', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010606', 'Longar', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010607', 'Mariscal Benavides', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010608', 'Milpuc', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010609', 'Omia', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010610', 'Santa Rosa', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010611', 'Totora', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010612', 'Vista Alegre', '0106', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010701', 'Bagua Grande', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010702', 'Cajaruro', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010703', 'Cumba', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010704', 'El Milagro', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010705', 'Jamalca', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010706', 'Lonya Grande', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('010707', 'Yamo', '0107', '01');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020101', 'Huaraz', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020102', 'Cochabamba', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020103', 'Colcabamba', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020104', 'Huanchay', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020105', 'Independencia', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020106', 'Jangas', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020107', 'La Libertad', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020108', 'Olleros', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020109', 'Pampas Grande', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020110', 'Pariacoto', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020111', 'Pira', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020112', 'Tarica', '0201', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020201', 'Aija', '0202', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020202', 'Coris', '0202', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020203', 'Huaclla', '0202', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020204', 'La Merced', '0202', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020205', 'Succha', '0202', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020301', 'Llamelli', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020302', 'Aczo', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020303', 'Chaccho', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020304', 'Chingas', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020305', 'Mirgas', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020306', 'San Juan de Rontoy', '0203', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020401', 'Chacas', '0204', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020402', 'Acochaca', '0204', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020501', 'Chiquia', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020502', 'Abelardo Pardo Lezameta', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020503', 'Antonio Raymondi', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020504', 'Aquia', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020505', 'Cajacay', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020506', 'Canis', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020507', 'Colquioc', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020508', 'Huallanca', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020509', 'Huasta', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020510', 'Huayllacaya', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020511', 'La Primavera', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020512', 'Mangas', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020513', 'Pacllo', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020514', 'San Miguel de Corpanqui', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020515', 'Ticllos', '0205', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020601', 'Carhuaz', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020602', 'Acopampa', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020603', 'Amashca', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020604', 'Anta', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020605', 'Ataquero', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020606', 'Marcara', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020607', 'Pariahuanca', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020608', 'San Miguel de Aco', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020609', 'Shilla', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020610', 'Tinco', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020611', 'Yungar', '0206', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020701', 'San Luis', '0207', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020702', 'San Nicolás', '0207', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020703', 'Yauya', '0207', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020801', 'Casma', '0208', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020802', 'Buena Vista Alta', '0208', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020803', 'Comandante Noel', '0208', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020804', 'Yauta', '0208', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020901', 'Coro', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020902', 'Aco', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020903', 'Bambas', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020904', 'Cusca', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020905', 'La Pampa', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020906', 'Yanac', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('020907', 'Yupa', '0209', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021001', 'Huari', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021002', 'Anra', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021003', 'Cajay', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021004', 'Chavin de Huantar', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021005', 'Huacachi', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021006', 'Huacchis', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021007', 'Huachis', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021008', 'Huantar', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021009', 'Masi', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021010', 'Paucas', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021011', 'Ponto', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021012', 'Rahuapampa', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021013', 'Rapaya', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021014', 'San Marcos', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021015', 'San Pedro de Chana', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021016', 'Uco', '0210', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021101', 'Huarmey', '0211', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021102', 'Cochapeti', '0211', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021103', 'Culebras', '0211', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021104', 'Huaya', '0211', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021105', 'Malvas', '0211', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021201', 'Caraz', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021202', 'Huallanca', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021203', 'Huata', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021204', 'Huaylas', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021205', 'Mato', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021206', 'Pamparomas', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021207', 'Pueblo Libre', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021208', 'Santa Cruz', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021209', 'Santo Toribio', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021210', 'Yuracmarca', '0212', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021301', 'Piscobamba', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021302', 'Casca', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021303', 'Eleazar Guzmán Barro', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021304', 'Fidel Olivas Escudero', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021305', 'Llama', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021306', 'Llumpa', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021307', 'Lucma', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021308', 'Musga', '0213', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021401', 'Ocros', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021402', 'Acas', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021403', 'Cajamarquilla', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021404', 'Carhuapampa', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021405', 'Cochas', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021406', 'Congas', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021407', 'Llipa', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021408', 'San Cristóbal de Raja', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021409', 'San Pedro', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021410', 'Santia de Chilcas', '0214', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021501', 'Cabana', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021502', 'Bolognesi', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021503', 'Conchucos', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021504', 'Huacaschuque', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021505', 'Huandoval', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021506', 'Lacabamba', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021507', 'Llapo', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021508', 'Pallasca', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021509', 'Pampas', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021510', 'Santa Rosa', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021511', 'Tauca', '0215', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021601', 'Pomabamba', '0216', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021602', 'Huaylla', '0216', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021603', 'Parobamba', '0216', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021604', 'Quinuabamba', '0216', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021701', 'Recuay', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021702', 'Catac', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021703', 'Cotaparaco', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021704', 'Huayllapampa', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021705', 'Llaclli', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021706', 'Marca', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021707', 'Pampas Chico', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021708', 'Parari', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021709', 'Tapacocha', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021710', 'Ticapampa', '0217', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021801', 'Chimbote', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021802', 'Cáceres del Perú', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021803', 'Coishco', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021804', 'Macate', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021805', 'Moro', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021806', 'Nepeña', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021807', 'Samanco', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021808', 'Santa', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021809', 'Nuevo Chimbote', '0218', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021901', 'Sihuas', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021902', 'Acobamba', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021903', 'Alfonso Ugarte', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021904', 'Cashapampa', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021905', 'Chingalpo', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021906', 'Huayllabamba', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021907', 'Quiches', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021908', 'Ragash', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021909', 'San Jua', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('021910', 'Sicsibamba', '0219', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022001', 'Yungay', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022002', 'Cascapara', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022003', 'Mancos', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022004', 'Matacoto', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022005', 'Quillo', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022006', 'Ranrahirca', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022007', 'Shupluy', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('022008', 'Yanama', '0220', '02');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030101', 'Abancay', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030102', 'Chacoche', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030103', 'Circa', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030104', 'Curahuasi', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030105', 'Huanipaca', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030106', 'Lambrama', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030107', 'Pichirhua', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030108', 'San Pedro de Cachora', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030109', 'Tamburco', '0301', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030201', 'Andahuaylas', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030202', 'Andarapa', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030203', 'Chiara', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030204', 'Huancarama', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030205', 'Huancaray', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030206', 'Huayana', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030207', 'Kishuara', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030208', 'Pacobamba', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030209', 'Pacucha', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030210', 'Pampachiri', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030211', 'Pomacocha', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030212', 'San Antonio de Cachi', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030213', 'San Jerónimo', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030214', 'San Miguel de Chaccrampa', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030215', 'Santa María de Chicmo', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030216', 'Talavera', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030217', 'Tumay Huaraca', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030218', 'Turpo', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030219', 'Kaquiabamba', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030220', 'José María Arguedas', '0302', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030301', 'Antabamba', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030302', 'El Oro', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030303', 'Huaquirca', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030304', 'Juan Espinoza Medrano', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030305', 'Oropesa', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030306', 'Pachaconas', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030307', 'Sabaino', '0303', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030401', 'Chalhuanca', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030402', 'Capaya', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030403', 'Caraybamba', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030404', 'Chapimarca', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030405', 'Colcabamba', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030406', 'Cotaruse', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030407', 'Ihuayllo', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030408', 'Justo Apu Sahuaraura', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030409', 'Lucre', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030410', 'Pocohuanca', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030411', 'San Juan de Chacña', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030412', 'Sañayca', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030413', 'Soraya', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030414', 'Tapairihua', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030415', 'Tintay', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030416', 'Toraya', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030417', 'Yanaca', '0304', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030501', 'Tambobamba', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030502', 'Cotabambas', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030503', 'Coyllurqui', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030504', 'Haquira', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030505', 'Mara', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030506', 'Challhuahuacho', '0305', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030601', 'Chincheros', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030602', 'Anco_Huallo', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030603', 'Cocharcas', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030604', 'Huaccana', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030605', 'Ocobamba', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030606', 'Ony', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030607', 'Uranmarca', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030608', 'Ranracancha', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030609', 'Rocchacc', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030610', 'El Porvenir', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030611', 'Los Chankas', '0306', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030701', 'Chuquibambilla', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030702', 'Curpahuasi', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030703', 'Gamarra', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030704', 'Huayllati', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030705', 'Mamara', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030706', 'Micaela Bastidas', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030707', 'Pataypampa', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030708', 'Progreso', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030709', 'San Antonio', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030710', 'Santa Rosa', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030711', 'Turpay', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030712', 'Vilcabamba', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030713', 'Virundo', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('030714', 'Curasco', '0307', '03');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040101', 'Arequipa', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040102', 'Alto Selva Alegre', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040103', 'Cayma', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040104', 'Cerro Colorado', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040105', 'Characato', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040106', 'Chiguata', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040107', 'Jacobo Hunter', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040108', 'La Joya', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040109', 'Mariano Melgar', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040110', 'Miraflores', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040111', 'Mollebaya', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040112', 'Paucarpata', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040113', 'Pocsi', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040114', 'Polobaya', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040115', 'Quequeña', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040116', 'Sabandia', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040117', 'Sachaca', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040118', 'San Juan de Siguas', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040119', 'San Juan de Tarucani', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040120', 'Santa Isabel de Siguas', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040121', 'Santa Rita de Siguas', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040122', 'Socabaya', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040123', 'Tiabaya', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040124', 'Uchumayo', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040125', 'Vitor', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040126', 'Yanahuara', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040127', 'Yarabamba', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040128', 'Yura', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040129', 'José Luis Bustamante Y Rivero', '0401', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040201', 'Camaná', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040202', 'José María Quimper', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040203', 'Mariano Nicolás Valcárcel', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040204', 'Mariscal Cáceres', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040205', 'Nicolás de Pierola', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040206', 'Ocoña', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040207', 'Quilca', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040208', 'Samuel Pastor', '0402', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040301', 'Caravelí', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040302', 'Acarí', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040303', 'Atico', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040304', 'Atiquipa', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040305', 'Bella Unió', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040306', 'Cahuacho', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040307', 'Chala', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040308', 'Chaparra', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040309', 'Huanuhuanu', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040310', 'Jaqui', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040311', 'Lomas', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040312', 'Quicacha', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040313', 'Yauca', '0403', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040401', 'Aplao', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040402', 'Andagua', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040403', 'Ayo', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040404', 'Chachas', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040405', 'Chilcaymarca', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040406', 'Choco', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040407', 'Huancarqui', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040408', 'Machaguay', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040409', 'Orcopampa', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040410', 'Pampacolca', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040411', 'Tipa', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040412', 'Uño', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040413', 'Uraca', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040414', 'Viraco', '0404', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040501', 'Chivay', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040502', 'Achoma', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040503', 'Cabanaconde', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040504', 'Callalli', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040505', 'Caylloma', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040506', 'Coporaque', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040507', 'Huambo', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040508', 'Huanca', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040509', 'Ichupampa', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040510', 'Lari', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040511', 'Lluta', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040512', 'Maca', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040513', 'Madrigal', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040514', 'San Antonio de Chuca', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040515', 'Sibayo', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040516', 'Tapay', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040517', 'Tisco', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040518', 'Tuti', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040519', 'Yanque', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040520', 'Majes', '0405', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040601', 'Chuquibamba', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040602', 'Andaray', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040603', 'Cayarani', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040604', 'Chichas', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040605', 'Iray', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040606', 'Río Grande', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040607', 'Salamanca', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040608', 'Yanaquihua', '0406', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040701', 'Mollendo', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040702', 'Cocachacra', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040703', 'Dean Valdivia', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040704', 'Islay', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040705', 'Mejia', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040706', 'Punta de Bombó', '0407', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040801', 'Cotahuasi', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040802', 'Alca', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040803', 'Charcana', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040804', 'Huaynacotas', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040805', 'Pampamarca', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040806', 'Puyca', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040807', 'Quechualla', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040808', 'Sayla', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040809', 'Tauria', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040810', 'Tomepampa', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('040811', 'Toro', '0408', '04');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050101', 'Ayacucho', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050102', 'Acocro', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050103', 'Acos Vinchos', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050104', 'Carmen Alto', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050105', 'Chiara', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050106', 'Ocros', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050107', 'Pacaycasa', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050108', 'Quinua', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050109', 'San José de Ticllas', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050110', 'San Juan Bautista', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050111', 'Santia de Pischa', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050112', 'Socos', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050113', 'Tambillo', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050114', 'Vinchos', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050115', 'Jesús Nazareno', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050116', 'Andrés Avelino Cáceres Dorregaray', '0501', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050201', 'Cangallo', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050202', 'Chuschi', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050203', 'Los Morochucos', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050204', 'María Parado de Bellido', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050205', 'Paras', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050206', 'Totos', '0502', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050301', 'Sancos', '0503', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050302', 'Carapo', '0503', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050303', 'Sacsamarca', '0503', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050304', 'Santia de Lucanamarca', '0503', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050401', 'Huanta', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050402', 'Ayahuanco', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050403', 'Huamanguilla', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050404', 'Iguai', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050405', 'Luricocha', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050406', 'Santillana', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050407', 'Sivia', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050408', 'Llochegua', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050409', 'Canayre', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050410', 'Uchuraccay', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050411', 'Pucacolpa', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050412', 'Chaca', '0504', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050501', 'San Miguel', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050502', 'Anco', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050503', 'Ayna', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050504', 'Chilcas', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050505', 'Chungui', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050506', 'Luis Carranza', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050507', 'Santa Rosa', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050508', 'Tambo', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050509', 'Samugari', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050510', 'Anchihuay', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050511', 'Oronccoy', '0505', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050601', 'Puquio', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050602', 'Aucara', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050603', 'Cabana', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050604', 'Carmen Salcedo', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050605', 'Chaviña', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050606', 'Chipao', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050607', 'Huac-Huas', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050608', 'Laramate', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050609', 'Leoncio Prado', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050610', 'Llauta', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050611', 'Lucanas', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050612', 'Ocaña', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050613', 'Otoca', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050614', 'Saisa', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050615', 'San Cristóbal', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050616', 'San Jua', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050617', 'San Pedro', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050618', 'San Pedro de Palco', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050619', 'Sancos', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050620', 'Santa Ana de Huaycahuacho', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050621', 'Santa Lucia', '0506', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050701', 'Coracora', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050702', 'Chumpi', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050703', 'Coronel Castañeda', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050704', 'Pacapausa', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050705', 'Pullo', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050706', 'Puyusca', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050707', 'San Francisco de Ravacayco', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050708', 'Upahuacho', '0507', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050801', 'Pausa', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050802', 'Colta', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050803', 'Corculla', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050804', 'Lampa', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050805', 'Marcabamba', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050806', 'Oyolo', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050807', 'Pararca', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050808', 'San Javier de Alpabamba', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050809', 'San José de Ushua', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050810', 'Sara Sara', '0508', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050901', 'Querobamba', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050902', 'Belé', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050903', 'Chalcos', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050904', 'Chilcayoc', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050905', 'Huacaña', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050906', 'Morcolla', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050907', 'Paico', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050908', 'San Pedro de Larcay', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050909', 'San Salvador de Quije', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050910', 'Santia de Paucaray', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('050911', 'Soras', '0509', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051001', 'Huancapi', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051002', 'Alcamenca', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051003', 'Apo', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051004', 'Asquipata', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051005', 'Canaria', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051006', 'Cayara', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051007', 'Colca', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051008', 'Huamanquiquia', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051009', 'Huancaraylla', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051010', 'Hualla', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051011', 'Sarhua', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051012', 'Vilcanchos', '0510', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051101', 'Vilcas Huama', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051102', 'Accomarca', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051103', 'Carhuanca', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051104', 'Concepció', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051105', 'Huambalpa', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051106', 'Independencia', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051107', 'Saurama', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('051108', 'Vischo', '0511', '05');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060101', 'Cajamarca', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060102', 'Asunció', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060103', 'Chetilla', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060104', 'Cospa', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060105', 'Encañada', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060106', 'Jesús', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060107', 'Llacanora', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060108', 'Los Baños del Inca', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060109', 'Magdalena', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060110', 'Matara', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060111', 'Namora', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060112', 'San Jua', '0601', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060201', 'Cajabamba', '0602', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060202', 'Cachachi', '0602', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060203', 'Condebamba', '0602', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060204', 'Sitacocha', '0602', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060301', 'Celendí', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060302', 'Chumuch', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060303', 'Cortegana', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060304', 'Huasmi', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060305', 'Jorge Chávez', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060306', 'José Gálvez', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060307', 'Miguel Iglesias', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060308', 'Oxamarca', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060309', 'Sorochuco', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060310', 'Sucre', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060311', 'Utco', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060312', 'La Libertad de Palla', '0603', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060401', 'Chota', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060402', 'Anguia', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060403', 'Chadi', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060404', 'Chiguirip', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060405', 'Chimba', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060406', 'Choropampa', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060407', 'Cochabamba', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060408', 'Concha', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060409', 'Huambos', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060410', 'Lajas', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060411', 'Llama', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060412', 'Miracosta', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060413', 'Paccha', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060414', 'Pio', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060415', 'Querocoto', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060416', 'San Juan de Licupis', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060417', 'Tacabamba', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060418', 'Tocmoche', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060419', 'Chalamarca', '0604', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060501', 'Contumaza', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060502', 'Chilete', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060503', 'Cupisnique', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060504', 'Guzma', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060505', 'San Benito', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060506', 'Santa Cruz de Toledo', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060507', 'Tantarica', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060508', 'Yona', '0605', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060601', 'Cutervo', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060602', 'Callayuc', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060603', 'Choros', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060604', 'Cujillo', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060605', 'La Ramada', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060606', 'Pimpins', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060607', 'Querocotillo', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060608', 'San Andrés de Cutervo', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060609', 'San Juan de Cutervo', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060610', 'San Luis de Lucma', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060611', 'Santa Cruz', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060612', 'Santo Domin de la Capilla', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060613', 'Santo Tomas', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060614', 'Socota', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060615', 'Toribio Casanova', '0606', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060701', 'Bambamarca', '0607', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060702', 'Chugur', '0607', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060703', 'Hualgayoc', '0607', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060801', 'Jaé', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060802', 'Bellavista', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060803', 'Chontali', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060804', 'Colasay', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060805', 'Huabal', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060806', 'Las Pirias', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060807', 'Pomahuaca', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060808', 'Pucara', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060809', 'Sallique', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060810', 'San Felipe', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060811', 'San José del Alto', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060812', 'Santa Rosa', '0608', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060901', 'San Ignacio', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060902', 'Chirinos', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060903', 'Huara', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060904', 'La Coipa', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060905', 'Namballe', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060906', 'San José de Lourdes', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('060907', 'Tabaconas', '0609', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061001', 'Pedro Gálvez', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061002', 'Chancay', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061003', 'Eduardo Villanueva', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061004', 'Grerio Pita', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061005', 'Ichoca', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061006', 'José Manuel Quiroz', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061007', 'José Sabogal', '0610', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061101', 'San Miguel', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061102', 'Bolívar', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061103', 'Calquis', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061104', 'Catilluc', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061105', 'El Prado', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061106', 'La Florida', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061107', 'Llapa', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061108', 'Nanchoc', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061109', 'Niepos', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061110', 'San Grerio', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061111', 'San Silvestre de Cocha', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061112', 'Tond', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061113', 'Unión Agua Blanca', '0611', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061201', 'San Pablo', '0612', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061202', 'San Bernardino', '0612', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061203', 'San Luis', '0612', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061204', 'Tumbade', '0612', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061301', 'Santa Cruz', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061302', 'Andabamba', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061303', 'Catache', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061304', 'Chancaybaños', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061305', 'La Esperanza', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061306', 'Ninabamba', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061307', 'Pula', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061308', 'Saucepampa', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061309', 'Sexi', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061310', 'Uticyacu', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('061311', 'Yauyuca', '0613', '06');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070101', 'Callao', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070102', 'Bellavista', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070103', 'Carmen de la Legua Reynoso', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070104', 'La Perla', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070105', 'La Punta', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070106', 'Ventanilla', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('070107', 'Mi Perú', '0701', '07');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080101', 'Cusco', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080102', 'Ccorca', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080103', 'Poroy', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080104', 'San Jerónimo', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080105', 'San Sebastia', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080106', 'Santia', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080107', 'Saylla', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080108', 'Wanchaq', '0801', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080201', 'Acomayo', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080202', 'Acopia', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080203', 'Acos', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080204', 'Mosoc Llacta', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080205', 'Pomacanchi', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080206', 'Rondoca', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080207', 'Sangarara', '0802', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080301', 'Anta', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080302', 'Ancahuasi', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080303', 'Cachimayo', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080304', 'Chinchaypujio', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080305', 'Huarocondo', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080306', 'Limatambo', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080307', 'Mollepata', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080308', 'Pucyura', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080309', 'Zurite', '0803', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080401', 'Calca', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080402', 'Coya', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080403', 'Lamay', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080404', 'Lares', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080405', 'Pisac', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080406', 'San Salvador', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080407', 'Taray', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080408', 'Yanatile', '0804', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080501', 'Yanaoca', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080502', 'Checca', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080503', 'Kunturkanki', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080504', 'Langui', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080505', 'Layo', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080506', 'Pampamarca', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080507', 'Quehue', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080508', 'Tupac Amaru', '0805', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080601', 'Sicuani', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080602', 'Checacupe', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080603', 'Combapata', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080604', 'Marangani', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080605', 'Pitumarca', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080606', 'San Pablo', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080607', 'San Pedro', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080608', 'Tinta', '0806', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080701', 'Santo Tomas', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080702', 'Capacmarca', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080703', 'Chamaca', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080704', 'Colquemarca', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080705', 'Livitaca', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080706', 'Llusco', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080707', 'Quiñota', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080708', 'Velille', '0807', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080801', 'Espinar', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080802', 'Condoroma', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080803', 'Coporaque', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080804', 'Ocoruro', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080805', 'Pallpata', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080806', 'Pichigua', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080807', 'Suyckutambo', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080808', 'Alto Pichigua', '0808', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080901', 'Santa Ana', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080902', 'Echarate', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080903', 'Huayopata', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080904', 'Maranura', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080905', 'Ocobamba', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080906', 'Quellouno', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080907', 'Kimbiri', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080908', 'Santa Teresa', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080909', 'Vilcabamba', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080910', 'Pichari', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080911', 'Inkawasi', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080912', 'Villa Virge', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080913', 'Villa Kintiarina', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('080914', 'Megantoni', '0809', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081001', 'Paruro', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081002', 'Accha', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081003', 'Ccapi', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081004', 'Colcha', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081005', 'Huanoquite', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081006', 'Omachaç', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081007', 'Paccaritambo', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081008', 'Pillpinto', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081009', 'Yaurisque', '0810', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081101', 'Paucartambo', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081102', 'Caicay', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081103', 'Challabamba', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081104', 'Colquepata', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081105', 'Huancarani', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081106', 'Kosñipata', '0811', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081201', 'Urcos', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081202', 'Andahuaylillas', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081203', 'Camanti', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081204', 'Ccarhuayo', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081205', 'Ccatca', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081206', 'Cusipata', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081207', 'Huaro', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081208', 'Lucre', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081209', 'Marcapata', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081210', 'Ocongate', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081211', 'Oropesa', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081212', 'Quiquijana', '0812', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081301', 'Urubamba', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081302', 'Chinchero', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081303', 'Huayllabamba', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081304', 'Machupicchu', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081305', 'Maras', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081306', 'Ollantaytambo', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('081307', 'Yucay', '0813', '08');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090101', 'Huancavelica', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090102', 'Acobambilla', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090103', 'Acoria', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090104', 'Conayca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090105', 'Cuenca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090106', 'Huachocolpa', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090107', 'Huayllahuara', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090108', 'Izcuchaca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090109', 'Laria', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090110', 'Manta', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090111', 'Mariscal Cáceres', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090112', 'Moya', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090113', 'Nuevo Occoro', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090114', 'Palca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090115', 'Pilchaca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090116', 'Vilca', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090117', 'Yauli', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090118', 'Ascensió', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090119', 'Huando', '0901', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090201', 'Acobamba', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090202', 'Andabamba', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090203', 'Anta', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090204', 'Caja', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090205', 'Marcas', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090206', 'Paucara', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090207', 'Pomacocha', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090208', 'Rosario', '0902', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090301', 'Lircay', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090302', 'Anchonga', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090303', 'Callanmarca', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090304', 'Ccochaccasa', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090305', 'Chincho', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090306', 'Congalla', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090307', 'Huanca-Huanca', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090308', 'Huayllay Grande', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090309', 'Julcamarca', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090310', 'San Antonio de Antaparco', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090311', 'Santo Tomas de Pata', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090312', 'Secclla', '0903', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090401', 'Castrovirreyna', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090402', 'Arma', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090403', 'Aurahua', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090404', 'Capillas', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090405', 'Chupamarca', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090406', 'Cocas', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090407', 'Huachos', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090408', 'Huamatambo', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090409', 'Mollepampa', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090410', 'San Jua', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090411', 'Santa Ana', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090412', 'Tantara', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090413', 'Ticrapo', '0904', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090501', 'Churcampa', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090502', 'Anco', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090503', 'Chinchihuasi', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090504', 'El Carme', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090505', 'La Merced', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090506', 'Locroja', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090507', 'Paucarbamba', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090508', 'San Miguel de Mayocc', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090509', 'San Pedro de Coris', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090510', 'Pachamarca', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090511', 'Cosme', '0905', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090601', 'Huaytara', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090602', 'Ayavi', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090603', 'Córdova', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090604', 'Huayacundo Arma', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090605', 'Laramarca', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090606', 'Ocoyo', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090607', 'Pilpichaca', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090608', 'Querco', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090609', 'Quito-Arma', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090610', 'San Antonio de Cusicancha', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090611', 'San Francisco de Sangayaico', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090612', 'San Isidro', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090613', 'Santia de Chocorvos', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090614', 'Santia de Quirahuara', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090615', 'Santo Domin de Capillas', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090616', 'Tambo', '0906', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090701', 'Pampas', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090702', 'Acostambo', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090703', 'Acraquia', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090704', 'Ahuaycha', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090705', 'Colcabamba', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090706', 'Daniel Hernández', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090707', 'Huachocolpa', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090709', 'Huaribamba', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090710', 'Ñahuimpuquio', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090711', 'Pazos', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090713', 'Quishuar', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090714', 'Salcabamba', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090715', 'Salcahuasi', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090716', 'San Marcos de Rocchac', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090717', 'Surcubamba', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090718', 'Tintay Puncu', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090719', 'Quichuas', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090720', 'Andaymarca', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090721', 'Roble', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090722', 'Pichos', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('090723', 'Santia de Tucuma', '0907', '09');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100101', 'Huanuco', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100102', 'Amarilis', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100103', 'Chinchao', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100104', 'Churubamba', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100105', 'Mars', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100106', 'Quisqui (Kichki)', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100107', 'San Francisco de Cayra', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100108', 'San Pedro de Chaula', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100109', 'Santa María del Valle', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100110', 'Yarumayo', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100111', 'Pillco Marca', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100112', 'Yacus', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100113', 'San Pablo de Pillao', '1001', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100201', 'Ambo', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100202', 'Cayna', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100203', 'Colpas', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100204', 'Conchamarca', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100205', 'Huacar', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100206', 'San Francisco', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100207', 'San Rafael', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100208', 'Tomay Kichwa', '1002', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100301', 'La Unió', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100307', 'Chuquis', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100311', 'Marías', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100313', 'Pachas', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100316', 'Quivilla', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100317', 'Ripa', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100321', 'Shunqui', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100322', 'Sillapata', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100323', 'Yanas', '1003', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100401', 'Huacaybamba', '1004', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100402', 'Canchabamba', '1004', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100403', 'Cochabamba', '1004', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100404', 'Pinra', '1004', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100501', 'Llata', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100502', 'Arancay', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100503', 'Chavín de Pariarca', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100504', 'Jacas Grande', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100505', 'Jirca', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100506', 'Miraflores', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100507', 'Monzó', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100508', 'Punchao', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100509', 'Puños', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100510', 'Singa', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100511', 'Tantamayo', '1005', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100601', 'Rupa-Rupa', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100602', 'Daniel Alomía Robles', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100603', 'Hermílio Valdiza', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100604', 'José Crespo y Castillo', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100605', 'Luyando', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100606', 'Mariano Damaso Berau', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100607', 'Pucayacu', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100608', 'Castillo Grande', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100609', 'Pueblo Nuevo', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100610', 'Santo Domin de Anda', '1006', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100701', 'Huacrachuco', '1007', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100702', 'Cholo', '1007', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100703', 'San Buenaventura', '1007', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100704', 'La Morada', '1007', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100705', 'Santa Rosa de Alto Yanajanca', '1007', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100801', 'Panao', '1008', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100802', 'Chaglla', '1008', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100803', 'Molino', '1008', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100804', 'Umari', '1008', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100901', 'Puerto Inca', '1009', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100902', 'Codo del Pozuzo', '1009', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100903', 'Honoria', '1009', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100904', 'Tournavista', '1009', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('100905', 'Yuyapichis', '1009', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101001', 'Jesús', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101002', 'Baños', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101003', 'Jivia', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101004', 'Queropalca', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101005', 'Rondos', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101006', 'San Francisco de Asís', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101007', 'San Miguel de Cauri', '1010', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101101', 'Chavinillo', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101102', 'Cahuac', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101103', 'Chacabamba', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101104', 'Aparicio Pomares', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101105', 'Jacas Chico', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101106', 'Obas', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101107', 'Pampamarca', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('101108', 'Choras', '1011', '10');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110101', 'Ica', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110102', 'La Tinguiña', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110103', 'Los Aquijes', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110104', 'Ocucaje', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110105', 'Pachacutec', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110106', 'Parcona', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110107', 'Pueblo Nuevo', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110108', 'Salas', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110109', 'San José de Los Molinos', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110110', 'San Juan Bautista', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110111', 'Santia', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110112', 'Subtanjalla', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110113', 'Tate', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110114', 'Yauca del Rosario', '1101', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110201', 'Chincha Alta', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110202', 'Alto Lara', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110203', 'Chavi', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110204', 'Chincha Baja', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110205', 'El Carme', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110206', 'Grocio Prado', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110207', 'Pueblo Nuevo', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110208', 'San Juan de Yanac', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110209', 'San Pedro de Huacarpana', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110210', 'Sunampe', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110211', 'Tambo de Mora', '1102', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110301', 'Nasca', '1103', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110302', 'Changuillo', '1103', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110303', 'El Ingenio', '1103', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110304', 'Marcona', '1103', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110305', 'Vista Alegre', '1103', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110401', 'Palpa', '1104', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110402', 'Llipata', '1104', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110403', 'Río Grande', '1104', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110404', 'Santa Cruz', '1104', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110405', 'Tibillo', '1104', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110501', 'Pisco', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110502', 'Huancano', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110503', 'Humay', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110504', 'Independencia', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110505', 'Paracas', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110506', 'San Andrés', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110507', 'San Clemente', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('110508', 'Tupac Amaru Inca', '1105', '11');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120101', 'Huancayo', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120104', 'Carhuacallanga', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120105', 'Chacapampa', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120106', 'Chicche', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120107', 'Chilca', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120108', 'Chons Alto', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120111', 'Chupuro', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120112', 'Colca', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120113', 'Cullhuas', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120114', 'El Tambo', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120116', 'Huacrapuquio', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120117', 'Hualhuas', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120119', 'Huanca', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120120', 'Huasicancha', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120121', 'Huayucachi', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120122', 'Ingenio', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120124', 'Pariahuanca', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120125', 'Pilcomayo', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120126', 'Pucara', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120127', 'Quichuay', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120128', 'Quilcas', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120129', 'San Agustí', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120130', 'San Jerónimo de Tuna', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120132', 'Saño', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120133', 'Sapallanga', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120134', 'Sicaya', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120135', 'Santo Domin de Acobamba', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120136', 'Viques', '1201', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120201', 'Concepció', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120202', 'Aco', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120203', 'Andamarca', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120204', 'Chambara', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120205', 'Cochas', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120206', 'Comas', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120207', 'Heroínas Toledo', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120208', 'Manzanares', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120209', 'Mariscal Castilla', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120210', 'Matahuasi', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120211', 'Mito', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120212', 'Nueve de Julio', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120213', 'Orcotuna', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120214', 'San José de Quero', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120215', 'Santa Rosa de Ocopa', '1202', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120301', 'Chanchamayo', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120302', 'Perene', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120303', 'Pichanaqui', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120304', 'San Luis de Shuaro', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120305', 'San Ramó', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120306', 'Vitoc', '1203', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120401', 'Jauja', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120402', 'Acolla', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120403', 'Apata', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120404', 'Ataura', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120405', 'Canchayllo', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120406', 'Curicaca', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120407', 'El Mantaro', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120408', 'Huamali', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120409', 'Huaripampa', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120410', 'Huertas', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120411', 'Janjaillo', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120412', 'Julcá', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120413', 'Leonor Ordóñez', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120414', 'Llocllapampa', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120415', 'Marco', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120416', 'Masma', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120417', 'Masma Chicche', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120418', 'Molinos', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120419', 'Monobamba', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120420', 'Muqui', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120421', 'Muquiyauyo', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120422', 'Paca', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120423', 'Paccha', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120424', 'Panca', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120425', 'Parco', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120426', 'Pomacancha', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120427', 'Ricra', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120428', 'San Lorenzo', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120429', 'San Pedro de Chuna', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120430', 'Sausa', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120431', 'Sincos', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120432', 'Tunan Marca', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120433', 'Yauli', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120434', 'Yauyos', '1204', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120501', 'Juni', '1205', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120502', 'Carhuamayo', '1205', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120503', 'Ondores', '1205', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120504', 'Ulcumayo', '1205', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120601', 'Satipo', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120602', 'Coviriali', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120603', 'Llaylla', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120604', 'Mazamari', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120605', 'Pampa Hermosa', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120606', 'Pana', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120607', 'Río Negro', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120608', 'Río Tambo', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120609', 'Vizcatan del Ene', '1206', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120701', 'Tarma', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120702', 'Acobamba', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120703', 'Huaricolca', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120704', 'Huasahuasi', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120705', 'La Unió', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120706', 'Palca', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120707', 'Palcamayo', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120708', 'San Pedro de Cajas', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120709', 'Tapo', '1207', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120801', 'La Oroya', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120802', 'Chacapalpa', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120803', 'Huay-Huay', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120804', 'Marcapomacocha', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120805', 'Morococha', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120806', 'Paccha', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120807', 'Santa Bárbara de Carhuacaya', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120808', 'Santa Rosa de Sacco', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120809', 'Suitucancha', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120810', 'Yauli', '1208', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120901', 'Chupaca', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120902', 'Ahuac', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120903', 'Chons Bajo', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120904', 'Huachac', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120905', 'Huamancaca Chico', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120906', 'San Juan de Iscos', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120907', 'San Juan de Jarpa', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120908', 'Tres de Diciembre', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('120909', 'Yanacancha', '1209', '12');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130101', 'Trujillo', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130102', 'El Porvenir', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130103', 'Florencia de Mora', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130104', 'Huanchaco', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130105', 'La Esperanza', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130106', 'Laredo', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130107', 'Moche', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130108', 'Poroto', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130109', 'Salaverry', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130110', 'Simbal', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130111', 'Victor Larco Herrera', '1301', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130201', 'Ascope', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130202', 'Chicama', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130203', 'Chocope', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130204', 'Magdalena de Cao', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130205', 'Paija', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130206', 'Rázuri', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130207', 'Santia de Cao', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130208', 'Casa Grande', '1302', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130301', 'Bolívar', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130302', 'Bambamarca', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130303', 'Condormarca', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130304', 'Lontea', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130305', 'Uchumarca', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130306', 'Ucuncha', '1303', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130401', 'Chepe', '1304', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130402', 'Pacanga', '1304', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130403', 'Pueblo Nuevo', '1304', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130501', 'Julca', '1305', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130502', 'Calamarca', '1305', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130503', 'Carabamba', '1305', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130504', 'Huaso', '1305', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130601', 'Otuzco', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130602', 'Agallpampa', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130604', 'Charat', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130605', 'Huaranchal', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130606', 'La Cuesta', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130608', 'Mache', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130610', 'Paranday', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130611', 'Salpo', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130613', 'Sinsicap', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130614', 'Usquil', '1306', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130701', 'San Pedro de Lloc', '1307', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130702', 'Guadalupe', '1307', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130703', 'Jequetepeque', '1307', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130704', 'Pacasmayo', '1307', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130705', 'San José', '1307', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130801', 'Tayabamba', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130802', 'Buldibuyo', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130803', 'Chillia', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130804', 'Huancaspata', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130805', 'Huaylillas', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130806', 'Huayo', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130807', 'On', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130808', 'Parcoy', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130809', 'Pataz', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130810', 'Pias', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130811', 'Santia de Challas', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130812', 'Taurija', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130813', 'Urpay', '1308', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130901', 'Huamachuco', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130902', 'Chugay', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130903', 'Cochorco', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130904', 'Curs', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130905', 'Marcabal', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130906', 'Sanara', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130907', 'Sari', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('130908', 'Sartimbamba', '1309', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131001', 'Santia de Chuco', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131002', 'Angasmarca', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131003', 'Cachicada', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131004', 'Mollebamba', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131005', 'Mollepata', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131006', 'Quiruvilca', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131007', 'Santa Cruz de Chuca', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131008', 'Sitabamba', '1310', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131101', 'Cascas', '1311', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131102', 'Lucma', '1311', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131103', 'Marmot', '1311', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131104', 'Sayapullo', '1311', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131201', 'Viru', '1312', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131202', 'Chao', '1312', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('131203', 'Guadalupito', '1312', '13');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140101', 'Chiclayo', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140102', 'Chonyape', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140103', 'Ete', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140104', 'Eten Puerto', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140105', 'José Leonardo Ortiz', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140106', 'La Victoria', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140107', 'Lagunas', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140108', 'Monsefu', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140109', 'Nueva Arica', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140110', 'Oyotu', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140111', 'Picsi', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140112', 'Pimentel', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140113', 'Reque', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140114', 'Santa Rosa', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140115', 'Saña', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140116', 'Cayalti', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140117', 'Patapo', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140118', 'Pomalca', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140119', 'Pucala', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140120', 'Tuma', '1401', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140201', 'Ferreñafe', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140202', 'Cañaris', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140203', 'Incahuasi', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140204', 'Manuel Antonio Mesones Muro', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140205', 'Pitipo', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140206', 'Pueblo Nuevo', '1402', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140301', 'Lambayeque', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140302', 'Chochope', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140303', 'Illimo', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140304', 'Jayanca', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140305', 'Mochumi', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140306', 'Morrope', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140307', 'Motupe', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140308', 'Olmos', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140309', 'Pacora', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140310', 'Salas', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140311', 'San José', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('140312', 'Tucume', '1403', '14');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150101', 'Lima', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150102', 'Ancó', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150103', 'Ate', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150104', 'Barranco', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150105', 'Breña', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150106', 'Carabayllo', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150107', 'Chaclacayo', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150108', 'Chorrillos', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150109', 'Cieneguilla', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150110', 'Comas', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150111', 'El Agustino', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150112', 'Independencia', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150113', 'Jesús María', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150114', 'La Molina', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150115', 'La Victoria', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150116', 'Lince', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150117', 'Los Olivos', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150118', 'Lurigancho', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150119', 'Luri', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150120', 'Magdalena del Mar', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150121', 'Pueblo Libre', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150122', 'Miraflores', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150123', 'Pachacamac', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150124', 'Pucusana', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150125', 'Puente Piedra', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150126', 'Punta Hermosa', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150127', 'Punta Negra', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150128', 'Rímac', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150129', 'San Bartolo', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150130', 'San Borja', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150131', 'San Isidro', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150132', 'San Juan de Lurigancho', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150133', 'San Juan de Miraflores', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150134', 'San Luis', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150135', 'San Martín de Porres', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150136', 'San Miguel', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150137', 'Santa Anita', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150138', 'Santa María del Mar', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150139', 'Santa Rosa', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150140', 'Santia de Surco', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150141', 'Surquillo', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150142', 'Villa El Salvador', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150143', 'Villa María del Triunfo', '1501', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150201', 'Barranca', '1502', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150202', 'Paramonga', '1502', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150203', 'Pativilca', '1502', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150204', 'Supe', '1502', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150205', 'Supe Puerto', '1502', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150301', 'Cajatambo', '1503', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150302', 'Copa', '1503', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150303', 'rr', '1503', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150304', 'Huancapo', '1503', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150305', 'Manas', '1503', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150401', 'Canta', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150402', 'Arahuay', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150403', 'Huamantanga', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150404', 'Huaros', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150405', 'Lachaqui', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150406', 'San Buenaventura', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150407', 'Santa Rosa de Quives', '1504', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150501', 'San Vicente de Cañete', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150502', 'Asia', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150503', 'Cala', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150504', 'Cerro Azul', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150505', 'Chilca', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150506', 'Coayllo', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150507', 'Imperial', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150508', 'Lunahuana', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150509', 'Mala', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150510', 'Nuevo Imperial', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150511', 'Pacara', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150512', 'Quilmana', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150513', 'San Antonio', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150514', 'San Luis', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150515', 'Santa Cruz de Flores', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150516', 'Zúñiga', '1505', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150601', 'Huaral', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150602', 'Atavillos Alto', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150603', 'Atavillos Bajo', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150604', 'Aucallama', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150605', 'Chancay', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150606', 'Ihuari', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150607', 'Lampia', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150608', 'Pacaraos', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150609', 'San Miguel de Acos', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150610', 'Santa Cruz de Andamarca', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150611', 'Sumbilca', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150612', 'Veintisiete de Noviembre', '1506', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150701', 'Matucana', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150702', 'Antioquia', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150703', 'Callahuanca', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150704', 'Carampoma', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150705', 'Chicla', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150706', 'Cuenca', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150707', 'Huachupampa', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150708', 'Huanza', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150709', 'Huarochiri', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150710', 'Lahuaytambo', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150711', 'Langa', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150712', 'Laraos', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150713', 'Mariatana', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150714', 'Ricardo Palma', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150715', 'San Andrés de Tupicocha', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150716', 'San Antonio', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150717', 'San Bartolomé', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150718', 'San Damia', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150719', 'San Juan de Iris', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150720', 'San Juan de Tantaranche', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150721', 'San Lorenzo de Quinti', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150722', 'San Mateo', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150723', 'San Mateo de Otao', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150724', 'San Pedro de Casta', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150725', 'San Pedro de Huancayre', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150726', 'Sangallaya', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150727', 'Santa Cruz de Cocachacra', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150728', 'Santa Eulalia', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150729', 'Santia de Anchucaya', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150730', 'Santia de Tuna', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150731', 'Santo Domin de Los Olleros', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150732', 'Surco', '1507', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150801', 'Huacho', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150802', 'Ambar', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150803', 'Caleta de Carqui', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150804', 'Checras', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150805', 'Hualmay', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150806', 'Huaura', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150807', 'Leoncio Prado', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150808', 'Paccho', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150809', 'Santa Leonor', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150810', 'Santa María', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150811', 'Saya', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150812', 'Vegueta', '1508', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150901', 'Oyo', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150902', 'Andajes', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150903', 'Caujul', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150904', 'Cochamarca', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150905', 'Nava', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('150906', 'Pachangara', '1509', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151001', 'Yauyos', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151002', 'Alis', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151003', 'Allauca', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151004', 'Ayaviri', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151005', 'Azángaro', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151006', 'Cacra', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151007', 'Carania', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151008', 'Catahuasi', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151009', 'Chocos', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151010', 'Cochas', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151011', 'Colonia', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151012', 'Hons', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151013', 'Huampara', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151014', 'Huancaya', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151015', 'Huangascar', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151016', 'Huanta', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151017', 'Huañec', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151018', 'Laraos', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151019', 'Lincha', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151020', 'Madea', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151021', 'Miraflores', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151022', 'Omas', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151023', 'Putinza', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151024', 'Quinches', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151025', 'Quinocay', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151026', 'San Joaquí', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151027', 'San Pedro de Pilas', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151028', 'Tanta', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151029', 'Tauripampa', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151030', 'Tomas', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151031', 'Tupe', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151032', 'Viñac', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('151033', 'Vitis', '1510', '15');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160101', 'Iquitos', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160102', 'Alto Nanay', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160103', 'Fernando Lores', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160104', 'Indiana', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160105', 'Las Amazonas', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160106', 'Maza', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160107', 'Napo', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160108', 'Punchana', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160110', 'Torres Causana', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160112', 'Belé', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160113', 'San Juan Bautista', '1601', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160201', 'Yurimaguas', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160202', 'Balsapuerto', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160205', 'Jeberos', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160206', 'Lagunas', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160210', 'Santa Cruz', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160211', 'Teniente Cesar López Rojas', '1602', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160301', 'Nauta', '1603', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160302', 'Parinari', '1603', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160303', 'Tigre', '1603', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160304', 'Trompeteros', '1603', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160305', 'Urarinas', '1603', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160401', 'Ramón Castilla', '1604', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160402', 'Pebas', '1604', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160403', 'Yavari', '1604', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160404', 'San Pablo', '1604', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160501', 'Requena', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160502', 'Alto Tapiche', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160503', 'Capelo', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160504', 'Emilio San Martí', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160505', 'Maquia', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160506', 'Puinahua', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160507', 'Saquena', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160508', 'Sopli', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160509', 'Tapiche', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160510', 'Jenaro Herrera', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160511', 'Yaquerana', '1605', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160601', 'Contamana', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160602', 'Inahuaya', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160603', 'Padre Márquez', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160604', 'Pampa Hermosa', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160605', 'Sarayacu', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160606', 'Vargas Guerra', '1606', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160701', 'Barranca', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160702', 'Cahuapanas', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160703', 'Manseriche', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160704', 'Morona', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160705', 'Pastaza', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160706', 'Andoas', '1607', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160801', 'Putumayo', '1608', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160802', 'Rosa Panduro', '1608', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160803', 'Teniente Manuel Clavero', '1608', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('160804', 'Yaguas', '1608', '16');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170101', 'Tambopata', '1701', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170102', 'Inambari', '1701', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170103', 'Las Piedras', '1701', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170104', 'Laberinto', '1701', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170201', 'Manu', '1702', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170202', 'Fitzcarrald', '1702', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170203', 'Madre de Dios', '1702', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170204', 'Huepetuhe', '1702', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170301', 'Iñapari', '1703', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170302', 'Iberia', '1703', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('170303', 'Tahuamanu', '1703', '17');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180101', 'Moquegua', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180102', 'Carumas', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180103', 'Cuchumbaya', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180104', 'Samegua', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180105', 'San Cristóbal', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180106', 'Torata', '1801', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180201', 'Omate', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180202', 'Chojata', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180203', 'Coalaque', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180204', 'Ichuña', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180205', 'La Capilla', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180206', 'Lloque', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180207', 'Matalaque', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180208', 'Puquina', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180209', 'Quinistaquillas', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180210', 'Ubinas', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180211', 'Yunga', '1802', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180301', 'Ilo', '1803', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180302', 'El Algarrobal', '1803', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('180303', 'Pacocha', '1803', '18');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190101', 'Chaupimarca', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190102', 'Huacho', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190103', 'Huariaca', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190104', 'Huayllay', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190105', 'Ninacaca', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190106', 'Pallanchacra', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190107', 'Paucartambo', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190108', 'San Francisco de Asís de Yarusyaca', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190109', 'Simon Bolívar', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190110', 'Ticlacaya', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190111', 'Tinyahuarco', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190112', 'Vicco', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190113', 'Yanacancha', '1901', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190201', 'Yanahuanca', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190202', 'Chacaya', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190203', 'yllarisquizga', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190204', 'Paucar', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190205', 'San Pedro de Pillao', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190206', 'Santa Ana de Tusi', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190207', 'Tapuc', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190208', 'Vilcabamba', '1902', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190301', 'Oxapampa', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190302', 'Chontabamba', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190303', 'Huancabamba', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190304', 'Palcazu', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190305', 'Pozuzo', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190306', 'Puerto Bermúdez', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190307', 'Villa Rica', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('190308', 'Constitució', '1903', '19');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200101', 'Piura', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200104', 'Castilla', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200105', 'Catacaos', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200107', 'Cura Mori', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200108', 'El Talla', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200109', 'La Arena', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200110', 'La Unió', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200111', 'Las Lomas', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200114', 'Tambo Grande', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200115', 'Veintiseis de Octubre', '2001', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200201', 'Ayabaca', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200202', 'Frias', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200203', 'Jilili', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200204', 'Lagunas', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200205', 'Montero', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200206', 'Pacaipampa', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200207', 'Paimas', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200208', 'Sapillica', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200209', 'Sicchez', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200210', 'Suyo', '2002', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200301', 'Huancabamba', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200302', 'Canchaque', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200303', 'El Carmen de la Frontera', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200304', 'Huarmaca', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200305', 'Lalaquiz', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200306', 'San Miguel de El Faique', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200307', 'Sondor', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200308', 'Sondorillo', '2003', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200401', 'Chulucanas', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200402', 'Buenos Aires', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200403', 'Chalaco', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200404', 'La Matanza', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200405', 'Morropo', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200406', 'Salitral', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200407', 'San Juan de Bite', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200408', 'Santa Catalina de Mossa', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200409', 'Santo Domi', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200410', 'Yama', '2004', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200501', 'Paita', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200502', 'Amotape', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200503', 'Arenal', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200504', 'Cola', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200505', 'La Huaca', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200506', 'Tamarindo', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200507', 'Vichayal', '2005', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200601', 'Sullana', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200602', 'Bellavista', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200603', 'Ignacio Escudero', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200604', 'Lancones', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200605', 'Marcavelica', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200606', 'Miguel Checa', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200607', 'Querecotillo', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200608', 'Salitral', '2006', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200701', 'Pariñas', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200702', 'El Alto', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200703', 'La Brea', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200704', 'Lobitos', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200705', 'Los Organos', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200706', 'Mancora', '2007', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200801', 'Sechura', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200802', 'Bellavista de la Unió', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200803', 'Bernal', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200804', 'Cristo Nos Valga', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200805', 'Vice', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('200806', 'Rinconada Llicuar', '2008', '20');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210101', 'Puno', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210102', 'Acora', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210103', 'Amantani', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210104', 'Atuncolla', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210105', 'Capachica', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210106', 'Chucuito', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210107', 'Coata', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210108', 'Huata', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210109', 'Mañazo', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210110', 'Paucarcolla', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210111', 'Pichacani', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210112', 'Plateria', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210113', 'San Antonio', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210114', 'Tiquillaca', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210115', 'Vilque', '2101', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210201', 'Azángaro', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210202', 'Achaya', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210203', 'Arapa', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210204', 'Asillo', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210205', 'Caminaca', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210206', 'Chupa', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210207', 'José Domin Choquehuanca', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210208', 'Muñani', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210209', 'Potoni', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210210', 'Sama', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210211', 'San Anto', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210212', 'San José', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210213', 'San Juan de Salinas', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210214', 'Santia de Pupuja', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210215', 'Tirapata', '2102', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210301', 'Macusani', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210302', 'Ajoyani', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210303', 'Ayapata', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210304', 'Coasa', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210305', 'Corani', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210306', 'Crucero', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210307', 'Ituata', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210308', 'Ollachea', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210309', 'San Gaba', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210310', 'Usicayos', '2103', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210401', 'Juli', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210402', 'Desaguadero', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210403', 'Huacullani', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210404', 'Kelluyo', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210405', 'Pisacoma', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210406', 'Pomata', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210407', 'Zepita', '2104', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210501', 'Ilave', '2105', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210502', 'Capazo', '2105', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210503', 'Pilcuyo', '2105', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210504', 'Santa Rosa', '2105', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210505', 'Conduriri', '2105', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210601', 'Huancane', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210602', 'Cojata', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210603', 'Huatasani', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210604', 'Inchupalla', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210605', 'Pusi', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210606', 'Rosaspata', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210607', 'Taraco', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210608', 'Vilque Chico', '2106', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210701', 'Lampa', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210702', 'Cabanilla', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210703', 'Calapuja', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210704', 'Nicasio', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210705', 'Ocuviri', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210706', 'Palca', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210707', 'Paratia', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210708', 'Pucara', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210709', 'Santa Lucia', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210710', 'Vilavila', '2107', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210801', 'Ayaviri', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210802', 'Antauta', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210803', 'Cupi', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210804', 'Llalli', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210805', 'Macari', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210806', 'Nuñoa', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210807', 'Orurillo', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210808', 'Santa Rosa', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210809', 'Umachiri', '2108', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210901', 'Moho', '2109', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210902', 'Conima', '2109', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210903', 'Huayrapata', '2109', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('210904', 'Tilali', '2109', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211001', 'Putina', '2110', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211002', 'Ananea', '2110', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211003', 'Pedro Vilca Apaza', '2110', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211004', 'Quilcapuncu', '2110', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211005', 'Sina', '2110', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211101', 'Juliaca', '2111', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211102', 'Cabana', '2111', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211103', 'Cabanillas', '2111', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211104', 'Caracoto', '2111', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211105', 'San Miguel', '2111', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211201', 'Sandia', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211202', 'Cuyocuyo', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211203', 'Limbani', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211204', 'Patambuco', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211205', 'Phara', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211206', 'Quiaca', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211207', 'San Juan del Oro', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211208', 'Yanahuaya', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211209', 'Alto Inambari', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211210', 'San Pedro de Putina Punco', '2112', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211301', 'Yunguyo', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211302', 'Anapia', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211303', 'Copani', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211304', 'Cuturapi', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211305', 'Ollaraya', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211306', 'Tinicachi', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('211307', 'Unicachi', '2113', '21');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220101', 'Moyobamba', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220102', 'Calzada', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220103', 'Habana', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220104', 'Jepelacio', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220105', 'Soritor', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220106', 'Yantalo', '2201', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220201', 'Bellavista', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220202', 'Alto Biavo', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220203', 'Bajo Biavo', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220204', 'Huallaga', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220205', 'San Pablo', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220206', 'San Rafael', '2202', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220301', 'San José de Sisa', '2203', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220302', 'Agua Blanca', '2203', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220303', 'San Martí', '2203', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220304', 'Santa Rosa', '2203', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220305', 'Shatoja', '2203', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220401', 'Saposoa', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220402', 'Alto Saposoa', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220403', 'El Eslabó', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220404', 'Piscoyacu', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220405', 'Sacanche', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220406', 'Tin de Saposoa', '2204', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220501', 'Lamas', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220502', 'Alonso de Alvarado', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220503', 'Barranquita', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220504', 'Caynarachi', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220505', 'Cuñumbuqui', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220506', 'Pinto Recodo', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220507', 'Rumisapa', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220508', 'San Roque de Cumbaza', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220509', 'Shanao', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220510', 'Tabalosos', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220511', 'Zapatero', '2205', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220601', 'Juanjuí', '2206', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220602', 'Campanilla', '2206', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220603', 'Huicu', '2206', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220604', 'Pachiza', '2206', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220605', 'Pajarillo', '2206', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220701', 'Picota', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220702', 'Buenos Aires', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220703', 'Caspisapa', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220704', 'Pilluana', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220705', 'Pucacaca', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220706', 'San Cristóbal', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220707', 'San Hilarió', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220708', 'Shamboyacu', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220709', 'Tin de Ponasa', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220710', 'Tres Unidos', '2207', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220801', 'Rioja', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220802', 'Awaju', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220803', 'Elías Soplin Vargas', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220804', 'Nueva Cajamarca', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220805', 'Pardo Miguel', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220806', 'Posic', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220807', 'San Fernando', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220808', 'Yorons', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220809', 'Yuracyacu', '2208', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220901', 'Tarapoto', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220902', 'Alberto Leveau', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220903', 'Cacatachi', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220904', 'Chazuta', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220905', 'Chipurana', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220906', 'El Porvenir', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220907', 'Huimbayoc', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220908', 'Juan Guerra', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220909', 'La Banda de Shilcayo', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220910', 'Morales', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220911', 'Papaplaya', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220912', 'San Antonio', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220913', 'Sauce', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('220914', 'Shapaja', '2209', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('221001', 'Tocache', '2210', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('221002', 'Nuevo Progreso', '2210', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('221003', 'Polvora', '2210', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('221004', 'Shunte', '2210', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('221005', 'Uchiza', '2210', '22');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230101', 'Tacna', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230102', 'Alto de la Alianza', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230103', 'Calana', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230104', 'Ciudad Nueva', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230105', 'Incla', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230106', 'Pachia', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230107', 'Palca', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230108', 'Pocollay', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230109', 'Sama', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230110', 'Coronel Grerio Albarracín Lanchipa', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230111', 'La Yarada los Palos', '2301', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230201', 'Candarave', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230202', 'Cairani', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230203', 'Camilaca', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230204', 'Curibaya', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230205', 'Huanuara', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230206', 'Quilahuani', '2302', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230301', 'Locumba', '2303', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230302', 'Ilabaya', '2303', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230303', 'Ite', '2303', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230401', 'Tarata', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230402', 'Héroes Albarrací', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230403', 'Estique', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230404', 'Estique-Pampa', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230405', 'Sitajara', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230406', 'Susapaya', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230407', 'Tarucachi', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('230408', 'Ticaco', '2304', '23');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240101', 'Tumbes', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240102', 'Corrales', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240103', 'La Cruz', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240104', 'Pampas de Hospital', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240105', 'San Jacinto', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240106', 'San Juan de la Virge', '2401', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240201', 'Zorritos', '2402', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240202', 'Casitas', '2402', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240203', 'Canoas de Punta Sal', '2402', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240301', 'Zarumilla', '2403', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240302', 'Aguas Verdes', '2403', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240303', 'Matapalo', '2403', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('240304', 'Papayal', '2403', '24');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250101', 'Calleria', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250102', 'Campoverde', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250103', 'Iparia', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250104', 'Masisea', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250105', 'Yarinacocha', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250106', 'Nueva Requena', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250107', 'Manantay', '2501', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250201', 'Raymondi', '2502', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250202', 'Sepahua', '2502', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250203', 'Tahuania', '2502', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250204', 'Yurua', '2502', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250301', 'Padre Abad', '2503', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250302', 'Irazola', '2503', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250303', 'Curimana', '2503', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250304', 'Neshuya', '2503', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250305', 'Alexander Von Humboldt', '2503', '25');

INSERT INTO district (id, name, province_id, department_id) VALUES ('250401', 'Purus', '2504', '25');

