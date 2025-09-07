package cookspot.com.cookspot.controller;


import cookspot.com.cookspot.dto.CommentsDTO;
import cookspot.com.cookspot.dto.PostDTO;
import cookspot.com.cookspot.dto.PostUserInteractionDTO;
import cookspot.com.cookspot.repository.RatingRepository;
import cookspot.com.cookspot.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auth/user_interaction")
public class PostUserInteractionController {

    private final BookmarkService bookmarkService;
    private final PostService postService;
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final RatingService ratingService;
    private final CommentService commentService;

    public PostUserInteractionController(BookmarkService bookmarkService, PostService postService, UserInfoService userInfoService, JwtService jwtService, RatingService ratingService , CommentService commentService) {
        this.bookmarkService = bookmarkService;
        this.postService = postService;
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }


    @PostMapping("/check/{idPost}")
    public ResponseEntity<PostUserInteractionDTO> checkInteractions(@PathVariable  String idPost, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);

        PostUserInteractionDTO dto = new PostUserInteractionDTO();

        // wyciągnij idUser z tokena
        String idUser = jwtService.extractIdUser(token);

        //String idPost = body.get("idPost");

        System.out.println("===============================================================================");
        System.out.println(idUser+"\n postid :"+idPost);
        System.out.println("===============================================================================");

        dto.setIdUser(idUser);
        dto.setIdPost(idPost);
        dto.setBooked(bookmarkService.isBookmarked(idUser, idPost));
        dto.setOwner(postService.isOwner(idPost, idUser));
        dto.setStars(ratingService.getStarsFromIdUserAndIdPost(idUser, idPost));
        dto.setIsLiked(ratingService.getVotesFromIdUserAndIdPost(idUser, idPost));
        dto.setComment(commentService.isCommented(idUser, idPost));

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/bookmark/update")
    public ResponseEntity<?> bookmarkUpdate(@RequestBody Map<String, String> body, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);

        PostUserInteractionDTO dto = new PostUserInteractionDTO();

        // wyciągnij idUser z tokena
        String idUser = jwtService.extractIdUser(token);

        String idPost = body.get("idPost");

        bookmarkService.updateBookmark(idUser, idPost);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/stars/update")
    public ResponseEntity<?> starsUpdate(@RequestBody Map<String, String> body,@RequestHeader("Authorization") String authorizationHeader){
        String idUser = jwtService.extractIdUserFromHeader(authorizationHeader);
        String idPost = body.get("idPost");
        int stars = Integer.parseInt(body.get("stars"));

        ratingService.setOrUpdateStars(idUser, idPost, stars);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/vote/update")
    public ResponseEntity<?> voteUpdate(@RequestBody Map<String, String> body,@RequestHeader("Authorization") String authorizationHeader){
        String idUser = jwtService.extractIdUserFromHeader(authorizationHeader);
        String idPost = body.get("idPost");
        int vote = Integer.parseInt(body.get("vote"));

        ratingService.setOrUpdateVote(idUser, idPost, vote);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/get/{idPost}")
    public ResponseEntity<List<CommentsDTO>> getComments(@PathVariable  String idPost){
        List<CommentsDTO> comments = commentService.getAllCommentsDTOByIdPost(idPost);
        if (comments == null || comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(@RequestBody Map<String, String> body,@RequestHeader("Authorization") String authorizationHeader){
        String idUser = jwtService.extractIdUserFromHeader(authorizationHeader);
        String idPost = body.get("idPost");
        String comment = body.get("comment_content");
        System.out.println(idUser+"\n postid :"+idPost+"\n comment :"+comment);
        if(commentService.addNewComment(idUser, idPost, comment)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
