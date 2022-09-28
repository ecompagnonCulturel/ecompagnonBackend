package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.ComplementService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class ComplementServiceImpl implements ComplementService {
    private final ComplementRepository complementRepository;
    private final QuestionnaireRepository questionnaireRepository;


    @Override
    public <S extends Complement> S save(Complement complement) {
        return (S) complementRepository.save(complement);
    }

    @Override
    public <S extends Complement> Iterable<S> saveAll(Iterable<S> iterable) {
        return complementRepository.saveAll(iterable);
    }

    @Override
    public Iterable<Complement> findAll() {
        return complementRepository.findAll();
    }

    @Override
    public Complement getByQuestionnaire(Long questionnaire) {
        Questionnaire questionnaire1=questionnaireRepository.findById(questionnaire)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Questionnaire not found"
                        ));

        return complementRepository.getByQuestionnaire(questionnaire1);
    }

    @Override
    public List<Activite> getAllActivitiesFromComplement() {
        return complementRepository.getAllActivitiesFromComplement();
    }


}
