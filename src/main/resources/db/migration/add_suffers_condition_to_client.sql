-- Add suffers_condition column to client table
-- This column indicates if the client suffers from any medical condition

ALTER TABLE client ADD COLUMN IF NOT EXISTS suffers_condition BOOLEAN;

-- Add comment to the column for documentation
COMMENT ON COLUMN client.suffers_condition IS 'Indicates if the client suffers from any medical condition (true/false)';
