package br.com.stefanini;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;

/**
 * @author leandro
 * @version 0.1.0
 * @email leandrojoapi@gmail.com
 * @created 04/10/2021 on 22:26
 */
@QuarkusTest
public class EquipeRestTesst {

	private final static String URL = "/equipe";

    @Test
    public void listarEquipeSucessoTest(){
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .get(URL)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaLista.json"));
    }
    @Test
    public void listarUmEquipeSucessoTest(){
    	String URL = "/equipe/1";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .get(URL)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaLista.json"));
    }
    @Test
    public void listarUmEquipeInvalidIDTest(){
    	String URL = "/equipe/teste";
    	
    	given()
    	.contentType(ContentType.JSON)
    	.when().body("")
    	.get(URL)
    	.then()
    	.statusCode(404);
    }
    @Test
    public void inserirEquipeSucessoTest(){
    	JsonObject bodyJson = new JsonObject();
        bodyJson.put("nome", "string");
        String body = bodyJson.toString();
        
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .post(URL)
                .then()
                .statusCode(201);
    }
    @Test
    public void inserirEquipeInvalidBodyTest(){
    	JsonObject bodyJson = new JsonObject();
        String body = bodyJson.toString();
        
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .post(URL)
                .then()
                .statusCode(400);
    }
    @Test
    public void deletarEquipeSucessoTest(){
    	String URL = "/equipe/1";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .delete(URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void deletarEquipeInvalidIDTest(){
    	String URL = "/equipe/teste";
    	
        given()
                .contentType(ContentType.JSON)
                .when().body("")
                .delete(URL)
                .then()
                .statusCode(404);
    }
    @Test
    public void atualizarEquipeSucessoTest(){
    	String URL = "/equipe/1";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("nome", "string2");
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void atualizarEquipeInvalidIDTest(){
    	String URL = "/equipe/teste";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("nome", "string2");
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(404);
    }
    @Test
    public void atualizarEquipeInvalidBodyTest(){
    	String URL = "/equipe/1";
    	
    	JsonObject bodyJson = new JsonObject();
    	bodyJson.put("cargo", 0);
        String body = bodyJson.toString();
    	
        given()
                .contentType(ContentType.JSON)
                .when().body(body)
                .delete(URL)
                .then()
                .statusCode(404);
    }
}
