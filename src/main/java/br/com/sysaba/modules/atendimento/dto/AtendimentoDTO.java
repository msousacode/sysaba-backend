package br.com.sysaba.modules.atendimento.dto;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.profissional.ProfissionalDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDTO {

    @JsonAlias({"atendimentoId", "uuid"})
    private String uuid;

    private AprendizDTO aprendiz;

    @JsonProperty("data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    private boolean sync;

    private List<TreinamentoItemDTO> treinamentos;

    private String aprendizUuidFk;

    private List<ProfissionalDTO> profissionais;

    // Getters e Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public AprendizDTO getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(AprendizDTO aprendiz) {
        this.aprendiz = aprendiz;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public List<TreinamentoItemDTO> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<TreinamentoItemDTO> treinamentos) {
        this.treinamentos = treinamentos;
    }

    public String getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(String aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public static AtendimentoDTO fromAtendimentoList(Atendimento atendimento) {

        AprendizDTO aprendizDTO = new AprendizDTO();
        aprendizDTO.setLabel(atendimento.getAprendiz().getNomeAprendiz());
        aprendizDTO.setValue(String.valueOf(atendimento.getAprendiz().getAprendizId()));

        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        atendimentoDTO.setUuid(String.valueOf(atendimento.getAtendimentoId()));

        List<TreinamentoItemDTO> list = new ArrayList<>();

        atendimento.getTreinamentoAtendimentos().forEach(i -> {
            TreinamentoItemDTO treinamentoItemDTO = MapperUtil.converte(i.getTreinamento(), TreinamentoItemDTO.class);
            ConfiguracoesDTO config = MapperUtil.converte(i.getConfiguracoes(), ConfiguracoesDTO.class);
            treinamentoItemDTO.setConfiguracoes(config);
            list.add(treinamentoItemDTO);
        });

        atendimentoDTO.setTreinamentos(list);
        atendimentoDTO.setDataInicio(atendimento.getDataInicio());
        atendimentoDTO.setAprendizUuidFk(aprendizDTO.getValue());
        atendimentoDTO.setAprendiz(aprendizDTO);

        return atendimentoDTO;
    }

    public static AtendimentoDTO fromAtendimento(Atendimento saved) {

        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
        TreinamentoItemDTO treinamentoItem = new TreinamentoItemDTO();

        atendimentoDTO.setDataInicio(saved.getDataInicio());

        AprendizDTO aprendizDTO = new AprendizDTO();
        aprendizDTO.setLabel(saved.getAprendiz().getNomeAprendiz());
        aprendizDTO.setValue(String.valueOf(saved.getAprendiz().getAprendizId()));
        atendimentoDTO.setAprendiz(aprendizDTO);

        atendimentoDTO.setUuid(String.valueOf(saved.getAtendimentoId()));
        atendimentoDTO.setAprendizUuidFk(String.valueOf(saved.getAprendiz().getAprendizId()));
        atendimentoDTO.setTreinamentos(List.of(treinamentoItem));

        return atendimentoDTO;
    }

    public List<ProfissionalDTO> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<ProfissionalDTO> profissionais) {
        this.profissionais = profissionais;
    }
}