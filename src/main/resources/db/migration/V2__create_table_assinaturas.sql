CREATE TABLE assinaturas(
                            id BIGSERIAL PRIMARY KEY,
                            servico VARCHAR(255) NOT NULL,
                            valor NUMERIC(10, 2) NOT NULL,
                            data_vencimento INT NOT NULL,
                            status VARCHAR(50) NOT NULL,
                            plano VARCHAR(100),
                            usuario_id BIGINT NOT NULL,
                            CONSTRAINT fk_assinatura_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);