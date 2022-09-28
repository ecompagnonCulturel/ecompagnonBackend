package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uqtr.ecompagnon.model.Cours;
import uqtr.ecompagnon.model.Resources;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    @Query("select distinct c from CourseResourse cr,Cours  c where  c.id=cr.coursResCours.id")
    List<Cours> getCoursBySession();
}
