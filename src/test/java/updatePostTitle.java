import api.DTO.PostsRequest;
import api.DTO.PostsResponse;
import api.requests.Requester;
import api.requests.ValidatedRequester;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class updatePostTitle {
    private Requester requester = new Requester();
    private ValidatedRequester validatedRequester = new ValidatedRequester();
    private SoftAssertions softly = new SoftAssertions();

    private static Random random = new Random();
    public static int generateRandomNumber() {
        return random.nextInt(100) + 1;
    }

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
    public void updatePostTitle(){
        int id = generateRandomNumber();
        PostsRequest request = new PostsRequest();
        ValidatedRequester requester = new ValidatedRequester();

        request.setUserId(1);
        request.setTitle("Обновлённый заголовок");
        request.setBody("Новое содержимое");

        PostsResponse response = requester.update(id, request );

        softly.assertThat(response.getTitle()).isEqualTo("Обновлённый заголовок");
        softly.assertThat(response.getBody()).isEqualTo("Новое содержимое");
        softly.assertThat(response.getId()).isEqualTo(id);
        softly.assertThat(response.getUserId()).isEqualTo(1);

        PostsResponse readResponse = requester.read(id);
        softly.assertThat(readResponse.getTitle()).isEqualTo("Обновлённый заголовок");
        softly.assertThat(readResponse.getBody()).isEqualTo("Новое содержимое");
        softly.assertThat(readResponse.getUserId()).isEqualTo(1);
    }
}
