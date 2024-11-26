package br.com.sysaba.modules.termo;

import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "termos")
public class Termo {

    @Id
    @GeneratedValue
    @Column(name = "termo_id")
    private UUID termoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_aceite")
    private LocalDate dataAceite;

    @Column(name = "aceite")
    private boolean aceite;

    public Termo() {
        this.dataAceite = LocalDate.now();
        this.aceite = true;
    }

    public Termo(UUID termoId, Usuario usuario, LocalDate dataAceite, boolean aceite) {
        this.termoId = termoId;
        this.usuario = usuario;
        this.dataAceite = dataAceite;
        this.aceite = aceite;
    }

    public UUID getTermoId() {
        return termoId;
    }

    public void setTermoId(UUID termoId) {
        this.termoId = termoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataAceite() {
        return dataAceite;
    }

    public void setDataAceite(LocalDate dataAceite) {
        this.dataAceite = dataAceite;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }
}
