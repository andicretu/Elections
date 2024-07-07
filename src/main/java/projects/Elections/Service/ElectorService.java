package projects.Elections.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.Elections.ElectionsRepository;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;

@Service
public class ElectorService {
    @Autowired
    private ElectionsRepository electionsRepository;
    public CandidateModel transformToCandidate(ElectorModel electorModel) {
        CandidateModel candidateModel = new CandidateModel();
        candidateModel.setId(electorModel.getId());
        candidateModel.setName(electorModel.getName());
        candidateModel.setEmail(electorModel.getEmail());
        candidateModel.setCandidateStatus(true);
        candidateModel.setResume();
        candidateModel.setElectoralPlatform();
        return electionsRepository.save(candidateModel);
    }
}
