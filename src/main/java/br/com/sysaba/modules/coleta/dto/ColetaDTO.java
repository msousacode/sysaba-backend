package br.com.sysaba.modules.coleta.dto;

import br.com.sysaba.modules.alvo.dto.AlvoDTO;
import br.com.sysaba.modules.coleta.Coleta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.UUID;

public class    ColetaDTO {
    private UUID coletaId;

    @JsonProperty("aprendiz_uuid_fk")
    private UUID aprendizUuidFk;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("data_coleta")
    private LocalDate dataColeta;

    private String resposta;

    private boolean sync;

    @JsonProperty("foi_respondido")
    private boolean foiRespondido;

    private AlvoDTO alvo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("data_final_coleta")
    private LocalDate dataFinalColeta;

    @JsonProperty("respondido_por")
    private String respondidoPor;

    private boolean seg;
    private boolean ter;
    private boolean qua;
    private boolean qui;
    private boolean sex;
    private boolean sab;
    private int semana;
    private boolean ativo;

    @JsonProperty("treinamento_uuid_fk")
    private UUID treinamentoId;

    public ColetaDTO() {
    }

    public ColetaDTO(UUID uuid, UUID aprendizUuidFk, LocalDate dataColeta, String resposta, boolean sync, boolean foiRespondido, AlvoDTO alvo, LocalDate dataFinalColeta, boolean seg, boolean ter, boolean qua, boolean qui, boolean sex, boolean sab, int semana, boolean ativo) {
        this.coletaId = uuid;
        this.aprendizUuidFk = aprendizUuidFk;
        this.dataColeta = dataColeta;
        this.resposta = resposta;
        this.sync = sync;
        this.foiRespondido = foiRespondido;
        this.alvo = alvo;
        this.dataFinalColeta = dataFinalColeta;
        this.seg = seg;
        this.ter = ter;
        this.qua = qua;
        this.qui = qui;
        this.sex = sex;
        this.sab = sab;
        this.semana = semana;
        this.ativo = ativo;
    }

    public static ColetaDTO converte(Coleta coleta) {
        ColetaDTO dto = new ColetaDTO();

        AlvoDTO alvoDTO = new AlvoDTO();
        alvoDTO.setAlvoId(String.valueOf(coleta.getAlvo().getAlvoId()));
        alvoDTO.setDescricaoAlvo(coleta.getAlvo().getDescricaoAlvo());
        alvoDTO.setNomeAlvo(coleta.getAlvo().getNomeAlvo());
        alvoDTO.setPergunta(coleta.getAlvo().getPergunta());

        dto.setColetaId(coleta.getColetaId());
        dto.setDataColeta(coleta.getDataColeta() != null ? LocalDate.from(coleta.getDataColeta()) : null);
        dto.setResposta(coleta.getResposta());
        dto.setFoiRespondido(coleta.isFoiRespondido());
        dto.setAlvo(alvoDTO);
        dto.setSeg(coleta.isSeg());
        dto.setTer(coleta.isTer());
        dto.setQua(coleta.isQua());
        dto.setQui(coleta.isQui());
        dto.setSex(coleta.isSex());
        dto.setSemana(coleta.getSemana());
        dto.setTreinamentoId(coleta.getTreinamento().getTreinamentoId());
        dto.setRespondidoPor(coleta.getCriadoNome());

        return dto;
    }

    public UUID getColetaId() {
        return coletaId;
    }

    public void setColetaId(UUID coletaId) {
        this.coletaId = coletaId;
    }

    public UUID getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(UUID aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isFoiRespondido() {
        return foiRespondido;
    }

    public void setFoiRespondido(boolean foiRespondido) {
        this.foiRespondido = foiRespondido;
    }

    public AlvoDTO getAlvo() {
        return alvo;
    }

    public void setAlvo(AlvoDTO alvo) {
        this.alvo = alvo;
    }

    public LocalDate getDataFinalColeta() {
        return dataFinalColeta;
    }

    public void setDataFinalColeta(LocalDate dataFinalColeta) {
        this.dataFinalColeta = dataFinalColeta;
    }

    public boolean isSeg() {
        return seg;
    }

    public void setSeg(boolean seg) {
        this.seg = seg;
    }

    public boolean isTer() {
        return ter;
    }

    public void setTer(boolean ter) {
        this.ter = ter;
    }

    public boolean isQua() {
        return qua;
    }

    public void setQua(boolean qua) {
        this.qua = qua;
    }

    public boolean isQui() {
        return qui;
    }

    public void setQui(boolean qui) {
        this.qui = qui;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isSab() {
        return sab;
    }

    public void setSab(boolean sab) {
        this.sab = sab;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public String getRespondidoPor() {
        return respondidoPor;
    }

    public void setRespondidoPor(String respondidoPor) {
        this.respondidoPor = respondidoPor;
    }
}

