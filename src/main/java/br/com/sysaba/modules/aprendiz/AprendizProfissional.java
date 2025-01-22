package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "aprendizes_profissionais")
public class AprendizProfissional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aprendizes_profissionais_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario profissional;

    public AprendizProfissional() {
    }

    public AprendizProfissional(UUID id, Aprendiz aprendiz, Usuario profissional) {
        this.id = id;
        this.aprendiz = aprendiz;
        this.profissional = profissional;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }
}
