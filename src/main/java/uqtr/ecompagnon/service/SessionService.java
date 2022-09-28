package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.Note;
import uqtr.ecompagnon.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    Iterable<Session> findAll();

    Session getActiveSession() ;
    Optional<Session> getById(Long id);
    void delQuestionnairetById(Long id);
    public <S extends Session> S save(Session session);
    List<Session> getSessionOrderByStartDate();

}
