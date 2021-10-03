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
@NamedNativeQueries({
        @NamedNativeQuery(name="INSERIR_EQUIPE", query = " INSERT INTO H2DB.equipe (nome) " +
                "VALUES (:nome) "),
        @NamedNativeQuery(name="LISTAR_EQUIPE", query = "SELECT id, nome FROM H2DB.equipe ", resultClass = Equipe.class),
        @NamedNativeQuery(name="DELETAR_EQUIPE", query = "DELETE FROM H2DB.equipe WHERE id = :id"),
})
public class Equipe implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
