package br.com.stefanini.services;

import br.com.stefanini.dao.EquipeDao;
import br.com.stefanini.models.Equipe;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

/**
 * @author leandro
 * @version 0.1.0
 * @email leandrojoapi@gmail.com
 * @created 02/10/2021 on 23:59
 */
@RequestScoped
public class EquipeService {

    @Inject
    EquipeDao dao;

    @Transactional(rollbackOn = Exception.class)
    public void inserir(Equipe equipeDto) {
        this.validar(equipeDto);
        dao.inserir(equipeDto);
    }

    private void validar(Equipe equipeDto) throws NotFoundException {
        if(equipeDto == null){
            throw new NotFoundException();
        }
        if(equipeDto.getNome() == null){
            throw new NotFoundException();
        }
    }
}
