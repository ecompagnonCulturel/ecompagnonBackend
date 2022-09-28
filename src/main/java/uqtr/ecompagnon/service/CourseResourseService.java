package uqtr.ecompagnon.service;

import org.springframework.data.jpa.repository.JpaRepository;
import uqtr.ecompagnon.dto.CoursResourceDTO;
import uqtr.ecompagnon.model.Cours;
import uqtr.ecompagnon.model.CourseResourse;
import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.model.Resources;

import java.util.List;
import java.util.Optional;

public interface CourseResourseService  {
    <S extends CourseResourse> S save(CoursResourceDTO coursResourceDTO);
    <S extends CourseResourse> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<CourseResourse> findById(Long CourseResourseid);
    boolean existsById(Long CourseResourseid);
    Iterable<CourseResourse> findAll();
    Iterable<CourseResourse> findAllById(Iterable<Long> iterable);
    long count();
    List<CourseResourse> getAllCourseResourse();
    Iterable<CourseResourse> getByCoursResResource(long resource);
    Iterable<CourseResourse> getCourseResourseByResourceAndCours(long resource,long cours);
    Iterable<Resources> getAllRessource();
    Iterable<Prof> findAllProf();
    Iterable<Cours> findAllCours();
    void deleteById(Long id);

}
