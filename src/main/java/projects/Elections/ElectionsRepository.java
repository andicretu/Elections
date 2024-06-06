package projects.Elections;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElectionsRepository extends CrudRepository<ElectorModel, String> {
    ElectorModel findByEmail(String email);
    List<ElectorModel> findByCandidateStatusTrue();
}
