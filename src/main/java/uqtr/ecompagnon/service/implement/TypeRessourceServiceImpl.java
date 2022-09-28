package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.TypeRessource;
import uqtr.ecompagnon.repository.TypeRessourceRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
public class TypeRessourceServiceImpl {

    private final TypeRessourceRepository typeRessourceRepository;

    public <S extends TypeRessource> S save(S s) {

        return typeRessourceRepository.save(s);
    }


    public <S extends TypeRessource> Iterable<S> saveAll(Iterable<S> iterable) {
        return typeRessourceRepository.saveAll(iterable);
    }


    public Optional<TypeRessource> findById(Long id) {
        return typeRessourceRepository.findById(id);
    }


    public boolean existsById(Long id) {
        return typeRessourceRepository.existsById(id);
    }


    public Iterable<TypeRessource> findAll() {
        return typeRessourceRepository.findAll();
    }


    public Iterable<TypeRessource> findAllById(Iterable<Long> iterable) {
        return typeRessourceRepository.findAllById(iterable);
    }


    public long count() {
        return typeRessourceRepository.count();
    }
}
