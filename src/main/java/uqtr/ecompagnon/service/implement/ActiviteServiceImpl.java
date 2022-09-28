package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.ActiviteService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ActiviteServiceImpl  implements ActiviteService {
    private final ActiviteRepository activiteRepository;
    private final SessionRepository sessionRepository;
    private final GroupEtudiantRepository groupEtudiantRepository;
    private final ComplementRepository complementRepository;
    private final QuestionnaireRepository questionnaireRepository;

    @Override
    public <S extends Activite> S save(ActiviteDTO activiteDTO) {
        Session session=sessionRepository.findById(activiteDTO.getSession())
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));

        Activite activite=new Activite();
        activite.setId(activiteDTO.getId());
        activite.setSession(session);
        activite.setType(1L);
        activite.setStatus(1L);
        activite.setActDesc(activiteDTO.getActDesc());
        return (S) activiteRepository.save(activite);
    }

    @Override
    public <S extends Activite> Iterable<S> saveAll(Iterable<S> iterable) {
        return activiteRepository.saveAll(iterable);
    }

    @Override
    public Iterable<Activite> findAll() {
        return activiteRepository.findAll();
    }


    @Override
    public List<Activite> getBySession(Long sessionP) {
        Session session=sessionRepository.findById(sessionP)
                .orElseThrow(()->
                new IllegalStateException(
                        "session not found"
                ));;

        return activiteRepository.getBySession(session);
    }

    @Override
    public Optional<Activite> findById(Long id) {
        return activiteRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        activiteRepository.deleteById(id);
    }

   /* @Override
    public <S extends Complement> S saveActiviteAndComplement(ActiviteDTO activiteDTO) {
        Session session=sessionRepository.findById(activiteDTO.getSession())
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));
        GroupEtudiant groupEtudiant=groupEtudiantRepository.findById(activiteDTO.getGroup())
                .orElseThrow(()->
                        new IllegalStateException(
                                "groupe Etudiant not found"
                        ));
        System.out.println(activiteDTO.getTypes()) ;

        Questionnaire questionnaire=questionnaireRepository
                .getOneByTypeAndGroupEtudiantAndSession(String.valueOf(activiteDTO.getTypes()),
                        activiteDTO.getGroup(),
                        activiteDTO.getSession());

        Activite activite=new Activite();
        activite.setId(0L);
        activite.setSession(session);
        activite.setStatus(1L);
        activite.setType(1L);
        activite.setActDesc(activiteDTO.getActDesc());

        activiteRepository.saveAndFlush(activite);

        Complement complement=new Complement();
        complement.setId(0L);
        complement.setActivite(activite);
        complement.setQuestionnaire(questionnaire);
        complement.setStatus(1L);

        return (S) complementRepository.save(complement);
    }
*/
}
