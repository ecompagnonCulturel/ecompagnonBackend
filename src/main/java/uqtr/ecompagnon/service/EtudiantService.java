package uqtr.ecompagnon.service;

import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.EtudiantDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.Etudiant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    <S extends Etudiant> S save(EtudiantDTO etudiantDTO);
    <S extends Etudiant> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<Etudiant> findById(Long Etudiantid);
    boolean existsById(Long Etudiantid);
    Iterable<Etudiant> findAll();
    Iterable<Etudiant> findAllById(Iterable<Long> iterable);
    long count();
    List<Etudiant> getAllEtudiant();
    Etudiant getByEtudCP(String CP);
    Iterable<Etudiant> getAllEtudiantOrderByFirstName();
    void deleteById(Long id);
    UploadResponseDTO saveAllByFile(MultipartFile file, HttpServletRequest request);


}
