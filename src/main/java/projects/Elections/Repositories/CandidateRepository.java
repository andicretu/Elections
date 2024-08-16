package projects.Elections.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.Elections.Models.CandidateModel;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
    @Override
    Optional<CandidateModel> findById(Long id);
    CandidateModel findCandidateByElectorId(Long id);
    CandidateModel findByElector_Email(String email);
}


