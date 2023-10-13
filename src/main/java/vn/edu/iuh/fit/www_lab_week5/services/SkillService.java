package vn.edu.iuh.fit.www_lab_week5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.www_lab_week5.models.Skill;
import vn.edu.iuh.fit.www_lab_week5.reponsitories.SkillRepository;

import java.util.List;

@Component
public class SkillService {
    @Autowired
    private SkillRepository  skillRepository;

    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

}
