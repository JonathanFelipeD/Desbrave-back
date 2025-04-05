package com.Desbrave.Desbrave.constants;

public enum TipoUsuario {
    ADMIN(0),
    USER(1);

    private final int codigo;

    TipoUsuario(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoUsuario fromCodigo(int codigo) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido para TipoUsuario: " + codigo);
    }
}
