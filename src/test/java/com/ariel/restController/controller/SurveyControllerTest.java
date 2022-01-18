package com.ariel.restController.controller;

import com.ariel.restController.model.Question;
import com.ariel.restController.services.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SurveyController.class)
class SurveyControllerTest {

    @MockBean
    private SurveyService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getQuestionFromSurvey() throws Exception {
        Question mockQuestion = new Question(0, "desc", "c", List.of("a", "b", "c"));
        when(service.retrieveQuestion(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockQuestion);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/1/questions/1");
        MvcResult mvcResult = mockMvc.perform(requestBuilder.accept(MediaType.APPLICATION_JSON)).andReturn();

        String expected = "{id:0, description:desc, correctAnswer:c, options:[a,b,c]}";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

}