--liquibase formatted create table cargos

--changeset michael:9
ALTER TABLE public.usuarios ADD COLUMN cargo_id uuid;

alter table if exists usuarios
       add constraint fk_usuarios_cargos
       foreign key (cargo_id)
       references cargos;