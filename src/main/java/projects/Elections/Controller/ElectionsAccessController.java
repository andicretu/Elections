package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

import java.util.List;
@org.springframework.stereotype.Controller
public class ElectionsAccessController {
    @Autowired
    private ElectionsRepository electionsRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ElectorModel electorModel;

    @Autowired
    public ElectionsAccessController() {
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
    @GetMapping("/registerElector")
    public String showElectorRegistrationForm(Model model) {
        model.addAttribute("electorModel", new ElectorModel());
        return "electionsRegisterElector";
    }
    @GetMapping("/registerCandidate")
    public String showCandidateRegistrationForm(Model model) {
        model.addAttribute("candidateModel", new CandidateModel());
        return "electionsRegisterCandidate";
    }
    @PostMapping("/registerElector")
    public String createElector(@ModelAttribute("electorModel")ElectorModel electorModel, Model model) {
        String hashedPassword = passwordEncoder.encode(electorModel.getPassword());
        electorModel.setPassword(hashedPassword);
        electionsRepository.save(electorModel);
        System.out.println("Elector salvat cu succes");
        model.addAttribute("message", "Elector profile saved successfully!");
        return "electionsShowElector";
    }
    @PostMapping("/registerCandidate")
    public String createCandidate(@ModelAttribute("candidateModel") CandidateModel candidateModel, Model model) {
        if (candidateModel.getResume() == null || candidateModel.getResume().isEmpty()) {
            model.addAttribute("message", "Resume cannot be empty!");
            return "electionsRegisterCandidate";
        }
        if (candidateModel.getElectoralPlatform() == null || candidateModel.getElectoralPlatform().isEmpty()) {
            model.addAttribute("message", "Electoral platform cannot be empty!");
            return "electionsRegisterCandidate";
        }
        ElectorModel electorModel = candidateModel.getElector();
        if (electorModel != null) {
            String hashedPassword = passwordEncoder.encode(electorModel.getPassword());
            electorModel.setPassword(hashedPassword);
            electionsRepository.save(electorModel);
        }
        candidateRepository.save(candidateModel);
        System.out.println("Candidat salvat cu succes");
        model.addAttribute("message", "Candidate profile saved successfully!");
        return "electionsShowCandidate";
    }

}
