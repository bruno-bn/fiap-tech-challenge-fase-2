-- Inserção das Categorias de Tipos de Usuário
INSERT INTO tb_tipo_usuario (nome, categoria) VALUES ('Proprietário VIP', 'DONO_RESTAURANTE');
INSERT INTO tb_tipo_usuario (nome, categoria) VALUES ('Cliente Standard', 'CLIENTE');

-- Inserção dos Usuários já associados aos tipos correspondentes
INSERT INTO tb_usuario (nome, email, tipo_usuario_id) VALUES ('João Silva', 'joao.dono@email.com', 1);
INSERT INTO tb_usuario (nome, email, tipo_usuario_id) VALUES ('Maria Souza', 'maria.cliente@email.com', 2);