package uqtr.ecompagnon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Region;


@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
}
