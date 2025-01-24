package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Category;
import models.Pet;
import models.Tag;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetApiTest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    private Pet createValidPet() {
        Pet pet = new Pet();
        pet.setId(12345L);
        pet.setName("Fluffy");
        pet.setCategory(new Category(1L, "Dogs"));
        pet.setTags(Collections.singletonList(new Tag(1L, "friendly")));
        pet.setStatus("available");
        return pet;
    }

    @Test
    public void testCreatePetSuccessful() {
        Pet pet = createValidPet();

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo(pet.getName()))
                .body("id", equalTo(pet.getId().intValue()));
    }

    @Test
    public void testCreatePetInvalidData() {
        String invalidPet = "{\"id\": \"invalid_id\", \"name\": 123, \"status\": \"invalid_status\"}";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(500);
    }

    @Test
    public void testGetPetByIdSuccessful() {
        Pet pet = createValidPet();

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");

        given()
                .when()
                .get("/pet/{id}", pet.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(pet.getName()));
    }

    @Test
    public void testGetPetNotFound() {
        given()
                .when()
                .get("/pet/99999999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testFindPetsByStatus() {
        given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("", instanceOf(java.util.List.class));
    }

    @Test
    public void testFindPetsByInvalidStatus() {
        given()
                .queryParam("status", "invalid")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(400);
    }

    @Test
    public void testUpdatePetSuccessful() {
        Pet pet = createValidPet();
        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");

        pet.setName("Updated Fluffy");

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Fluffy"));
    }

    @Test
    public void testUpdatePetInvalidData() {
        String invalidPet = "{\"id\": \"invalid_id\", \"name\": 123, \"status\": \"invalid_status\"}";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPet)
                .when()
                .put("/pet")
                .then()
                .statusCode(500);
    }

    @Test
    public void testDeletePetSuccessful() {
        Pet pet = createValidPet();
        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");

        given()
                .when()
                .delete("/pet/{id}", pet.getId())
                .then()
                .statusCode(200);
        given()
                .when()
                .get("/pet/{id}", pet.getId())
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeletePetNotFound() {
        given()
                .when()
                .delete("/pet/99999999")
                .then()
                .statusCode(404);
    }
} 