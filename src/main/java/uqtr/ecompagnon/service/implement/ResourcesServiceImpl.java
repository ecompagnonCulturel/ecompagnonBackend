package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.ResourceDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.RegionRepository;
import uqtr.ecompagnon.repository.ResourcesRepository;
import uqtr.ecompagnon.repository.SessionRepository;
import uqtr.ecompagnon.repository.TypeRessourceRepository;
import uqtr.ecompagnon.service.ResourcesService;
import uqtr.ecompagnon.util.Excel;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final RegionRepository regionRepository;
    private final TypeRessourceRepository typeRessourceRepository;
    private final SessionRepository sessionRepository;
    @Override
    public <S extends Resources> S save(ResourceDTO resourceDTO) {

        Region region;

        if(resourceDTO.getRessRegion()>0)
        {
             region=regionRepository.findById(resourceDTO.getRessRegion())
                    .orElseThrow(()->
                            new IllegalStateException(
                                    "region not found"
                            ));
        }
        else
        {
            region=null;
        }



        TypeRessource typeRessource =typeRessourceRepository.findById(resourceDTO.getRessType())
                .orElseThrow(()->
                        new IllegalStateException(
                                "Type not found"
                        ));

        Session session=sessionRepository.findById(resourceDTO.getRessSession())
                .orElseThrow(()->
                        new IllegalStateException(
                                "session not found"
                        ));

       // System.out.println(session.getSessNom());
      //  System.out.println(typeRessource.getTRDesc());

        Resources resources=new Resources();
        resources.setId(resourceDTO.getId());
        resources.setRessDesc(resourceDTO.getRessDesc());
        resources.setRessCodeP(resourceDTO.getRessCodeP());
        resources.setRessVille(resourceDTO.getRessVille());
        resources.setRessLieu(resourceDTO.getRessLieu());
        resources.setRessRegion(region);
        resources.setRessSession(session);
        resources.setRessType(typeRessource);
        resources.setRessUrl(resourceDTO.getRessUrl());
        resources.setRessStatus("1");
        resources.setRessDate(new Date());
        resources.setRessUpdateDate(new Date());

        return (S) resourcesRepository.save(resources);
    }

    @Override
    public Optional<Resources> findById(Long Resourcesid) {
        return resourcesRepository.findById(Resourcesid);
    }

    @Override
    public Boolean existsResourcesById(Long Resourcesid) {
        return resourcesRepository.existsById(Resourcesid);
    }

    @Override
    public Iterable<Resources> findAll() {
        return resourcesRepository.findAll();
    }

    @Override
    public void deleteResources(Long id) {

    }

    @Override
    public Iterable<Resources> getResourceBytRDesc(String trdesc) {
        return resourcesRepository.getResourceBytRDesc(trdesc);
    }

    @Override
    public long count() {
        return resourcesRepository.count();
    }
    @Override
    public <S extends Resources> Iterable<S> saveAll(Iterable<S> iterable) {
        return resourcesRepository.saveAll(iterable);
    }

    @Override
    public void deleteById(Long id) {
        resourcesRepository.deleteById(id);
    }



    public Optional<Resources> getResourcesById(long id) {
        return resourcesRepository.findById(id);
    }

    @Override
    public Iterable<Resources> getRessourceBySession() {

        return resourcesRepository.getRessourceBySession();
    }

    @Override
    public Iterable<Resources> getRessourceByCours(long cours) {
        return resourcesRepository.getRessourceByCours(cours);
    }

    @Override
    public List<Resources> getByRessourceAndCours(long ressource, long cours) {
        return resourcesRepository.getByRessourceAndCours(ressource,cours);
    }

    @Override
    public UploadResponseDTO saveAllByFile(MultipartFile file, Long sessionP, HttpServletRequest request) {
        final int[] s = {0};
        String[] e = {null};
        final int[] total = {0};
        final int[] doublon = {0};
        List<String> errors = new ArrayList<String>();
        List<Resources> resourceSend = new ArrayList<Resources>();
        UploadResponseDTO uploadResponseDTO=new UploadResponseDTO();

        List<ResourceDTO> resourceDTOS = Excel.excelToRessource(file,sessionP,request);
        resourceDTOS.forEach(resourceDTO -> {
            Resources resourcesExist=resourcesRepository.findResourcesByRessUrl(resourceDTO.getRessUrl());
            if(resourcesExist==null)
            {
                Region region ;
                TypeRessource typeRessource = typeRessourceRepository.findById(resourceDTO.getRessType())
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Type not found"
                                ));
                Session session = sessionRepository.findById(sessionP)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "session not found"
                                ));

                Resources resources = new Resources();
                resources.setId(resourceDTO.getId());
                resources.setRessDesc(resourceDTO.getRessDesc());
                resources.setRessSession(session);
                resources.setRessType(typeRessource);
                resources.setRessUrl(resourceDTO.getRessUrl());
                resources.setRessStatus("1");
                resources.setRessDate(new Date());
                resources.setRessUpdateDate(new Date());

                if ((typeRessource.getTRDesc().equals("Lieux")) && (resourceDTO.getRessRegion() != 0)) {
                    region = regionRepository.findById(resourceDTO.getRessRegion())
                            .orElseThrow(() ->
                                    new IllegalStateException(
                                            "region not found"
                                    ));
                    resources.setRessCodeP(resourceDTO.getRessCodeP());
                    resources.setRessVille(resourceDTO.getRessVille());
                    resources.setRessLieu(resourceDTO.getRessLieu());
                    resources.setRessRegion(region);
                } else {
                    region = null;
                    resources.setRessCodeP(null);
                    resources.setRessVille(null);
                    resources.setRessLieu(null);
                    resources.setRessRegion(null);
                }
                resourceSend.add(resources);
                s[0] = s[0] + 1;
            }
            else
            {
                System.out.println("else ");
                errors.add(resourceDTO.getRessDesc());
                // System.out.println("error "+errors.size());
                doublon[0] = doublon[0] +1;
            }
            total[0] = total[0] +1;
        });

        if(resourceSend!=null)
        {
            this.saveAll(resourceSend);
        }

        uploadResponseDTO.setSucces((long) s[0]);
        uploadResponseDTO.setErrors(errors);
        uploadResponseDTO.setTotal((long) total[0]);
        uploadResponseDTO.setDoublon((long) doublon[0]);

        return uploadResponseDTO;
    }
}
