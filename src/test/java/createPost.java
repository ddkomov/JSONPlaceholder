import api.DTO.PostsRequest;
import api.DTO.PostsResponse;
import api.requests.Requester;
import api.requests.ValidatedRequester;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import jdk.jfr.Name;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
 1. JSON Placeholder
Сервис: https://jsonplaceholder.typicode.com
GET /posts — проверить статус 200, убедиться, что возвращается список из 100 постов
GET /posts/{id} — проверить возврат одного поста по ID
POST /posts — отправить новый пост, убедиться в статусе 201 и наличии ID
PUT /posts/{id} — изменить заголовок поста, убедиться в обновлении
DELETE /posts/{id} — убедиться в статусе успешного удаления
 */

public class createPost {

    private Requester requester = new Requester();
    private ValidatedRequester validatedRequester = new ValidatedRequester();
    private SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    public static void before(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @BeforeEach
    public void beforeEach(){
        softly = new SoftAssertions();
    }
    @AfterEach
    public void afterEach(){
        softly.assertAll();
    }

    @Test
    public void createPost() {
        PostsRequest postsRequest = PostsRequest.builder()
                .title("sss")
                .body("qqq")
                .build();

        PostsResponse response = validatedRequester.create(postsRequest);

        softly.assertThat(response.getId()).isNotNull().isPositive();
    }


}
