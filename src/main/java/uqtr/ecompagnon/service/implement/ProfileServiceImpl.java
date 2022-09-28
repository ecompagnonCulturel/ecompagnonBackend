package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.repository.ProfileRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ProfileServiceImpl {

    private final ProfileRepository profileRepository;

    public <S extends Profile> S save(S s) {

        return profileRepository.save(s);
    }


    public <S extends Profile> Iterable<S> saveAll(Iterable<S> iterable) {
        return profileRepository.saveAll(iterable);
    }


    public Optional<Profile> findById(Long Profileid) {
        return profileRepository.findById(Profileid);
    }


    public boolean existsById(Long Profileid) {
        return profileRepository.existsById(Profileid);
    }


    public Iterable<Profile> findAll() {
        return profileRepository.findAll();
    }


    public Iterable<Profile> findAllById(Iterable<Long> iterable) {
        return profileRepository.findAllById(iterable);
    }


    public long count() {
        return profileRepository.count();
    }

    public List<Profile> getAllProfile() {
        return (List<Profile>) profileRepository.findAll();
    }


    public void delProfiletById(Long id) {
        profileRepository.deleteById(id);
    }


    public Profile getProfileByNom(String profile) {
        return profileRepository.getProfileByNom(profile);
    }
}
