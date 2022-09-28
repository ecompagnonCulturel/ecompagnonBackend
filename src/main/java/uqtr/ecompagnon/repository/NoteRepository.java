package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Note;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    @Query("select n from Note n where n.noteUser.idUsers=:idUsers and n.noteResource.id=:idResource ")
    List<Note> getNoteByUser(@Param("idUsers") Long idUsers, @Param("idResource") Long idResource);

}
