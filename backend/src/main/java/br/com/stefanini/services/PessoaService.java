package br.com.stefanini.services;

import br.com.stefanini.dao.PessoaDao;
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
    public void inserir(Pessoa pessoaDto) throws Exception {
        this.validar(pessoaDto, "POST");
        dao.inserir(pessoaDto);
    }

    public List<Pessoa> listar() throws Exception {
        return dao.listar();
    }

    public Pessoa listarUm(Integer id) throws Exception {
        return dao.listarUm(id);
    }
    
    @Transactional(rollbackOn = Exception.class)
    public void deletar(Integer id) throws Exception {
        Pessoa pessoa = listarUm(id);

        if (pessoa.getEquipe() == null) {
            Integer delete = dao.deletar(id);

            if (delete == 0) {
                throw new NotFoundException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void atualizar(Integer id, Pessoa pessoaDto) throws Exception {
        this.validar(pessoaDto, "PUT");
        Integer put = dao.atualizar(id, pessoaDto);

        if (put == 0) {
            throw new NotFoundException();
        }
    }

    private void validar(Pessoa pessoaDto, String metodo) throws NotFoundException{
        if(pessoaDto == null){
            throw new IllegalArgumentException();
        }
        if(pessoaDto.getEmail() == null ){
            throw new IllegalArgumentException();
        }
        if(pessoaDto.getContato() == null ){
            throw new IllegalArgumentException();
        }
        if (metodo == "POST") {
            if(pessoaDto.getNome() == null || pessoaDto.getSobrenome() == null){
                throw new IllegalArgumentException();
            }
        }
    }
}
