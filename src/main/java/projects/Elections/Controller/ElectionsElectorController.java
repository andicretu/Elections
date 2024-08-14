package projects.Elections.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@org.springframework.stereotype.Controller
public class ElectionsElectorController {
    @Autowired
    private final ElectionsRepository electionsRepository;
    @Autowired
    private final CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ElectionsElectorController(ElectionsRepository electionsRepository, CandidateRepository candidateRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
    }
    @GetMapping("/registerElector")
    public String showElectorRegistrationForm(Model model) {
        model.addAttribute("electorModel", new ElectorModel());
        return "electionsRegisterElector";
    }
    @GetMapping("elections/show-elector/{email}")
    public String showElectorProfile(@PathVariable String email, Model model) {
        ElectorModel electorModel = electionsRepository.findByEmail(email);
        List<CandidateModel> candidatesList = candidateRepository.findAll();
        model.addAttribute("electorModel", electorModel);
        model.addAttribute("candidatesList", candidatesList);
        return "electionsShowElector";
    }
    @PostMapping("/registerElector")
    public String createElector(@ModelAttribute("electorModel") ElectorModel electorModel, Model model) {
        String hashedPassword = passwordEncoder.encode(electorModel.getPassword());
        electorModel.setPassword(hashedPassword);
        electionsRepository.save(electorModel);
        return "redirect:/elections/show-elector/" + electorModel.getEmail();
    }
    @GetMapping("/elections/edit-elector/{email}")
    public String showElectorEditForm(@PathVariable String email, Model model) {
        ElectorModel electorModel = electionsRepository.findByEmail(email);
        model.addAttribute("electorModel", electorModel);
        return "electionsEditElector";
    }
    @PostMapping("/elections/update-elector")
    public String updateElector(@ModelAttribute("electorModel") ElectorModel electorModel, Model model) {
        ElectorModel existingElector = electionsRepository.findByEmail(electorModel.getEmail());
        existingElector.setName(electorModel.getName());
        existingElector.setDescription(electorModel.getDescription());
        electionsRepository.save(existingElector);
        return "redirect:/elections/show-elector/" + existingElector.getEmail();
    }
}
