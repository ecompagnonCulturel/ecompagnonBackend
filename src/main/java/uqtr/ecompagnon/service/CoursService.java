package uqtr.ecompagnon.service;

import org.springframework.data.repository.query.Param;
import uqtr.ecompagnon.model.Cours;

import java.util.List;
import java.util.Optional;

public interface CoursService {
    <S extends Cours> S save(Cours cours);
    <S extends Cours> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<Cours> findById(Long Coursid);
    boolean existsById(Long Coursid);
    Iterable<Cours> findAll();
    Iterable<Cours> findAllById(Iterable<Long> iterable);
    long count();
    List<Cours> getAllCours();
    List<Cours> getCoursBySession();
    Iterable<Cours> getCoursByResourceAndSession(long ressource, long session);
    void deleteById(Long id);

}
