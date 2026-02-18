import api.DTO.PostsResponse;
import api.requests.Requester;
import api.requests.ValidatedRequester;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class readAllPosts {
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
    public void readAllPosts() {
        List<PostsResponse> posts = requester.readAll();
        
        softly.assertThat(posts).hasSize(100);
    }
}
