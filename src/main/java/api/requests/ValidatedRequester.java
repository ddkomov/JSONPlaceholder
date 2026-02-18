package api.requests;

import api.DTO.PostsRequest;
import api.DTO.PostsResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ValidatedRequester implements CrudInterface<PostsRequest> {
    private Requester requester = new Requester();

    @Override
    public PostsResponse create(PostsRequest model) {
        return requester.create(model).extract().as(PostsResponse.class);
    }

    @Override
    public PostsResponse read(int id) {
        return requester.read(id)
                .extract()
                .as(PostsResponse.class);
    }

    @Override
    public List<PostsResponse> readAll() {
        return requester.readAll();
    }

    @Override
    public void delete(int id) {
        requester.delete(id);
    }

    @Override
    public PostsResponse update(int id, PostsRequest model) {
        return requester.update(id, model).extract().as(PostsResponse.class);
    }



}
