package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Note;
import uqtr.ecompagnon.model.Resources;
import uqtr.ecompagnon.dto.NoteDTO;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.repository.NoteRepository;
import uqtr.ecompagnon.repository.ResourcesRepository;
import uqtr.ecompagnon.service.NoteService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ResourcesRepository resourcesRepository;
    private final AppUserRepository appUserRepository;

    public <S extends Note> S save(NoteDTO noteDTO) {
        AppUser appUser=appUserRepository.findById(noteDTO.getNoteUser())
                .orElseThrow(()->
                        new IllegalStateException(
                                "User not found"
                        ));
        Resources resources=resourcesRepository.findById(noteDTO.getNoteResource())
                .orElseThrow(()->
                        new IllegalStateException(
                                "Ressource not found"
                        ));

        Note note=new Note(noteDTO.getId(),appUser,
                resources,
                LocalDateTime.now(),
                noteDTO.getNoteDesc(),
                noteDTO.getNoteStatus()
        );


        return (S) noteRepository.save(note);
    }


    public <S extends Note> Iterable<S> saveAll(Iterable<S> iterable) {
        return noteRepository.saveAll(iterable);
    }


    public Optional<Note> findById(Long Noteid) {
        return noteRepository.findById(Noteid);
    }


    public boolean existsById(Long Noteid) {
        return noteRepository.existsById(Noteid);
    }


    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }


    public Iterable<Note> findAllById(Iterable<Long> iterable) {
        return noteRepository.findAllById(iterable);
    }


    public long count() {
        return noteRepository.count();
    }

    public List<Note> getAllNote() {
        return (List<Note>) noteRepository.findAll();
    }


    public void delNotetById(Long id) {
        noteRepository.deleteById(id);
    }


    public Iterable<Note> getNoteByUserAndResource(long user, long resource) {
        return noteRepository.getNoteByUser(user, resource);
    }

}
