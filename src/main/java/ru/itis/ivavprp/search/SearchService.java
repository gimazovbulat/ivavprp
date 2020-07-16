package ru.itis.ivavprp.search;

import com.google.common.base.Joiner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.ResumeDto;
import ru.itis.ivavprp.dto.SkillDto;
import ru.itis.ivavprp.dto.StudentDto;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Resume;
import ru.itis.ivavprp.models.Student;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.search.resumes.ResumeSpecificationBuilder;
import ru.itis.ivavprp.search.users.StudentSpecificationBuilder;
import ru.itis.ivavprp.search.vacancies.VacancySpecificationBuilder;
import ru.itis.ivavprp.services.ResumeService;
import ru.itis.ivavprp.services.SkillsService;
import ru.itis.ivavprp.services.StudentService;
import ru.itis.ivavprp.services.VacanciesService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {
    private final VacanciesService vacanciesService;
    private final SkillsService skillsService;
    private final ResumeService resumeService;
    private final StudentService studentService;

    public SearchService(VacanciesService vacanciesService, SkillsService skillsService,
                         ResumeService resumeService, StudentService studentService) {
        this.vacanciesService = vacanciesService;
        this.skillsService = skillsService;
        this.resumeService = resumeService;
        this.studentService = studentService;
    }

    public List<?> getVacanciesResults(String search,
                                       int page,
                                       int size,
                                       int collection) {
        List<VacancyDto> vacanciesBySkills = new ArrayList<>();
        EntitySpecificationBuilder<Vacancy> builder = new VacancySpecificationBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        String searchWithoutCollections = search;

        if (collection == 1) {
            if (search.contains("skills")) {
                String searchSkills = search;
                List<SkillDto> skillsDtos = new ArrayList<>();
                int skillsIndex = searchSkills.indexOf("skills");
                int commaIndex = searchSkills.indexOf(",", skillsIndex);
                if (commaIndex == -1) {
                    searchSkills = search.substring(skillsIndex);
                } else {
                    searchSkills = search.substring(skillsIndex, commaIndex);
                }
                String[] splittedSkills = searchSkills.substring(7).split("\\*");
                for (String s : splittedSkills) {
                    s = s.replaceAll("_", " ");
                    SkillDto skill = skillsService.findByName(s).get(0);
                    skillsDtos.add(skill);
                }

                vacanciesBySkills = vacanciesService.findAllBySkills(skillsDtos);
                searchWithoutCollections = search.replace(searchSkills + ",", "");
            }
        }

        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchWithoutCollections + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Vacancy> spec = builder.build();
        List<VacancyDto> vacancies = vacanciesService.findAll(spec, page, size);
        if (vacanciesBySkills.size() != 0) {
            vacancies.retainAll(vacanciesBySkills);
        }
        return vacancies;
    }

    public List<?> getResumesResults(String search,
                                     int page,
                                     int size,
                                     int collection) {
        List<ResumeDto> resumesBySkills = new ArrayList<>();
        EntitySpecificationBuilder<Resume> builder = new ResumeSpecificationBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        String searchWithoutCollections = search;

        if (collection == 1) {
            if (search.contains("skills")) {
                String searchSkills = search;
                List<SkillDto> skillsDtos = new ArrayList<>();
                int skillsIndex = searchSkills.indexOf("skills");
                int commaIndex = searchSkills.indexOf(",", skillsIndex);
                if (commaIndex == -1) {
                    searchSkills = search.substring(skillsIndex);
                } else {
                    searchSkills = search.substring(skillsIndex, commaIndex);
                }
                String[] splittedSkills = searchSkills.substring(7).split("\\*");
                for (String s : splittedSkills) {
                    s = s.replaceAll("_", " ");
                    SkillDto skill = skillsService.findByName(s).get(0);
                    skillsDtos.add(skill);
                }

                resumesBySkills = resumeService.findAllBySkills(skillsDtos);
                searchWithoutCollections = search.replace(searchSkills + ",", "");
            }
        }

        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchWithoutCollections + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Resume> spec = builder.build();
        List<ResumeDto> vacancies = resumeService.findAll(spec, page, size);
        if (resumesBySkills.size() != 0) {
            vacancies.retainAll(resumesBySkills);
        }
        return vacancies;
    }

    public List<?> getStudentsResults(String search,
                                      int page,
                                      int size) {
        StudentSpecificationBuilder builder = new StudentSpecificationBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);

        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Student> spec = builder.build();
        List<StudentDto> students = studentService.findAll(spec, page, size);

        return students;
    }

}
