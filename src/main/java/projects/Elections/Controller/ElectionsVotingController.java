package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Models.SessionModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;
import projects.Elections.Repositories.SessionsRepository;
import projects.Elections.Repositories.VoteRepository;
import projects.Elections.VotingService;

@Controller
public class ElectionsVotingController {
    @Autowired
    private VotingService votingService;
    private final CandidateRepository candidateRepository;
    private final ElectionsRepository electionsRepository;
    private final VoteRepository voteRepository;
    private final SessionsRepository sessionRepository;

    @Autowired
    public ElectionsVotingController(ElectionsRepository electionsRepository, CandidateRepository candidateRepository, VoteRepository voteRepository, SessionsRepository sessionsRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.sessionRepository = sessionsRepository;
    }
    @PostMapping("/elections/vote")
    public String castVote(@RequestParam Long candidateId, @RequestParam Long electorId, @RequestParam Long sessionId, Model model) {
        ElectorModel electorModel = electionsRepository.findById(electorId).orElseThrow();
        SessionModel sessionModel = sessionRepository.findById(sessionId).orElseThrow();
        try {
            CandidateModel candidate = candidateRepository.findById(candidateId).orElseThrow();
            votingService.castVote(candidate, electorModel, sessionModel);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "You have already voted.");
            return "redirect:/elections/show-elector/" + electorModel.getEmail();
        }
        return "redirect:/elections/show-elector/" + electorModel.getEmail();
    }
}
