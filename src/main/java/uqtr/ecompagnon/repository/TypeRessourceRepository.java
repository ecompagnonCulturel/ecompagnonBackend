package uqtr.ecompagnon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.TypeRessource;

@Repository
public interface TypeRessourceRepository extends CrudRepository<TypeRessource, Long> {
}
