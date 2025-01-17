--liquibase formatted create table vbmapp_barreira

--changeset michael:1
create table treinamentos_base (
        treinamento_base_id uuid not null,
        descricao varchar(1000) not null,
        habilidade varchar(255) not null check (habilidade in ('ATENCAO','IMITACAO','LINGUAGEM_RECEPTIVA','LINGUAGEM_EXPRESSIVA','PRE_ACADEMICAS','MEMORIA','COORDENACAO','RACIOCINIO','SOCIALIZACAO','AUTOAJUDA')),
        protocolo varchar(255) not null check (protocolo in ('ABC','OCORRENCIA_RESPOSTA')),
        titulo varchar(255) not null,
        primary key (treinamento_base_id)
    );
--rollback drop table treinamentos_base;

create table treinamentos_objetivos_base (
        treinamento_objetivo_id uuid not null,
        descricao varchar(1000) not null,
        titulo varchar(255) not null,
        treinamento_base_id uuid not null,
        primary key (treinamento_objetivo_id)
    );
--rollback drop table treinamentos_objetivos_base;

alter table if exists treinamentos_objetivos_base
       add constraint FKe3quo1b31gp1slvu189ordv3o
       foreign key (treinamento_base_id)
       references treinamentos_base;