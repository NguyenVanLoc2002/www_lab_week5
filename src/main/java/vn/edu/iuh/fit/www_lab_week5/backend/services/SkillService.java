package vn.edu.iuh.fit.www_lab_week5.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Skill;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.SkillRepository;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository  skillRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    public Skill getById(Long id){
        return skillRepository.getById(id);
    }

    public List<Skill> findSkillByCanId(Long id){
        String jpql="SELECT s FROM Skill s LEFT JOIN Candidate_skill  cs ON s.id = cs.skill.id AND cs.candidate.id =:id " +
                "where cs.skill.id IS NULL";
        TypedQuery<Skill> query = entityManager.createQuery(jpql, Skill.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

}
