package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Connected;
import uqtr.ecompagnon.repository.ConnectedRepository;
import uqtr.ecompagnon.service.ConnectedService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class ConnectedServiceImpl implements ConnectedService {

    private final ConnectedRepository connectedRepository;
    private final AppUserServiceImpl appUserServiceImpl;

    public <S extends Connected> S save(S s) {

        return connectedRepository.save(s);
    }


    public <S extends Connected> Iterable<S> saveAll(Iterable<S> iterable) {
        return connectedRepository.saveAll(iterable);
    }


    public Optional<Connected> findById(Long Connectedid) {
        return connectedRepository.findById(Connectedid);
    }


    public boolean existsById(Long Connectedid) {
        return connectedRepository.existsById(Connectedid);
    }


    public Iterable<Connected> findAll() {
        return connectedRepository.findAll();
    }


    public Iterable<Connected> findAllById(Iterable<Long> iterable) {
        return connectedRepository.findAllById(iterable);
    }


    public long count() {
        return connectedRepository.count();
    }

    public List<Connected> getAllConnected() {
        return (List<Connected>) connectedRepository.findAll();
    }


    public void delConnectedtById(Long id) {
        connectedRepository.deleteById(id);
    }


    public Iterable<Connected> getConnectedByUser(String email) {
        AppUser appUser= (AppUser) appUserServiceImpl.loadUserByUsername(email);
          return connectedRepository.getConnectedByAppUser(appUser);
    }
}
