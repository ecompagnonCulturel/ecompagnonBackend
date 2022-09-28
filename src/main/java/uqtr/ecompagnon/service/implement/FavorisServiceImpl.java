package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Favoris;
import uqtr.ecompagnon.model.Resources;
import uqtr.ecompagnon.dto.FavorisDTO;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.repository.FavorisRepository;
import uqtr.ecompagnon.repository.ResourcesRepository;
import uqtr.ecompagnon.service.FavorisService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class FavorisServiceImpl implements FavorisService {

    private final FavorisRepository favorisRepository;
    private final AppUserRepository appUserRepository;
    private final ResourcesRepository resourcesRepository;
    @Override
    public <S extends Favoris> S save(FavorisDTO favorisDTO) {


        AppUser appUser=appUserRepository.findById(favorisDTO.getFavUser())
                .orElseThrow(()->
                        new IllegalStateException(
                                "User not found"
                        ));
        Resources resources=resourcesRepository.findById(favorisDTO.getFavResource())
                .orElseThrow(()->
                        new IllegalStateException(
                                "Ressource not found"
                        ));

        Favoris favoris=new Favoris(favorisDTO.getId(),appUser,
                resources,
                LocalDateTime.now(),
                favorisDTO.getFavStatus()
        );


        return (S) favorisRepository.save(favoris);
    }


    public <S extends Favoris> Iterable<S> saveAll(Iterable<S> iterable) {
        return favorisRepository.saveAll(iterable);
    }

    @Override
    public Optional<Favoris> findById(Long Favorisid) {
        return favorisRepository.findById(Favorisid);
    }

    @Override
    public Boolean existsFavorisById(Long Favorisid) {
        return favorisRepository.existsById(Favorisid);
    }


    @Override
    public Iterable<Favoris> getFavorisByUserAndResources(long user, long resource) {
        return favorisRepository.getFavorisByUser(user, resource);
    }

    @Override
    public Iterable<Favoris> getFavorisByUser(long user,long status) {
        AppUser appUser=appUserRepository.findById(user)
                .orElseThrow(()->
                new AppRequestException(
                        "l'utilisateur  n'existe pas"
                ));
        return favorisRepository.findFavorisByFavUserAndFavStatus(appUser,status);
    }

    @Override
    public long count() {
        return favorisRepository.count();
    }

    public List<Favoris> getAllFavoris() {
        return (List<Favoris>) favorisRepository.findAll();
    }

    @Override
    public void delFavoristById(Long id) {
        favorisRepository.deleteById(id);
    }


}
