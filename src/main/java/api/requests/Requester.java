package api.requests;

import api.DTO.BaseModel;
import api.DTO.PostsRequest;
import api.DTO.PostsResponse;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Requester implements CrudInterface<PostsRequest> {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Override
    public ValidatableResponse create(PostsRequest model) {
        return given()
                .body(model)
                .when()
                .post(BASE_URL + "/posts")
                .then()
                .statusCode(201);
    }

    @Override
    public ValidatableResponse read(int id) {
        return given()
                .when()
                .get(BASE_URL + "/posts/" + id)
                .then()
                .statusCode(200);
    }

    @Override
    public List<PostsResponse> readAll() {
        return given()
                .when()
                .get(BASE_URL + "/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", PostsResponse.class);
    }

    @Override
    public void delete(int id) {
       given()
                .baseUri(BASE_URL)
                .when()
                .delete("/posts/{id}", id)
                .then()
                .statusCode(200);
    }

    @Override
    public ValidatableResponse update(int id, PostsRequest model) {
        return given()
                .contentType("application/json")
                .body(model)
                .when()
                .put(BASE_URL + "/posts/" + id)
                .then()
                .statusCode(200);
    }


}
