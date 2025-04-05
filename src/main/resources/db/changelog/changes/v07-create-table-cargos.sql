--liquibase formatted create table cargos

--changeset michael:8
create table cargos (
        cargo_id uuid not null,
        descricao varchar(255) not null,
        preco varchar(255) not null,
        ativo boolean not null,
        created_at timestamp(6) not null,
        criado_por uuid,
        tenant_id uuid not null,
        primary key (cargo_id)
    );