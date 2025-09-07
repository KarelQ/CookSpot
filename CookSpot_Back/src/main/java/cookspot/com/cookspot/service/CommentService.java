package cookspot.com.cookspot.service;

import cookspot.com.cookspot.dto.CommentsDTO;
import cookspot.com.cookspot.entity.Comment;
import cookspot.com.cookspot.entity.CommentId;
import cookspot.com.cookspot.entity.UserInfo;
import cookspot.com.cookspot.repository.CommentRepository;
import cookspot.com.cookspot.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserInfoRepository userInfoRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,  UserInfoRepository userInfoRepository) {
        this.commentRepository = commentRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public CommentsDTO convertCommentToCommentDTO(Comment comment){
       CommentsDTO dto = new CommentsDTO();

       dto.setCommentContent(comment.getCommentContent());
       dto.setCommentDate(comment.getPublicationDate());
       System.out.println("TEST_________");
       System.out.println(comment.getId().getIdUser());
       String idUser = comment.getId().getIdUser();
       System.out.println(idUser);
       UserInfo user = userInfoRepository.findById(idUser).orElse(null);
       if (user == null){
           dto.setUsername("Anonymous");
       } else {
           dto.setUsername(user.getUsername());
       }
       return dto;
    }


    public List<CommentsDTO> getAllCommentsDTOByIdPost(String idPost){
        List<Comment> comments = commentRepository.findById_IdPost(idPost).orElse(null);
        if (comments == null){
            return List.of(new CommentsDTO());
        } else {
            return comments.stream()
                    .map(this::convertCommentToCommentDTO)
                    .collect(Collectors.toList());
        }



    }


    public boolean isCommented(String idUser, String idPost) {
        return commentRepository.existsById(new CommentId(idUser, idPost));
    }

    public boolean addNewComment(String idUser, String idPost, String comment) {
        try {
            Comment newComment = new Comment();
            newComment.setId(new CommentId(idUser, idPost));
            newComment.setCommentContent(comment);
            newComment.setPublicationDate(Instant.now());
            commentRepository.save(newComment);
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}



