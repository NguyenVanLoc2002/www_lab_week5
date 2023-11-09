package vn.edu.iuh.fit.www_lab_week5.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate_skill;

import java.util.List;

public interface Candidate_skillRepository extends JpaRepository<Candidate_skill, Candidate> {
    @Query("SELECT cs.skill.id FROM Candidate_skill cs WHERE cs.candidate.id = :candidateId")
    List<Long> findSkillIdByCandidateId(@Param("candidateId") Long canId);

    List<Candidate_skill> findCandidate_skillByCandidateId(Long id);
}