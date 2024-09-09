package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.VoteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class ElectionsResultsController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/showResults")
    public String showResults(Model model) {
        List<CandidateModel> candidatesList = candidateRepository.findAll();
        Map<Long, Integer> votesMap = new HashMap<>();
        for (CandidateModel candidate : candidatesList) {
            int votesCount = voteRepository.countByCandidateId(candidate.getId());
            votesMap.put(candidate.getId(), votesCount);
        }
        model.addAttribute("candidatesList", candidatesList);
        model.addAttribute("votesMap", votesMap);
        return "electionsShowResults";
    }
}
