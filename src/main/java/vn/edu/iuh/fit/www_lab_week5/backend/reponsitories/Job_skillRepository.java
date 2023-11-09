package vn.edu.iuh.fit.www_lab_week5.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.www_lab_week5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job_skill;

import java.util.List;
import java.util.Optional;

public interface Job_skillRepository extends JpaRepository<Job_skill, Job> {

     List<Job_skill> findJob_skillByJob_Id(Long id);

}