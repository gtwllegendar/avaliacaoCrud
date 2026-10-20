CREATE DATABASE IF NOT EXISTS labcontrol;
USE labcontrol;
CREATE TABLE IF NOT EXISTS equipamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    numero_patrimonio VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_numero_patrimonio UNIQUE (numero_patrimonio)
);
