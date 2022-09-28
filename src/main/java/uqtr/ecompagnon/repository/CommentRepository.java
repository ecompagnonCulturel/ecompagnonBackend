package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Comment;
import uqtr.ecompagnon.model.Resources;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.commentUser.idUsers=:idUsers and  c.commentResource.id=:idResource")
    List<Comment> getCommentByUser(@Param("idUsers") Long idUsers, @Param("idResource") Long idResource);

    List<Comment> getCommentByCommentResource(Resources resources);

}
