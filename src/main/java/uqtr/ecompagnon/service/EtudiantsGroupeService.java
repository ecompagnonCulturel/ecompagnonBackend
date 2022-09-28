package uqtr.ecompagnon.service;

import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.CheckListRequestDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface EtudiantsGroupeService {
    EtudiantGroupe getByEtudiantAndGroupEtudiantAndSession(String CP, Long group,Long session);
    UploadResponseDTO saveAll(MultipartFile file, Long groupe, HttpServletRequest request);
    List<GroupEtudiant> getAllGroupeEtudiantBySession(Long session);
    List<Etudiant> getAllEtudiantByGroup(Long group);
    List<GroupEtudiant> getAllGroupeEtudiant();
    EtudiantGroupe getBySessionAndEtudiant(Long session,String cp);
    List<EtudiantGroupe> getBySessionAndEtudiantList(Long session,String cp);
    void deleteByGroupEtudiantAndEtudiantAndSession(Long group,Long session,Long idEtud);
    List<Etudiant> getAllEtudiantNotInGroup(Long group);
    CheckListRequestDTO saveSomeStudentInGroup(CheckListRequestDTO checkListRequestDTO);
    List<Etudiant> getAllEtudiant();


}
