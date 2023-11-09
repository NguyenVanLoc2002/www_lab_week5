package vn.edu.iuh.fit.www_lab_week5.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.CandidateRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    public Page<Candidate> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Candidate> list;
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.size() < startItem) {
            list = Collections.emptyList();
        } else {

            int toIndex = Math.min(startItem + pageSize, candidates.size());
            list = candidates.subList(startItem, toIndex);
        }
        Page<Candidate> candidatesPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), candidates.size());
        return candidatesPage;
    }

    public List<Candidate> findAll(){
        return candidateRepository.findAll();
    }

    public void addCan(Candidate candidate){
        candidateRepository.save(candidate);
    }

    public Optional<Candidate> findById(Long id){
        return candidateRepository.findById(id);
    }

    public List<Candidate> findCanMatchingSkill(Long skillID){
        String jpql = "SELECT DISTINCT c " +
                "FROM Candidate c JOIN Candidate_skill cs " +
                "ON c.id = cs.candidate.id " +
                "WHERE cs.skill.id = :skillID ";
        TypedQuery<Candidate> query = entityManager.createQuery(jpql, Candidate.class);
        query.setParameter("skillID",skillID);
        List<Candidate> candidates = query.getResultList();
        return candidates;
    }



}
