package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.CoursResourceDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.CourseResourseService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
@AllArgsConstructor
public class CourseResourceServiceImpl implements CourseResourseService {
    private final CourseResourceRepository courseResourceRepository;
    private final ResourcesRepository resourcesRepository;
    private final SessionRepository sessionRepository;
    private final ProfRepository profRepository;
    private final CoursRepository coursRepository;
    @Override
    public <S extends CourseResourse> S save(CoursResourceDTO coursResourceDTO)
    {
        List<CourseResourse> courseResourseExist=courseResourceRepository.getExistIntention(coursResourceDTO.getCoursResSession(),
                                                                                    coursResourceDTO.getCoursResResource(),
                                                                                    coursResourceDTO.getCoursResProf(),
                                                                                    coursResourceDTO.getCoursResCours());
        if(courseResourseExist.size()<=1)
        {
            Session session=sessionRepository.findById(coursResourceDTO.getCoursResSession()) .orElseThrow(()->
                    new IllegalStateException(
                            "session not found"
                    ));

            Resources resources=resourcesRepository.findById(coursResourceDTO.getCoursResResource())
                    .orElseThrow(()->
                            new IllegalStateException(
                                    "Ressource not found"
                            ));

            Prof prof=profRepository.findById(coursResourceDTO.getCoursResProf())
                    .orElseThrow(()->
                            new IllegalStateException(
                                    "formateurs not found"
                            ));

            Cours cours=coursRepository.findById(coursResourceDTO.getCoursResCours())
                    .orElseThrow(()->
                            new IllegalStateException(
                                    "cours not found"
                            ));

            CourseResourse courseResourse=new CourseResourse();
            courseResourse.setId(coursResourceDTO.getId());
            courseResourse.setCoursResIntention(coursResourceDTO.getCoursResIntention());
            courseResourse.setCoursResCours(cours);
            courseResourse.setCoursResResource(resources);
            courseResourse.setCoursResProf(prof);
            courseResourse.setCoursResSession(session);
            courseResourse.setCoursResStatus(1);

            return (S)courseResourceRepository.save(courseResourse);

        }
        else
        {
            throw new AppRequestException("Pour cette session et ce cours, cette ressource a déjà " +
                    "une intention!!!");
        }

    }

    @Override
    public <S extends CourseResourse> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CourseResourse> findById(Long CourseResourseid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long CourseResourseid) {
        return false;
    }

    @Override
    public Iterable<CourseResourse> findAll() {
        return courseResourceRepository.findAll();
    }

    @Override
    public Iterable<Resources> getAllRessource() {
       return courseResourceRepository.findAllRessource() ;
    }

    @Override
    public Iterable<CourseResourse> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<CourseResourse> getAllCourseResourse() {
        return courseResourceRepository.findAll();
    }

    @Override
    public Iterable<CourseResourse> getByCoursResResource(long resource) {

        Resources resources=resourcesRepository.findById(resource)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Ressource not found"
                        ));
        return courseResourceRepository.findByCoursResResource(resources);
    }



    @Override
    public Iterable<CourseResourse> getCourseResourseByResourceAndCours(long resource, long cours) {

        Resources resources=resourcesRepository.findById(resource)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Ressource not found"
                        ));

        Cours courss=coursRepository.findById(cours)
                .orElseThrow(()->
                        new IllegalStateException(
                                "cours not found"
                        ));
        return courseResourceRepository.findByCoursResResourceAndCoursResCours(resources,courss);
    }
    @Override
   public Iterable<Prof> findAllProf(){
        return courseResourceRepository.findAllProf();

    };

    @Override
    public Iterable<Cours> findAllCours(){
        return courseResourceRepository.findAllCours();

    };

    @Override
    public void deleteById(Long id) {
        //Etudiant etudiant=etudiantRepository.getById(id);
        //cpMailService.deleteByCpeCP(etudiant.getEtudCP());
        courseResourceRepository.deleteById(id);
    }
}
