package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;
import projects.Elections.Repositories.VoteRepository;
import projects.Elections.VotingService;

@Controller
public class ElectionsVotingController {
    @Autowired
    private VotingService votingService;
    private final CandidateRepository candidateRepository;
    private final ElectionsRepository electionsRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public ElectionsVotingController(ElectionsRepository electionsRepository, CandidateRepository candidateRepository, VoteRepository voteRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
    }
    @PostMapping("/elections/vote")
    public String castVote(@RequestParam Long candidateId, @RequestParam Long electorId, Model model) {
        ElectorModel electorModel = electionsRepository.findById(electorId).orElseThrow();
        try {
            CandidateModel candidate = candidateRepository.findById(candidateId).orElseThrow();
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "You have already voted.");
            return "redirect:/elections/show-elector/" + electorModel.getEmail();
        }
        return "redirect:/elections/show-elector/" + electorModel.getEmail();
    }
}
