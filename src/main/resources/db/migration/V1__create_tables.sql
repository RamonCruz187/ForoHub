CREATE TABLE categorias (
                            id VARCHAR(255) PRIMARY KEY
);

CREATE TABLE cursos (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        nombre VARCHAR(255) NOT NULL,
                        categoria_id VARCHAR(255) NOT NULL,
                        FOREIGN KEY (categoria_id) REFERENCES categorias (id)
);

CREATE TABLE usuarios (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          username VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL
);

CREATE TABLE topicos (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje VARCHAR(255) NOT NULL,
                         fecha_creacion TIMESTAMP NOT NULL,
                         status BOOLEAN NOT NULL,
                         curso_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (curso_id) REFERENCES cursos (id),
                         FOREIGN KEY (user_id) REFERENCES usuarios (id)



);

CREATE TABLE respuestas (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            mensaje VARCHAR(255) NOT NULL,
                            fecha_creacion TIMESTAMP NOT NULL,
                            topico_id BIGINT NOT NULL,
                            user_id BIGINT NOT NULL,
                            FOREIGN KEY (topico_id) REFERENCES topicos (id),
                            FOREIGN KEY (user_id) REFERENCES usuarios (id)
);

-- Insertar categorías
INSERT INTO categorias (id) VALUES ('FRONTEND'), ('BACKEND'), ('MOBILE'), ('DATA_SCIENCE'), ('CLOUD'), ('DEVOPS');