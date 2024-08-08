package projects.Elections.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

@Service
public class ElectorService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ElectionsRepository electionsRepository;

    public ElectorService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void transformToCandidate(ElectorModel electorModel) {
        CandidateModel candidateModel = new CandidateModel();
        candidateModel.setElector((electorModel));
        candidateModel.setResume();
        candidateModel.setElectoralPlatform();
        electionsRepository.save(electorModel);
        candidateRepository.save(candidateModel);
    }
}
