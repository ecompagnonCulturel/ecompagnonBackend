package uqtr.ecompagnon.service;


import uqtr.ecompagnon.model.Question;

import java.util.List;

public interface QuestionService {

    <S extends Question> S save(Question question);
    <S extends Question> Iterable<S> saveAll(Iterable<S> iterable);
    Iterable<Question> findAll();
    List<Question> getQuestionByQuestCate(String questionCate);
    List<Question> findQuestionByIdIn(List<Long> question);
}
