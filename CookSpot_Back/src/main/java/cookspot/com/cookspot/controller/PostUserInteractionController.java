package cookspot.com.cookspot.controller;


import cookspot.com.cookspot.dto.PostUserInteractionDTO;
import cookspot.com.cookspot.service.BookmarkService;
import cookspot.com.cookspot.service.JwtService;
import cookspot.com.cookspot.service.PostService;
import cookspot.com.cookspot.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth/user_interaction")
public class PostUserInteractionController {

    private final BookmarkService bookmarkService;
    private final PostService postService;
    private final UserInfoService userInfoService;
    private final JwtService jwtService;

    public PostUserInteractionController(BookmarkService bookmarkService, PostService postService, UserInfoService userInfoService, JwtService jwtService) {
        this.bookmarkService = bookmarkService;
        this.postService = postService;
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
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

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/bookmark/update")
    public ResponseEntity<?> bookmarkUpdate(@RequestBody Map<String, String> body, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);

        PostUserInteractionDTO dto = new PostUserInteractionDTO();

        // wyciągnij idUser z tokena
        String idUser = jwtService.extractIdUser(token);

        String idPost = body.get("idPost");

        bookmarkService.updateBookmark(idUser, idPost);
        return ResponseEntity.ok().build();

    }
}
