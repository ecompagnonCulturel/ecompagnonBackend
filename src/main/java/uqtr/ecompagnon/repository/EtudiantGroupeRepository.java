package uqtr.ecompagnon.repository;

import liquibase.pro.packaged.G;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.*;

import java.util.List;

@Repository
public interface EtudiantGroupeRepository extends JpaRepository<EtudiantGroupe, Long> {

EtudiantGroupe getByEtudiantAndGroupEtudiantAndSession(Etudiant e, GroupEtudiant ge, Session s);

@Query("select distinct e.etudiant from etudiantgroupe e " +
        "where e.groupEtudiant=:groupEtudiant " +
        "order by e.etudiant.etudFirstName")
List<Etudiant> getAllEtudiantByGroup(@Param("groupEtudiant") GroupEtudiant groupEtudiant);



@Query("select distinct g from etudiantgroupe e,groupEtudiant g where g.id=e.groupEtudiant and e.session=:session")
List<GroupEtudiant> getAllGroupeEtudiantBySession(@Param("session") Session session);

@Query("select distinct g from etudiantgroupe e,groupEtudiant g where g.id=e.groupEtudiant order by g.groupName asc")
List<GroupEtudiant> getAllGroupeEtudiant();


@Query("select distinct e from etudiantgroupe eg, etudiant e where e.id=eg.etudiant ")
List<Etudiant> getAllEtudiant();


@Query("select e  from etudiant e "
        +"where e.id not in " +
        "(select eg.etudiant.id from etudiantgroupe eg  where eg.groupEtudiant.id=:group) " +
        "order by e.etudFirstName"
        )
List<Etudiant> getAllEtudiantNotInGroup(Long group);

EtudiantGroupe getEtudiantGroupeByEtudiantAndGroupEtudiantAndSession(Etudiant etudiant, GroupEtudiant groupEtudiant, Session session);

List<EtudiantGroupe> getBySessionAndEtudiant(Session session,Etudiant etudiant);
@Modifying
@Query("delete from etudiantgroupe e where e.groupEtudiant.id=:group and e.etudiant.id=:idEtud " +
        "and e.groupEtudiant.id in (select distinct g.id from groupEtudiant g " +
                                "where g.id=e.groupEtudiant.id " +
                                 "and g.groupSession.id=:session)")
void deleteByGroupEtudiantAndEtudiantAndSession(Long group,Long idEtud,Long session);

}
