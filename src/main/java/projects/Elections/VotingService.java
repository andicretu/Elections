package projects.Elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Models.SessionModel;
import projects.Elections.Models.VoteModel;
import projects.Elections.Repositories.SessionsRepository;
import projects.Elections.Repositories.VoteRepository;

import java.time.LocalDateTime;

@Service
public class VotingService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    SessionsRepository sessionsRepository;
    public void castVote(CandidateModel candidate, ElectorModel electorModel, SessionModel sessionModel) {
        boolean hasVoted = voteRepository.existsByElectorModel(electorModel);
        if (hasVoted) {
            throw new IllegalStateException("This elector has already voted.");
        }
        VoteModel vote = new VoteModel();
        vote.setCandidate(candidate);
        vote.setElectorModel(electorModel);
        vote.setSessionModel(sessionModel);
        vote.setTimestamp(LocalDateTime.now());
        voteRepository.save(vote);
    }
    public int getTotalVotesForCandidate(CandidateModel candidate, String email) {
        return voteRepository.findCandidateByElectorModelEmail(email).size();
    }
}
