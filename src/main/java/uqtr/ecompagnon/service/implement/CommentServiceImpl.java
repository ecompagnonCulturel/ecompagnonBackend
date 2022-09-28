package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Comment;
import uqtr.ecompagnon.dto.CommentDTO;
import uqtr.ecompagnon.model.Resources;
import uqtr.ecompagnon.dto.CommentUpdateDTO;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.repository.CommentRepository;
import uqtr.ecompagnon.repository.ResourcesRepository;
import uqtr.ecompagnon.service.CommentService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

   private final CommentRepository commentRepository;
   private final ResourcesRepository resourcesRepository;
   private final AppUserRepository appUserRepository;

    public <S extends Comment> S save(CommentUpdateDTO commentUpdateDTO) {

        AppUser appUser=appUserRepository.findById(commentUpdateDTO.getCommentUser())
                .orElseThrow(()->
        new IllegalStateException(
                "User not found"
        ));
       Resources resources=resourcesRepository.findById(commentUpdateDTO.getCommentRessource())
               .orElseThrow(()->
               new IllegalStateException(
                       "Ressource not found"
               ));

        Comment comment=new Comment(commentUpdateDTO.getId(),
                                        appUser,
                                        resources,
                                        LocalDateTime.now(),
                                        commentUpdateDTO.getCommentDesc(),
                                        commentUpdateDTO.getCommentStatut()
                                    );

        return (S) commentRepository.save(comment);
    }

    public <S extends Comment> S update(CommentUpdateDTO commentUpdateDTO) {

        AppUser appUser=appUserRepository.findById(commentUpdateDTO.getCommentUser())
                .orElseThrow(()->
                        new IllegalStateException(
                                "User not found"
                        ));

        Resources resources=resourcesRepository.findById(commentUpdateDTO.getCommentRessource())
                .orElseThrow(()->
                        new IllegalStateException(
                                "Ressource not found"
                        ));

        Comment comment=new Comment(commentUpdateDTO.getId(),appUser,
                resources,
                LocalDateTime.now(),
                commentUpdateDTO.getCommentDesc(),
                commentUpdateDTO.getCommentStatut()
        );


        comment.setCommentDate(LocalDateTime.now());

        return (S) commentRepository.save(comment);
    }


    public <S extends Comment> Iterable<S> saveAll(Iterable<S> iterable) {
        return commentRepository.saveAll(iterable);
    }


    public Optional<Comment> findById(Long Commentid) {
        return commentRepository.findById(Commentid);
    }


    public boolean existsById(Long Commentid) {
        return commentRepository.existsById(Commentid);
    }


    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }


    public Iterable<Comment> findAllById(Iterable<Long> iterable) {
        return commentRepository.findAllById(iterable);
    }


    public long count() {
        return commentRepository.count();
    }

    public List<Comment> getAllComment() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllCommentByResource(Long id) {
        Resources resources=resourcesRepository.findById(id)
                .orElseThrow(()->
                        new AppRequestException(
                                "la ressource n'existe pas"
                        ));
        return commentRepository.getCommentByCommentResource(resources);
    }


    public void delCommenttById(Long id) {
        commentRepository.deleteById(id);
    }


    public Iterable<Comment> getCommentByUser(long user, long resource) {
        return commentRepository.getCommentByUser(user, resource);
    }

}
