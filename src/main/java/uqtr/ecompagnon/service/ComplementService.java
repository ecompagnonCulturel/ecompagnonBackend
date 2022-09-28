package uqtr.ecompagnon.service;


import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.dto.ComplementDTO;
import uqtr.ecompagnon.model.Activite;
import uqtr.ecompagnon.model.Complement;
import uqtr.ecompagnon.model.Questionnaire;

import java.util.List;

public interface ComplementService {
    <S extends Complement> S save(Complement complement);
    <S extends Complement> Iterable<S> saveAll(Iterable<S> iterable);
    Iterable<Complement> findAll();
   Complement getByQuestionnaire(Long questionnaire);
    List<Activite> getAllActivitiesFromComplement();

}
