package br.com.sysaba.modules.atendimento.dto;

import br.com.sysaba.modules.atendimento.Atendimento;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class AtendimentoDTO {

    @JsonAlias({"treinamentoId", "uuid"})
    private String uuid;

    private AprendizDTO aprendiz;

    @JsonProperty("data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    private boolean sync;

    private List<TreinamentoItemDTO> treinamentos;

    private String aprendizUuidFk;

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

        //List<TreinamentoItemDTO> list = atendimento.getTreinamento().stream().map(TreinamentoItemDTO::fromDTO).toList();

        //atendimentoDTO.setTreinamentos(list);
        atendimentoDTO.setDataInicio(atendimento.getDataInicio());
        atendimentoDTO.setAprendizUuidFk(aprendizDTO.getValue());
        atendimentoDTO.setAprendiz(aprendizDTO);

        return atendimentoDTO;
    }
}