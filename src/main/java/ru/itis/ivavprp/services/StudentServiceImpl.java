package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.repositories.ResumesRepository;
import ru.itis.ivavprp.repositories.SkillsRepository;
import ru.itis.ivavprp.repositories.StudentRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends UserService implements StudentService {
    private final StudentRepository studentRepository;
    private final SkillsRepository skillsRepository;
    private final UserRepository userRepository;
    private final ResumesRepository resumesRepository;


    public StudentServiceImpl(StudentRepository studentRepository,
                              SkillsRepository skillsRepository,
                              UserRepository userRepository,
                              ResumesRepository resumesRepository,
                              PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.skillsRepository = skillsRepository;
        this.userRepository = userRepository;
        this.resumesRepository = resumesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public boolean save(StudentDto studentDto) {
        if (userRepository.findByEmail(studentDto.getEmail()).isPresent()) {
            return false;
        }
        Student student = new Student();
        student.setEmail(studentDto.getEmail());
        student.setRating(0);
        student.setIsActive(true);
        student.setRoles(Collections.singleton(Role.STUDENT));
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findAll(Specification<Student> spec, int page, int size) {
        return studentRepository.findAll(spec, PageRequest.of(page, size)).stream()
                .map(Student::toStudentDto)
                .collect(Collectors.toList());
    }



    public StudentDto findStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Skill> allSkills = student.getAllSkills();
            for (Skill skill : allSkills) {
                boolean confirmed = skillsRepository.isConfirmed(id, skill.getId());
                skill.setConfirmed(confirmed);
            }
            return Student.toStudentDto(student);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<SkillDto> addSkills(StudentDto studentDto, List<Long> skillIds) {
        List<SkillDto> allSkills = addOrRemoveSkills(studentDto, skillIds, 1);
        return allSkills;
    }

    @Override
    public List<SkillDto> removeSkills(StudentDto studentDto, List<Long> skillIds) {
        List<SkillDto> allSkills = addOrRemoveSkills(studentDto, skillIds, 0);
        return allSkills;
    }

    //action = 1 if add, 0 otherwise
    private List<SkillDto> addOrRemoveSkills(StudentDto studentDto, List<Long> skillIds, int action) {
        Optional<Student> optionalStudent = studentRepository.findById(studentDto.getId());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Skill> oldSkills = student.getAllSkills();
            for (Long id : skillIds) {
                Optional<Skill> optionalSkill = skillsRepository.findById(id);
                if (optionalSkill.isPresent()) {
                    Skill skill = optionalSkill.get();
                    if (action == 1) {
                        if (!oldSkills.contains(skill)) {
                            oldSkills.add(skill);
                        }
                    } else {
                        oldSkills.remove(skill);
                    }
                }
            }
            student.setAllSkills(oldSkills);
            studentRepository.save(student);
            return oldSkills.stream().map(Skill::toSkillDto).collect(Collectors.toList());
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<SkillDto> addSkillsToResume(Long studentId, Long resumeId, List<Long> skillIds) {
        return addOrRemoveResumeSkills(studentId, resumeId, skillIds, 1);
    }

    @Override
    public List<SkillDto> removeSkillsToResume(Long studentId, Long resumeId, List<Long> skillIds) {
        return addOrRemoveResumeSkills(studentId, resumeId, skillIds, 0);
    }

    private List<SkillDto> addOrRemoveResumeSkills(Long studentId, Long resumeId, List<Long> skillIds, int action) {
        Resume resume = resumesRepository.getOne(resumeId);
        List<Skill> skillsInResume = resume.getSkills();
        for (Long id : skillIds) {
            boolean confirmed = skillsRepository.isConfirmed(studentId, id);
            if (confirmed) {
                Skill skill = skillsRepository.getOne(id);
                if (action == 1) {
                    skillsInResume.add(skill);
                } else {
                    skillsInResume.remove(skill);
                }
            }
        }
        resumesRepository.save(resume);
        return skillsInResume.stream().map(Skill::toSkillDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto update(Long id, StudentDto info) {
        Student student = studentRepository.getOne(id);
        if (info.getFirstName() != null) {
            student.setFirstName(info.getFirstName());
        }
        if (info.getLastName() != null) {
            student.setLastName(info.getLastName());
        }
        Student savedStudent = studentRepository.save(student);
        StudentDto dto = Student.toStudentDto(savedStudent);
        return dto;
    }
}
