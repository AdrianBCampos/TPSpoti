CREATE TABLE IF NOT EXISTS canciones (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL,
                                         letra TEXT,
                                         genero VARCHAR(50)
    );

-- Inserción de datos en la tabla canciones
INSERT INTO canciones (nombre, letra, genero) VALUES ('Turing', 'Letra de Turing', 'ROCK');
INSERT INTO canciones (nombre, letra, genero) VALUES ('Lovelace', 'Letra de Lovelace', 'POP');