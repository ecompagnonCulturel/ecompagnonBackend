package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uqtr.ecompagnon.model.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @Query("select p from Profile p where p.profNom=:profile")
    Profile getProfileByNom(@Param("profile") String profile);

}
