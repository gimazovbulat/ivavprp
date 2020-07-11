package ru.itis.ivavprp.services;

import ru.itis.ivavprp.dto.VacancyDto;

import java.util.List;

public interface VacanciesService {
    VacancyDto save(VacancyDto vacancy);

    VacancyDto update(VacancyDto vacancy);

    void delete(Long id);

    List<VacancyDto> findByName(String name, int page, int size);

    List<VacancyDto> findAll(int page, int size);
}
