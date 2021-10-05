package br.com.stefanini.dao;

import br.com.stefanini.models.Equipe;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author leandro
 * @version 0.1.0
 * @email leandrojoapi@gmail.com
 * @created 02/10/2021 on 00:00
 */
@RequestScoped
public class EquipeDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Integer inserir(Equipe equipe) throws Exception {
        String nameQuery = "INSERIR_EQUIPE";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("nome",equipe.getNome());

        return query.executeUpdate();
    }

    public List<Equipe> listar() throws Exception {
        String nameQuery = "LISTAR_EQUIPE";

        TypedQuery<Equipe> query = em
                .createNamedQuery(nameQuery, Equipe.class);
        return query.getResultList();
    }

    public Equipe listarUm(Integer id) throws Exception {
        String nameQuery = "LISTARUM_EQUIPE";

        TypedQuery<Equipe> query = em
                .createNamedQuery(nameQuery, Equipe.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Transactional
    public Integer deletar(Integer id) throws Exception {
        String nameQuery = "DELETAR_EQUIPE";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id", id);

        return query.executeUpdate();
    }

    @Transactional
    public Integer atualizar(Integer id, Equipe novaEquipe) throws Exception {
        String nameQuery = "ATUALIZAR_EQUIPE";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id", id);
        query.setParameter("nome", novaEquipe.getNome());

        return query.executeUpdate();
    }
}
