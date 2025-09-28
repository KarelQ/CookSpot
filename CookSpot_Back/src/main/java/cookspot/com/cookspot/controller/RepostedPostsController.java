package cookspot.com.cookspot.controller;



import cookspot.com.cookspot.dto.ReportedPostDTO;
import cookspot.com.cookspot.entity.ReportedPost;
import cookspot.com.cookspot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RepostedPostsController {

    private final PostService postService;
    private final JwtService jwtService;
    private final PostReportsService postReportsService;
    private final PostReportsSummaryServices postReportsSummaryServices;


    @Autowired
    public RepostedPostsController(PostService postService, JwtService jwtService, PostReportsService postReportsService ,  PostReportsSummaryServices postReportsSummaryServices) {
        this.postService = postService;
        this.jwtService = jwtService;
        this.postReportsService = postReportsService;
        this.postReportsSummaryServices = postReportsSummaryServices;
    }

    @GetMapping("/admin/reported/posts")
    public ResponseEntity<List<ReportedPostDTO>> getAllReportedPostsDTO() {
        List<ReportedPostDTO> posts = postService.getAllReportedPostsDTO();
        if (posts == null || posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }




}
