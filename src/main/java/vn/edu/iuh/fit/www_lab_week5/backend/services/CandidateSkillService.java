package vn.edu.iuh.fit.www_lab_week5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate_skill;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.Candidate_skillRepository;

@Service
public class CandidateSkillService {
    @Autowired
    private Candidate_skillRepository candidateSkillRepository;

    public void addCanSkill(Candidate_skill candidateSkill){
        candidateSkillRepository.save(candidateSkill);
    }

}
