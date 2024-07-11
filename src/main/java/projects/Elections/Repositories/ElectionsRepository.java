package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;

import java.util.List;

public interface ElectionsRepository extends CrudRepository<ElectorModel, String> {
    ElectorModel findByEmail(String email);
    List<ElectorModel> findByCandidateStatusTrue();
    void deleteAll();
}
