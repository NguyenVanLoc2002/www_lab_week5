package vn.edu.iuh.fit.www_lab_week5.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.models.Candidate_skill;

public interface Candidate_skillRepository extends JpaRepository<Candidate_skill, Candidate> {
}