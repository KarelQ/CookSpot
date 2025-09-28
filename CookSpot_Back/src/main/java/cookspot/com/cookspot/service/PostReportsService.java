package cookspot.com.cookspot.service;


import cookspot.com.cookspot.embeddedId.ReportedPostId;
import cookspot.com.cookspot.entity.ReportedPost;
import cookspot.com.cookspot.repository.PostReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostReportsService {

    private PostReportsRepository postReportsRepository;


    @Autowired
    public void setReportedPostRepository(PostReportsRepository postReportsRepository) {
        this.postReportsRepository = postReportsRepository;
    }



    public boolean addNewReport(String idUser, String idPost, String report) {
        ReportedPost newReport = new ReportedPost();
        newReport.setId(new ReportedPostId(idPost, idUser));
        newReport.setRepostDate(Instant.now());
        newReport.setRepostContent(report);
        try {
            postReportsRepository.save(newReport);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

