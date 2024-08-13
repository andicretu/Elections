package projects.Elections;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

import java.io.IOException;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ElectionsRepository electionsRepository;
    private final CandidateRepository candidateRepository;
    public CustomAuthenticationSuccessHandler(ElectionsRepository electionsRepository, CandidateRepository candidateRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String userEmail = authentication.getName();
        ElectorModel elector = electionsRepository.findByEmail(userEmail);
        CandidateModel candidate = candidateRepository.findByElector_Email(userEmail);
        if (candidate!= null) {
            response.sendRedirect("/elections/show-candidate/" + userEmail);
        } else if (elector != null) {
            response.sendRedirect("/elections/show-elector/" + userEmail);
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
