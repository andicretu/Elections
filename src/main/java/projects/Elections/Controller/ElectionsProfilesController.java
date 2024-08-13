package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

import java.util.List;

@org.springframework.stereotype.Controller
public class ElectionsProfilesController {
    private final ElectionsRepository electionsRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public ElectionsProfilesController(ElectionsRepository electionsRepository, CandidateRepository candidateRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
    }
    @GetMapping("elections/show-elector/{email}")
    public String showElectorProfile(@PathVariable String email, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ElectorModel electorModel = electionsRepository.findByEmail(email);
        List<CandidateModel> candidatesList = candidateRepository.findAll();
        model.addAttribute("electorModel", electorModel);
        model.addAttribute("candidatesList", candidatesList);
        return "electionsShowElector";
    }
    @GetMapping("/elections/show-candidate/{email}")
    public String showCandidateProfile(@PathVariable String email, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ElectorModel elector = electionsRepository.findByEmail(email);
        CandidateModel candidate = candidateRepository.findByElector_Email(email);
        if (candidate != null) {
            model.addAttribute("candidateModel", candidate);
            model.addAttribute("electorModel", candidate.getElector());
            return "electionsShowCandidate";
        } else {
            return "redirect:/welcome";
        }
    }
}
