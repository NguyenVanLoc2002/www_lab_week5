package vn.edu.iuh.fit.www_lab_week5.frontend.controller;

import com.neovisionaries.i18n.CountryCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.www_lab_week5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.www_lab_week5.backend.models.*;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.CandidateRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateServices;

    @Autowired
    private CandidateSkillService candidateSkillService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobSkillService jobSkillService;


    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateServices.findAll());
        return "candidate/candidates";
    }

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateServices.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidate/candidates-paging";
    }

    @GetMapping("/apply")
    public String applyCandidate(Model model) {
        List<Address> addresses = addressService.findAll();
        List<Skill> skills = skillService.findAll();
        SkillLevel[] skillLevels = SkillLevel.values();
        model.addAttribute("skills", skills);
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("addresses", addresses);

        model.addAttribute("candidate", new Candidate());
        model.addAttribute("candidate_Skill", new Candidate_skill());
        return "candidate/add-candidate";
    }

    @PostMapping("/apply/new")
    public String applyJob(@ModelAttribute("candidate_skill") Candidate_skill candidateSkill,
                           HttpServletRequest request, HttpSession session) {
        Candidate newCandidate = new Candidate();
        newCandidate.setFull_name(request.getParameter("name"));
        newCandidate.setDob(LocalDate.parse(request.getParameter("dob")));
        newCandidate.setEmail(request.getParameter("email"));
        newCandidate.setPhone(request.getParameter("phone"));

        Long idAddress = Long.parseLong(request.getParameter("selAddress"));
        Optional<Address> op = addressService.findById(idAddress);
        newCandidate.setAddress(op.get());
        candidateServices.addCan(newCandidate);

        String[] skillArrays = request.getParameterValues("skill");
        String[] skillLevelArrays = request.getParameterValues("skill_level");
        String[] moreInfoArrays = request.getParameterValues("more_info");
        for (int i = 0; i < skillArrays.length; i++) {
            String skillId = skillArrays[i];
            String skillLv = skillLevelArrays[i];
            String moreInfo = moreInfoArrays[i];
            Skill skill = skillService.getById(Long.parseLong(skillId));
            //Chuyển đổi dữ liệu SKillLevel Enum
            SkillLevel skillLevel = SkillLevel.valueOf(skillLv);

            Candidate_skill newCandidate_skill = new Candidate_skill();
            newCandidate_skill.setCandidate(newCandidate);
            newCandidate_skill.setSkill_level(skillLevel);
            newCandidate_skill.setSkill(skill);
            newCandidate_skill.setMore_info(moreInfo);
            candidateSkillService.addCanSkill(newCandidate_skill);
        }
        session.setAttribute("candidate", newCandidate);
        return "redirect:/candidates/success";
    }

    @GetMapping("/matchingJob/{id}")
    public String matchingJob(Model model, @PathVariable("id") Long id) {
        Candidate candidate = candidateServices.findById(id).get();
        List<Job> jobs = jobService.findJobMatchingCandidate(candidate);
        model.addAttribute("jobs", jobs);
        for (Job job : jobs) {
            List<Job_skill> jobSkills = jobSkillService.findJob_skillByJob(job.getId());
            model.addAttribute("jobSkills", jobSkills);
        }
        return "jobs/jobsmatchingcandidate";
    }

    @GetMapping("/findCanBySkill")
    public String findCanBySkill(Model model){
        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills", skills);
        return "/candidate/findcandidatebyskill";
    }

    @GetMapping("/findCanBySkill/candidates")
    public String matchingSkill(Model model,@RequestParam("selSkill") Long skillID){
        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills", skills);
        List<Candidate> matchingCandidates = candidateServices.findCanMatchingSkill(skillID);
        model.addAttribute("candidates",matchingCandidates);
        return "/candidate/findcandidatebyskill";
    }

    @GetMapping("/details/{id}")
    public String detailsCandidate(Model model, @PathVariable("id") Long id){
        List<Candidate_skill> candidateSkills = candidateSkillService.findCanSkillByCanId(id);
        Candidate candidate = candidateServices.findById(id).get();
        List<Skill> skills = skillService.findAll();
        List<Skill> skillList = skillService.findSkillByCanId(id);
        model.addAttribute("skillList",skillList);
        model.addAttribute("candidateSkills",candidateSkills);
        model.addAttribute("candidate",candidate);
        return "candidate/candidatedetails";
    }


}
