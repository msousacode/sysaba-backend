package br.com.sysaba.modules.cargo;

import br.com.sysaba.core.models.Tenantable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cargos")
public class Cargo extends Tenantable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cargo_id")
    private UUID cargoId;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private String preco;

    public Cargo(LocalDateTime createdAt) {
        super(createdAt);
        super.setAtivo(true);
    }

    public Cargo(){
        super(LocalDateTime.now());
    }

    public UUID getCargoId() {
        return cargoId;
    }

    public void setCargoId(UUID cargoId) {
        this.cargoId = cargoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
