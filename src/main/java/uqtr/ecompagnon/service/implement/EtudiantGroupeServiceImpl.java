package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.CheckListRequestDTO;
import uqtr.ecompagnon.dto.UploadExcelResponseDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.EtudiantGroupeRepository;
import uqtr.ecompagnon.repository.EtudiantRepository;
import uqtr.ecompagnon.repository.GroupEtudiantRepository;
import uqtr.ecompagnon.repository.SessionRepository;
import uqtr.ecompagnon.service.EtudiantsGroupeService;
import uqtr.ecompagnon.util.Excel;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class EtudiantGroupeServiceImpl implements EtudiantsGroupeService {
    private final EtudiantServiceImpl etudiantService;
    private final GroupEtudiantServiceImpl groupEtudiantService;
    private final SessionServiceImpl sessionService;
    private final EtudiantGroupeRepository etudiantGroupeRepository;
    private final SessionRepository sessionRepository;
    private final EtudiantRepository etudiantRepository;
    private final GroupEtudiantRepository groupEtudiantRepository;


    @Override
    public EtudiantGroupe getByEtudiantAndGroupEtudiantAndSession(String CP, Long group,Long session) {
        Etudiant etudiant=etudiantService.getByEtudCP(CP);
        GroupEtudiant groupEtudiant=groupEtudiantService.findById(group)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Groupe Etudiant not found"
                        ));
        Session session1=sessionService.getById(session)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Session not found"
                        ));

        return  etudiantGroupeRepository.getByEtudiantAndGroupEtudiantAndSession(etudiant,groupEtudiant,session1);
    }

    public UploadResponseDTO saveAll(MultipartFile file, Long groupe, HttpServletRequest request) {
        final int[] s = {0};
         String[] e = {null};
        final int[] total = {0};
        final int[] doublon = {0};
        List<String> errors = new ArrayList<String>();
        UploadResponseDTO uploadResponseDTO=new UploadResponseDTO();
        List<UploadExcelResponseDTO> uploadExcelResponsDTOS = Excel.excelToEtudiantGroup(file,groupe,request);
        List<EtudiantGroupe> etudiantGroupes = new ArrayList<EtudiantGroupe>();
        GroupEtudiant groupEtudiant=groupEtudiantService.findById(groupe)
                                    .orElseThrow(()->
                                            new IllegalStateException(
                                                    "groupe not found"
                                            ));

       // System.out.println(uploadExcelResponsDTOS);
        uploadExcelResponsDTOS.forEach(uploadExcelResponseDTO -> {
            EtudiantGroupe etudiantGroupe=new EtudiantGroupe();
            String cp= uploadExcelResponseDTO.getCpEtudiant();
            Etudiant etudiant=etudiantService.getByEtudCP(cp);
            //System.out.println(etudiant);
            if (etudiant==null)
            {
                // new IllegalStateException("Session not found");
                System.out.println("me");
                System.out.println(cp);
                System.out.println("meme");
                errors.add(cp);
                System.out.println(errors);


            }
            else
            {
                EtudiantGroupe etudiantGroupeDoublon=etudiantGroupeRepository.getEtudiantGroupeByEtudiantAndGroupEtudiantAndSession(etudiant,groupEtudiant, groupEtudiant.getGroupSession());
                //System.out.println("meme");
                if(etudiantGroupeDoublon==null) {
                   // System.out.println("mememe");
                    etudiantGroupe.setEtudiant(etudiant);
                    etudiantGroupe.setGroupEtudiant(groupEtudiant);
                    etudiantGroupe.setSession(groupEtudiant.getGroupSession());
                    etudiantGroupe.setStatut(1L);
                    s[0] = s[0] + 1;
                    etudiantGroupes.add(etudiantGroupe);
                }
                else
                {
                    doublon[0] = doublon[0] +1;
                    System.out.println("memememe");
                }
            }


            total[0] = total[0] +1;




        });
        //System.out.println(etudiantGroupes);
        if(etudiantGroupes!=null)
        {
            etudiantGroupeRepository.saveAll(etudiantGroupes);
        }
        uploadResponseDTO.setSucces((long) s[0]);
        uploadResponseDTO.setErrors(errors);
        uploadResponseDTO.setTotal((long) total[0]);
        uploadResponseDTO.setDoublon((long) doublon[0]);

        return uploadResponseDTO;
    }

    @Override
    public List<GroupEtudiant> getAllGroupeEtudiantBySession(Long sessionP) {
        Session session=sessionRepository.findById(sessionP)
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));
        return etudiantGroupeRepository.getAllGroupeEtudiantBySession(session);
    }

    @Override
    public List<Etudiant> getAllEtudiantByGroup(Long group) {
        GroupEtudiant groupEtudiant=groupEtudiantService.findById(group)
                .orElseThrow(()->
                        new IllegalStateException(
                                "sessiogroupen not found"
                        ));
        return etudiantGroupeRepository.getAllEtudiantByGroup(groupEtudiant);
    }

    @Override
    public List<GroupEtudiant> getAllGroupeEtudiant() {
        return etudiantGroupeRepository.getAllGroupeEtudiant();
    }

    @Override
    public EtudiantGroupe getBySessionAndEtudiant(Long session, String cp) {
        Session sessionRec=sessionRepository.findById(session)
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));

        Etudiant etudiantRec=etudiantService.getByEtudCP(cp);
        List<EtudiantGroupe> etudiantGroupes=etudiantGroupeRepository.getBySessionAndEtudiant(sessionRec,etudiantRec);
        if(etudiantGroupes.size()>0)
        {
            return etudiantGroupes.get(0);//pour ne retourner qu'un seul groupe meme si l'etudiant appartient à plusieurs groupes
        }
        else {
            return null;
        }
    }

    @Override
    public  List<EtudiantGroupe> getBySessionAndEtudiantList(Long session, String cp) {
        Session sessionRec=sessionRepository.findById(session)
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));

        Etudiant etudiantRec=etudiantService.getByEtudCP(cp);
        List<EtudiantGroupe> etudiantGroupes=etudiantGroupeRepository.getBySessionAndEtudiant(sessionRec,etudiantRec);

            return etudiantGroupes;//pour ne retourner qu'un seul groupe meme si l'etudiant appartient à plusieurs groupes

    }

    @Override
    public void deleteByGroupEtudiantAndEtudiantAndSession(Long group,Long idEtud,Long session) {
        etudiantGroupeRepository.deleteByGroupEtudiantAndEtudiantAndSession(group,idEtud,session);
    }

    @Override
    public List<Etudiant> getAllEtudiantNotInGroup(Long group) {
        return etudiantGroupeRepository.getAllEtudiantNotInGroup(group);
    }

    @Override
    public List<Etudiant> getAllEtudiant() {
        return etudiantGroupeRepository.getAllEtudiant();
    }

    @Override
    public CheckListRequestDTO saveSomeStudentInGroup(CheckListRequestDTO checkListRequestDTOs) {
        List<EtudiantGroupe> etudiantGroupes = new ArrayList<EtudiantGroupe>();
        GroupEtudiant groupEtudiant=groupEtudiantRepository.findById(checkListRequestDTOs.getGroup())
                .orElseThrow(()->
                        new IllegalStateException(
                                "groupEtudiant not found"
                        ));
        checkListRequestDTOs.getIdEtud().forEach(checkListRequestDTO -> {
            Etudiant etudiant=etudiantRepository.findById(checkListRequestDTO) .orElseThrow(()->
                    new IllegalStateException(
                            "etudiant not found"
                    ));
            EtudiantGroupe etudiantGroupe=new EtudiantGroupe();
            etudiantGroupe.setEtudiant(etudiant);
            etudiantGroupe.setSession(groupEtudiant.getGroupSession());
            etudiantGroupe.setGroupEtudiant(groupEtudiant);
            etudiantGroupe.setStatut(1L);
            etudiantGroupe.setId(0L);

            etudiantGroupes.add(etudiantGroupe);

        });

        etudiantGroupeRepository.saveAll(etudiantGroupes);
        return checkListRequestDTOs;
    }


}
