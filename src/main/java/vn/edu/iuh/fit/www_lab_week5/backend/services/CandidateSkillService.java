package vn.edu.iuh.fit.www_lab_week5.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate_skill;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.Candidate_skillRepository;

import java.util.List;

@Service
public class CandidateSkillService {
    @Autowired
    private Candidate_skillRepository candidateSkillRepository;


    public void addCanSkill(Candidate_skill candidateSkill){
        candidateSkillRepository.save(candidateSkill);
    }

    public List<Long> findSkillIdByCandidateId(Long canId){
       return candidateSkillRepository.findSkillIdByCandidateId(canId);
    }

    public List<Candidate_skill> findCanSkillByCanId(Long id){
        return  candidateSkillRepository.findCandidate_skillByCandidateId(id);
    }
}
