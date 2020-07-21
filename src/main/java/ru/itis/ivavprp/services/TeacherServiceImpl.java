package ru.itis.ivavprp.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.TeacherDto;
import ru.itis.ivavprp.dto.TeacherInfoDto;
import ru.itis.ivavprp.dto.TeacherStatusDto;
import ru.itis.ivavprp.models.Role;
import ru.itis.ivavprp.models.Skill;
import ru.itis.ivavprp.models.Teacher;
import ru.itis.ivavprp.repositories.SkillsRepository;
import ru.itis.ivavprp.repositories.TeacherRepository;
import ru.itis.ivavprp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends UserService implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SkillsRepository skillsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              SkillsRepository skillsRepository,
                              PasswordEncoder passwordEncoder,
                              UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.skillsRepository = skillsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean save(TeacherDto teacherDto) {
        if (userRepository.findByEmail(teacherDto.getEmail()).isPresent()) {
            return false;
        }
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherDto.getEmail());
        teacher.setIsActive(true);
        teacher.setRoles(Collections.singleton(Role.TEACHER));
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacherRepository.save(teacher);
        return true;
    }

    public TeacherInfoDto findTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(IllegalStateException::new);
        TeacherInfoDto dto = TeacherInfoDto
                .builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .photo(teacher.getPhoto())
                .build();
        return dto;
    }


    @Override
    public TeacherInfoDto update(Long id, TeacherInfoDto info) {
        Teacher teacher = teacherRepository.getOne(id);
        if (info.getFirstName() != null) {
            teacher.setFirstName(info.getFirstName());
        }
        if (info.getLastName() != null) {
            teacher.setLastName(info.getLastName());
        }
        Teacher savedTeacher = teacherRepository.save(teacher);
        TeacherInfoDto dto = TeacherInfoDto
                .builder()
                .firstName(savedTeacher.getFirstName())
                .lastName(savedTeacher.getLastName())
                .photo(savedTeacher.getPhoto())
                .build();
        return dto;
    }

    @Transactional
    @Override
    public List<SkillDto> confirmSkills(TeacherDto teacherDto,
                                        Long studentId,
                                        List<Long> skillIds) {
        List<SkillDto> confirmedSkills = new ArrayList<>();
        for (Long id : skillIds) {
            Optional<Skill> optionalSkill = skillsRepository.findById(id);
            if (optionalSkill.isPresent()) {
                Skill skill = optionalSkill.get();
                teacherRepository.confirmSkill(studentId, skill.getId());
                confirmedSkills.add(Skill.toSkillDto(skill));
            }
        }
        return confirmedSkills;
    }
    @Override
    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream().map(Teacher::toTeacherDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void updateStatus(TeacherStatusDto teacherStatusDto) {
        Teacher teacher = teacherRepository.getOne(teacherStatusDto.getId());
        teacher.setIsActive(teacherStatusDto.isActive());
        teacherRepository.save(teacher);
    }




}
