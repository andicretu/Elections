package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import projects.Elections.ElectionsRepository;
import projects.Elections.Models.ElectorModel;

import java.util.List;
@org.springframework.stereotype.Controller
public class ElectionsAccessController {
    private final ElectionsRepository electionsRepository;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public ElectionsAccessController(ElectionsRepository electionsRepository, PasswordEncoder passwordEncoder) {
        this.electionsRepository = electionsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        List<ElectorModel> candidates = electionsRepository.findByCandidateStatusTrue();
        model.addAttribute("candidatesList", candidates);
        return "electionsWelcome";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "electionsLogin";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("electorModel", new ElectorModel());
        return "electionsRegister";
    }
    @PostMapping("/register")
    public String createElector(@ModelAttribute("electorModel")ElectorModel electorModel, Model model) {
        String hashedPassword = passwordEncoder.encode(electorModel.getPassword());
        electorModel.setPassword(hashedPassword);
        electionsRepository.save(electorModel);
        System.out.println("Salvat cu succes");
        model.addAttribute("message", "Profile saved successfully!");
        return "redirect:/login";
    }
}
