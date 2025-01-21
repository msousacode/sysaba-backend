--liquibase formatted create table vbmapp_barreira

--changeset michael:3
ALTER TABLE public.usuarios ADD perfil varchar(25);