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

import java.util.Random;

public class readPostsById {
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
    public void readPostsById(){

        PostsResponse response = validatedRequester.read(generateRandomNumber());

        softly.assertThat(response.getId()).isNotNull();
        softly.assertThat(response.getTitle()).isNotNull();
        softly.assertThat(response.getBody()).isNotNull();
        softly.assertThat(response.getUserId()).isNotNull();
    }
}
