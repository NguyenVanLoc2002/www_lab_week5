package vn.edu.iuh.fit.www_lab_week5.frontend.controller;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Address;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.CandidateRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.services.AddressService;
import vn.edu.iuh.fit.www_lab_week5.backend.services.CandidateService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateServices;

    @Autowired
    private AddressService addressService;


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
    public String applyCandidate(Model model){
        List<Address> addresses = addressService.findAll();
        model.addAttribute("addresses",addresses);
        model.addAttribute("candidate", new Candidate());
        return "candidate/add-candidate";
    }
}
