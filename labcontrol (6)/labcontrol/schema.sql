CREATE DATABASE IF NOT EXISTS labcontrol CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE labcontrol;

CREATE TABLE IF NOT EXISTS equipamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    numero_patrimonio VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_numero_patrimonio (numero_patrimonio),
    CONSTRAINT chk_quantidade CHECK (quantidade >= 0)
);
