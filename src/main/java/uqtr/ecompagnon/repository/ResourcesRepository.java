package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.CourseResourse;
import uqtr.ecompagnon.model.Resources;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    @Query("select r from Resources r where r.ressType.tRDesc=:tRDesc ")
    List<Resources> getResourceBytRDesc(@Param("tRDesc") String tRDesc);

    @Query("select distinct r from CourseResourse cr,Resources  r where r.id=cr.coursResResource.id")
    List<Resources> getRessourceBySession();

    @Query("select distinct r from CourseResourse cr,Resources  r where r.id=cr.coursResResource.id and cr.coursResCours.id=:cours")
    List<Resources> getRessourceByCours(@Param("cours") long cours);

    @Query("select distinct r from CourseResourse cr,Resources  r where r.id=cr.coursResResource.id and cr.coursResCours.id=:cours and r.id=:ressource")
    List<Resources> getByRessourceAndCours(@Param("ressource") long ressource,@Param("cours") long cours);

    Resources findResourcesByRessUrl(String Url);


}
