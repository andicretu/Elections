package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projects.Elections.ElectionsRepository;
import projects.Elections.Models.ElectorModel;

@org.springframework.stereotype.Controller
public class ElectionsProfilesController {
    private final ElectionsRepository electionsRepository;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public ElectionsProfilesController(ElectionsRepository electionsRepository, PasswordEncoder passwordEncoder) {
        this.electionsRepository = electionsRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("elections/show-elector")
    public String showElectorProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ElectorModel electorModel = electionsRepository.findByEmail(email);
        model.addAttribute("electorModel", electorModel);
        return "electionsShowElector";
    }
    @GetMapping("/elections/show-candidate/{email}")
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
