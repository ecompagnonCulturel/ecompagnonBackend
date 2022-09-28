package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.repository.ProfRepository;
import uqtr.ecompagnon.service.ProfService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor
public class ProfServiceImpl implements ProfService {
    private final ProfRepository profRepository;
    @Override
    public <S extends Prof> S save(Prof prof) {
        return (S) profRepository.save(prof);
    }

    @Override
    public <S extends Prof> Iterable<S> saveAll(Iterable<S> iterable) {
        return profRepository.saveAll(iterable);
    }

    @Override
    public Optional<Prof> findById(Long Profid) {
        return profRepository.findById(Profid);
    }

    @Override
    public boolean existsById(Long Profid) {
        return profRepository.existsById(Profid);
    }

    @Override
    public Iterable<Prof> findAll() {
        return profRepository.findAll();
    }

    @Override
    public Iterable<Prof> findAllById(Iterable<Long> iterable) {
        return profRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return profRepository.count();
    }

    @Override
    public List<Prof> getAllProf() {
        return profRepository.findAll();
    }

    @Override
    public Iterable<Prof> getProfByProfAndProfLastName(String lastName) {
        return profRepository.getProfByProfLastName(lastName);
    }

    @Override
    public void deleteById(Long id) {
        //Etudiant etudiant=etudiantRepository.getById(id);
        //cpMailService.deleteByCpeCP(etudiant.getEtudCP());
        profRepository.deleteById(id);
    }
}
