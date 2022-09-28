package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getQuestionByQuestCate(String questionCate);

    List<Question> findQuestionByIdIn(List<Long> question);
}
