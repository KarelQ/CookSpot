package cookspot.com.cookspot.service;


import cookspot.com.cookspot.entity.PostReportsSummary;
import cookspot.com.cookspot.repository.PostReportsSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostReportsSummaryServices {

    private final PostReportsSummaryRepository postReportsSummaryRepository;

    @Autowired
    public PostReportsSummaryServices(PostReportsSummaryRepository postReportsSummaryRepository) {
        this.postReportsSummaryRepository = postReportsSummaryRepository;
    }

    public List<PostReportsSummary> getAllReportedPosts() {
        return postReportsSummaryRepository.findAll();
    }

}
