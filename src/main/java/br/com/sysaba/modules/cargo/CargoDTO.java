package br.com.sysaba.modules.cargo;

import java.util.UUID;

public class CargoDTO {
    UUID cargoId;
    String descricao;
    String preco;

    public CargoDTO(){}

    public CargoDTO(String descricao, String preco, UUID cargoId) {
        this.cargoId = cargoId;
        this.descricao = descricao;
        this.preco = preco;
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
