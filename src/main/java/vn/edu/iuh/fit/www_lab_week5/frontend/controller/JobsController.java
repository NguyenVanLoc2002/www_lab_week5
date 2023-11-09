package vn.edu.iuh.fit.www_lab_week5.frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.iuh.fit.www_lab_week5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Job_skill;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Skill;
import vn.edu.iuh.fit.www_lab_week5.frontend.models.JobModel;
import vn.edu.iuh.fit.www_lab_week5.frontend.models.JobSkillModel;
import vn.edu.iuh.fit.www_lab_week5.frontend.models.SkillModel;


import java.util.List;

@Controller
public class JobsController {
    @Autowired
    private JobModel jobModel;
    @Autowired
    private SkillModel skillModel;
    @Autowired
    private JobSkillModel jobSkillModel;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JobsController.class.getName());

    @GetMapping("/cj")
    public String showForm(Model model) {
        List<Job> jobs = jobModel.findAll();
        List<Skill> skills = skillModel.findAll();
        SkillLevel[] skillLevels = SkillLevel.values();
        model.addAttribute("jobs", jobs);
        model.addAttribute("skills", skills);
        model.addAttribute("skillLevels", skillLevels);

        model.addAttribute("job_skill", new Job_skill());
        return "jobs/createjobposting";
    }


    @PostMapping("/createjobposting/new")
    public String createJobPosting(@ModelAttribute("job_skill") Job_skill jobSkill, HttpServletRequest request) {
//        String[] skillLevelArrays1 = request.getParameterValues("skill_level");
//        for (String skillLevel : skillLevelArrays1) {
//            logger.info("Skill Level: " + skillLevel);
//        }
        String[] skillArrays = request.getParameterValues("skill");
        String[] skillLevelArrays = request.getParameterValues("skill_level");
        String[] moreInfoArrays = request.getParameterValues("more_info");
        for (int i = 0; i < skillArrays.length; i++) {
            String skillId = skillArrays[i];
            String skillLv = skillLevelArrays[i];
            String moreInfo = moreInfoArrays[i];
            Skill skill = skillModel.getById(Long.parseLong(skillId));
            //Chuyển đổi dữ liệu SKillLevel Enum
            SkillLevel skillLevel = SkillLevel.valueOf(skillLv);


            Job_skill newJobSkill = new Job_skill();
            newJobSkill.setSkill(skill);
            newJobSkill.setJob(jobSkill.getJob());
            newJobSkill.setSkill_level(skillLevel);
            newJobSkill.setMore_info(moreInfo);
            jobSkillModel.insertJobSkill(newJobSkill);

        }
        return "redirect:/viewjobpostings";
    }

    @GetMapping("/view")
    public String showViewJob(Model model) {
        List<Job_skill> jobSkills = jobSkillModel.findAll();
        model.addAttribute("jobSkills", jobSkills);
        List<Job> jobs = jobModel.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs/viewjobpostings";
    }

    @GetMapping("/candidates/success")
    public String jobMatchingCandidate(Model model, HttpSession session){
        Candidate candidate =(Candidate) session.getAttribute("candidate");
        List<Job> jobs = jobModel.findJobMatchingCandidate(candidate);
        model.addAttribute("jobs", jobs);
        for(Job job :jobs){
            List<Job_skill> jobSkills = jobSkillModel.findJob_skillByJob(job.getId());
            model.addAttribute("jobSkills", jobSkills);
        }
        return "jobs/jobsmatchingcandidate";
    }
}
