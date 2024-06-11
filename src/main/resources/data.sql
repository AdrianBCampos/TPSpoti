CREATE TABLE IF NOT EXISTS canciones (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL,
                                         letra TEXT,
                                         genero VARCHAR(50)
    );

