package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.GroupEtudiantDTO;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.EtudiantGroupe;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Session;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.GroupEtudiantService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor
public class GroupEtudiantServiceImpl implements GroupEtudiantService {
    private final GroupEtudiantRepository groupEtudiantRepository;
    private final EtudiantGroupeRepository etudiantGroupeRepository;
    private final EtudiantRepository etudiantRepository;
    private final SessionRepository sessionRepository;
    private final QuestionnaireRepository questionnaireRepository;

    @Override
    public <S extends GroupEtudiant> S saveGroupeEtudiant(GroupEtudiant groupEtudiant,List<Long> etudiantList) {
        ArrayList<EtudiantGroupe> etudiantGroupArray=new ArrayList<>();

        long idGroup=groupEtudiantRepository.save(groupEtudiant).getId();
        GroupEtudiant groupEtudiant1=groupEtudiantRepository.findById(idGroup)
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));

        etudiantList.forEach(etudiantId -> {
            Etudiant etudiant1=etudiantRepository.findById(etudiantId)
                    .orElseThrow(() ->
                            new AppRequestException("Cet étudiant n'existe pas"));

            EtudiantGroupe etudiantGroupe=new EtudiantGroupe();
            etudiantGroupe.setId(0L);
            etudiantGroupe.setEtudiant(etudiant1);
            etudiantGroupe.setGroupEtudiant(groupEtudiant);
            etudiantGroupe.setSession(groupEtudiant.getGroupSession());
            etudiantGroupe.setStatut(1L);


            etudiantGroupArray.add(etudiantGroupe);

        });
        etudiantGroupeRepository.saveAll(etudiantGroupArray);

        return (S) groupEtudiant1;
    }

    @Override
    public <S extends GroupEtudiant> S save(GroupEtudiantDTO groupEtudiantDTO) {
        Session session=sessionRepository.findById(groupEtudiantDTO.getGroupSession())
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));
        GroupEtudiant groupEtudiant=new GroupEtudiant();
        groupEtudiant.setId(groupEtudiantDTO.getId());
        groupEtudiant.setGroupName(groupEtudiantDTO.getGroupName());
        groupEtudiant.setGroupSession(session);
        groupEtudiant.setGroupStatus(groupEtudiantDTO.getGroupStatus());
        groupEtudiant.setGroupDateCreate(groupEtudiantDTO.getGroupDateCreate());

        return (S) groupEtudiantRepository.save(groupEtudiant);
    }

    @Override
    public <S extends GroupEtudiant> Iterable<S> saveAll(Iterable<S> iterable) {
        return groupEtudiantRepository.saveAll(iterable);
    }

    @Override
    public Optional<GroupEtudiant> findById(Long GroupEtudiantid) {
        return groupEtudiantRepository.findById(GroupEtudiantid);
    }

    @Override
    public boolean existsById(Long GroupEtudiantid) {
        return false;
    }

    @Override
    public Iterable<GroupEtudiant> findAll() {
        return groupEtudiantRepository.findAll();
    }

    @Override
    public Iterable<GroupEtudiant> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return groupEtudiantRepository.count();
    }

    @Override
    public void deleteById(Long id) {
         groupEtudiantRepository.deleteById(id);
    }

    @Override
    public List<GroupEtudiant> getGroupEtudiantBySessionAndType(Long session, List<String> types) {
        return questionnaireRepository.getGroupEtudiantBySessionAndType(session,types);
    }


}
