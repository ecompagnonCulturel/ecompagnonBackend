package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Cours;
import uqtr.ecompagnon.repository.CoursRepository;
import uqtr.ecompagnon.service.CoursService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;
    @Override
    public <S extends Cours> S save(Cours cours) {
        return (S)coursRepository.save(cours);
    }

    @Override
    public <S extends Cours> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Cours> findById(Long Coursid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long Coursid) {
        return false;
    }

    @Override
    public Iterable<Cours> findAll() {
        return coursRepository.findAll();
    }

    @Override
    public Iterable<Cours> findAllById(Iterable<Long> iterable) {
        return coursRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public List<Cours> getCoursBySession() {
        return coursRepository.getCoursBySession();
    }

    @Override
    public Iterable<Cours> getCoursByResourceAndSession(long ressource, long session) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        //Etudiant etudiant=etudiantRepository.getById(id);
        //cpMailService.deleteByCpeCP(etudiant.getEtudCP());
        coursRepository.deleteById(id);
    }
}

