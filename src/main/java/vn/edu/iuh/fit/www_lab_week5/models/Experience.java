package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "company",length = 120, nullable = false)
    private String company;
    @Column(name = "from_date", nullable = false)
    private Date from_date;
    @Column(name = "to_date", nullable = false)
    private Date to_date;
    @Column(name = "role",length = 100, nullable = false)
    private String role;
    @Column(name = "word_desc",length = 400, nullable = false)
    private String word_desc;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public Experience(String company, Date from_date, Date to_date, String role, String word_desc, Candidate candidate) {
        this.company = company;
        this.from_date = from_date;
        this.to_date = to_date;
        this.role = role;
        this.word_desc = word_desc;
        this.candidate = candidate;
    }
}
