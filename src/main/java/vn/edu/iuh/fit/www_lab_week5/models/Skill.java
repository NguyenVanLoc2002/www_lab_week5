package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.www_lab_week5.enums.SkillType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;
    @Column(name = "skill_desc", length = 300, nullable = false)
    private String desc;
    @Column(name = "skill_name", length = 150, nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_type", nullable = false)
    private SkillType type;
    @OneToMany(mappedBy = "skill")
    private List<Job_skill> jobSkills;

    public Skill(String desc, String name, SkillType type) {
        this.desc = desc;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
