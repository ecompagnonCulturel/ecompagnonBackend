package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Question;
import uqtr.ecompagnon.repository.QuestionRepository;
import uqtr.ecompagnon.service.QuestionService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    @Override
    public <S extends Question> S save(Question question) {
        return (S) questionRepository.save(question);
    }

    @Override
    public <S extends Question> Iterable<S> saveAll(Iterable<S> iterable) {
        return questionRepository.saveAll(iterable);
    }

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionByQuestCate(String questionCate) {
        return questionRepository.getQuestionByQuestCate(questionCate);
    }

    @Override
    public List<Question> findQuestionByIdIn(List<Long> questions) {
        return questionRepository.findQuestionByIdIn(questions);
    }
}
