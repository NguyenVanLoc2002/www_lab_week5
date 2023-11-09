package vn.edu.iuh.fit.www_lab_week5.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Skill;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.JobRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CandidateSkillService candidateSkillService;

    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    public List<Job> findJobMatchingCandidate(Candidate candidate) {
        List<Long> idSkills = candidateSkillService.findSkillIdByCandidateId(candidate.getId());

        String jpql = "SELECT DISTINCT j FROM Job j JOIN Job_skill js ON j.id=js.job.id WHERE js.skill.id IN :idSkills";

        TypedQuery<Job> query = entityManager.createQuery(jpql, Job.class);
        query.setParameter("idSkills", idSkills);

        List<Job> jobs = query.getResultList();

        return jobs;
    }



}
