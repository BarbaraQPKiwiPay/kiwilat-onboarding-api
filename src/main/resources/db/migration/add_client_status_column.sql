-- Agregar columna de estado a la tabla client
-- Estado inicial por defecto: MANUAL

ALTER TABLE client ADD COLUMN IF NOT EXISTS status VARCHAR(50) NOT NULL DEFAULT 'MANUAL';

-- Agregar comentario para documentación
COMMENT ON COLUMN client.status IS 'Estado del flujo de evaluación de crédito. Estados: MANUAL, DOCUMENTOS_COMPLETADOS, APROBADO_POR_ADV, OBSERVADO_POR_ADV, APROBADO_POR_RIESGOS, RECHAZO_POR_RIESGOS, OBSERVADO_POR_RIESGOS, CREDITO_PRE_APROBADO, CREDITO_RECHAZADO';

-- Crear índice para mejorar performance de consultas por estado
CREATE INDEX IF NOT EXISTS idx_client_status ON client(status);