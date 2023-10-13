package vn.edu.iuh.fit.www_lab_week5.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}