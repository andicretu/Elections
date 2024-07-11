package projects.Elections.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.Elections.Models.CandidateModel;

public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
    CandidateModel findCandidateByEmail(String email);
}


