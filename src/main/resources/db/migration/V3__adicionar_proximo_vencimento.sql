-- Adiciona a coluna de proximo_vencimento
ALTER TABLE assinaturas ADD COLUMN proximo_vencimento DATE;

-- Inicializa com a data atual para registros existentes (para evitar erro de nulo)
UPDATE assinaturas SET proximo_vencimento = CURRENT_DATE WHERE proximo_vencimento IS NULL;

-- Torna a coluna not null após preencher os dados
ALTER TABLE assinaturas ALTER COLUMN proximo_vencimento SET NOT NULL;