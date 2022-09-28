package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Region;
import uqtr.ecompagnon.repository.RegionRepository;

import javax.transaction.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class RegionServiceImpl {

    private final RegionRepository regionRepository;

    public Iterable<Region> findAll() {
        return regionRepository.findAll();
    }

}
