package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Note;
import uqtr.ecompagnon.model.Notification;

import javax.transaction.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Transactional
    @Query("delete from notification n where n.questionnaire in (select q from questionnaire q " +
            "where q.id=:questionnaire " +
            "and q.session.id=:session)")
    void deleteByQuestionnaireAndSession(Long questionnaire,Long session);
}
