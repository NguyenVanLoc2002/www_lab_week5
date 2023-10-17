package vn.edu.iuh.fit.www_lab_week5.frontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;
import vn.edu.iuh.fit.www_lab_week5.backend.services.JobService;

import java.util.List;

@Component
public class JobModel {
    @Autowired
    private JobService jobService;

    public List<Job> findAll(){
        return jobService.findAll();
    }

}
