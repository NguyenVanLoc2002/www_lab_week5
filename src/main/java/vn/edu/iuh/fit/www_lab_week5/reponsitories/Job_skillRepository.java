package vn.edu.iuh.fit.www_lab_week5.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.models.Job;
import vn.edu.iuh.fit.www_lab_week5.models.Job_skill;

public interface Job_skillRepository extends JpaRepository<Job_skill, Job> {
}