package ru.itis.ivavprp.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.ivavprp.dto.VacancyDto;
import ru.itis.ivavprp.models.Vacancy;
import ru.itis.ivavprp.repositories.VacanciesRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.ivavprp.models.Vacancy.fromVacancyDto;
import static ru.itis.ivavprp.models.Vacancy.toVacancyDto;

@Service
public class VacanciesServiceImpl implements VacanciesService {
    private final VacanciesRepository vacanciesRepository;

    public VacanciesServiceImpl(VacanciesRepository vacanciesRepository) {
        this.vacanciesRepository = vacanciesRepository;
    }

    @Override
    public VacancyDto save(VacancyDto vacancy) {
        return toVacancyDto(vacanciesRepository.save(fromVacancyDto(vacancy)));
    }

    @Override
    public VacancyDto update(VacancyDto vacancy) {
        return save(vacancy);
    }

    @Override
    public void delete(Long id) {
        vacanciesRepository.deleteById(id);
    }

    @Override
    public List<VacancyDto> findByName(String name, int page, int size) {
        return vacanciesRepository.findAllByName(name, PageRequest.of(page, size)).stream()
                .map(Vacancy::toVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VacancyDto> findAll(int page, int size) {
        return vacanciesRepository.findAll(PageRequest.of(page, size)).stream()
                .map(Vacancy::toVacancyDto)
                .collect(Collectors.toList());
    }
}
