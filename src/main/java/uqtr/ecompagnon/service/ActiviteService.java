package uqtr.ecompagnon.service;


import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.model.Activite;
import uqtr.ecompagnon.model.Complement;
import uqtr.ecompagnon.model.Session;

import java.util.List;
import java.util.Optional;

public interface ActiviteService {
    <S extends Activite> S save(ActiviteDTO activiteDTO);
    <S extends Activite> Iterable<S> saveAll(Iterable<S> iterable);
    Iterable<Activite> findAll();
    List<Activite> getBySession(Long session);
    Optional<Activite> findById(Long id);
    void deleteById(Long id);

}
