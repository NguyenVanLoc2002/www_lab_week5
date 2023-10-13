package vn.edu.iuh.fit.www_lab_week5.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.www_lab_week5.enums.SkillLevel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job_skill")
public class Job_skill {
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Column(name = "more_infos",length = 1000, nullable = false)
    private String more_info;
    @Column(name = "skill_level",length = 10, nullable = false)
    private SkillLevel skill_level;

}
