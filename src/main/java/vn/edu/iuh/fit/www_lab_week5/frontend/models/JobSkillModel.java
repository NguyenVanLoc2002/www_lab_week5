package vn.edu.iuh.fit.www_lab_week5.frontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job_skill;
import vn.edu.iuh.fit.www_lab_week5.backend.services.JobSkillService;

import java.util.List;

@Component
public class JobSkillModel {

    @Autowired
    private JobSkillService jobSkillService;

    public void insertJobSkill(Job_skill jobSkill){
        jobSkillService.insertJobSkill(
                jobSkill);
    }

    public List<Job_skill> findAll(){
        return jobSkillService.findAll();
    }



}
