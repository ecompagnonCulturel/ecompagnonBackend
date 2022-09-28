package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.Favoris;
import uqtr.ecompagnon.dto.FavorisDTO;

import java.util.Optional;

public interface FavorisService {
    <S extends Favoris> S save(FavorisDTO favorisDTO);
    Iterable<Favoris> getFavorisByUserAndResources(long user, long resource);
    Iterable<Favoris> getFavorisByUser(long user,long status);
    Optional<Favoris> findById(Long id);
    Boolean existsFavorisById(Long id);
    public long count();
    Iterable<Favoris> getAllFavoris();
    void delFavoristById(Long id);
    <S extends Favoris> Iterable<S> saveAll(Iterable<S> iterable);
}
