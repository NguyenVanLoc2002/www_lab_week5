package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.www_lab_week5.enums.SkillLevel;
import vn.edu.iuh.fit.www_lab_week5.ids.Candidate_skillID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "candidate_skill")
@IdClass(Candidate_skillID.class)
public class Candidate_skill {
    @Id
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Column(name = "more_infos",length = 1000, nullable = false)
    private String more_info;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level",length = 10, nullable = false)
    private SkillLevel skill_level;
}
