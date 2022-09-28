package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.EtudiantDTO;
import uqtr.ecompagnon.dto.UploadExcelResponseDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.CpMail;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.EtudiantGroupe;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.repository.CpMailRepository;
import uqtr.ecompagnon.repository.EtudiantRepository;
import uqtr.ecompagnon.service.EtudiantService;
import uqtr.ecompagnon.util.Excel;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final CpMailServiceImpl cpMailService;
    @Override
    public <S extends Etudiant> S save(EtudiantDTO etudiantDTO) {
        Etudiant etudiant=new Etudiant();
        Etudiant etudiantExist=etudiantRepository.getByEtudCP(etudiantDTO.getEtudCP());
        CpMail cpMailExist=cpMailService.findByCpeCP((etudiantDTO.getEtudCP()).toLowerCase());
        CpMail cpMail=new CpMail();
        if((Objects.isNull(etudiantExist))||(etudiantExist.getId()!=0))
        {
            etudiant.setId(etudiantDTO.getId());
            etudiant.setEtudFirstName(etudiantDTO.getEtudFirstName());
            etudiant.setEtudLastName(etudiantDTO.getEtudLastName());
            etudiant.setEtudCP(etudiantDTO.getEtudCP());
            etudiant.setEtudAdresse(etudiantDTO.getEtudAdresse());
            etudiant.setEtudStatus(1L);
            etudiant.setEtudProg(1L);
            /*
            Add cpMail if it not exist
            */
            if(Objects.isNull(cpMailExist))
            {
                cpMail.setId(0L);
                cpMail.setCpeMail(etudiantDTO.getEtudAdresse());
                cpMail.setCpeCP(etudiantDTO.getEtudCP());

            }
             /*
                update cpMail if student is updating
            */
            else if(etudiantExist.getId()!=0)
            {
                cpMail.setId(cpMailExist.getId());
                cpMail.setCpeMail(etudiantDTO.getEtudAdresse());
                cpMail.setCpeCP(etudiantDTO.getEtudCP());

            }
        }
        else
        {
            throw new IllegalStateException("Un étudiant avec ce code permanent existe déjà");

        }

        cpMailService.addCpMail(cpMail);
        return (S) etudiantRepository.save(etudiant);
    }

    @Override
    public <S extends Etudiant> Iterable<S> saveAll(Iterable<S> iterable) {
        return etudiantRepository.saveAll(iterable);
    }

    @Override
    public UploadResponseDTO saveAllByFile(MultipartFile file, HttpServletRequest request) {
        final int[] s = {0};
        String[] e = {null};
        final int[] total = {0};
        final int[] doublon = {0};
        List<String> errors = new ArrayList<String>();
        List<Etudiant> etudiantsForSend = new ArrayList<Etudiant>();
        List<CpMail> CpMails = new ArrayList<CpMail>();
        UploadResponseDTO uploadResponseDTO=new UploadResponseDTO();
        List<Etudiant> etudiants = Excel.excelToEtudiant(file,request);

        etudiants.forEach(etudiant -> {
            Etudiant etudiantDoublon=etudiantRepository
                    .getByEtudCP(etudiant.getEtudCP());
            boolean cpMailDoublon=cpMailService.existsCpMailByMailAndCp((etudiant.getEtudAdresse()).toLowerCase(),
                    (etudiant.getEtudCP()).toUpperCase());

            if(cpMailDoublon==false)
            {
                CpMail cpMail=new CpMail();
                cpMail.setId(0L);
                cpMail.setCpeMail(etudiant.getEtudAdresse());
                cpMail.setCpeCP(etudiant.getEtudCP());
               // System.out.println("1 "+etudiant.getEtudAdresse());
                //System.out.println("2 "+etudiant.getEtudCP());
                CpMails.add(cpMail);
            }

            if (etudiantDoublon!=null)
            {
                errors.add(etudiant.getEtudCP());
               // System.out.println("error "+errors.size());
                doublon[0] = doublon[0] +1;

            }
            else
            {
                s[0] = s[0] + 1;
                etudiantsForSend.add(etudiant);
            }


            total[0] = total[0] +1;



        });
        //System.out.println(etudiantGroupes);
        if(etudiantsForSend!=null)
        {
            this.saveAll(etudiantsForSend);
        }
       // System.out.println(CpMails.size());

        if(CpMails!=null)
        {
            System.out.println(CpMails.size());
            cpMailService.saveAll(CpMails);
        }
        uploadResponseDTO.setSucces((long) s[0]);
        uploadResponseDTO.setErrors(errors);
        uploadResponseDTO.setTotal((long) total[0]);
        uploadResponseDTO.setDoublon((long) doublon[0]);

        return uploadResponseDTO;
    }

    @Override
    public Optional<Etudiant> findById(Long Etudiantid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long Etudiantid) {
        return false;
    }

    @Override
    public Iterable<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Iterable<Etudiant> getAllEtudiantOrderByFirstName() {
        return etudiantRepository.getAllEtudiantOrderByFirstName();
    }

    @Override
    public void deleteById(Long id) {
        //Etudiant etudiant=etudiantRepository.getById(id);
        //cpMailService.deleteByCpeCP(etudiant.getEtudCP());
        etudiantRepository.deleteById(id);
    }

    @Override
    public Iterable<Etudiant> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Etudiant> getAllEtudiant() {
        return null;
    }

    @Override
    public Etudiant getByEtudCP(String CP) {
        return etudiantRepository.getByEtudCP(CP);
    }


}
