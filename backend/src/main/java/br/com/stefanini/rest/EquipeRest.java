package br.com.stefanini.rest;

import br.com.stefanini.models.Equipe;
import br.com.stefanini.services.EquipeService;
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
 * @author leandro
 * @version 0.1.0
 * @email leandrojoapi@gmail.com
 * @created 02/10/2021 on 23:56
 */
@RequestScoped
@Path("/equipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipeRest {

    @Inject
    EquipeService service;
    @POST
    @Operation(summary = "Inserir equipe",
            description = "Com os dados faz a validação e inserção na base de dados")
    @APIResponse(
            responseCode = "201",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response inserirEquipe(Equipe equipe) {
        try {
            service.inserir(equipe);
            return  Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao inserir! Campo Nome é obrigatório!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ERROR ao inserir! Problema no servidor!").build();
        }
    }
    @GET
    @Operation(summary = "Listar equipes",
            description = "Retorna uma lista de equipes sem a necessidade de parametros")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response listarEquipe() {
        try {
            return  Response.status(Response.Status.OK).entity(service.listar()).build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao listar! Problema no servidor!").build();
        }
    }
    @GET @Path("{id}")
    @Operation(summary = "Listar uma equipe",
            description = "Retorna uma equipe a partir do ID")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response listarUmEquipe(@PathParam("id") Integer id) {
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
    @Operation(summary = "Deletar equipe",
            description = "Faz a validação e deleta uma equipe na base de dados")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response deletarEquipe(@PathParam("id") Integer id) {
        try {
            service.deletar(id);
            return  Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            error.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("ERROR ao deletar! ID não encontrado!").build();
        } catch (RuntimeException error) {
            error.printStackTrace();
            return Response.status(Response.Status.CONFLICT).entity("ERROR ao deletar! Existem funcionarios nesta equipe!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao deletar! Problema no servidor!").build();
        }
    }
    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar equipe",
            description = "Faz a validação e atualiza uma equipe na base de dados")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response atualizarPessoa(@PathParam("id") Integer id, Equipe equipe) {
        try {
            service.atualizar(id, equipe);
            return  Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            error.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("ERROR ao atualizar! ID não encontrado!").build();
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("ERROR ao atualizar! Campo Nome é obrigatório!").build();
        } catch (Exception error) {
            error.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR ao atualizar! Problema no servidor!").build();
        }
    }
}
