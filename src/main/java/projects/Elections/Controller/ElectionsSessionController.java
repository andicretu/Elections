package projects.Elections.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projects.Elections.Models.SessionModel;
import projects.Elections.Repositories.SessionsRepository;
import projects.Elections.Repositories.VoteRepository;

@Controller
public class ElectionsSessionController {
    private final VoteRepository voteRepository;
    private final SessionsRepository sessionsRepository;
    public ElectionsSessionController(VoteRepository voteRepository, SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
        this.voteRepository = voteRepository;
    }
    @GetMapping("/startSession")
    public String showStartSession(Model model) {
        model.addAttribute("sessionModel", new SessionModel());
        return "electionsStartSession";
    }
}
