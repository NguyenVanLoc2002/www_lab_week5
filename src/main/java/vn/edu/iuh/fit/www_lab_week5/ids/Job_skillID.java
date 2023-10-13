package vn.edu.iuh.fit.www_lab_week5.ids;

import jakarta.servlet.annotation.HandlesTypes;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Job_skillID implements Serializable {
    private Long job;
    private Long skill;

}
