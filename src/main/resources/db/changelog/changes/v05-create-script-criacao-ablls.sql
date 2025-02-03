--liquibase formatted create table vbmapp_barreira

--changeset michael:6
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

create table ablls_coletas (
        ablls_coleta_id uuid not null,
        ativo boolean not null,
        created_at timestamp(6) not null,
        criado_por uuid,
        tenant_id uuid not null,
        codigo varchar(255),
        coleta_id integer not null,
        pontuacao_max integer not null,
        criado_por_nome varchar(255),
        data_coleta timestamp(6) not null,
        descricao varchar(500),
        habilidade smallint not null check (habilidade between 0 and 23),
        resposta integer not null,
        ablls_id uuid not null,
        aprendiz_id uuid not null,
        primary key (ablls_coleta_id)
    );

alter table if exists ablls_coletas
       add constraint FK7b7he325s36wycxit4cmsebej
       foreign key (ablls_id)
       references ablls_avaliacoes;

alter table if exists ablls_coletas
       add constraint FK9348xmxq4hbe3oun67mj9e43j
       foreign key (aprendiz_id)
       references aprendizes;