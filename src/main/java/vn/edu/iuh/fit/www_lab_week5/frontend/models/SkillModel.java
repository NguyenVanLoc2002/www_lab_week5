package vn.edu.iuh.fit.www_lab_week5.frontend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Skill;
import vn.edu.iuh.fit.www_lab_week5.backend.services.SkillService;

import java.util.List;

@Component
public class SkillModel {
    @Autowired
    private SkillService skillService;

    public List<Skill> findAll(){
        return skillService.findAll();
    }

    public Skill getById(Long id){
        return skillService.getById(id);
    }
}
