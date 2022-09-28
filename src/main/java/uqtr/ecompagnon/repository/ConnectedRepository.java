package uqtr.ecompagnon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Connected;
import uqtr.ecompagnon.model.Cours;

import java.util.List;

@Repository
public interface ConnectedRepository extends CrudRepository<Connected, Long> {

    List<Connected> getConnectedByAppUser(AppUser appUser);
}
