package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.CommentUpdateDTO;
import uqtr.ecompagnon.model.Comment;
import uqtr.ecompagnon.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    <S extends Comment> S save(CommentUpdateDTO commentUpdateDTO);
    <S extends Comment> Iterable<S> saveAll(Iterable<S> iterable);
    Optional<Comment> findById(Long Commentid);
    boolean existsById(Long Commentid);
    Iterable<Comment> findAll();
    Iterable<Comment> findAllById(Iterable<Long> iterable);
    long count();
    List<Comment> getAllComment();
    List<Comment> getAllCommentByResource(Long id);

     void delCommenttById(Long id);
    Iterable<Comment> getCommentByUser(long user, long resource);
}
