--liquibase formatted create table vbmapp_barreira

--changeset michael:10
create table faturamento (
        faturamento_id uuid not null,
        profissional_id uuid not null,
        nome_profissional varchar(50) not null,
        cargo_profissional varchar(50) not null,
        preco varchar(50) not null,
        aprendiz_id uuid not null,
        nome_aprendiz varchar(255) not null,
        ausencia_justificada boolean,
        descricao_justificada varchar(255),
        presente boolean not null,
        dia int not null,
        mes int not null,
        ano int not null,
        situacao varchar(255) not null check (situacao in ('PENDENTE','PAGO')),
        created_at timestamp(6) not null,
        tenant_id uuid not null,
        ativo boolean not null,
        criado_por uuid,
        primary key (faturamento_id)
    );