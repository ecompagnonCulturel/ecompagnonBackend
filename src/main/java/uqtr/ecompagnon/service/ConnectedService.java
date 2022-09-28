package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Connected;

import java.util.List;
import java.util.Optional;

public interface ConnectedService {

    <S extends Connected> S save(S s);


    <S extends Connected> Iterable<S> saveAll(Iterable<S> iterable);


    Optional<Connected> findById(Long Connectedid);

    boolean existsById(Long Connectedid);

    Iterable<Connected> findAll();

    Iterable<Connected> findAllById(Iterable<Long> iterable);


    long count();

    List<Connected> getAllConnected();

    void delConnectedtById(Long id);

    Iterable<Connected> getConnectedByUser(String email);

}
