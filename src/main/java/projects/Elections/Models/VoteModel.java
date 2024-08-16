package projects.Elections.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class VoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private CandidateModel candidate;

    @ManyToOne
    @JoinColumn(name = "VOTER_ID")
    private ElectorModel voter;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    @Column(name = "VOTES")
    private int votes;
    public VoteModel() {
    }
    public VoteModel(CandidateModel candidate, ElectorModel voter, LocalDateTime timestamp, int votes) {
        this.candidate = candidate;
        this.voter = voter;
        this.timestamp = timestamp;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CandidateModel getCandidate() {
        return candidate;
    }
    public void setCandidate(CandidateModel candidate) {
        this.candidate = candidate;
    }
    public ElectorModel getVoter() {
        return voter;
    }
    public  void setVoter(ElectorModel voter) {
        this.voter = voter;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
