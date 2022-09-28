package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.*;

import java.util.List;
@Repository
public interface CourseResourceRepository extends JpaRepository<CourseResourse, Long> {

    Iterable<CourseResourse> findByCoursResResource(Resources coursResResource);

    @Query("select distinct cr.coursResResource from CourseResourse cr")
    List<Resources> findAllRessource();

    @Query("select distinct cr.coursResProf from CourseResourse cr")
    List<Prof> findAllProf();

    @Query("select distinct cr.coursResCours from CourseResourse cr")
    List<Cours> findAllCours();

    @Query("select distinct cr from CourseResourse cr where cr.coursResSession.id=:session and " +
            "cr.coursResResource.id=:resource and " +
            "cr.coursResProf.id=:prof and " +
            "cr.coursResCours.id=:cours")
    List<CourseResourse> getExistIntention(long session, long resource, long prof, long cours);


    /* Iterable<CourseResourse> findByCoursResSession(Session session);
*/


    Iterable<CourseResourse> findByCoursResResourceAndCoursResCours(Resources resource,Cours cours);
}
