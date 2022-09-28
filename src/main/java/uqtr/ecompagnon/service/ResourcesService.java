package uqtr.ecompagnon.service;

import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.ResourceDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.Resources;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ResourcesService {
    <S extends Resources> S save(ResourceDTO resourceDTO);
    Iterable<Resources> getResourceBytRDesc(String trdesc);
    Optional<Resources> findById(Long id);
    Boolean existsResourcesById(Long id);
    public long count();
    Iterable<Resources> findAll();
    void deleteResources(Long id);
    Iterable<Resources>  getRessourceBySession();
    Iterable<Resources>   getRessourceByCours(long cours);
    List<Resources> getByRessourceAndCours( long ressource, long cours);
    <S extends Resources> Iterable<S> saveAll(Iterable<S> iterable);
    void deleteById(Long id);
    UploadResponseDTO saveAllByFile(MultipartFile file,Long session, HttpServletRequest request);
}
