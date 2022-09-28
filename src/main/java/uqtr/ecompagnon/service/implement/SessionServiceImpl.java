package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.NoteDTO;
import uqtr.ecompagnon.model.Note;
import uqtr.ecompagnon.model.Session;
import uqtr.ecompagnon.repository.SessionRepository;
import uqtr.ecompagnon.service.SessionService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public Iterable<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session getActiveSession() {
        return sessionRepository.findBySessStatus(1L);
    }

    public Optional<Session> getById(Long id) {
        return sessionRepository.findById(id);
    }

    public void delQuestionnairetById(Long id) {
        sessionRepository.deleteById(id);
    }

    public <S extends Session> S save(Session session) {
        return (S) sessionRepository.save(session);

    }

    @Override
    public List<Session> getSessionOrderByStartDate() {
        return sessionRepository.getSessionOrderByStartDate();
    }
}
