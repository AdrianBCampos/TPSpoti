CREATE TABLE IF NOT EXISTS instrumentos (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            nombre VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS artistas (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
                                        genero VARCHAR(50),
                                        pais VARCHAR(50),
                                        fecha_nacimiento DATE,
                                        fecha_fallecimiento DATE,
                                        biografia TEXT
    );

CREATE TABLE IF NOT EXISTS canciones (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
                                        letra TEXT,
                                        genero VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS discos (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    nombre VARCHAR(255) NOT NULL,
                                    genero VARCHAR(50),
                                    fecha_lanzamiento DATE,
                                    artista_id BIGINT,
    FOREIGN KEY (artista_id) REFERENCES artistas(id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS artista_instrumentos (
                                                    artista_id BIGINT,
                                                    instrumento_id INT,
                                                    PRIMARY KEY (artista_id, instrumento_id),
    FOREIGN KEY (artista_id) REFERENCES artistas(id) ON DELETE CASCADE,
    FOREIGN KEY (instrumento_id) REFERENCES instrumentos(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS disco_canciones (
                                               disco_id BIGINT,
                                               cancion_id BIGINT,
                                               PRIMARY KEY (disco_id, cancion_id),
    FOREIGN KEY (disco_id) REFERENCES discos(id) ON DELETE CASCADE,
    FOREIGN KEY (cancion_id) REFERENCES canciones(id) ON DELETE CASCADE
    );
