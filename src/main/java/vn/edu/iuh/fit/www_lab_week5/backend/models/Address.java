package vn.edu.iuh.fit.www_lab_week5.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.www_lab_week5.backend.enums.Country;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "add_id")
    private long id;
    @Column(name = "stress" , length = 150,  nullable = false)
    private String stress;
    @Column(name = "city" , length = 50,  nullable = false)
    private String city;
    @Enumerated(EnumType.STRING)
    @Column(name = "country" , length = 20,  nullable = false)
    private Country country;
    @Column(name = "number" , length = 20,  nullable = false)
    private String number;
    @Column(name = "zipcode" , length = 7,  nullable = false)
    private String zipcode;
    @OneToMany(mappedBy = "address")
    private List<Company> companies;
    @OneToMany(mappedBy = "address")
    private List<Candidate> candidates;

    public Address(String stress, String city, Country country, String number, String zipcode) {
        this.stress = stress;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", stress='" + stress + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", number='" + number + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
