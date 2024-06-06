package projects.Elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@org.springframework.stereotype.Controller
public class ElectionsController {
    private final ElectionsRepository electionsRepository;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public ElectionsController(ElectionsRepository electionsRepository, PasswordEncoder passwordEncoder) {
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
    @GetMapping("elections/showElector")
    public String showElectorProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ElectorModel electorModel = electionsRepository.findByEmail(email);
        model.addAttribute("electorModel", electorModel);
        return "electionsShowElector";
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
    @GetMapping("/elections/showCandidate/{email}")
    public String showCandidateProfile(@PathVariable String email, Model model) {
        ElectorModel candidate = electionsRepository.findByEmail(email);
        if (candidate != null && candidate.getCandidateStatus()) {
            model.addAttribute("candidate", candidate);
            return "electionsShowCandidate";
        } else {
            return "redirect:/welcome";
        }
    }
}
