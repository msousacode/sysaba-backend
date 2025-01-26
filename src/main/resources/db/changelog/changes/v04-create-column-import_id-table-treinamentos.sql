--liquibase formatted create table vbmapp_barreira

--changeset michael:4
ALTER TABLE public.treinamentos ADD import_id uuid NULL;