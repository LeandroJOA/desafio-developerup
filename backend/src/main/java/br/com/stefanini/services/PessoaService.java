package br.com.stefanini.services;

import br.com.stefanini.dao.PessoaDao;
import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Pessoa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:11
 */
@RequestScoped
public class PessoaService {

    @Inject
    PessoaDao dao;

    @Transactional(rollbackOn = Exception.class)
    public void inserir(Pessoa pessoaDto) {
        this.validar(pessoaDto);
        this.validaEmailDuplicado(pessoaDto.getEmail());
        dao.inserir(pessoaDto);
    }


    public List<Pessoa> listar() throws ErroNegocialException {
        return  dao.listar();
    }

    private void validar(Pessoa pessoaDto) throws NotFoundException{
        if(pessoaDto == null){
            throw new NotFoundException();
        }
        if(pessoaDto.getNome() == null &&  pessoaDto.getSobrenome() == null){
            throw new NotFoundException();
        }
        if(pessoaDto.getEmail() == null ){
            throw new NotFoundException();
        }
        if(pessoaDto.getContato() == null ){
            throw new NotFoundException();
        }
    }

    private void validaEmailDuplicado(String email){
        System.out.println(email);
    }
    
    @Transactional(rollbackOn = Exception.class)
    public void deletar(Integer id) {
        dao.deletar(id);
    }
}
