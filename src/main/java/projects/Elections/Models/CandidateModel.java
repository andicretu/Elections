package projects.Elections.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class CandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PARTY_AFFILIATION", nullable = true)
    private String partyAffiliation;
    @Column(name = "RESUME",nullable = false)
    private String resume;
    @Column(name = "ELECTORAL_PLATFORM",nullable = false)
    private String electoralPlatform;
    @OneToOne
    @JoinColumn(name = "ELECTOR_ID", referencedColumnName = "ID")
    private ElectorModel elector;
    public CandidateModel() {
    }
    public CandidateModel(String partyAffiliation, String resume, String electoralPlatform, ElectorModel elector) {
        this.partyAffiliation = partyAffiliation;
        this.resume = resume;
        this.electoralPlatform = electoralPlatform;
        this.elector = elector != null ? elector : new ElectorModel();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPartyAffiliation() {
        return partyAffiliation;
    }
    public void setPartyAffiliation (String partyAffiliation) {
        this.partyAffiliation = partyAffiliation;
    }
    public String getResume() { return resume; }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public String getElectoralPlatform() {
        return electoralPlatform;
    }
    public void setElectoralPlatform(String electoralPlatform) {
        this.electoralPlatform = electoralPlatform;
    }
    public ElectorModel getElector() { return elector; }
    public void setElector(ElectorModel elector) { this.elector = elector; }
}
