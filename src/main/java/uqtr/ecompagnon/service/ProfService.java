package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.model.Prof;


import java.util.List;
import java.util.Optional;

public interface ProfService {
    <S extends Prof> S save(Prof prof);
    <S extends Prof> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<Prof> findById(Long Profid);
    boolean existsById(Long Profid);
    Iterable<Prof> findAll();
    Iterable<Prof> findAllById(Iterable<Long> iterable);
    long count();
    List<Prof> getAllProf();
    Iterable<Prof> getProfByProfAndProfLastName(String lastName);
    public void deleteById(Long id);
}
