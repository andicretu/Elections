package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.ElectorModel;

public interface ElectionsRepository extends CrudRepository<ElectorModel, String> {
    ElectorModel findByEmail(String email);
    void deleteAll();
}
