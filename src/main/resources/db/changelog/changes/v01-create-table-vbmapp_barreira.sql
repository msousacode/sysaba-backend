--liquibase formatted create table vbmapp_barreira

--changeset michael:1
create table vbmapp_barreiras (
    vbmapp_barreira_id uuid not null,
    ativo boolean not null,
    created_at timestamp(6) not null,
    criado_por uuid,
    tenant_id uuid not null,
    descricao varchar(300) not null,
    questao varchar(300) not null,
    resposta varchar(300) not null,
    aprendiz_id uuid not null,
    primary key (vbmapp_barreira_id)
);
--rollback drop table vbmapp_barreiras;

alter table if exists vbmapp_barreiras drop constraint if exists UKeqkqrouuo0d2d9pdg9a9y6dce;

alter table if exists vbmapp_barreiras add constraint UKeqkqrouuo0d2d9pdg9a9y6dce unique (aprendiz_id);

alter table if exists vbmapp_barreiras add constraint FKe77imxu272gnudrxurpu62ckd
   foreign key (aprendiz_id)
   references aprendizes;


