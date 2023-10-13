package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private long id;
    @Column(name = "dob", nullable = false)
    private Date dob;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "full_name", nullable = false)
    private String full_name;
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(mappedBy = "candidate")
    private List<Experience> experiences;
    @OneToMany(mappedBy = "candidate")
    private List<Candidate_skill> candidateSkills;
    public Candidate(Date dob, String email, String full_name, String phone, Address address) {
        this.dob = dob;
        this.email = email;
        this.full_name = full_name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
