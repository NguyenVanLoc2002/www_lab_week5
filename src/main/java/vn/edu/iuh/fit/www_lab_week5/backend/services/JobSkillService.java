package vn.edu.iuh.fit.www_lab_week5.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job_skill;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.Job_skillRepository;

@Service
public class JobSkillService {
    @Autowired
    private Job_skillRepository jobSkillRepository;

    public void insertJobSkill(Job_skill jobSkill){
        jobSkillRepository.save(jobSkill);
    }
}
