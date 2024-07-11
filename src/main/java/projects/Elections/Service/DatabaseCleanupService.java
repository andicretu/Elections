package projects.Elections.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;
@Service
public class DatabaseCleanupService {
    @Autowired
    private ElectionsRepository electionsRepository;
    private CandidateRepository candidateRepository;
    public void deleteAllRecords() {
        candidateRepository.deleteAll();
        electionsRepository.deleteAll();
    }
}
