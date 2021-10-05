package br.com.stefanini.models;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 06:35
 */
@Entity
@Table(name="pessoa", schema="H2DB")
@NamedNativeQueries({
        @NamedNativeQuery(name="INSERIR_PESSOA", query = " INSERT INTO H2DB.pessoa (nome, sobrenome, contato, email, cargo, equipe) " +
                "VALUES (:nome, :sobrenome, :contato, :email, :cargo, :equipe) "),
        @NamedNativeQuery(name="LISTAR_PESSOA", query = "SELECT id, nome, sobrenome, contato, email, cargo, equipe FROM H2DB.pessoa ", resultClass = Pessoa.class),
        @NamedNativeQuery(name="LISTARUM_PESSOA", query = "SELECT id, nome, sobrenome, contato, email, cargo, equipe FROM H2DB.pessoa WHERE id = :id", resultClass = Pessoa.class),
        @NamedNativeQuery(name="DELETAR_PESSOA", query = "DELETE FROM H2DB.pessoa WHERE id = :id"),
        @NamedNativeQuery(name="ATUALIZAR_PESSOA", query = "UPDATE H2DB.pessoa SET contato = :contato, email = :email, cargo = :cargo, equipe = :equipe WHERE id = :id"),
})
public class Pessoa implements Serializable{
	@Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "contato", nullable = false)
    private String contato;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cargo")
    private Integer cargo;

    @ManyToOne
    @JoinColumn(name = "equipe")
    private Equipe equipe;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
