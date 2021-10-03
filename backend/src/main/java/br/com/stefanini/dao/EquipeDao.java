package br.com.stefanini.dao;

import br.com.stefanini.models.Equipe;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
    public int inserir(Equipe equipe)  {
        String nameQuery = "INSERIR_EQUIPE";

        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("nome",equipe.getNome());

        return query.executeUpdate();
    }
}
