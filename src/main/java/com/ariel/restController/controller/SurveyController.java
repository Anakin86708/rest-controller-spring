package com.ariel.restController.controller;

import com.ariel.restController.model.Question;
import com.ariel.restController.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class SurveyController {

    @Autowired
    SurveyService service;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getQuestionsFromSurvey(@PathVariable int surveyId) {
        return service.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question getQuestionFromSurvey(@PathVariable int surveyId, @PathVariable int questionId) {
        return service.retrieveQuestion(surveyId, questionId);
    }
}
