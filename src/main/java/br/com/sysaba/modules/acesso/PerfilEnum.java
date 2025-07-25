package br.com.sysaba.modules.acesso;

import java.util.HashMap;
import java.util.Map;

public enum PerfilEnum {
    ADMIN("Administrador"),
    ESPECIALISTA("Especialista"),
    AT("AT"),
    CHECKIN("CHECKIN"),
    ADMIN_CHECKIN("ADMIN_CHECKIN"),
    CONVIDADO("Convidado"),
    ;

    private String perfil;

    PerfilEnum(String perfil) {
        this.perfil = perfil;
    }

    private static final Map<String, PerfilEnum> lookup = new HashMap<>();

    static {
        for (PerfilEnum s : PerfilEnum.values()) {
            lookup.put(s.getPerfil(), s);
        }
    }

    public static PerfilEnum getEnum(String key) {
        return lookup.get(key);
    }

    public String getPerfil() {
        return perfil;
    }
}
