package com.ariel.restController.controller;

import com.ariel.restController.model.Question;
import com.ariel.restController.model.Survey;
import com.ariel.restController.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
public class SurveyController {

    @Autowired
    SurveyService service;

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return service.retrieveAllSurveys();
    }

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getQuestionsFromSurvey(@PathVariable int surveyId) {
        return service.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question getQuestionFromSurvey(@PathVariable int surveyId, @PathVariable int questionId) {
        return service.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Void> addQuestionForSurvey(@PathVariable int surveyId, @RequestBody Question question) {
        Question createdQuestion = service.addQuestion(surveyId, question);
        if (createdQuestion == null) {
            return ResponseEntity.noContent().build();
        }
        // Obtendo a URI do novo recurso criado e deve ser retornada
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdQuestion.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
