CREATE TABLE TB_PRODUTO(
    ID_PRODUTO      NUMBER,
    NOME            VARCHAR2(50),
    DESCRICAO       VARCHAR2(50),
    QUANTIDADE      NUMBER,
    DISPONIBILIDADE VARCHAR2(1),
    NOME_IMAGEM		VARCHAR2(50),
	IMAGEM          BLOB,
	CONSTRAINT PRODUTO_PK PRIMARY KEY (ID_PRODUTO)
);

CREATE SEQUENCE SEQ_PRODUTOS
	start with 0
	increment by 1
	maxvalue 999999
	minvalue 0
	nocache
	cycle;

CREATE TABLE TB_COMPRAS(
    ID_COMPRA       NUMBER,
    DATA_COMPRA     VARCHAR2(20),
    EMAIL           VARCHAR2(50),
    VALIDADA        VARCHAR2(1),
    CONSTRAINT COMPRA_PK PRIMARY KEY (ID_COMPRA)
);

CREATE SEQUENCE SEQ_COMPRAS
	start with 0
	increment by 1
	maxvalue 999999
	minvalue 0
	nocache
	cycle;
	
CREATE TABLE TB_AVALIACAO(
	ID_AVALIACAO	NUMBER,
	TEXTO			VARCHAR2(200),
	NOTA			NUMBER,
	ID_PRODUTO		NUMBER,
	ID_AUTENTICACAO	NUMBER,
	CONSTRAINT AVALIACAO_PK PRIMARY KEY (ID_AVALIACAO),
	CONSTRAINT PRODUTO_AVALIACAO_FK FOREIGN KEY	(ID_PRODUTO) REFERENCES TB_PRODUTO (ID_PRODUTO)
);

CREATE SEQUENCE SEQ_AVALIACAO
	start with 0
	increment by 1
	maxvalue 999999
	minvalue 0
	nocache
	cycle;

CREATE TABLE TB_COMPRA_PRODUTO(
	ID_COMPRA			NUMBER,
	ID_PRODUTO			NUMBER,
	CONSTRAINT COMPRA_COMPRA_PRODUTO_FK	FOREIGN KEY (ID_COMPRA) REFERENCES TB_COMPRAS (ID_COMPRA),
	CONSTRAINT PRODUTO_COMPRA_PRODUTO_FK FOREIGN KEY (ID_PRODUTO) REFERENCES TB_PRODUTO(ID_PRODUTO)
);

CREATE SEQUENCE SEQ_COMPRA_PRODUTO
	start with 0
	increment by 1
	maxvalue 999999
	minvalue 0
	nocache
	cycle;

CREATE TABLE TB_VALIDA_AVALIACAO(
	ID_VALIDA_AVALIACAO	NUMBER,
	ID_PRODUTO			NUMBER,
	ID_GERADO			NUMBER,
	BIT_EXCL_LOGIC		VARCHAR2(1),
	CONSTRAINT VALIDA_AVALIACAO_PK PRIMARY KEY (ID_VALIDA_AVALIACAO),
	CONSTRAINT PRODUTO_VALIDA_AVALIACAO_FK FOREIGN KEY (ID_PRODUTO) REFERENCES TB_PRODUTO(ID_PRODUTO)
);

CREATE SEQUENCE SEQ_VALIDA_AVALIACAO
	start with 0
	increment by 1
	maxvalue 999999
	minvalue 0
	nocache
	cycle;