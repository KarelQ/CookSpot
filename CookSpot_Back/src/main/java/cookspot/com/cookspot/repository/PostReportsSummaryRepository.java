package cookspot.com.cookspot.repository;


import cookspot.com.cookspot.entity.PostReportsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface PostReportsSummaryRepository extends JpaRepository<PostReportsSummary, String> {
}