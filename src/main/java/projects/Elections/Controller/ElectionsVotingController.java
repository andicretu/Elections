package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;
import projects.Elections.VotingService;

@Controller
public class ElectionsVotingController {
    @Autowired
    private VotingService votingService;
    private final CandidateRepository candidateRepository;
    private final ElectionsRepository electionsRepository;

    @Autowired
    public ElectionsVotingController(ElectionsRepository electionsRepository, CandidateRepository candidateRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
    }
    @PostMapping("/elections/vote")
    public String castVote(@RequestParam Long candidateId, @RequestParam Long electorId) {
        CandidateModel candidate = candidateRepository.findById(candidateId).orElseThrow();
        ElectorModel electorModel = electionsRepository.findById(electorId).orElseThrow();
        votingService.castVote(candidate, electorModel);
        return "redirect:/elections/show-elector/" + electorModel.getEmail();
    }
}
