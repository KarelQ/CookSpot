package cookspot.com.cookspot.controller;



import cookspot.com.cookspot.dto.PostDTO;
import cookspot.com.cookspot.entity.Post;
import cookspot.com.cookspot.service.BookmarkService;
import cookspot.com.cookspot.service.JwtService;
import cookspot.com.cookspot.service.PostService;
import cookspot.com.cookspot.service.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PostController {
    private final PostService postService;
    private final JwtService jwtService;
    private final BookmarkService bookmarkService;

    @Autowired
    public PostController(PostService postService, JwtService jwtService,  BookmarkService bookmarkService) {
        this.postService = postService;
        this.jwtService = jwtService;
        this.bookmarkService = bookmarkService;
    }


    @GetMapping("/auth/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsDTO() {
        List<PostDTO> posts = postService.getAllPostsDTO();
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/auth/posts/id/{id}")
    public ResponseEntity<PostDTO> getPostDTOById(@PathVariable String id) {
        PostDTO post = postService.getPostDTOById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @GetMapping("/auth/posts/user")
    public ResponseEntity<List<Post>> getPostDTOByUserId(@RequestHeader("Authorization") String authorizationHeader) {
        String idUser = jwtService.extractIdUserFromHeader(authorizationHeader);
        List<Post> posts = postService.getPostsByUserId(idUser);
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/auth/posts/bookmarks/get")
    public ResponseEntity<List<PostDTO>> getBookmarkedPostDTOByUserId(@RequestHeader("Authorization") String authorizationHeader) {
        String idUser = jwtService.extractIdUserFromHeader(authorizationHeader);
        List<Post> posts = bookmarkService.getBookmarkedPostsByUserId(idUser);
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(posts.stream()
                .map(postService::convertPostToPostDTO)
                .collect(Collectors.toList()));


    }



//    @GetMapping("/auth/posts/user")
//    public ResponseEntity<List<Post>> getPostDTOByUserId(Authentication authentication) {
//        UserInfoDetails userDetails = (UserInfoDetails) authentication.getPrincipal();
//        String idUser = userDetails.getIdUser();
//        List<Post> posts = postService.getPostsByUserId(idUser);
//        if (posts == null || posts.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(posts);
//    }





    @GetMapping("/auth/posts/category/{id}")
    public List<Post> getPostDTOByCategoryId(@PathVariable String id) {
        return postService.getPostDTOByCategoryId(id);
    }


    @PostMapping("/auth/posts/addpost")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        // wyciągnij idUser z tokena
        String idUser = jwtService.extractIdUser(token);

        System.out.println("===============================================================================");
        System.out.println(idUser);

        postDTO.setIdUser(idUser);

        PostDTO savedPost = postService.savePost(postDTO);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }


//    @PostMapping("/auth/posts/addpost")
//    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
//        PostDTO savedPost = postService.savePost(postDTO);
//        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
//    }

//    @PostMapping("/auth/posts/addpost")
//    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String idUser = null;
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                idUser = ((UserDetails) principal).getUsername();
//            } else {
//                idUser = principal.toString();
//            }
//        }
//
//        // ustaw username w postDto albo w encji Post
//        postDTO.setIdUser();
//
//        // dalej logika zapisu posta
//        PostDTO savedPost = postService.savePost(postDTO);
//
//        System.out.println( savedPost.toString());
//
//        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
//    }







    @DeleteMapping("/auth/posts/delete/{id}")
    public String deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return "deleted";
    }
}