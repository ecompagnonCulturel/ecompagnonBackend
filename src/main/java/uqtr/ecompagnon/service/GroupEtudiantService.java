package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.GroupEtudiantDTO;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.EtudiantGroupe;
import uqtr.ecompagnon.model.GroupEtudiant;

import java.util.List;
import java.util.Optional;

public interface GroupEtudiantService {
    <S extends GroupEtudiant> S saveGroupeEtudiant(GroupEtudiant groupEtudiant,List<Long> etudiantList);
    <S extends GroupEtudiant> S save(GroupEtudiantDTO groupEtudiantDTO);
    <S extends GroupEtudiant> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<GroupEtudiant> findById(Long GroupEtudiantid);
    boolean existsById(Long GroupEtudiantid);
    Iterable<GroupEtudiant> findAll();
    Iterable<GroupEtudiant> findAllById(Iterable<Long> iterable);
    long count();
    void deleteById(Long id);

    List<GroupEtudiant> getGroupEtudiantBySessionAndType(Long session, List<String> types);

}
