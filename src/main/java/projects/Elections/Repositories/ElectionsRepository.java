package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.ElectorModel;

import java.util.Optional;

public interface ElectionsRepository extends CrudRepository<ElectorModel, String> {
    ElectorModel findByEmail(String email);
    @Override
    Optional<ElectorModel> findById(String id);
}
