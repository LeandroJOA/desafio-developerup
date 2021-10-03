package br.com.stefanini.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author leandro
 * @version 0.1.0
 * @email leandrojoapi@gmail.com
 * @created 02/10/2021 on 00:04
 */
@Entity
@Table(name="equipe", schema="H2DB")
public class Equipe implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
