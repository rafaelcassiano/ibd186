CREATE TABLE usuario (
  usuario_id BIGINT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(100) NOT NULL,
  nome VARCHAR(200) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  PRIMARY KEY(usuario_id),
  INDEX UK_usuario(usuario)
);

CREATE TABLE categoria (
  categoria_id BIGINT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(500) NOT NULL,
  PRIMARY KEY(categoria_id)
);

CREATE TABLE produto (
  produto_id BIGINT NOT NULL AUTO_INCREMENT,
  categoria_id BIGINT NOT NULL,
  descricao VARCHAR(500) NOT NULL,
  preco NUMERIC(10,2) NOT NULL,
  fg_disponivel BOOL NOT NULL DEFAULT 1,
  PRIMARY KEY(produto_id),
  INDEX produto_FKIndex1(categoria_id),
  FOREIGN KEY(categoria_id)
    REFERENCES categoria(categoria_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

