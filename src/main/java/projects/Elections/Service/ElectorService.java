package projects.Elections.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;

@Service
public class ElectorService {
    @Autowired
    private CandidateRepository candidateRepository;
    public void transformToCandidate(ElectorModel electorModel) {
        CandidateModel candidateModel = new CandidateModel();
        candidateModel.setElector((electorModel));
        candidateModel.setResume();
        candidateModel.setElectoralPlatform();
        candidateRepository.save(candidateModel);
    }
}
