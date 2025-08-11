package cookspot.com.cookspot.controller;



import cookspot.com.cookspot.dto.PostDTO;
import cookspot.com.cookspot.entity.Post;
import cookspot.com.cookspot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    @GetMapping("/auth/posts")
    public List<PostDTO> getAllPostsDTO() {
        return postService.getAllPostsDTO();
    }



    @GetMapping("/auth/posts/id/{id}")
    public PostDTO getPostDTOById(@PathVariable String id) {
        return postService.getPostDTOById(id);
    }



    @GetMapping("/auth/posts/user/{id}")
    public List<Post> getPostDTOByUserId(@PathVariable String id) {
        return postService.getPostsByUserId(id);
    }



    @GetMapping("/auth/posts/category/{id}")
    public List<Post> getPostDTOByCategoryId(@PathVariable String id) {
        return postService.getPostDTOByCategoryId(id);
    }



//    @PostMapping("/auth/posts/addpost")
//    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
//        PostDTO savedPost = postService.savePost(postDTO);
//        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
//    }

    @PostMapping("/auth/posts/addpost")
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }

        // ustaw username w postDto albo w encji Post
        postDTO.setUsername(username);

        // dalej logika zapisu posta
        PostDTO savedPost = postService.savePost(postDTO);

        System.out.println( savedPost.toString());

        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }




    @DeleteMapping("/auth/posts/delete/{id}")
    public String deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return "deleted";
    }
}