package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.*;

import java.util.List;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

Etudiant getByEtudCP(String CP);

Etudiant getById(Long id);

@Query("select distinct g from questionnaire q ,groupEtudiant g where g.id=q.groupEtudiant")
List<GroupEtudiant> getQuestionnaireGroupEtudiant();

@Query("select distinct e from etudiant e order by e.etudFirstName")
List<Etudiant> getAllEtudiantOrderByFirstName();


}
