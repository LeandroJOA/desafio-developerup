package br.com.stefanini.rest;

import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Equipe;
import br.com.stefanini.models.Pessoa;
import br.com.stefanini.services.EquipeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
    public Response inserirEquipe(Equipe equipe) throws Exception {
        service.inserir(equipe);
        return  Response.status(Response.Status.CREATED).build();
    }
    @GET
    @Operation(summary = "Listar equipes",
            description = "Retorna uma lista de equipes sem a necessidade de parametros")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response listarEquipe() throws ErroNegocialException {
        return  Response.status(Response.Status.OK).entity(service.listar()).build();
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
        service.deletar(id);
        return  Response.status(Response.Status.OK).build();
    }
}
