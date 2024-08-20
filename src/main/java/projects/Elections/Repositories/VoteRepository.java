package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.VoteModel;

import java.util.List;

public interface VoteRepository extends CrudRepository<VoteModel, Long> {
    List<VoteModel> findCandidateByElectorModelEmail(String email);
    List<VoteModel> findByElectorModelEmail(String email);
}
