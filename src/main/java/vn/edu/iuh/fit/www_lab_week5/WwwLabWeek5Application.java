package vn.edu.iuh.fit.www_lab_week5;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Address;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Candidate;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.AddressRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.reponsitories.CandidateRepository;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class WwwLabWeek5Application {

    public static void main(String[] args) {
        SpringApplication.run(WwwLabWeek5Application.class, args);
    }

//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private CandidateRepository candidateRepository;
//
//    //    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random random = new Random();
//            for (int i = 0; i < 1000; i++) {
//                Address address = new Address("Quang Trung", "HCM",
//                        CountryCode.VN, random.nextInt(1, 1000) + "",
//                        random.nextInt(70000, 80000) + "");
//                addressRepository.save(address);
//                Candidate candidate = new Candidate(LocalDate.of(1998, random.nextInt(1, 13), random.nextInt(1, 29)),
//                        "email_" + i + "@gmail.com", "Name #" + i, random.nextLong(1111111111L, 9999999999L) + "", address);
//                candidateRepository.save(candidate);
//                System.out.println("Added: " + candidate);
//            }
//        };
//    }


}
