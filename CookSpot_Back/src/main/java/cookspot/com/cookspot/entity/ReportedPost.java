package cookspot.com.cookspot.entity;

import cookspot.com.cookspot.embeddedId.ReportedPostId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "reported_posts")
public class ReportedPost {
    @EmbeddedId
    private ReportedPostId id;

    @Column(name = "report_content", length = Integer.MAX_VALUE)
    private String repostContent;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "report_date", nullable = false)
    private Instant repostDate;

    public ReportedPostId getId() {
        return id;
    }

    public void setId(ReportedPostId id) {
        this.id = id;
    }

    public String getRepostContent() {
        return repostContent;
    }

    public void setRepostContent(String repostContent) {
        this.repostContent = repostContent;
    }

    public Instant getRepostDate() {
        return repostDate;
    }

    public void setRepostDate(Instant repostDate) {
        this.repostDate = repostDate;
    }

}