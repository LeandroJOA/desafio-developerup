package br.com.stefanini.services;

import br.com.stefanini.dao.EquipeDao;
import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Equipe;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

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
    public void inserir(Equipe equipeDto) throws Exception {
        this.validar(equipeDto);
        dao.inserir(equipeDto);
    }

    public List<Equipe> listar() throws Exception {
        return  dao.listar();
    }

    public Equipe listarUm(Integer id) throws Exception {
        return dao.listarUm(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deletar(Integer id) throws Exception {
        Integer delete = dao.deletar(id);

        if (delete == 0) {
            throw new NotFoundException();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void atualizar(Integer id, Equipe equipeDto) throws Exception {
        this.validar(equipeDto);
        Integer put = dao.atualizar(id, equipeDto);

        if (put == 0) {
            throw new NotFoundException();
        }
    }

    private void validar(Equipe equipeDto) throws NotFoundException {
        if(equipeDto == null){
            throw new IllegalArgumentException();
        }
        if(equipeDto.getNome() == null){
            throw new IllegalArgumentException();
        }
    }
}
