package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uqtr.ecompagnon.model.Comment;
import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.model.Resources;

import java.util.List;

public interface ProfRepository extends JpaRepository<Prof, Long> {
     List<Prof> getProfByProfLastName(String lastName);
}
