CREATE SCHEMA IF NOT EXISTS H2DB AUTHORIZATION sa;

create table H2DB.equipe
(
    id int not null,
    nome varchar(255) not null,
);

create unique index equipe_id_uindex
    on H2DB.equipe (id);

alter table H2DB.equipe
    add constraint equipe_pk
        primary key (id);

alter table H2DB.equipe modify id int auto_increment;

create table H2DB.pessoa
(
    id int not null,
    codigo_equipe int not null,
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    contato varchar(200) not null,
    email varchar(255) not null,
    cargo int
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

alter table H2DB.pessoa modify id int auto_increment;

alter table H2DB.pessoa
    add foreign key (codigo_equipe)
        references H2DB.equipe(id);



