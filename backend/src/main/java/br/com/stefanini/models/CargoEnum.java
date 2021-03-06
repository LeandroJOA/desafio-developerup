package br.com.stefanini.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 23/09/2021 on 20:18
 */
public enum CargoEnum {
    JR(1,"Analista de Sistemas Jr."),
    PL(2,"Analista de Sistemas Pl."),
    SR(3,"Analista de Sistemas Sr.");

    private Integer codigo;
    private String descricao;

    CargoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
