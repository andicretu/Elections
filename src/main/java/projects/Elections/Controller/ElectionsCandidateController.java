package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

@org.springframework.stereotype.Controller
public class ElectionsCandidateController {
    @Autowired
    private ElectionsRepository electionsRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public ElectionsCandidateController() {
    }
    @GetMapping("/register-candidate")
    public String showCandidateRegistrationForm(Model model) {
        model.addAttribute("candidateModel", new CandidateModel());
        return "electionsRegisterCandidate";
    }
    @GetMapping("/elections/show-candidate/{email}")
    public String showCandidateProfile(@PathVariable String email, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        ElectorModel elector = electionsRepository.findByEmail(email);
        CandidateModel candidate = candidateRepository.findByElector_Email(email);
        if (candidate != null) {
            model.addAttribute("candidateModel", candidate);
            model.addAttribute("electorModel", candidate.getElector());
            boolean isOwner = loggedInUserEmail.equals((candidate.getElector().getEmail()));
            model.addAttribute("isOwner", isOwner);
            return "electionsShowCandidate";
        } else {
            return "redirect:/welcome";
        }
    }
    @PostMapping("/register-candidate")
    public String createCandidate(@ModelAttribute("candidateModel") CandidateModel candidateModel, Model model) {
        ElectorModel electorModel = candidateModel.getElector();
        if (candidateModel.getResume() == null || candidateModel.getResume().isEmpty()) {
            model.addAttribute("message", "Resume cannot be empty!");
            return "electionsRegisterCandidate";
        }
        if (candidateModel.getElectoralPlatform() == null || candidateModel.getElectoralPlatform().isEmpty()) {
            model.addAttribute("message", "Electoral platform cannot be empty!");
            return "electionsRegisterCandidate";
        }
        if (electorModel != null) {
            String hashedPassword = passwordEncoder.encode(electorModel.getPassword());
            electorModel.setPassword(hashedPassword);
            electionsRepository.save(electorModel);
        }
        candidateRepository.save(candidateModel);
        System.out.println("Candidat salvat cu succes");
        model.addAttribute("message", "Candidate profile saved successfully!");
        return "redirect:/elections/show-candidate/" + candidateModel.getElector().getEmail();
    }
    @GetMapping("/elections/edit-candidate/{email}")
    public String showCandidateEditForm(@PathVariable String email, Model model) {
        CandidateModel candidateModel = candidateRepository.findByElector_Email(email);
        model.addAttribute("candidateModel", candidateModel);
        return "electionsEditCandidate";
    }
    @PostMapping("/elections/update-candidate")
    public String updateCandidate(@ModelAttribute("candidateModel") CandidateModel candidateModel, Model model) {
        CandidateModel existingCandidate = candidateRepository.findByElector_Email(candidateModel.getElector().getEmail());
        ElectorModel existingElector = electionsRepository.findByEmail(candidateModel.getElector().getEmail());
        existingCandidate.getElector().setName(candidateModel.getElector().getName());
        existingCandidate.getElector().setDescription(candidateModel.getElector().getDescription());
        electionsRepository.save(existingElector);
        existingCandidate.setPartyAffiliation(candidateModel.getPartyAffiliation());
        existingCandidate.setResume(candidateModel.getResume());
        existingCandidate.setElectoralPlatform(candidateModel.getElectoralPlatform());
        candidateRepository.save(existingCandidate);
        return "redirect:/elections/show-candidate/" + existingCandidate.getElector().getEmail();
    }
}
