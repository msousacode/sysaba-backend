--liquibase formatted create table vbmapp_barreira

--changeset michael:5
 create table ablls_avaliacoes (
        ablls_id uuid not null,
        ativo boolean not null,
        created_at timestamp(6) not null,
        criado_por uuid,
        tenant_id uuid not null,
        habilidate_coleta varchar(255) not null,
        objetivo_documento varchar(255),
        protocolo varchar(255),
        aprendiz_id uuid not null,
        primary key (ablls_id)
    );

 alter table if exists ablls_avaliacoes
        add constraint FK4g7ykcni81lqnknnm9814u3a0
        foreign key (aprendiz_id)
        references aprendizes;