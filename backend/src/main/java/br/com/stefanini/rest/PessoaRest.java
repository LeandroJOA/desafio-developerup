package br.com.stefanini.rest;

import br.com.stefanini.models.Pessoa;
import br.com.stefanini.services.PessoaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:10
 */
@RequestScoped
@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaRest {

    @Inject
    PessoaService service;
    @POST
    @Operation(summary = "Inserir pessoa",
            description = "Com os dados faz a validação e inserção na base de dados")
    @APIResponse(
            responseCode = "201",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response inserirPessoa(Pessoa pessoa) {
        try {
            service.inserir(pessoa);
            return  Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao inserir! Campos Nome, Sobrenome, E-Mail e Contato são obrigatorios!").build();
        } catch (RuntimeException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao inserir! Email ou Contato já cadastrado!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao inserir! Problema no servidor!").build();
        }
    }
    @GET
    @Operation(summary = "Listar pessoas",
            description = "Retorna uma lista de pessoas sem a necessidade de parametros")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response listarPessoa() {
        try {
            return  Response.status(Response.Status.OK).entity(service.listar()).build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao listar! Problema no servidor!").build();
        }
    }
    @GET @Path("{id}")
    @Operation(summary = "Listar uma pessoa",
            description = "Retorna uma pessoa a partir do ID")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response listarUmPessoa(@PathParam("id") Integer id) {
        try {
            return  Response.status(Response.Status.OK).entity(service.listarUm(id)).build();
        } catch (NoResultException error) {
            error.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("ERROR ao buscar! ID não encontrado!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao buscar! Problema no servidor!").build();
        }
    }
    @DELETE @Path("{id}")
    @Operation(summary = "Deletar pessoa",
    		description = "Faz a validação e deleta uma pessoa na base de dados")
    @APIResponse(
    		responseCode = "200",
    		description = "Pessoa",
    		content = { @Content(mediaType = "application/json",
            	schema = @Schema(implementation = Pessoa.class))})
    public Response deletarPessoa(@PathParam("id") Integer id) {
        try {
            service.deletar(id);
            return  Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            error.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("ERROR ao deletar! ID não encontrado!").build();
        } catch (RuntimeException error) {
            error.printStackTrace();
            return Response.status(Response.Status.CONFLICT).entity("ERROR ao deletar! Não é possivel excluir um funcionario enquanto associado à uma equipe!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao deletar! Problema no servidor!").build();
        }
    }
    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar pessoa",
            description = "Faz a validação e atualiza uma pessoa na base de dados")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response atualizarPessoa(@PathParam("id") Integer id, Pessoa pessoa) {
        try {
            service.atualizar(id, pessoa);
            return  Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            error.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("ERROR ao atualizar! ID não encontrado!").build();
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao atualizar! Campos E-Mail e Contato são obrigatorios!").build();
        } catch (RuntimeException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao atualizar! Email ou Contato já cadastrado!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao atualizar! Problema no servidor!").build();
        }
    }
}
