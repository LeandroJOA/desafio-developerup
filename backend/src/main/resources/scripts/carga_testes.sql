CREATE SCHEMA IF NOT EXISTS H2DB AUTHORIZATION sa;

create table H2DB.equipe
(
    id integer not null,
    nome varchar(255) not null,
);

create unique index equipe_id_uindex
    on H2DB.equipe (id);

alter table H2DB.equipe
    add constraint equipe_pk
        primary key (id);

alter table H2DB.equipe modify id integer auto_increment;

create table H2DB.pessoa
(
    id integer not null,
    equipe integer,
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    contato varchar(200) not null,
    email varchar(255) not null,
    cargo integer
);

create unique index pessoa_contato_uindex
    on H2DB.pessoa (contato);

create unique index pessoa_email_uindex
    on H2DB.pessoa (email);

create unique index pessoa_id_uindex
    on H2DB.pessoa (id);

alter table H2DB.pessoa
    add constraint pessoa_pk
        primary key (id);

alter table H2DB.pessoa modify id integer auto_increment;

alter table H2DB.pessoa
    add foreign key (equipe)
        references H2DB.equipe(id);



