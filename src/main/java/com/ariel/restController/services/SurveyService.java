package com.ariel.restController.services;

import com.ariel.restController.model.Question;
import com.ariel.restController.model.Survey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();

    static {
        Question q1 = new Question(1, "Largest country", "Russia", Arrays.asList("Brazil", "United States", "Russia", "China"));
        Question q2 = new Question(2, "1+1", "2", Arrays.asList("2", "1", "11", "0"));

        List<Question> questionList = Arrays.asList(q1, q2);
        Survey survey = new Survey(1, "General survey", "A general survey", questionList);
        surveys.add(survey);
    }

    private Random random = new Random();

    public List<Survey> retrieveAllSurveys() {
        return new ArrayList<>(surveys);
    }

    public Survey retrieveSurvey(int surveyId) {
        for (Survey survey : surveys) {
            if (survey.getId() == surveyId) {
                return survey;
            }
        }
        return null;
    }

    public List<Question> retrieveQuestions(int surveyId) {
        Survey survey = retrieveSurvey(surveyId);

        if (survey == null) {
            return null;
        }

        return survey.getQuestions();
    }

    public Question retrieveQuestion(int surveyId, int questionId) {
        Survey survey = retrieveSurvey(surveyId);

        if (survey == null) {
            return null;
        }

        for (Question question : survey.getQuestions()) {
            if (question.getId() == questionId) {
                return question;
            }
        }

        return null;
    }

    public Question addQuestion(int surveyId, Question question) {
        Survey survey = retrieveSurvey(surveyId);

        if (survey == null) {
            return null;
        }

        int randomId = random.nextInt(255);
        question.setId(randomId);

        List<Question> newQuestions = new ArrayList<>(survey.getQuestions());
        newQuestions.add(question);
        survey.setQuestions(newQuestions);

        return question;
    }
}
