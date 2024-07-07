package projects.Elections.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "candidates")
public class CandidateModel extends ElectorModel implements UserDetails {
    @Column(name = "RESUME",nullable = false)
    private String resume;
    @Column(name = "ELECTORAL PLATFORM",nullable = false)
    private String electoralPlatform;
    public CandidateModel() {
    }
    public CandidateModel(String resume, String electoralPlatform) {
        this.resume = resume;
        this.electoralPlatform = electoralPlatform;
    }
    public String getResume() {
        return resume;
    }
    public void setResume() {
        this.resume = resume;
    }
    public String getElectoralPlatform() {
        return electoralPlatform;
    }
    public void setElectoralPlatform() {
        this.electoralPlatform = electoralPlatform;
    }
}