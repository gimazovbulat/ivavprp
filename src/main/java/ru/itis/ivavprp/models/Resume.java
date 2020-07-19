package ru.itis.ivavprp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ivavprp.dto.EmploymentType;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.WorkSchedule;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "ivavprp", name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "ivavprp", name = "skills_resumes",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType employmentType;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_schedule")
    private WorkSchedule workSchedule;
    @Transient
    private String workScheduleToShow;
    @Transient
    private String emplTypeToShow;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public static Resume fromResumeDto(ResumeDto resumeDto) {
        return Resume.builder()
                .workSchedule(resumeDto.getWorkSchedule())
                .employmentType(resumeDto.getEmploymentType())
                .emplTypeToShow(resumeDto.getEmplTypeToShow())
                .name(resumeDto.getName())
                .id(resumeDto.getId())
                .skills(resumeDto.getSkills())
                .text(resumeDto.getText())
                .workScheduleToShow(resumeDto.getWorkScheduleToShow())
                .build();
    }

    public static ResumeDto toResumeDto(Resume resume) {
        return ResumeDto.builder()
                .workSchedule(resume.getWorkSchedule())
                .employmentType(resume.getEmploymentType())
                .emplTypeToShow(resume.getEmplTypeToShow())
                .name(resume.getName())
                .id(resume.getId())
                .skills(resume.getSkills())
                .text(resume.getText())
                .workScheduleToShow(resume.getWorkScheduleToShow())
                .build();
    }

    @PostLoad
    private void fillValuesToShow() {
        emplTypeToShow = employmentType.getValueToShow();
        workScheduleToShow = workSchedule.getValueToShow();
    }
}
