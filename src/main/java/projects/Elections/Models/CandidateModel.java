package projects.Elections.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class CandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "RESUME",nullable = false)
    private String resume;
    @Column(name = "ELECTORAL PLATFORM",nullable = false)
    private String electoralPlatform;
    @OneToOne
    @JoinColumn(name = "ELECTOR_ID", referencedColumnName = "ID")
    private ElectorModel elector;
    public CandidateModel() {
    }
    public CandidateModel(String resume, String electoralPlatform, ElectorModel elector) {
        this.resume = resume;
        this.electoralPlatform = electoralPlatform;
        this.elector = elector;
    }
    public String getResume() { return resume; }
    public void setResume() {
        this.resume = resume;
    }
    public String getElectoralPlatform() {
        return electoralPlatform;
    }
    public void setElectoralPlatform() {
        this.electoralPlatform = electoralPlatform;
    }
    public ElectorModel getElector() { return elector; }
    public void setElector(ElectorModel elector) { this.elector = elector; }
}