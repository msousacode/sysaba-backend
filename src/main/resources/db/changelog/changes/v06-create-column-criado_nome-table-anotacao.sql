--liquibase formatted create table vbmapp_barreira

--changeset michael:7
ALTER TABLE public.anotacoes ADD criado_por_nome varchar(100);