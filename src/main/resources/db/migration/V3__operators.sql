CREATE TABLE operators (
  id bigint NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  password TEXT NOT NULL,
  registration TEXT,
  CONSTRAINT pk_operators PRIMARY KEY (id));