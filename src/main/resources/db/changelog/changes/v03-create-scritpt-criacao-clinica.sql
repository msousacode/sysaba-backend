--liquibase formatted create table vbmapp_barreira

--changeset michael:3
ALTER TABLE public.usuarios ADD perfil varchar(25);

alter table if exists alvos alter column descricao_alvo set data type varchar(500);

create table aprendizes_profissionais (
        aprendizes_profissionais_id uuid not null,
        aprendiz_id uuid not null,
        usuario_id uuid not null,
        primary key (aprendizes_profissionais_id)
    );

alter table if exists aprendizes_profissionais
   add constraint FK5ufckaev8b3c0ppaih7itnusc
   foreign key (aprendiz_id)
   references aprendizes;

alter table if exists aprendizes_profissionais
   add constraint FKhcq2g5xfjwf9c89lr6qf4gpsw
   foreign key (usuario_id)
   references usuarios;