package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.CpMail;
import uqtr.ecompagnon.model.Favoris;

import java.util.List;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Long> {

    @Query("select f from Favoris f where f.favUser.idUsers=:idUsers and  f.favResource.id=:idResource and f.favStatus=1")
    List<Favoris> getFavorisByUser(@Param("idUsers") Long idUsers, @Param("idResource") Long idResource);

    Iterable<Favoris> findFavorisByFavUserAndFavStatus(AppUser appUser, Long status);
}
