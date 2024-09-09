package projects.Elections.Repositories;

import org.springframework.data.repository.CrudRepository;
import projects.Elections.Models.SessionModel;

import java.util.Optional;

public interface SessionsRepository extends CrudRepository<SessionModel, Long> {
    @Override
    Optional<SessionModel> findById(Long id);
}
