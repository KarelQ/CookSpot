package cookspot.com.cookspot.repository;

import cookspot.com.cookspot.embeddedId.ReportedPostId;
import cookspot.com.cookspot.entity.Comment;
import cookspot.com.cookspot.entity.ReportedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostReportsRepository extends JpaRepository<ReportedPost, ReportedPostId> {
    Optional<List<ReportedPost>> findById_IdPost(String idPost);
}


