package br.com.stefanini;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import br.com.stefanini.models.Equipe;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 23/09/2021 on 20:24
 */
@QuarkusTest
public class PessoaRestTest {
    private final static String URL = "/pessoa";

    @Test
    public void listarPessoaSucessoTest(){
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .get(URL)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaLista.json"));
    }
    @Test
    public void listarUmPessoaSucessoTest(){
    	String URL = "/pessoa/3";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .get(URL)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaLista.json"));
    }
    @Test
    public void listarUmPessoaInvalidIDTest(){
    	String URL = "/pessoa/999";
    	
    	given()
    	.contentType(ContentType.JSON)
    	.when().body("")
    	.get(URL)
    	.then()
    	.statusCode(404);
    }
    @Test
    public void inserirPessoaSucessoTest(){
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("cargo", 1);
        bodyJson.put("contato", "string");
        bodyJson.put("email", "string");
        bodyJson.put("nome", "string");
        bodyJson.put("sobrenome", "string");
        String body = bodyJson.toString();
        
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .post(URL)
                .then()
                .statusCode(201);
    }
    @Test
    public void inserirPessoaInvalidBodyTest(){
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("cargo", 1);
        bodyJson.put("contato", "string");
        bodyJson.put("email", "string");
        bodyJson.put("nome", "string");
        String body = bodyJson.toString();
        
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .post(URL)
                .then()
                .statusCode(400);
    }
    @Test
    public void deletarPessoaSucessoTest(){
    	String URL = "/pessoa/1";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .delete(URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void deletarPessoaInvalidIDTest(){
    	String URL = "/pessoa/teste";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .delete(URL)
                .then()
                .statusCode(404);
    }
    @Test
    public void atualizarPessoaSucessoTest(){
    	String URL = "/pessoa/1";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("cargo", 0);
        bodyJson.put("contato", "string2");
        bodyJson.put("email", "string2");
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void atualizarPessoaInvalidIDTest(){
    	String URL = "/pessoa/teste";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("cargo", 0);
        bodyJson.put("contato", "string2");
        bodyJson.put("email", "string2");
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(404);
    }
    @Test
    public void atualizarPessoaInvalidBodyTest(){
    	String URL = "/pessoa/1";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("nome", 1);
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(404);
    }
}
