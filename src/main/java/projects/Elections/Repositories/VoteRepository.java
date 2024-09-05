package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Models.VoteModel;

import java.util.List;

public interface VoteRepository extends CrudRepository<VoteModel, Long> {
    List<VoteModel> findCandidateByElectorModelEmail(String email);
    List<VoteModel> findByElectorModelEmail(String email);
    List<VoteModel> findByElectorModel(ElectorModel electorModel);
    boolean existsByElectorModel(ElectorModel electorModel);

    int countByCandidateId(Long candidateId);
}
