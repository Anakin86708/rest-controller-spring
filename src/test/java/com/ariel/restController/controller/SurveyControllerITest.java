package com.ariel.restController.controller;

import com.ariel.restController.RestControllerApplication;
import com.ariel.restController.model.Question;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RestControllerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyControllerITest {

    private final Logger log = LoggerFactory.getLogger(SurveyControllerITest.class);
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetResponse() throws JSONException {
        String url = getUrl("/surveys/1/questions/1");
        HttpEntity<Question> entity = prepareHttpEntity(null);

        ResponseEntity<String> response = getResponse(url, HttpMethod.GET, entity);
        log.info("Response: " + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals("{id:" + "1" + "}", response.getBody(), false);
    }

    @Test
    public void testAddQuestionOnSurvey() throws JSONException {
        String url = getUrl("/surveys/1/questions");
        Question newQuestion = new Question(
                1,
                "2-1",
                "1",
                List.of("11", "21", "12", "1")
        );
        HttpEntity<Question> entity = prepareHttpEntity(newQuestion);

        ResponseEntity<String> response = getResponse(url, HttpMethod.POST, entity);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        log.info("Actual: " + actual);

        assertTrue(actual.contains("surveys/1/questions"));
    }

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    private ResponseEntity<String> getResponse(String url, HttpMethod method, HttpEntity<Question> entity) {
        return restTemplate.exchange(url, method, entity, String.class);
    }

    private HttpEntity<Question> prepareHttpEntity(Question body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<Question>(body, headers);
    }

}