package vn.edu.iuh.fit.www_lab_week5.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job_skill;

public interface Job_skillRepository extends JpaRepository<Job_skill, Job> {

    @Override
    <S extends Job_skill> S save(S entity);
}