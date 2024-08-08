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
import projects.Elections.Service.ElectorService;

import java.util.List;
@org.springframework.stereotype.Controller
public class ElectionsAccessController {
    @Autowired
    private ElectionsRepository electionsRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ElectorService electorService;

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
    public String showRegistrationForm(Model model) {
        model.addAttribute("electorModel", new ElectorModel());
        return "electionsRegisterElector";
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
}
