CREATE TABLE usuario (
  usuario_id BIGINT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(100) NOT NULL,
  nome VARCHAR(200) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  PRIMARY KEY(usuario_id),
  INDEX UK_usuario(usuario)
);

CREATE TABLE permissao (
  permissao_id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(100) NULL,
  PRIMARY KEY(permissao_id)
);

CREATE TABLE categoria (
  categoria_id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(200) NOT NULL,
  PRIMARY KEY(categoria_id),
  INDEX UK_categoria(descricao)
);

CREATE TABLE produto (
  produto_id BIGINT NOT NULL AUTO_INCREMENT,
  categoria_id BIGINT NULL,
  descricao VARCHAR(200) NOT NULL,
  preco NUMERIC(10,2) NOT NULL,
  fg_disponivel BOOL NOT NULL DEFAULT 1,
  PRIMARY KEY(produto_id),
  INDEX FK_produto__categoria(categoria_id),
  INDEX UK_produto(descricao),
  FOREIGN KEY(categoria_id)
    REFERENCES categoria(categoria_id)
      ON DELETE SET NULL
      ON UPDATE NO ACTION
);

CREATE TABLE usuario__permissoes (
  usuario_id BIGINT NOT NULL,
  permissao_id BIGINT NOT NULL,
  PRIMARY KEY(usuario_id, permissao_id),
  INDEX usuario__permissao(usuario_id),
  INDEX permissao__usuario(permissao_id),
  FOREIGN KEY(usuario_id)
    REFERENCES usuario(usuario_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(permissao_id)
    REFERENCES permissao(permissao_id)
      ON DELETE CASCADE
      ON UPDATE NO ACTION
);

