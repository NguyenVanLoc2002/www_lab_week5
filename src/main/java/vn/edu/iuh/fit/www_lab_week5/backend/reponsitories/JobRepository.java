package vn.edu.iuh.fit.www_lab_week5.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Override
    List<Job> findAll();
}