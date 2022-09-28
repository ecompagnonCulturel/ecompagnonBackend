package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.Note;
import uqtr.ecompagnon.dto.NoteDTO;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    <S extends Note> S save(NoteDTO noteDTO);
    <S extends Note> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<Note> findById(Long Noteid);
    boolean existsById(Long Noteid);
    Iterable<Note> findAll();
    Iterable<Note> findAllById(Iterable<Long> iterable);
    long count();
    List<Note> getAllNote();
    Iterable<Note> getNoteByUserAndResource(long user, long resource);
}
