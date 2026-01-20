CREATE DATABASE IF NOT EXISTS filmes_db;
USE filmes_db;

DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS filmes;


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);


CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);


CREATE TABLE users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE filmes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    nota DECIMAL(3,1),
    sinopse TEXT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password) VALUES('admin', '$2a$10$3d/vJIWwOSq0fiiFgdX2FOn8Adc8pJwqphSRQ9VjpTQerSMC7iHaG');
INSERT INTO users (username, password) VALUES('user', '$2a$10$3d/vJIWwOSq0fiiFgdX2FOn8Adc8pJwqphSRQ9VjpTQerSMC7iHaG');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Poderoso Chefao', 'Drama', 1972, 9.2, 'Classico do cinema');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Poderoso Chefao II', 'Drama', 1974, 9.0, 'Continuacao consagrada');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Interestelar', 'Ficcao Cientifica', 2014, 8.6, 'Viagem no espaco-tempo');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Clube da Luta', 'Drama', 1999, 8.8, 'Psicologico e impactante');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Forrest Gump', 'Drama', 1994, 8.8, 'Inspirador');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Matrix', 'Ficcao Cientifica', 1999, 8.7, 'Revolucao visual');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Gladiador', 'Acao', 2000, 8.5, 'Epico romano');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Cidade de Deus', 'Drama', 2002, 8.6, 'Realidade brasileira');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Batman: O Cavaleiro das Trevas', 'Acao', 2008, 9.0, 'Coringa iconico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Pulp Fiction', 'Crime', 1994, 8.9, 'Narrativa nao linear');

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('A Origem', 'Ficcao Cientifica', 2010, 8.8, 'Sonhos dentro de sonhos');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Titanic', 'Drama', 1997, 7.9, 'Romance tragico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Star Wars: Uma Nova Esperanca', 'Ficcao Cientifica', 1977, 8.6, 'Inicio da saga');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Jurassic Park', 'Aventura', 1993, 8.2, 'Dinossauros realistas');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Toy Story', 'Animacao', 1995, 8.3, 'Pixar classico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Senhor dos Aneis: A Sociedade do Anel', 'Fantasia', 2001, 8.8, 'Fantasia epica');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Senhor dos Aneis: As Duas Torres', 'Fantasia', 2002, 8.7, 'Continuacao epica');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Senhor dos Aneis: O Retorno do Rei', 'Fantasia', 2003, 9.0, 'Final memoravel');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Vingadores: Ultimato', 'Acao', 2019, 8.4, 'Conclusao epica');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Homem Aranha: Sem Volta Para Casa', 'Acao', 2021, 8.3, 'Multiverso');

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Silencio dos Inocentes', 'Suspense', 1991, 8.6, 'Thriller psicologico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Se7en', 'Crime', 1995, 8.6, 'Investigacao sombria');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Os Infiltrados', 'Crime', 2006, 8.5, 'Mafia e traicao');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Django Livre', 'Faroeste', 2012, 8.4, 'Vinganca e liberdade');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Bastardos Inglorios', 'Guerra', 2009, 8.3, 'Releitura da Segunda Guerra');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Coringa', 'Drama', 2019, 8.4, 'Origem do vilao');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Parasita', 'Drama', 2019, 8.5, 'sinopse social');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Whiplash', 'Drama', 2014, 8.5, 'Perfeccionismo extremo');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Resgate do Soldado Ryan', 'Guerra', 1998, 8.6, 'Realismo da guerra');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Lobo de Wall Street', 'Drama', 2013, 8.2, 'Excessos do mercado financeiro');

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('A Lista de Schindler', 'Drama', 1993, 9.0, 'Holocausto e humanidade');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Iluminado', 'Terror', 1980, 8.4, 'Terror psicologico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('De Volta para o Futuro', 'Ficcao Cientifica', 1985, 8.5, 'Viagem no tempo');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Os Caca-Fantasmas', 'Comedia', 1984, 7.8, 'Classico divertido');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Indiana Jones e os Cacadores da Arca Perdida', 'Aventura', 1981, 8.4, 'Aventura classica');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Exterminador do Futuro 2', 'Acao', 1991, 8.6, 'Classico da acao');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Alien: O Oitavo Passageiro', 'Terror', 1979, 8.5, 'Suspense espacial');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Blade Runner', 'Ficcao Cientifica', 1982, 8.1, 'Futuro distopico');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Mad Max: Estrada da Furia', 'Acao', 2015, 8.1, 'Acao intensa');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Rocky: Um Lutador', 'Drama', 1976, 8.1, 'Superacao pessoal');

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Os Suspeitos', 'Suspense', 2013, 8.1, 'Misterio envolvente');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Her', 'Drama', 2013, 8.0, 'Relacionamento com IA');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Ex Machina', 'Ficcao Cientifica', 2014, 7.7, 'Consciencia artificial');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Gravidade', 'Ficcao Cientifica', 2013, 7.7, 'Sobrevivencia no espaco');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Cisne Negro', 'Drama', 2010, 8.0, 'Obsessao e arte');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('A Vida e Bela', 'Drama', 1997, 8.6, 'Esperanca em tempos dificeis');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Pianista', 'Drama', 2002, 8.5, 'Sobrevivencia na guerra');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Os Vingadores', 'Acao', 2012, 8.0, 'Uniao dos herois');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Capitao America: Guerra Civil', 'Acao', 2016, 7.8, 'Conflito entre herois');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Pantera Negra', 'Acao', 2018, 7.3, 'Representatividade');

INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Logan', 'Acao', 2017, 8.1, 'Fim de uma era');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Up: Altas Aventuras', 'Animacao', 2009, 8.2, 'Emocionante');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Divertida Mente', 'Animacao', 2015, 8.1, 'Emocoes humanas');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Wall-E', 'Animacao', 2008, 8.4, 'Futuro e humanidade');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Procurando Nemo', 'Animacao', 2003, 8.1, 'Aventura submarina');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Shrek', 'Animacao', 2001, 7.9, 'Conto de fadas moderno');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('O Rei Leao', 'Animacao', 1994, 8.5, 'Classico da Disney');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Harry Potter e a Pedra Filosofal', 'Fantasia', 2001, 7.6, 'Inicio da saga');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Harry Potter e o Prisioneiro de Azkaban', 'Fantasia', 2004, 7.9, 'Capitulo marcante');
INSERT INTO filmes (titulo, genero, ano, nota, sinopse) VALUES ('Animais Fantasticos e Onde Habitam', 'Fantasia', 2016, 7.2, 'Expansao do universo magico');



