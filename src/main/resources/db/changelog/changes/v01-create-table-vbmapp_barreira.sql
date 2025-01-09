--liquibase formatted create table vbmapp_barreira

--changeset michael:1
create table vbmapp_barreiras (
        vbmapp_barreira_id uuid not null,
        ativo boolean not null,
        codigo int not null,
        created_at timestamp(6) not null,
        criado_por uuid,
        tenant_id uuid not null,
        descricao varchar(255) not null,
        questao varchar(255) not null,
        resposta varchar(255) not null,
        aprendiz_id uuid not null,
        primary key (vbmapp_barreira_id)
    );
--rollback drop table vbmapp_barreiras;

alter table if exists vbmapp_barreiras
       add constraint FKgan6u60j6oty7lkw57hsc8ilk
       foreign key (aprendiz_id)
       references aprendizes;