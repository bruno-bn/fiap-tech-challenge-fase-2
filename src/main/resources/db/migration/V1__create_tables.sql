CREATE TABLE tb_tipo_usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE tb_usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    tipo_usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario_tipo FOREIGN KEY (tipo_usuario_id) REFERENCES tb_tipo_usuario(id)
);

CREATE TABLE tb_restaurante (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo_cozinha VARCHAR(100) NOT NULL,
    horario_funcionamento VARCHAR(100) NOT NULL,
    dono_id BIGINT NOT NULL,
    CONSTRAINT fk_restaurante_dono FOREIGN KEY (dono_id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_item_cardapio (
    id BIGSERIAL PRIMARY KEY,
    restaurante_id BIGINT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    preco NUMERIC(10,2) NOT NULL,
    apenas_no_local BOOLEAN DEFAULT FALSE,
    caminho_foto VARCHAR(255),
    CONSTRAINT fk_item_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante(id) ON DELETE CASCADE
);