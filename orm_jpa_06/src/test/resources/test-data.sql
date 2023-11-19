insert into AUTHORS (id, name) values (10, 'PUSHKIN');
insert into AUTHORS (id, name) values (20, 'LERMONTOV');
insert into AUTHORS (id, name) values (100, 'author_helper');

insert into GENRES (id, name) values (10, 'HORROR');
insert into GENRES (id, name) values (20, 'DRAMA');
insert into GENRES (id, name) values (100, 'genre_helper');

insert into BOOKS (id, name, author_id, genre_id) values (10, 'RUSALKA', 10, 20);
insert into BOOKS (id, name, author_id, genre_id) values (20, 'MASQARAD', 20, 20);
