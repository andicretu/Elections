package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

import java.util.List;
@org.springframework.stereotype.Controller
public class ElectionsApplicationController {
    @Autowired
    private ElectionsRepository electionsRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ElectionsApplicationController() {
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        List<CandidateModel> candidates = candidateRepository.findAll();
        model.addAttribute("candidatesList", candidates);
        return "electionsWelcome";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "electionsLogin";
    }
}
