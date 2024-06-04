package projects.Elections;

import org.springframework.data.repository.CrudRepository;

public interface ElectionsRepository extends CrudRepository<ElectorModel, String> {
    ElectorModel findByEmail(String email);
}
