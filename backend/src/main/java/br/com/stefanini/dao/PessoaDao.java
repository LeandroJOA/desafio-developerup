package br.com.stefanini.dao;

import br.com.stefanini.models.Pessoa;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:13
 */
@RequestScoped
public class PessoaDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Integer inserir(Pessoa pessoa) throws Exception {
        String nameQuery = "INSERIR_PESSOA";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("nome", pessoa.getNome());
        query.setParameter("sobrenome", pessoa.getSobrenome());
        query.setParameter("contato", pessoa.getContato());
        query.setParameter("email", pessoa.getEmail());
        query.setParameter("cargo", pessoa.getCargo());
        query.setParameter("equipe", pessoa.getEquipe());

        return query.executeUpdate();
    }

    public List<Pessoa> listar() throws Exception {
        String nameQuery = "LISTAR_PESSOA";
        TypedQuery<Pessoa> query = em
                .createNamedQuery(nameQuery, Pessoa.class);
        return query.getResultList();

    }

    public Pessoa listarUm(Integer id) throws Exception {
        String nameQuery = "LISTARUM_PESSOA";

        TypedQuery<Pessoa> query = em
                .createNamedQuery(nameQuery, Pessoa.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }
    
    @Transactional
    public Integer deletar(Integer id) throws Exception {
        String nameQuery = "DELETAR_PESSOA";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id", id);

        return query.executeUpdate();
    }

    @Transactional
    public Integer atualizar(Integer id, Pessoa Pessoa) throws Exception {
        String nameQuery = "ATUALIZAR_PESSOA";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id", id);
        query.setParameter("contato", Pessoa.getContato());
        query.setParameter("email", Pessoa.getEmail());
        query.setParameter("cargo", Pessoa.getCargo());
        query.setParameter("equipe", Pessoa.getEquipe());

        return query.executeUpdate();
    }
}
