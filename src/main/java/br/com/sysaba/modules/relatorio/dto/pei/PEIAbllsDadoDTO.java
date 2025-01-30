package br.com.sysaba.modules.relatorio.dto.pei;

import java.util.List;

/**
 * Regra de negócio do relatório:
 * Os objetivos prioritários são os aqueles que a criança está próximo de alcançar, por isso são colocados como prioritários.
 * Os demais objetivos são aqueles que o aprendiz apresentou mais dificuldade e precisam se trabalhados no longo tempo.
 */
public class PEIAbllsDadoDTO {
    private String titulo;
    private String habilidade;

    private List<PEIObjetivoDTO> objetivosPrioritariosAteDoisPontos;//Aqui estão os objetivos em que a criança marcou 1 ponto, quase 2 pontos.
    private List<PEIObjetivoDTO> objetivosPrioritariosAteQuatroPontos;//Aqui estão os objetivos em que a crinça marcou 2 ou 3 pontos, quase 4 pontos.

    private List<PEIObjetivoDTO> objetivosZeroPontosAteDoisPontos;//Aqui estão os objetivos que a criança marcou 0 pontos.
    private List<PEIObjetivoDTO> objetivosZeroAteUmPntoAteQuatroPontos;//Aqui estão os objetivos que a criança marcou 0 ou 1 ponto.

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public List<PEIObjetivoDTO> getObjetivosPrioritariosAteDoisPontos() {
        return objetivosPrioritariosAteDoisPontos;
    }

    public void setObjetivosPrioritariosAteDoisPontos(List<PEIObjetivoDTO> objetivosPrioritariosAteDoisPontos) {
        this.objetivosPrioritariosAteDoisPontos = objetivosPrioritariosAteDoisPontos;
    }

    public List<PEIObjetivoDTO> getObjetivosPrioritariosAteQuatroPontos() {
        return objetivosPrioritariosAteQuatroPontos;
    }

    public void setObjetivosPrioritariosAteQuatroPontos(List<PEIObjetivoDTO> objetivosPrioritariosAteQuatroPontos) {
        this.objetivosPrioritariosAteQuatroPontos = objetivosPrioritariosAteQuatroPontos;
    }

    public List<PEIObjetivoDTO> getObjetivosZeroPontosAteDoisPontos() {
        return objetivosZeroPontosAteDoisPontos;
    }

    public void setObjetivosZeroPontosAteDoisPontos(List<PEIObjetivoDTO> objetivosZeroPontosAteDoisPontos) {
        this.objetivosZeroPontosAteDoisPontos = objetivosZeroPontosAteDoisPontos;
    }

    public List<PEIObjetivoDTO> getObjetivosZeroAteUmPntoAteQuatroPontos() {
        return objetivosZeroAteUmPntoAteQuatroPontos;
    }

    public void setObjetivosZeroAteUmPntoAteQuatroPontos(List<PEIObjetivoDTO> objetivosZeroAteUmPntoAteQuatroPontos) {
        this.objetivosZeroAteUmPntoAteQuatroPontos = objetivosZeroAteUmPntoAteQuatroPontos;
    }
}
