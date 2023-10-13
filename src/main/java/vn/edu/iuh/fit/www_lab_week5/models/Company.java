package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private long id;
    @Column(name = "about", length = 2000, nullable = false)
    private String about;
    @Column(name = "email", length = 255, nullable = false)
    private String email;
    @Column(name = "comp_name", length = 255, nullable = false)
    private String comp_name;
    @Column(name = "phone", nullable = false)
    private String  phone;
    @Column(name = "web_url", nullable = false)
    private String web_url;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    public Company(String about, String email, String comp_name, String phone, String web_url, Address address) {
        this.about = about;
        this.email = email;
        this.comp_name = comp_name;
        this.phone = phone;
        this.web_url = web_url;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", comp_name='" + comp_name + '\'' +
                ", phone='" + phone + '\'' +
                ", web_url='" + web_url + '\'' +
                ", address=" + address +
                '}';
    }
}
