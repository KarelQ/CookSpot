package cookspot.com.cookspot.service;
import cookspot.com.cookspot.dto.ReportedPostDTO;
import cookspot.com.cookspot.entity.Category;
import cookspot.com.cookspot.entity.PostReportsSummary;
import cookspot.com.cookspot.entity.UserInfo;
import cookspot.com.cookspot.repository.*;
import cookspot.com.cookspot.entity.Post;
import cookspot.com.cookspot.dto.PostDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {


    private PostRepository postRepository;
    private UserInfoRepository userRepository;
    private CategoryRepository categoryRepository;

    private PostReportsSummaryRepository postReportsSummaryRepository;
    private PostReportsRepository  postReportsRepository;

    private PostReportsSummaryServices  postReportsSummaryServices;
    private PostReportsService postReportsService;

    @Autowired
    public PostService(PostRepository postRepository, PostReportsService postReportsService, PostReportsSummaryServices postReportsSummaryServices, PostReportsRepository postReportsRepository, PostReportsSummaryRepository postReportsSummaryRepository, CategoryRepository categoryRepository, UserInfoRepository userRepository) {
        this.postRepository = postRepository;
        this.postReportsService = postReportsService;
        this.postReportsSummaryServices = postReportsSummaryServices;
        this.postReportsRepository = postReportsRepository;
        this.postReportsSummaryRepository = postReportsSummaryRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }



    public PostDTO convertPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setIdPost(post.getIdPost());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setIngredients(post.getIngredients());
        postDTO.setRecipe(post.getRecipe());
        postDTO.setImage(post.getImage());
        postDTO.setPrepTime(post.getPrepTime());
        postDTO.setDifficulty(post.getDifficulty());
        postDTO.setNumberOfServings(post.getNumberOfServings());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setLike(post.getLikes());
        postDTO.setDislike(post.getDislike());
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setIdUser(post.getUser().getIdUser());
        postDTO.setStarRating(post.getStarsScore());
        postDTO.setCategoryNames(
                post.getPostCategoriesList()
                        .stream()
                        .map(Category::getCategoryName) // Use map to get the category names
                        .collect(Collectors.toSet()) // Collect the results into a set
        );


        return postDTO;

    }

    public List<PostDTO> getAllPostsDTO(){
        return postRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Post::getStarsScore).reversed())
                .map(this::convertPostToPostDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostDTOById(String id) {
        return convertPostToPostDTO(postRepository.findById(id).orElse(null));
    }


    public PostDTO savePost(PostDTO postDTO) {

        //UserInfo user = userRepository.findById("user1").orElse(null);
        UserInfo user = userRepository.findById(postDTO.getIdUser()).orElse(null);

        LocalDate today = LocalDate.now();

        // Formatowanie do "dd.MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = today.format(formatter);

        List<Category> category =  categoryRepository.findAllById(postDTO.getCategoryNames());

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setIngredients(postDTO.getIngredients());
        post.setRecipe(postDTO.getRecipe());

        post.setPrepTime(postDTO.getPrepTime());
        post.setDifficulty(postDTO.getDifficulty());
        post.setNumberOfServings(postDTO.getNumberOfServings());
        post.setIdPost(postDTO.getIdPost());
        post.setPostCategoriesList(category.stream().collect(Collectors.toSet()));


        post.setLikes(0);
        post.setDislike(0);
        post.setStarsScore(0.F);
        post.setUser(user);

        post.setImage(postDTO.getImage());
        post.setCreatedAt(formattedDate);

        //System.out.println(post.toString());

        Post savedPost = postRepository.save(post);
        return this.convertPostToPostDTO(post);
    }

    public List<Post> getAllPosts() {
        List<Post> test = postRepository.findAll();
        return postRepository.findAll();
    }


    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    public List<Post> getPostsByUserId(String id) {
        UserInfo user = userRepository.findById(id).orElse(null);
        return postRepository.findByUser(user);
    }

    public List<Post> getPostDTOByCategoryId(String id) {
        Set<Category> category = categoryRepository.findById(id).stream().collect(Collectors.toSet());
        return postRepository.findByPostCategoriesList(category);
    }

    public boolean isOwner(String idPost, String idUser) {
        Post post = postRepository.findById(idPost).orElse(null);
        return post != null && post.getUser().getIdUser().equals(idUser);
    }

    public ReportedPostDTO convertPostToReportedPostDTO(Post post, PostReportsSummary postReportsSummary) {
        ReportedPostDTO dto = new ReportedPostDTO();

        dto.setIdPost(post.getIdPost());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setIngredients(post.getIngredients());
        dto.setRecipe(post.getRecipe());
        dto.setImage(post.getImage());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setLike(post.getLikes());
        dto.setDislike(post.getDislike());
        dto.setUsername(post.getUser().getUsername());
        dto.setIdUser(post.getUser().getIdUser());
        dto.setNumberOfReports(postReportsSummary.getNumberOfReports());

        return dto;

    }

    public List<ReportedPostDTO> getAllReportedPostsDTO() {
        List<PostReportsSummary> summaries = postReportsSummaryRepository.findAll();

        return summaries.stream()
                .map(summary -> {
                    Post post = postRepository.findById(summary.getIdPost())
                            .orElse(null);
                    if (post != null) {
                        return convertPostToReportedPostDTO(post, summary);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}