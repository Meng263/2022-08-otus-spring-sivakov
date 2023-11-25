DROP TABLE IF EXISTS PERSONS;
CREATE TABLE AUTHORS
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    author_id bigint references AUTHORS (id) on delete cascade on update cascade,
    genre_id  bigint references GENRES (id) on delete cascade on update cascade
);

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    text    VARCHAR(255) NOT NULL,
    book_id bigint references BOOKS (id) on delete cascade on update cascade
);
